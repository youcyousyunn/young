package com.ycs.msg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ycs.basbo.constants.HiMsgCdConstants;
import com.ycs.base.annotation.HiOperLog;
import com.ycs.base.controller.IBaseController;
import com.ycs.base.exception.HiBusinessReturnException;
import com.ycs.base.exception.HiException;
import com.ycs.base.log4j.logger.HiBizLogger;
import com.ycs.msg.domain.dto.NotifyRequestDto;
import com.ycs.msg.domain.dto.NotifyResponseDto;
import com.ycs.msg.domain.dto.QryNotifyRequestDto;
import com.ycs.msg.domain.dto.QryNotifyResponseDto;
import com.ycs.msg.service.MsgNotifyService;

/**
 * @description 发布公告Controller
 * @author youcyousyunn
 * @date 2018年11月24日
 */
@Controller
@RequestMapping("/work/v1")
public class MsgNotifyController extends IBaseController {
	@Autowired
	private MsgNotifyService msgNotifyService;
	
	/**
	 * 发布公告
	 * @param request
	 * @throws HiBusinessReturnException 
	 */
	@RequestMapping("/notify.do")
	@ResponseBody
	@HiOperLog(title = "发布公告请求", action = "发布公告操作", isSaveData = true, channel = "WEB")
    public NotifyResponseDto publish(@RequestBody NotifyRequestDto request) throws HiException {
        //接口报文检查
        if(!request.checkRequestDto()){
            HiBizLogger.info("接口请求报文异常" + request.toString());
            throw new HiBusinessReturnException(HiMsgCdConstants.TX_REQUESTBODY_FAIL, "接口请求报文异常 ");
        }
        NotifyResponseDto responseDto = new NotifyResponseDto();
        try {
            responseDto = msgNotifyService.addAnnounce(request);
        } catch (HiBusinessReturnException e) {
            responseDto.setRspCd(e.getCode());
            return responseDto;
        }
        responseDto.setRspCd(HiMsgCdConstants.SUCCESS);
        return responseDto;
    }
	
	/**
	 * 查找公告消息
	 * @param
	 * @return QryNotifyResponseDto
	 * @throws 
	 */
	@RequestMapping("/qryNotify.do")
	@ResponseBody
	@HiOperLog(title = "查找公告请求", action = "查找公告操作", isSaveData = true, channel = "WEB")
    public QryNotifyResponseDto qryNotify(@RequestBody QryNotifyRequestDto request) throws HiException {
		//接口报文检查
        if(!request.checkRequestDto()){
            HiBizLogger.info("接口请求报文异常" + request.toString());
            throw new HiBusinessReturnException(HiMsgCdConstants.TX_REQUESTBODY_FAIL, "接口请求报文异常 ");
        }
		QryNotifyResponseDto responseDto = new QryNotifyResponseDto();
        try {
            responseDto = msgNotifyService.qryNotify(request);
        } catch (HiBusinessReturnException e) {
            responseDto.setRspCd(e.getCode());
            return responseDto;
        }
        responseDto.setRspCd(HiMsgCdConstants.SUCCESS);
        return responseDto;
    }

}
