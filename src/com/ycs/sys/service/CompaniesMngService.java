package com.ycs.sys.service;

import com.ycs.base.exception.HiBusinessAbortException;
import com.ycs.base.exception.HiBusinessReturnException;
import com.ycs.coobo.compan.domain.dto.QryCompanInfoRequestDto;
import com.ycs.coobo.compan.domain.dto.QryCompanInfoResponseDto;

/**
 * 运营公司Service接口
 * @author youcyousyunn
 * @date 2018年3月19日
 */
public interface CompaniesMngService {

	/**
     * Title: qryCompanInfo<br/>
     * Description: 查询运营公司信息<br/>
     * @param request
     * @return
     */
	QryCompanInfoResponseDto qryCompanInfo(QryCompanInfoRequestDto request) throws HiBusinessAbortException,HiBusinessReturnException;

	
}
