package com.ycs.coobo.shop.dao;

import com.ycs.coobo.shop.domain.po.ShopOrdrPlanPo;
import com.ycs.coobo.shop.domain.po.ShopPo;

public interface ShopMngDao {

	public ShopPo qryShopInfoByUsrNo(String usrNo);

	public ShopOrdrPlanPo qryShopOrderPlan(String shopNo);
	
	
	
}
