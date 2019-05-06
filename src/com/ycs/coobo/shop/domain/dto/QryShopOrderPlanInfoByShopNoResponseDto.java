package com.ycs.coobo.shop.domain.dto;

import com.ycs.base.domain.dto.BaseResponseDto;

/**
 * 获取门店下单计划DTO
 * @author youcyousyunn
 * @date 2018年5月23日
 */
public class QryShopOrderPlanInfoByShopNoResponseDto extends BaseResponseDto {

	//门店号
    private String shopNo;
    //门店名称
    private String shopNm;
    //普通订单计划
    private String normalPlan;
    //物料订单计划
    private String materPlan;
    //创建日期
    private String creDt;
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
	 * @return the shopNm
	 */
	public String getShopNm() {
		return shopNm;
	}
	/**
	 * @param shopNm the shopNm to set
	 */
	public void setShopNm(String shopNm) {
		this.shopNm = shopNm;
	}
	/**
	 * @return the normalPlan
	 */
	public String getNormalPlan() {
		return normalPlan;
	}
	/**
	 * @param normalPlan the normalPlan to set
	 */
	public void setNormalPlan(String normalPlan) {
		this.normalPlan = normalPlan;
	}
	/**
	 * @return the materPlan
	 */
	public String getMaterPlan() {
		return materPlan;
	}
	/**
	 * @param materPlan the materPlan to set
	 */
	public void setMaterPlan(String materPlan) {
		this.materPlan = materPlan;
	}
	/**
	 * @return the creDt
	 */
	public String getCreDt() {
		return creDt;
	}
	/**
	 * @param creDt the creDt to set
	 */
	public void setCreDt(String creDt) {
		this.creDt = creDt;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "QryShopOrderPlanInfoByShopNoResponseDto [shopNo=" + shopNo + ", shopNm=" + shopNm + ", normalPlan="
				+ normalPlan + ", materPlan=" + materPlan + ", creDt=" + creDt + "]";
	}
    
    

}
