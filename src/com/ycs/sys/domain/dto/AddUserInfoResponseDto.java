package com.ycs.sys.domain.dto;

import com.ycs.base.domain.dto.BaseResponseDto;

public class AddUserInfoResponseDto extends BaseResponseDto {

	public AddUserInfoResponseDto() {
	}
	
	/**
	 * 构造函数
	 * @param responseCode 响应代码
	 * @param message 响应消息
	 */
	public AddUserInfoResponseDto(String responseCode, String message) {
        this.rspCd = responseCode;
        this.rspInf = message;
    }

}
