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
import com.ycs.sys.domain.dto.QryAuthDataLstRequestDto;
import com.ycs.sys.domain.dto.QryAuthDataLstResponseDto;
import com.ycs.sys.service.DataAuthService;

/**
 * 数据权限接口
 * @author youcyousyunn
 * @date 2018年5月2日
 */
@Controller
@RequestMapping("/dataauth/v1")
public class DataAuthController extends IBaseController {

	@Autowired
    private DataAuthService dataAuthService;
	
	
	/**
	 * 获取数据权限列表
	 * @param
	 * @return QryAuthDataLstResponseDto
	 */
	@RequestMapping(value = "/qryAuthDataLst.do")
	@ResponseBody
    public QryAuthDataLstResponseDto qryAuthDataLst(@RequestBody QryAuthDataLstRequestDto request)
            throws HiException {
        QryAuthDataLstResponseDto responseDto = new QryAuthDataLstResponseDto();
        //接口报文检查
        if(!request.checkRequestDto()){
            HiBizLogger.info("接口请求报文异常" + request.toString());
            throw new HiBusinessReturnException(HiMsgCdConstants.TX_REQUESTBODY_FAIL, "接口请求报文异常 ");
        }
        try {
            responseDto = dataAuthService.qryAuthDataLst(request);
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
