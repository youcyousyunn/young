package com.ycs.sys.domain.dto;

import java.io.Serializable;

import com.ycs.base.domain.dto.BaseResponseDto;

/**
 * @description 注册响应DTO
 * @author youcyousyunn
 * @date 2018年11月29日
 */
public class RegisterResponseDto extends BaseResponseDto implements Serializable {
	private static final long serialVersionUID = -1711513949080480804L;

	public RegisterResponseDto(){
	}
	
	/**
     * 构造函数
     * @param responseCode 响应code
     * @param responseInfo 响应信息
     */
    public RegisterResponseDto(String responseCode, String responseInfo) {
        this.rspCd = responseCode;
        this.rspInf = responseInfo;
    }
}
