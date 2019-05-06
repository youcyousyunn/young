package com.ycs.work.controller;

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
import com.ycs.msg.service.MsgNotifyService;
import com.ycs.work.domain.dto.IsReadMsgRequestDto;
import com.ycs.work.domain.dto.IsReadMsgResponseDto;
import com.ycs.work.domain.dto.QryMsgNotifyRequestDto;
import com.ycs.work.domain.dto.QryMsgNotifyResponseDto;


/**
 * 消息Controller
 * @author youcyousyunn
 * @date 2018年6月25日
 */
@Controller
@RequestMapping("/work/v1")
public class WorkMsgController extends IBaseController {
	
    @Autowired
    private MsgNotifyService msgNotifyService;
    
    
    /**
     * 查询工作台消息
     * @param request
     * @return QryMsgNotifyResponseDto
     * @throws HiException
     */
    @RequestMapping(value = "/qryMsgNotify.do")
    @ResponseBody
    public QryMsgNotifyResponseDto qryMsgNotify(@RequestBody QryMsgNotifyRequestDto request)
            throws HiException {
        QryMsgNotifyResponseDto responseDto = new QryMsgNotifyResponseDto();
        //接口报文检查
        if(!request.checkRequestDto()){
            HiBizLogger.info("接口请求报文异常" + request.toString());
            throw new HiBusinessReturnException(HiMsgCdConstants.TX_REQUESTBODY_FAIL, "接口请求报文异常 ");
        }
        try {
            responseDto = msgNotifyService.qryMsgNotify(request);
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
     * 阅读消息请求
     * @param request
     * @return IsReadMsgResponseDto
     * @throws HiException
     */
    @RequestMapping(value = "/isReadMsg.do")
    @ResponseBody
    public IsReadMsgResponseDto isReadMsg(@RequestBody IsReadMsgRequestDto request) throws HiException {
        IsReadMsgResponseDto responseDto = new IsReadMsgResponseDto();
        //接口报文检查
        if(!request.checkRequestDto()){
            HiBizLogger.info("接口请求报文异常" + request.toString());
            throw new HiBusinessReturnException(HiMsgCdConstants.TX_REQUESTBODY_FAIL, "接口请求报文异常 ");
        }
        try {
            responseDto = msgNotifyService.isReadMsg(request);
        } catch (HiBusinessAbortException e) {
            responseDto.setRspCd(e.getCode());
            return responseDto;
        } catch (HiBusinessReturnException e) {
            responseDto.setRspCd(e.getCode());
            return responseDto;
        }
        responseDto.setRspCd(HiMsgCdConstants.SUCCESS);
        return responseDto;
    }
    
    
    
    
    
    
}
