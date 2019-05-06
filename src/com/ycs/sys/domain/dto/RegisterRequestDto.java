package com.ycs.sys.domain.dto;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;

import com.ycs.base.domain.dto.BaseRequestDto;

/**
 * @description 注册请求DTO
 * @author youcyousyunn
 * @date 2018年11月29日
 */
public class RegisterRequestDto extends BaseRequestDto implements Serializable {

	/**
	 * 序列化版本号
	 */
	private static final long serialVersionUID = 4332702813072447554L;

	/**
     * 登录名
     */
    private String loginNm;

    /**
     * 密码
     */
    private String password;
    
    /**
     * 邮箱
     */
    private String email;
	
    /**
     * 构造函数
     */
	public RegisterRequestDto() {
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
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
        if (null == email || StringUtils.isBlank(email)) {
            return false;
        }
        return true;
    }
	
}
