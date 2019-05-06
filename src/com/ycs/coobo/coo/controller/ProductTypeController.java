package com.ycs.coobo.coo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ycs.base.annotation.HiOperLog;
import com.ycs.base.controller.IBaseController;
import com.ycs.base.exception.HiBusinessReturnException;
import com.ycs.base.exception.HiException;
import com.ycs.coobo.coo.domain.dto.QueryProductTypeTreeRequestDto;
import com.ycs.coobo.coo.domain.dto.QueryProductTypeTreeResponseDto;
import com.ycs.coobo.coo.service.IProductTypeService;

/**
 * 产品分类Controller
 * @author youcyousyunn
 * @date 2018年5月23日
 */
@Controller
@RequestMapping(value = "/producttype/v1")
public class ProductTypeController extends IBaseController {

	/**
     * 产品分类service
     */
    @Autowired
    private IProductTypeService productTypeService;
    
    
    /**
     * 查询产品分类tree
     * @param request
     * @return QueryProductTypeTreeResponseDto
     * @throws HiException
     */
    @ResponseBody
    @RequestMapping(value = "/tree.do")
    @HiOperLog(title = "查询产品类型树形菜单", action = "查询操作", isSaveData = true, channel = "WEB")
    public QueryProductTypeTreeResponseDto queryTypeTree(@RequestBody QueryProductTypeTreeRequestDto request)throws HiException{
        try {
            return productTypeService.queryProductTypeTree(request);
        } catch (HiBusinessReturnException e) {
            return new QueryProductTypeTreeResponseDto(e.getCode(), e.getMessage());
        }
    }
    
    
    

}
