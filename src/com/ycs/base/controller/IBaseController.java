package com.ycs.base.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import com.ycs.basbo.constants.Constants;
import com.ycs.sys.domain.dto.LoginResponseDto;

/**
 * 自定义基类Controller
 * @author youcyousyunn
 * @date 2018年2月9日
 */
public class IBaseController {
	
	@Autowired
	private HttpServletRequest request;
	
	/**
	 * 定时删除session中email验证码
	 * @param httpSession
	 * @return void
	 */
	public void removeEmailCode(final HttpSession httpSession) {
		final Timer timer = new Timer();
		timer.schedule(new TimerTask(){
			@Override
			public void run() {
				httpSession.removeAttribute(Constants.SESSION_EMAIL_CODE);
				timer.cancel();
			}
		}, 2*60*1000); 
	}
	
	/**
	 * 设置登录成功后用户信息session
	 * @param loginResponse 登录请求
	 * @return Object
	 */
	public Map<String, Object> setLoginSession(LoginResponseDto loginResponse) {
		Map<String, Object> sessionMap = new HashMap<>();
        sessionMap.put(Constants.USER_SESSION_USERNAME, loginResponse.getUsrNm());
        sessionMap.put(Constants.USER_SESSION_USRNO, loginResponse.getUsrNo());
        sessionMap.put(Constants.USER_SESSION_MOBILE, loginResponse.getMblNo());
        return sessionMap;
	}
	
}
