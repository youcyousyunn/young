package com.ycs.sys.controller;

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
import com.ycs.sys.domain.dto.QryFlowActionLstRequestDto;
import com.ycs.sys.domain.dto.QryFlowActionLstResponseDto;
import com.ycs.sys.domain.dto.QryFlowJrnLstRequestDto;
import com.ycs.sys.domain.dto.QryFlowJrnLstResponseDto;
import com.ycs.sys.domain.dto.QryFlowOrdInfoRequestDto;
import com.ycs.sys.domain.dto.QryFlowOrdInfoResponseDto;
import com.ycs.sys.domain.dto.UpdFlowActionRequestDto;
import com.ycs.sys.domain.dto.UpdFlowActionResponseDto;
import com.ycs.sys.service.FlowOrdMngService;

/**
 * 流程动作管理Controller
 * @author youcyousyunn
 * @date 2018年5月18日
 */
@Controller
@RequestMapping("/sys/v1")
public class FlowOrdMngController extends IBaseController {

	@Autowired
    private FlowOrdMngService flowOrdMngService;
	
	
	/**
	 * 查询流程动作列表
	 * @param
	 * @return QryFlowActionLstResponseDto
	 * @throws HiException
	 */
	@RequestMapping(value = "/qryFlowActionLst.do")
	@ResponseBody
    public QryFlowActionLstResponseDto qryFlowActionLst(@RequestBody QryFlowActionLstRequestDto request)
            throws HiException {
        QryFlowActionLstResponseDto responseDto = new QryFlowActionLstResponseDto();
        //接口报文检查
        if(!request.checkRequestDto()){
            HiBizLogger.info("接口请求报文异常" + request.toString());
            throw new HiBusinessReturnException(HiMsgCdConstants.TX_REQUESTBODY_FAIL, "接口请求报文异常 ");
        }
        try {
            responseDto = flowOrdMngService.qryFlowActionLst(request);
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
	 * 更改流程动作部门或者岗位
	 * @param
	 * @return UpdFlowActionResponseDto
	 * @throws HiException
	 */
	@RequestMapping(value = "/updFlowAction.do")
	@ResponseBody
    public UpdFlowActionResponseDto updFlowAction(@RequestBody UpdFlowActionRequestDto request)
            throws HiException {
		UpdFlowActionResponseDto responseDto = new UpdFlowActionResponseDto();
        //接口报文检查
        if(!request.checkRequestDto()){
            HiBizLogger.info("接口请求报文异常" + request.toString());
            throw new HiBusinessReturnException(HiMsgCdConstants.TX_REQUESTBODY_FAIL, "接口请求报文异常 ");
        }
        try {
            responseDto = flowOrdMngService.updFlowAction(request);
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
	 * @description 获取流程单据信息
	 * @param
	 * @return QryFlowOrdInfoResponseDto
	 * @throws HiException
	 */
    @RequestMapping(value = "/getFlowOrdInfo.do")
    @ResponseBody
    public QryFlowOrdInfoResponseDto getFlowOrdInfo(@RequestBody QryFlowOrdInfoRequestDto request)
            throws HiException {
        QryFlowOrdInfoResponseDto responseDto = new QryFlowOrdInfoResponseDto();
        //接口报文检查
        if(!request.checkRequestDto()){
            HiBizLogger.info("接口请求报文异常" + request.toString());
            throw new HiBusinessReturnException(HiMsgCdConstants.TX_REQUESTBODY_FAIL, "接口请求报文异常 ");
        }
        try {
            responseDto = flowOrdMngService.getFlowOrdInfo(request);
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
	 * @description 查询流程流水信息
	 * @param request
	 * @return QryFlowJrnLstResponseDto
	 * @throws HiException
	 */
    @RequestMapping(value = "/qryFlowJrnLst.do")
    @ResponseBody
    public QryFlowJrnLstResponseDto qryFlowJrnLst(@RequestBody QryFlowJrnLstRequestDto request)
            throws HiException {
        QryFlowJrnLstResponseDto responseDto = new QryFlowJrnLstResponseDto();
        //接口报文检查
        if(!request.checkRequestDto()){
            HiBizLogger.info("接口请求报文异常" + request.toString());
            throw new HiBusinessReturnException(HiMsgCdConstants.TX_REQUESTBODY_FAIL, "接口请求报文异常 ");
        }
        try {
            responseDto = flowOrdMngService.qryFlowJrnLst(request);
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
