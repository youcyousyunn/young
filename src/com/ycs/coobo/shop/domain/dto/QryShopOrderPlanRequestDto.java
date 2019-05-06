package com.ycs.coobo.shop.domain.dto;

import org.apache.commons.lang.StringUtils;

import com.ycs.base.domain.dto.BaseRequestDto;

/**
 * 获取门店下单计划请求DTO
 * @author youcyousyunn
 * @date 2018年5月23日
 */
public class QryShopOrderPlanRequestDto extends BaseRequestDto {

	//门店号
    private String shopNo;

	/**
	 * @return the shopNo
	 */
	public String getShopNo() {
		return shopNo;
	}

	/**
	 * @param shopNo the shopNo to set
	 */
	public void setShopNo(String shopNo) {
		this.shopNo = shopNo;
	}
    
	/**
	 * 接口报文检查
	 * @param @return
	 * @return boolean
	 */
    public boolean checkRequestDto(){
    	if (null == shopNo || StringUtils.isBlank(shopNo)){
    		return false;
    	}
    	return true;
    }

}
