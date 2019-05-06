package com.ycs.base.utils;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ycs.base.property.SystemPropertyConfigure;

public class SSOClientUtil {
	public static String SERVER_URL_PREFIX;
	public static String CLIENT_HOST_URL;
	
	static {
		SERVER_URL_PREFIX = SystemPropertyConfigure.getProperty("server-url-prefix");
		CLIENT_HOST_URL = SystemPropertyConfigure.getProperty("client-host-url");
	}
	
	/**
	 * 当客户端请求被拦截,跳往统一认证中心,需要带redirectUrl的参数,统一认证中心登录后回调的地址
	 * 通过request获取这次请求的地址 http://www.young.com:8080/young/index.html
	 * @param request
	 * @return
	 */
	public static String getRedirectUrl(HttpServletRequest request){
		return CLIENT_HOST_URL + "/index.html";
	}
	
	/**
	 * 根据request获取跳转到统一认证中心的地址 http://www.sso.com:8443/sso/checkLogin.do?redirectUrl=http://www.young.com:8080/young/index.html
	 * 通过response跳转到指定的地址
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	public static void redirectToSSOURL(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String redirectUrl = getRedirectUrl(request);
		StringBuilder url = new StringBuilder(50)
				.append(SERVER_URL_PREFIX)
				.append("/checkLogin.do?redirectUrl=")
				.append(redirectUrl);
		response.sendRedirect(url.toString());
	}
	
}
