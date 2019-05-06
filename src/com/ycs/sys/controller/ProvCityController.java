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
import com.ycs.sys.domain.dto.QryCityLstRequestDto;
import com.ycs.sys.domain.dto.QryCityLstResponseDto;
import com.ycs.sys.service.ProvCityService;

@Controller
@RequestMapping("/sys/v1")
public class ProvCityController extends IBaseController {

	@Autowired
    private ProvCityService provCityService;
	
	
	/**
	 * 获取城市列表
	 * @param request
	 * @return QryCityLstResponseDto
	 */
	@RequestMapping(value = "/getCityLst.do")
	@ResponseBody
    public QryCityLstResponseDto getCityLst(@RequestBody QryCityLstRequestDto request) throws HiException {
        QryCityLstResponseDto responseDto = new QryCityLstResponseDto();
        try {
            responseDto = provCityService.getCityLst(request);
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
