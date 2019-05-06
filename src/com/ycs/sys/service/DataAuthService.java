package com.ycs.sys.service;

import com.ycs.base.exception.HiBusinessAbortException;
import com.ycs.base.exception.HiBusinessReturnException;
import com.ycs.sys.domain.dto.QryAuthDataLstRequestDto;
import com.ycs.sys.domain.dto.QryAuthDataLstResponseDto;

/**
 * 数据权限Service接口
 * @author youcyousyunn
 * @date 2018年5月2日
 */
public interface DataAuthService {

	/**
	 * 分页查询数据权限
	 * @param
	 * @return QryAuthDataLstResponseDto
	 */
	QryAuthDataLstResponseDto qryAuthDataLst(QryAuthDataLstRequestDto request) throws HiBusinessReturnException,HiBusinessAbortException;

	
	
	
	
}
