package com.ycs.coobo.coo.service.impl;

import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ycs.base.exception.HiBusinessAbortException;
import com.ycs.base.exception.HiBusinessReturnException;
import com.ycs.base.utils.PageUtil;
import com.ycs.coobo.coo.bo.IProductBo;
import com.ycs.coobo.coo.domain.dto.QueryProductRequestDto;
import com.ycs.coobo.coo.domain.dto.QueryProductResponseDto;
import com.ycs.coobo.coo.domain.po.ProductPo;
import com.ycs.coobo.coo.service.IProductService;

@Service
public class ProductServiceImpl implements IProductService {

	@Autowired
    private IProductBo productBo;

	
	@Override
	public QueryProductResponseDto qryProductlst(QueryProductRequestDto request) throws HiBusinessAbortException, HiBusinessReturnException {
		LinkedHashMap<String, Object> paramMap = new LinkedHashMap<String, Object>();
        paramMap.put("prov", null != request.getProv() ? request.getProv() : null);
        paramMap.put("city", null != request.getCity() ? request.getCity() : null);
        paramMap.put("classType", null != request.getClassType() ? request.getClassType() : null);
        paramMap.put("search", StringUtils.isNotBlank(request.getSearch()) ? "%" + request.getSearch().trim() + "%" : null);
        
        // 首先查询共有多少记录
        int totalCount = productBo.qryProductlstCount(paramMap);
        
        // 计算分页信息
        PageUtil.calculatePageInfo(totalCount, request.getPageSize(), request.getCurrentPage());
        
        // 分页查询
        List<ProductPo> products = productBo.qryProductlst(paramMap, PageUtil.getStartRow(), PageUtil.getOffset());
        
        QueryProductResponseDto response = new QueryProductResponseDto();
        response.setRows(products);
        response.setTotal(totalCount);
        return response;
	}
	
	
	

}
