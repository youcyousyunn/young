package com.ycs.coobo.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ycs.basbo.constants.HiMsgCdConstants;
import com.ycs.base.controller.IBaseController;
import com.ycs.base.exception.HiBusinessAbortException;
import com.ycs.base.exception.HiBusinessReturnException;
import com.ycs.base.exception.HiException;
import com.ycs.base.log4j.logger.HiBizLogger;
import com.ycs.coobo.shop.domain.dto.QryShopInfoByUsrNoRequestDto;
import com.ycs.coobo.shop.domain.dto.QryShopInfoByUsrNoResponseDto;
import com.ycs.coobo.shop.domain.dto.QryShopOrderPlanInfoByShopNoResponseDto;
import com.ycs.coobo.shop.domain.dto.QryShopOrderPlanRequestDto;
import com.ycs.coobo.shop.service.ShopMngService;

/**
 * 门店管理相关Controller
 * @author youcyousyunn
 * @date 2018年5月19日
 */
@Controller
@RequestMapping("/shop/v1")
public class ShopMngController extends IBaseController {

	@Autowired
    private ShopMngService shopMngService;
	
	
	/**
	 * 根据内部用户号查询单个门店信息
	 * @param request
	 * @return QryShopInfoByUsrNoResponseDto
	 * @throws HiException
	 */
	@RequestMapping(value = "/qryShopInfoByUsr.do")
	@ResponseBody
    public QryShopInfoByUsrNoResponseDto qryStoreInfoByUsr(@RequestBody QryShopInfoByUsrNoRequestDto request)
            throws HiException {
		QryShopInfoByUsrNoResponseDto responseDto = new QryShopInfoByUsrNoResponseDto();
		//接口报文检查
        if(!request.checkRequestDto()){
            HiBizLogger.info("接口请求报文异常" + request.toString());
            throw new HiBusinessReturnException(HiMsgCdConstants.TX_REQUESTBODY_FAIL, "接口请求报文异常 ");
        }
        try {
            responseDto = shopMngService.qryShopInfoByUsr(request);
        } catch (HiBusinessAbortException e) {
            // 业务处理终止异常HiBusinessAbortException捕获后，需将异常码设置到传输对象，并返回
            // 业务处理终止异常意为，该错误导致业务无法继续进行
            responseDto.setRspCd(e.getCode());
            return responseDto;
        } catch (HiBusinessReturnException e) {
            // 业务处理返回异常HiBusinessReturnException补货后，需检查code，根据返回做特殊处理，比如：忽略错误等
            responseDto.setRspCd(e.getCode());
            return responseDto;
        }
        responseDto.setRspCd(HiMsgCdConstants.SUCCESS);
        return responseDto;
    }
	
	/**
	 * 获取门店下单计划
	 * @param request
	 * @return QryShopOrderPlanInfoByShopNoResponseDto
	 * @throws HiException
	 */
	@RequestMapping(value = "/qryShopOrderPlan.do")
    @ResponseBody
    public QryShopOrderPlanInfoByShopNoResponseDto qryShopOrderPlan(@RequestBody QryShopOrderPlanRequestDto request)
            throws HiException {
		QryShopOrderPlanInfoByShopNoResponseDto responseDto = new QryShopOrderPlanInfoByShopNoResponseDto();
    	//接口报文检查
        if(!request.checkRequestDto()){
            HiBizLogger.info("接口请求报文异常" + request.toString());
            throw new HiBusinessReturnException(HiMsgCdConstants.TX_REQUESTBODY_FAIL, "接口请求报文异常 ");
        }
    	try {
            responseDto = shopMngService.qryShopOrderPlan(request);
        } catch (HiBusinessAbortException e) {
            // 业务处理终止异常HiBusinessAbortException捕获后，需将异常码设置到传输对象，并返回
            // 业务处理终止异常意为，该错误导致业务无法继续进行
            responseDto.setRspCd(e.getCode());
            return responseDto;
        } catch (HiBusinessReturnException e) {
            // 业务处理返回异常HiBusinessReturnException补货后，需检查code，根据返回做特殊处理，比如：忽略错误等
            responseDto.setRspCd(e.getCode());
            return responseDto;
        }
        responseDto.setRspCd(HiMsgCdConstants.SUCCESS);
        return responseDto;
    }
	
	

}
