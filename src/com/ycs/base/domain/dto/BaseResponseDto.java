package com.ycs.base.domain.dto;

import java.text.SimpleDateFormat;
import java.util.Date;

public class BaseResponseDto extends BaseDto {
	protected String rspCd;
	protected String rspInf;
	protected String responseTm;
	
	/**
	 * 构造函数
	 */
	public BaseResponseDto() {
	}
	
	/**
	 * 构造函数
	 * @param responseCode
	 */
	public BaseResponseDto(String responseCode) {
		this.rspCd = responseCode;
	}

	/**
	 * 构造函数
	 * @param responseCode
	 * @param responseInfo
	 */
	public BaseResponseDto(String responseCode, String responseInfo) {
		this.rspCd = responseCode;
		this.rspInf = responseInfo;
	}

	public String getRspCd() {
		return rspCd;
	}

	public void setRspCd(String rspCd) {
		this.rspCd = rspCd;
	}

	public String getRspInf() {
		return rspInf;
	}

	public void setRspInf(String rspInf) {
		this.rspInf = rspInf;
	}

	public String getResponseTm() {
		if (null == this.responseTm) {
			SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
			this.responseTm = df.format(new Date());
		}
		return this.responseTm;
	}

	public void setResponseTm(String responseTm) {
		this.responseTm = responseTm;
	}
	
}
