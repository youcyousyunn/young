package com.ycs.sys.service;

import com.ycs.base.exception.HiBusinessAbortException;
import com.ycs.base.exception.HiBusinessReturnException;
import com.ycs.sys.domain.dto.QryCityLstRequestDto;
import com.ycs.sys.domain.dto.QryCityLstResponseDto;

public interface ProvCityService {

	/**
	 * 获取城市列表
	 * @param
	 * @return QryCityLstResponseDto
	 */
	public QryCityLstResponseDto getCityLst(QryCityLstRequestDto request)throws HiBusinessAbortException,HiBusinessReturnException;

	
	
}
