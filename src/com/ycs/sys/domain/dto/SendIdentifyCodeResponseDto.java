package com.ycs.sys.domain.dto;

import java.io.Serializable;

import com.ycs.base.domain.dto.BaseResponseDto;

/**
 * @description 发送验证码DTO
 * @author youcyousyunn
 * @date 2018年11月29日
 */
public class SendIdentifyCodeResponseDto extends BaseResponseDto implements Serializable {
	private static final long serialVersionUID = 5140307862044869746L;

	public SendIdentifyCodeResponseDto(){
	}
	
	/**
     * 构造函数
     * @param responseCode 响应code
     */
    public SendIdentifyCodeResponseDto(String responseCode) {
        this.rspCd = responseCode;
    }
    
	/**
     * 构造函数
     * @param responseCode 响应code
     * @param responseInfo 响应信息
     */
    public SendIdentifyCodeResponseDto(String responseCode, String responseInfo) {
        this.rspCd = responseCode;
        this.rspInf = responseInfo;
    }
}
