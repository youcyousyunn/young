package com.ycs.coobo.stoh.service;

import com.ycs.base.exception.HiBusinessAbortException;
import com.ycs.base.exception.HiBusinessReturnException;
import com.ycs.coobo.coo.domain.dto.QryStohAllPrdLstRequestDto;
import com.ycs.coobo.coo.domain.dto.QryStohAllPrdLstResponseDto;

public interface StoHoseMngService {

	/**
	 * 查询仓库可供产品信息
	 * @param
	 * @return QryStohAllPrdLstResponseDto
	 * @throws HiBusinessAbortException,HiBusinessReturnException;
	 */
	public QryStohAllPrdLstResponseDto qryStohAllPrdLst(QryStohAllPrdLstRequestDto request)throws HiBusinessAbortException,HiBusinessReturnException;

	
	
}
