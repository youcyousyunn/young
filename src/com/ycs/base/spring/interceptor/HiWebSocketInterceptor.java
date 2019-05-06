package com.ycs.base.spring.interceptor;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import com.ycs.basbo.constants.Constants;

/**
 * @description WebSocket握手请求的拦截器
 * @author youcyousyunn
 * @date 2018年11月24日
 */
public class HiWebSocketInterceptor implements HandshakeInterceptor {
	
	@Override
	public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
			Map<String, Object> attributes) throws Exception {
		if (request instanceof ServletServerHttpRequest) {
			ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
			HttpSession session = servletRequest.getServletRequest().getSession(false);
			if (null != session) {
				HashMap sessionMap = (HashMap) session.getAttribute(Constants.USER_SESSION_INFO);
				if (null != sessionMap) {
					attributes.put(Constants.USER_SESSION_USRNO, sessionMap.get(Constants.USER_SESSION_USRNO));
				}
			}
		}
		return true;
	}
	
	@Override
	public void afterHandshake(ServerHttpRequest request, ServerHttpResponse resposne, WebSocketHandler wsHandler, Exception exception) {
	}

}
