package com.ycs.alipay.domain.dto;

import com.ycs.base.domain.dto.BaseRequestDto;

/**
 * Alipay请求DTO
 * @author youcyousyunn
 * @date 2018年6月13日
 */
public class AlipayRequestDto extends BaseRequestDto {
	//订单号
	private String ordNo;
	//订单名称
	private String ordNm;
	//订单总金额
	private String totAmt;
	//订单描述
	private String ordDesc;
	
	/**
	 * @return the ordNo
	 */
	public String getOrdNo() {
		return ordNo;
	}
	/**
	 * @param ordNo the ordNo to set
	 */
	public void setOrdNo(String ordNo) {
		this.ordNo = ordNo;
	}
	/**
	 * @return the ordNm
	 */
	public String getOrdNm() {
		return ordNm;
	}
	/**
	 * @param ordNm the ordNm to set
	 */
	public void setOrdNm(String ordNm) {
		this.ordNm = ordNm;
	}
	/**
	 * @return the totAmt
	 */
	public String getTotAmt() {
		return totAmt;
	}
	/**
	 * @param totAmt the totAmt to set
	 */
	public void setTotAmt(String totAmt) {
		this.totAmt = totAmt;
	}
	/**
	 * @return the ordDesc
	 */
	public String getOrdDesc() {
		return ordDesc;
	}
	/**
	 * @param ordDesc the ordDesc to set
	 */
	public void setOrdDesc(String ordDesc) {
		this.ordDesc = ordDesc;
	}
	

}
