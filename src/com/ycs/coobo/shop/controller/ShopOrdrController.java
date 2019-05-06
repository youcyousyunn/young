package com.ycs.coobo.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ycs.basbo.constants.HiMsgCdConstants;
import com.ycs.base.annotation.HiOperLog;
import com.ycs.base.controller.IBaseController;
import com.ycs.base.exception.HiBusinessAbortException;
import com.ycs.base.exception.HiBusinessReturnException;
import com.ycs.base.exception.HiException;
import com.ycs.base.log4j.logger.HiBizLogger;
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
import com.ycs.coobo.shop.service.ShopOrdrService;

/**
 * 门店订单相关Controller
 * @author youcyousyunn
 * @date 2018年5月19日
 */
@Controller
@RequestMapping("/shop/v1")
public class ShopOrdrController extends IBaseController {

	@Autowired
    private ShopOrdrService shopOrdrService;
	
	
	/**
	 * 查询门店订单列表
	 * @param  request
	 * @return QryShopOrdrLstResponseDto
	 * @throws HiException
	 */
	@RequestMapping(value = "/qryShopOrdrLst.do")
	@ResponseBody
    public QryShopOrdrLstResponseDto qryShopOrdrLst(@RequestBody QryShopOrdrLstRequestDto request)
            throws HiException {
        QryShopOrdrLstResponseDto responseDto = new QryShopOrdrLstResponseDto();
        //接口报文检查
        if(!request.checkRequestDto()){
            HiBizLogger.info("接口请求报文异常" + request.toString());
            throw new HiBusinessReturnException(HiMsgCdConstants.TX_REQUESTBODY_FAIL, "接口请求报文异常 ");
        }
        try {
            responseDto = shopOrdrService.qryShopOrdrLst(request);
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
	 * 门店下单申请
	 * @param request
	 * @return ShopOrdrApplyResponseDto
	 * @throws HiException
	 */
	@HiOperLog(title = "门店订单操作", action = "添加门店订单申请", isSaveData = true, channel = "WEB")
    @RequestMapping(value = "/shopOrdrApply.do")
	@ResponseBody
    public ShopOrdrApplyResponseDto shopOrdrApply(@RequestBody ShopOrdrApplyRequestDto request)throws HiException {
        ShopOrdrApplyResponseDto responseDto = new ShopOrdrApplyResponseDto();
        //接口报文检查
        if(!request.checkRequestDto()){
            HiBizLogger.info("接口请求报文异常" + request.toString());
            throw new HiBusinessReturnException(HiMsgCdConstants.TX_REQUESTBODY_FAIL, "接口请求报文异常 ");
        }
        try {
            responseDto = shopOrdrService.shopOrdrApply(request);
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
	 * 查询门店订单信息
	 * @param
	 * @return QryShopOrdrInfoResponseDto
	 * @throws 
	 * youcyousyunn
	 * 2018年6月13日
	 */
	@RequestMapping(value = "/qryShopOrdrInfo.do")
	@ResponseBody
    public QryShopOrdrInfoResponseDto qryShopOrdrInfo(@RequestBody QryShopOrdrInfoRequestDto request)
            throws HiException {
        QryShopOrdrInfoResponseDto responseDto = new QryShopOrdrInfoResponseDto();
        //接口报文检查
        if(!request.checkRequestDto()){
            HiBizLogger.info("接口请求报文异常" + request.toString());
            throw new HiBusinessReturnException(HiMsgCdConstants.TX_REQUESTBODY_FAIL, "接口请求报文异常 ");
        }
        try {
            responseDto = shopOrdrService.qryShopOrdrInfo(request);
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
	 * 查询门店订单明细信息
	 * @param request
	 * @return QryShopOrdrDetailInfoResponseDto
	 * @throws HiException
	 */
	@RequestMapping(value = "/qryShopOrdrDetailInfo.do")
	@ResponseBody
    public QryShopOrdrDetailInfoResponseDto qryShopOrdrDetailInfo(@RequestBody QryShopOrdrDetailInfoRequestDto request)
            throws HiException {
        QryShopOrdrDetailInfoResponseDto responseDto = new QryShopOrdrDetailInfoResponseDto();
        //接口报文检查
        if(!request.checkRequestDto()){
            HiBizLogger.info("接口请求报文异常" + request.toString());
            throw new HiBusinessReturnException(HiMsgCdConstants.TX_REQUESTBODY_FAIL, "接口请求报文异常 ");
        }
        try {
            responseDto = shopOrdrService.qryShopOrdrDetailInfo(request);
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
	 * 门店订单审核请求 
	 * @param request
	 * @return ReviewShopOrdrResponseDto
	 * @throws HiException
	 */
	@RequestMapping(value = "/reviewShopOrdr.do")
	@ResponseBody
    public ReviewShopOrdrResponseDto reviewShopOrdr(@RequestBody ReviewShopOrdrRequestDto request)
            throws HiException {
        ReviewShopOrdrResponseDto responseDto = new ReviewShopOrdrResponseDto();
        //接口报文检查
        if(!request.checkRequestDto()){
            HiBizLogger.info("接口请求报文异常" + request.toString());
            throw new HiBusinessReturnException(HiMsgCdConstants.TX_REQUESTBODY_FAIL, "接口请求报文异常 ");
        }
        try {
            responseDto = shopOrdrService.reviewShopOrdr(request);
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
	 * 采购门店订单合并需求
	 * @param request
	 * @return ShopOrdrCombinResponseDto
	 * @throws HiException
	 */
    @RequestMapping(value = "/shopOrdrCombin.do")
    @ResponseBody
    public ShopOrdrCombinResponseDto shopOrdrCombin(@RequestBody ShopOrdrCombinRequestDto request)
            throws HiException {
        ShopOrdrCombinResponseDto responseDto = new ShopOrdrCombinResponseDto();
        //接口报文检查
        if(!request.checkRequestDto()){
            HiBizLogger.info("接口请求报文异常" + request.toString());
            throw new HiBusinessReturnException(HiMsgCdConstants.TX_REQUESTBODY_FAIL, "接口请求报文异常 ");
        }
        try {
            responseDto = shopOrdrService.shopOrdrCombin(request);
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
     * 采购门店订单合并提交
     * @param request
     * @return ShopOrdrCombinSubmitResponseDto
     * @throws HiException
     */
    @RequestMapping(value = "/shopOrdrCombinSubmit.do")
    @ResponseBody
    public ShopOrdrCombinSubmitResponseDto shopOrdrCombinSubmit(@RequestBody ShopOrdrCombinSubmitRequestDto request)
            throws HiException {
        ShopOrdrCombinSubmitResponseDto responseDto = new ShopOrdrCombinSubmitResponseDto();
        //接口报文检查
        if(!request.checkRequestDto()){
            HiBizLogger.info("接口请求报文异常" + request.toString());
            throw new HiBusinessReturnException(HiMsgCdConstants.TX_REQUESTBODY_FAIL, "接口请求报文异常 ");
        }
        try {
            responseDto = shopOrdrService.shopOrdrCombinSubmit(request);
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
