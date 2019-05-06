package com.ycs.base.spring.interceptor;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.ycs.basbo.constants.Constants;
import com.ycs.basbo.constants.Constants.OUT_AUTH_URL;
import com.ycs.base.context.HiTransactionInfo;
import com.ycs.base.log4j.logger.HiITFLogger;
import com.ycs.base.log4j.logger.HiWebLogger;
import com.ycs.base.property.SystemPropertyConfigure;
import com.ycs.base.spring.bo.UsrAuthBo;

public class HiTxAuthInterceptor implements HandlerInterceptor {

	@Autowired
	private UsrAuthBo usrAuthBo;
	private String logger;
	private String rspCd = "T0000";
	private String rspInfo = "接口不存在 ";

	public void setLogger(String logger) {
		this.logger = logger;
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HiTransactionInfo transactionInfo = HiTransactionInfo.getCurInstance();
		if (this.isAccept(transactionInfo, request)) {
			this.logInfo("ACCEPTED, logFlag[" + transactionInfo.getLogSwitch() + "]");
			return true;
		} else {
			this.writeResponse(response);
			if (null != transactionInfo) {
				this.logWarn("REFUSED, txFlag[" + transactionInfo.getTxSwitch() + "]");
			} else {
				this.logWarn("REFUSED, txif is null.");
			}

			return false;
		}
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}

	private boolean isAccept(HiTransactionInfo transactionInfo, HttpServletRequest request) {
		return !this.initAuthInfo(transactionInfo, request)? false : (transactionInfo != null ? transactionInfo.isAccept() : false);
	}

	/**
	 * 接口访问权限判断
	 * @param
	 * @return boolean
	 * @throws 
	 */
	private boolean initAuthInfo(HiTransactionInfo transactionInfo, HttpServletRequest request) {
		boolean flag = false;
		OUT_AUTH_URL[] authUrlLst = OUT_AUTH_URL.values();
		int length = authUrlLst.length;

		for (int i = 0; i < length; ++i) {
			OUT_AUTH_URL urlAuth = authUrlLst[i];
			if (urlAuth.toString().equals(transactionInfo.getUrlAuth())) {
				flag = true;
				break;
			}
		}

		if (!flag) {
			authUrlLst = null;
			if (Constants.SESSION_STATUS_OFF.equals(SystemPropertyConfigure.getParams(Constants.SESSION_RUN_MODE))) {
				return true;
			}

			HttpSession session = request.getSession();
			Map sessionMap = (HashMap) session.getAttribute(Constants.USER_SESSION_INFO);
			if (null == sessionMap) {
				this.rspCd = "M0000";
				this.rspInfo = "未登录,不允许请求";
				return false;
			}

			String usrNo = (String) sessionMap.get(Constants.USER_SESSION_USRNO);
			boolean result = this.usrAuthBo.isPermission(usrNo, transactionInfo.getUrlAuth());
			if (!result) {
				this.rspCd = "T0006";
				this.rspInfo = "接口权限不足";
				return false;
			}
		}

		return true;
	}
	
	private void writeResponse(HttpServletResponse response) {
		PrintWriter w = null;
		try {
			response.setCharacterEncoding("UTF-8");
			w = response.getWriter();
			w.write("{\"rspCd\":\"" + this.rspCd + "\",\"rspInfo\":\"" + this.rspInfo + "\",\"responseTm\":\""
					+ (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date()) + "\"}");
		} catch (IOException arg6) {
			response.setStatus(400);
		} finally {
			if (null != w) {
				w.flush();
				w.close();
			}
			this.rspCd = "T0000";
			this.rspInfo = "接口不存在 ";
		}
	}

	private void logInfo(String msg) {
		if (this.logger.equals("HiITFLogger")) {
			HiITFLogger.infoITF(msg);
		}
		if (this.logger.equals("HiWebLogger")) {
			HiWebLogger.infoWeb(msg);
		}
	}

	private void logWarn(String msg) {
		if (this.logger.equals("HiITFLogger")) {
			HiITFLogger.warnITF(msg);
		}
		if (this.logger.equals("HiWebLogger")) {
			HiWebLogger.warnWeb(msg);
		}
	}

}
