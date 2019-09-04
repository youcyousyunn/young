package com.ycs.sys.domain.dto;

import org.apache.commons.lang.StringUtils;

import com.ycs.base.domain.dto.BaseRequestDto;

/**
 * 登录请求DTO
 * @author youcyousyunn
 * @date 2018年2月11日
 */
public class LoginRequestDto extends BaseRequestDto {

	/**
     * 登录名
     */
    private String loginNm;

    /**
     * 密码
     */
    private String password;
	
    /**
     * 构造函数
     */
	public LoginRequestDto() {
	}

	public String getLoginNm() {
		return loginNm;
	}

	public void setLoginNm(String loginNm) {
		this.loginNm = loginNm;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * 接口报文请求检查
	 * @param
	 * @return boolean
	 */
	public boolean checkRequestDto() {
        if (null == loginNm || StringUtils.isBlank(loginNm)) {
            return false;
        }
        if (null == password || StringUtils.isBlank(password)) {
            return false;
        }
        return true;
    }

}
