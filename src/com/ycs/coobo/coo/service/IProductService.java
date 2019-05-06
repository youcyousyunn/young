package com.ycs.coobo.coo.service;

import com.ycs.base.exception.HiBusinessAbortException;
import com.ycs.base.exception.HiBusinessReturnException;
import com.ycs.coobo.coo.domain.dto.QueryProductRequestDto;
import com.ycs.coobo.coo.domain.dto.QueryProductResponseDto;

public interface IProductService {

	/**
	 * 查询商品总库一览
	 * @param
	 * @return QueryProductResponseDto
	 */
	public QueryProductResponseDto qryProductlst(QueryProductRequestDto request) throws HiBusinessAbortException, HiBusinessReturnException;
	
	

}
