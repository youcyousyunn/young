package com.ycs.base.spring.configurer;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.HandshakeInterceptor;

import com.ycs.base.property.SystemPropertyConfigure;
import com.ycs.base.spring.handler.SystemWebSocketHandler;
import com.ycs.base.spring.interceptor.HiWebSocketInterceptor;

/**
 * @description WebSocketConfigurer配置
 * @author youcyousyunn
 * @date 2018年11月24日
 */
@Component
@EnableWebMvc
@EnableWebSocket
public class WebSocketConfigure extends WebMvcConfigurerAdapter implements WebSocketConfigurer {
	@Resource
	private SystemWebSocketHandler systemWebSocketHandler;
	
	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(this.systemWebSocketHandler, new String[]{"/ws/ws.do"})
		.setAllowedOrigins(new String[]{SystemPropertyConfigure.getParams("host.host_nm")})
				.addInterceptors(new HandshakeInterceptor[]{new HiWebSocketInterceptor()});
//		registry.addHandler(this.systemWebSocketHandler, new String[]{"/ws/sockjs.do"})
//				.addInterceptors(new HandshakeInterceptor[]{new HiWebSocketInterceptor()}).withSockJS();
	}

}
