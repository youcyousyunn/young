package com.ycs.coobo.shop.bo;

import com.ycs.base.annotation.HiBoMethod;
import com.ycs.coobo.shop.domain.po.ShopOrdrPlanPo;
import com.ycs.coobo.shop.domain.po.ShopPo;

public interface ShopMngBo {

	/**
	 * 根据内部用户号查询单个门店信息
	 * @param usrNo
	 * @return ShopPo
	 */
	@HiBoMethod
	public ShopPo qryShopInfoByUsrNo(String usrNo);

	/**
	 * 根据门店账号查询门店下单计划
	 * @param shopNo
	 * @return ShopOrdrPlanPo
	 */
	@HiBoMethod
	public ShopOrdrPlanPo qryShopOrderPlan(String shopNo);
	
}
