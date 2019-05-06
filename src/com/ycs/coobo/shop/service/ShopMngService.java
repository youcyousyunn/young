package com.ycs.coobo.shop.service;

import com.ycs.base.exception.HiBusinessAbortException;
import com.ycs.base.exception.HiBusinessReturnException;
import com.ycs.coobo.shop.domain.dto.QryShopInfoByUsrNoRequestDto;
import com.ycs.coobo.shop.domain.dto.QryShopInfoByUsrNoResponseDto;
import com.ycs.coobo.shop.domain.dto.QryShopOrderPlanInfoByShopNoResponseDto;
import com.ycs.coobo.shop.domain.dto.QryShopOrderPlanRequestDto;

public interface ShopMngService {

	/**
	 * 根据内部用户号查询单个门店信息
	 * @param
	 * @return QryShopInfoByUsrNoResponseDto
	 * @throws HiBusinessAbortException,HiBusinessReturnException
	 */
	public QryShopInfoByUsrNoResponseDto qryShopInfoByUsr(QryShopInfoByUsrNoRequestDto request)throws HiBusinessAbortException,HiBusinessReturnException;

	/**
	 * 获取门店下单计划
	 * @param
	 * @return QryShopOrderPlanInfoByShopNoResponseDto
	 * @throws 
	 */
	public QryShopOrderPlanInfoByShopNoResponseDto qryShopOrderPlan(QryShopOrderPlanRequestDto request)throws HiBusinessAbortException,HiBusinessReturnException;
	
	
	
}
