package com.ycs.coobo.shop.bo.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ycs.coobo.shop.bo.ShopMngBo;
import com.ycs.coobo.shop.dao.ShopMngDao;
import com.ycs.coobo.shop.domain.po.ShopOrdrPlanPo;
import com.ycs.coobo.shop.domain.po.ShopPo;

@Component
public class ShopMngBoImpl implements ShopMngBo {

	@Autowired
	private ShopMngDao shopMngDao;

	@Override
	public ShopPo qryShopInfoByUsrNo(String usrNo) {
		return shopMngDao.qryShopInfoByUsrNo(usrNo);
	}

	@Override
	public ShopOrdrPlanPo qryShopOrderPlan(String shopNo) {
		return shopMngDao.qryShopOrderPlan(shopNo);
	}

}
