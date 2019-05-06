package com.ycs.coobo.shop.service;

import com.ycs.base.exception.HiBusinessAbortException;
import com.ycs.base.exception.HiBusinessReturnException;
import com.ycs.coobo.shop.domain.dto.QryShopOrdrDetailInfoRequestDto;
import com.ycs.coobo.shop.domain.dto.QryShopOrdrDetailInfoResponseDto;
import com.ycs.coobo.shop.domain.dto.QryShopOrdrInfoRequestDto;
import com.ycs.coobo.shop.domain.dto.QryShopOrdrInfoResponseDto;
import com.ycs.coobo.shop.domain.dto.QryShopOrdrLstRequestDto;
import com.ycs.coobo.shop.domain.dto.QryShopOrdrLstResponseDto;
import com.ycs.coobo.shop.domain.dto.ReviewShopOrdrRequestDto;
import com.ycs.coobo.shop.domain.dto.ReviewShopOrdrResponseDto;
import com.ycs.coobo.shop.domain.dto.ShopOrdrApplyRequestDto;
import com.ycs.coobo.shop.domain.dto.ShopOrdrApplyResponseDto;
import com.ycs.coobo.shop.domain.dto.ShopOrdrCombinRequestDto;
import com.ycs.coobo.shop.domain.dto.ShopOrdrCombinResponseDto;
import com.ycs.coobo.shop.domain.dto.ShopOrdrCombinSubmitRequestDto;
import com.ycs.coobo.shop.domain.dto.ShopOrdrCombinSubmitResponseDto;
import com.ycs.coobo.shop.domain.po.ShopOrdrPo;

public interface ShopOrdrService {

	/**
	 * 查询门店订单列表
	 * @param request
	 * @return QryShopOrdrLstResponseDto
	 * @throws HiBusinessAbortException, HiBusinessReturnException
	 */
	public QryShopOrdrLstResponseDto qryShopOrdrLst(QryShopOrdrLstRequestDto request)throws HiBusinessAbortException, HiBusinessReturnException;

	/**
	 * 门店补货单申请
	 * @param request
	 * @return ShopOrdrApplyResponseDto
	 * @throws HiBusinessAbortException, HiBusinessReturnException;
	 */
	public ShopOrdrApplyResponseDto shopOrdrApply(ShopOrdrApplyRequestDto request)throws HiBusinessAbortException, HiBusinessReturnException;

	/**
	 * 门店下单首页通知
	 * @param shopOrdrPo
	 * @return boolean
	 * @throws HiBusinessAbortException, HiBusinessReturnException;
	 */
	boolean shopOrdrMatch(ShopOrdrPo shopOrdrPo) throws HiBusinessAbortException, HiBusinessReturnException;
	
	/**
	 * 查询门店订单明细信息
	 * @param 
	 * @return QryShopOrdrDetailInfoResponseDto
	 * @throws HiBusinessAbortException, HiBusinessReturnException;
	 */
	public QryShopOrdrDetailInfoResponseDto qryShopOrdrDetailInfo(QryShopOrdrDetailInfoRequestDto request)throws HiBusinessAbortException, HiBusinessReturnException;

	/**
	 * 查询门店订单信息
	 * @param
	 * @return QryShopOrdrInfoResponseDto
	 * @throws HiBusinessAbortException, HiBusinessReturnException;
	 */
	public QryShopOrdrInfoResponseDto qryShopOrdrInfo(QryShopOrdrInfoRequestDto request)throws HiBusinessAbortException, HiBusinessReturnException;

	/**
	 * 门店订单审核请求 
	 * @param request
	 * @return ReviewShopOrdrResponseDto
	 * @throws HiBusinessAbortException, HiBusinessReturnException
	 */
	public ReviewShopOrdrResponseDto reviewShopOrdr(ReviewShopOrdrRequestDto request)throws HiBusinessAbortException, HiBusinessReturnException;

	/**
	 * 采购门店订单合并
	 * @param request
	 * @return ShopOrdrCombinResponseDto
	 * @throws HiBusinessAbortException, HiBusinessReturnException
	 */
	public ShopOrdrCombinResponseDto shopOrdrCombin(ShopOrdrCombinRequestDto request)throws HiBusinessAbortException, HiBusinessReturnException;

	/**
	 * 采购门店订单合并提交
	 * @param request
	 * @return ShopOrdrCombinSubmitResponseDto
	 * @throws HiBusinessAbortException, HiBusinessReturnException
	 */
	public ShopOrdrCombinSubmitResponseDto shopOrdrCombinSubmit(ShopOrdrCombinSubmitRequestDto request)throws HiBusinessAbortException, HiBusinessReturnException;

	

	
	
	
}
