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
import com.ycs.coobo.coo.domain.dto.QueryProductRequestDto;
import com.ycs.coobo.coo.domain.dto.QueryProductResponseDto;
import com.ycs.coobo.coo.service.IProductService;

/**
 * 产品Controller
 * @author youcyousyunn
 * @date 2018年5月15日
 */
@Controller
@RequestMapping(value = "/product/v1")
public class ProductController extends IBaseController {

	@Autowired
	private IProductService productService;
	
	
	/**
	 * 查询商品总库一览
	 * @param
	 * @return QueryProductResponseDto
	 */
	@RequestMapping(value = "/qryProductlst.do")
    @ResponseBody
    public QueryProductResponseDto qryAllProducts(@RequestBody QueryProductRequestDto request) throws HiException{
    	QueryProductResponseDto responseDto = new QueryProductResponseDto();
    	try {
            responseDto = productService.qryProductlst(request);
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
