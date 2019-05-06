package com.ycs.coobo.coo.controller;

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
import com.ycs.coobo.coo.domain.dto.QryStohAllPrdLstRequestDto;
import com.ycs.coobo.coo.domain.dto.QryStohAllPrdLstResponseDto;
import com.ycs.coobo.stoh.service.StoHoseMngService;

/**
 * 仓库管理Controller
 * @author youcyousyunn
 * @date 2018年5月24日
 */
@Controller
@RequestMapping("/coo/v1")
public class StoHoseMngController extends IBaseController {

	@Autowired
    private StoHoseMngService stoHoseMngService;
	
	
	/**
	 * 查询仓库可供产品信息
	 * @param
	 * @return QryStohAllPrdLstResponseDto
	 * @throws 
	 * youcyousyunn
	 * 2018年5月24日
	 */
	@RequestMapping(value = "/qryStohAllPrdLst.do")
	@ResponseBody
    public QryStohAllPrdLstResponseDto qryStohAllPrdLst(@RequestBody QryStohAllPrdLstRequestDto request)
            throws HiException {
        QryStohAllPrdLstResponseDto responseDto = new QryStohAllPrdLstResponseDto();
        //接口报文检查
        if(!request.checkRequestDto()){
            HiBizLogger.info("接口请求报文异常" + request.toString());
            throw new HiBusinessReturnException(HiMsgCdConstants.TX_REQUESTBODY_FAIL, "接口请求报文异常 ");
        }
        try {
            responseDto = stoHoseMngService.qryStohAllPrdLst(request);
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
