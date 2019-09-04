package com.ycs.base.spring.interceptor;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.ycs.basbo.constants.Constants;
import com.ycs.base.context.HiContext;
import com.ycs.base.context.HiRequestInfo;
import com.ycs.base.context.HiTransactionInfo;
import com.ycs.base.log4j.logger.HiITFLogger;
import com.ycs.base.log4j.logger.HiWebLogger;
import com.ycs.base.property.SystemPropertyConfigure;
import com.ycs.base.spring.bo.TxIfBo;
import com.ycs.base.spring.domain.po.TxIfPo;

/**
 * 公共拦截器
 * @author youcyousyunn
 * @date 2018年8月4日
 */
public class HiCommonInterceptor implements HandlerInterceptor {

	@Autowired
	private TxIfBo txIfBo;
	private String logger;

	public void setLogger(String logger) {
		this.logger = logger;
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HiContext ctx = HiContext.createContext("currentContext", (HiContext) null);
		HiContext.pushCurrentContext(ctx);
		HttpSession session = request.getSession();
		HiRequestInfo requestInfo = this.initRequestInfo(request, ctx.getRequestId());
		this.initTxInfo(requestInfo.getUrlWithOutContext(), session);
		this.logInfo("Req[" + request.getRequestURL() + "],SIP[" + requestInfo.getRemoteIp() + "], UA["
				+ requestInfo.getUserAgent() + "]");
		return true;
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		HiRequestInfo requestInfo = HiRequestInfo.getCurInstance();
		requestInfo.setResponseTime(System.currentTimeMillis());
		long totTime = requestInfo.getResponseTime() - requestInfo.getRequestTime();
		requestInfo.setInsId(SystemPropertyConfigure.getInsId());
		requestInfo.setNodId(SystemPropertyConfigure.getNodId());
		this.logInfo("Process[" + requestInfo.getModId() + "|" + requestInfo.getSysCnl() + "|" + requestInfo.getCode() + "|"
				+ requestInfo.getDesc() + "|" + requestInfo.getUsrNo() + "|" + requestInfo.getMsgCd() + "|" + requestInfo.getMsgInf()
				+ "|" + totTime + "|" + requestInfo.getRemoteIp() + "|" + requestInfo.getUserAgent() + "|"
				+ requestInfo.getTermType() + "|" + requestInfo.getTermId() + "|" + requestInfo.getTermVersion() + "|"
				+ requestInfo.getAppVersion() + "|" + requestInfo.getAppCnl() + "]");
		HiContext currentContext = HiContext.popCurrentContext();
		currentContext.clear();
		currentContext = null;
	}

	private HiTransactionInfo initTxInfo(String url, HttpSession session) {
		TxIfPo txIfPo = this.txIfBo.queryTxIf(url);
		if (null == txIfPo) {
			return null;
		} else {
			HiTransactionInfo transactionInfo = new HiTransactionInfo();
			transactionInfo.setAppId(txIfPo.getAppId());
			transactionInfo.setModId(txIfPo.getModId());
			transactionInfo.setTxSwitch(txIfPo.getTxFlg());
			transactionInfo.setLogSwitch(txIfPo.getLogFlg());
			transactionInfo.setCode(url);
			transactionInfo.setDesc(txIfPo.getUrlDesc());
			transactionInfo.setPwdSwitch(txIfPo.getPwdFlg());
			transactionInfo.setUrlAuth(txIfPo.getUrlAuth());
			HiRequestInfo.getCurInstance().setDesc(transactionInfo.getDesc());
			HiRequestInfo.getCurInstance().setModId(transactionInfo.getModId());
			HiTransactionInfo.setCurInstance(transactionInfo);
			return transactionInfo;
		}
	}

	/**
	 * 初始化请求
	 * @param request
	 * @param requestId
	 * @return HiRequestInfo
	 */
	private HiRequestInfo initRequestInfo(HttpServletRequest request, String requestId) {
		String reqUrl = request.getRequestURI();
		String reqUrlWithoutCtx = StringUtils.substringAfter(reqUrl, request.getContextPath());
		HttpSession session = request.getSession();
		Map sessionMap = (HashMap) session.getAttribute(Constants.USER_SESSION_INFO);
		String usrNo = null;
		if (null != sessionMap) {
			usrNo = (String) sessionMap.get(Constants.USER_SESSION_USRNO);
		}

		HiRequestInfo requestInfo = new HiRequestInfo();
		requestInfo.setMsgId(requestId);
		requestInfo.setRequestTime(System.currentTimeMillis());
		requestInfo.setRemoteIp(this.getClientIp(request));
		requestInfo.setCode(reqUrl);
		requestInfo.setUrlWithOutContext(reqUrlWithoutCtx);
		requestInfo.setUserAgent(this.getUserAgent(request));
		requestInfo.setUsrNo(usrNo);
		HiRequestInfo.setCurInstance(requestInfo);
		return requestInfo;
	}

	/**
	 * 获取用户代理
	 * @param
	 * @return String
	 * @throws 
	 */
	private String getUserAgent(HttpServletRequest request) {
		return request.getHeader("User-Agent");
	}

	/**
	 * 获取客户端IP
	 * @param request
	 * @return clientIp
	 */
	private String getClientIp(HttpServletRequest request) {
		String clientIp = request.getHeader("X-Real-IP");
		if (clientIp == null || clientIp.length() == 0 || "unknown".equalsIgnoreCase(clientIp)) {
			clientIp = request.getHeader("Proxy-Client-IP");
		}

		if (clientIp == null || clientIp.length() == 0 || "unknown".equalsIgnoreCase(clientIp)) {
			clientIp = request.getHeader("x-forwarded-for");
			if (StringUtils.isNotEmpty(clientIp) && !"unKnown".equalsIgnoreCase(clientIp)) {
				// 多次反向代理后会有多个IP值，第一个IP才是真实IP
	            int index = clientIp.indexOf(",");
	            if (index != -1){
	            	clientIp = clientIp.substring(0, index);
	            }
			}
		}

		if (clientIp == null || clientIp.length() == 0 || "unknown".equalsIgnoreCase(clientIp)) {
			clientIp = request.getHeader("WL-Proxy-Client-IP");
		}

		if (clientIp == null || clientIp.length() == 0 || "unknown".equalsIgnoreCase(clientIp)) {
			clientIp = request.getRemoteAddr();
		}

		return clientIp;
	}

	/**
	 * 记录日志
	 * @param
	 * @return void
	 * @throws 
	 */
	private void logInfo(String msg) {
		if (this.logger.equals("HiITFLogger")) {
			HiITFLogger.info(msg);
		}

		if (this.logger.equals("HiWebLogger")) {
			HiWebLogger.info(msg);
		}
	}

}
