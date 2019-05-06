package com.ycs.coobo.shop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ycs.base.domain.dto.BaseDto;
import com.ycs.base.exception.HiBusinessAbortException;
import com.ycs.base.exception.HiBusinessReturnException;
import com.ycs.base.exception.HiException;
import com.ycs.coobo.shop.bo.ShopMngBo;
import com.ycs.coobo.shop.domain.dto.QryShopInfoByUsrNoRequestDto;
import com.ycs.coobo.shop.domain.dto.QryShopInfoByUsrNoResponseDto;
import com.ycs.coobo.shop.domain.dto.QryShopOrderPlanInfoByShopNoResponseDto;
import com.ycs.coobo.shop.domain.dto.QryShopOrderPlanRequestDto;
import com.ycs.coobo.shop.domain.po.ShopOrdrPlanPo;
import com.ycs.coobo.shop.domain.po.ShopPo;
import com.ycs.coobo.shop.service.ShopMngService;

@Service
public class ShopMngServiceImpl implements ShopMngService {

	@Autowired
    private ShopMngBo shopMngBo;

	@Override
	@Transactional(readOnly=true)
	public QryShopInfoByUsrNoResponseDto qryShopInfoByUsr(QryShopInfoByUsrNoRequestDto request)
			throws HiBusinessAbortException, HiBusinessReturnException {
		QryShopInfoByUsrNoResponseDto response = new QryShopInfoByUsrNoResponseDto();
        ShopPo shopPo = shopMngBo.qryShopInfoByUsrNo(request.getUsrNo());
        if (null != shopPo) {
            try {
                response = BaseDto.entity2Trans(shopPo, QryShopInfoByUsrNoResponseDto.class);
            } catch (HiException e) {
                e.printStackTrace();
            }
        }
        return response;
	}

	@Override
	@Transactional(readOnly=true)
	public QryShopOrderPlanInfoByShopNoResponseDto qryShopOrderPlan(QryShopOrderPlanRequestDto request)
			throws HiBusinessAbortException, HiBusinessReturnException {
		QryShopOrderPlanInfoByShopNoResponseDto response = new QryShopOrderPlanInfoByShopNoResponseDto();
        ShopOrdrPlanPo shopOrdrPlanPo = shopMngBo.qryShopOrderPlan(request.getShopNo());
        if (null != shopOrdrPlanPo) {
            try {
                response = BaseDto.entity2Trans(shopOrdrPlanPo, QryShopOrderPlanInfoByShopNoResponseDto.class);
            } catch (HiException e) {
                e.printStackTrace();
            }
        }
        return response;
	}
	
	
	

}
