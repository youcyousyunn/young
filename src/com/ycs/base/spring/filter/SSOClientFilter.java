package com.ycs.base.spring.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.ycs.basbo.constants.Constants;
import com.ycs.base.encrypt.Encodes;
import com.ycs.base.utils.HttpUtil;
import com.ycs.base.utils.SSOClientUtil;
import com.ycs.sys.service.IUserInfoService;

public class SSOClientFilter implements Filter {
	private IUserInfoService userInfoService;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		ServletContext sc = filterConfig.getServletContext();
		WebApplicationContext cxt =WebApplicationContextUtils.getWebApplicationContext(sc);
		if(null != cxt && null != cxt.getBean(IUserInfoService.class) && userInfoService == null){
			userInfoService = (IUserInfoService) cxt.getBean(IUserInfoService.class);
		}
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		// 1.判断是否有局部会话
		HttpSession session = req.getSession();
		Boolean isLogin = (Boolean) session.getAttribute("isLogin");
		if(null != isLogin && isLogin){
			// 如果有局部会话则放行
			chain.doFilter(request, response);
			return;
		}
		// 2.判断地址栏中是否携带token参数
		String token = req.getParameter("token");
		if(StringUtils.isNoneBlank(token)){
			// 判断认证信息token是否由认证中心产生的
			Map<String,String> params = new HashMap<String,String>();
			params.put("token", token);
			String verifyUrl = SSOClientUtil.SERVER_URL_PREFIX + "/verify.do";
			try {
				boolean result = HttpUtil.sendVerifyRequest(verifyUrl, params);
				if (result) {
					// 子系统会话设置用户信息
					String[] usrInfoArray = token.split("-");
					Map<String, Object> sessionMap = new HashMap<>();
			        sessionMap.put(Constants.USER_SESSION_USRNO, new String(Encodes.decodeBase64(usrInfoArray[usrInfoArray.length-1])));
					session.setAttribute(Constants.USER_SESSION_INFO, sessionMap);
					
					// 注册当前子系统认证信息
					Map<String,String> registParams = new HashMap<String,String>();
					registParams.put("jsessionId", session.getId());
					registParams.put("token", token);
					registParams.put("registerUrl", SSOClientUtil.CLIENT_HOST_URL);
					String registUrl = SSOClientUtil.SERVER_URL_PREFIX + "/regist.do";
	            	HttpUtil.sendRegistRequest(registUrl, registParams);
					session.setAttribute("isLogin", true);
					chain.doFilter(request, response);
					return;
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("无法访问统一认证中心");
			}
		}
		
		// 3.没有局部会话且地址栏中无token参数,重定向到统一认证中心,检查是否有其他的系统已经登录过
		SSOClientUtil.redirectToSSOURL(req, res);
	}
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
