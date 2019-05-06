package com.ycs.coobo.coo.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ycs.basbo.constants.HiMsgCdConstants;
import com.ycs.base.controller.IBaseController;
import com.ycs.base.exception.HiBusinessAbortException;
import com.ycs.base.exception.HiBusinessReturnException;
import com.ycs.base.exception.HiException;
import com.ycs.base.log4j.logger.HiBizLogger;
import com.ycs.coobo.coo.service.ExportCommonExcelService;

/**
 * 公共下载请求接口
 * @author youcyousyunn
 * @date 2018年5月17日
 */
@Controller
@RequestMapping(value = "/cmm/v1")
public class ExportCommonExcelController extends IBaseController {

	@Autowired
    private ExportCommonExcelService exportCommonExcelService;
	
	
	/**
	 * 公共下载
	 * @param httpRequest 请求
	 * @param httpResponse 响应
	 * @return void
	 * @throws HiException
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value = "/CommonExport.do")
    public void CommonExport(HttpServletRequest httpRequest, HttpServletResponse httpResponse)throws HiException, UnsupportedEncodingException{
		httpRequest.setCharacterEncoding("utf-8");
        // 所有的请求参数可以从parameterMap获取
		Map<String, String[]> parameterMap = httpRequest.getParameterMap();
        StringBuffer sb = new StringBuffer();
        Iterator<?> iter = parameterMap.entrySet().iterator();
        while(iter.hasNext()){ 
            @SuppressWarnings("rawtypes")
			Map.Entry element = (Map.Entry)iter.next();
            sb.append(element.getKey()+":"+((String[])element.getValue())[0]+"\t");
        }
        // 打个日志记录下请求参数
        HiBizLogger.info(sb.toString());
           
        String downType = httpRequest.getParameter("downType");
        if(StringUtils.isBlank(downType)){
            throw new HiException(HiMsgCdConstants.TX_REQUESTPRAM_FAIL, "接口请求参数异常");
        }
        try {
            httpResponse = exportCommonExcelService.CommonExport(downType, parameterMap, httpRequest, httpResponse);
        } catch (HiBusinessAbortException e) {
            // 业务处理终止异常HiBusinessAbortException捕获后，需将异常码设置到传输对象，并返回
            // 业务处理终止异常意为，该错误导致业务无法继续进行
        } catch (HiBusinessReturnException e) {
            // 业务处理返回异常HiBusinessReturnException补货后，需检查code，根据返回做特殊处理，比如：忽略错误等
        } catch (IOException e) {
        }
        
    }
	
	
	

}
