package com.ycs.coobo.coo.service;

import com.ycs.base.exception.HiBusinessReturnException;
import com.ycs.coobo.coo.domain.dto.QueryProductTypeTreeRequestDto;
import com.ycs.coobo.coo.domain.dto.QueryProductTypeTreeResponseDto;

public interface IProductTypeService {

	/**
	 * 查询产品分类tree
	 * @param request
	 * @return QueryProductTypeTreeResponseDto
	 */
	public QueryProductTypeTreeResponseDto queryProductTypeTree(QueryProductTypeTreeRequestDto request)throws HiBusinessReturnException;

	
	
}
