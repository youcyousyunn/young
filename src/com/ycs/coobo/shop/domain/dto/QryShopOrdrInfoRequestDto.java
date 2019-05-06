package com.ycs.coobo.shop.domain.dto;

import org.apache.commons.lang.StringUtils;

import com.ycs.base.domain.dto.BaseRequestDto;

/**
 * 查询订单请求DTO
 * @author youcyousyunn
 * @date 2018年6月13日
 */
public class QryShopOrdrInfoRequestDto extends BaseRequestDto {
	//订单号
    private String ordNo;

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
	 * 接口报文请求检查
	 * @param
	 * @return boolean
	 */
	public boolean checkRequestDto() {
        if (null == ordNo || StringUtils.isBlank(ordNo)) {
            return false;
        }
        return true;
    }

}
