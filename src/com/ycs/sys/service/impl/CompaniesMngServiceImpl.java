package com.ycs.sys.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ycs.basbo.constants.HiMsgCdConstants;
import com.ycs.base.domain.dto.BaseDto;
import com.ycs.base.exception.HiBusinessAbortException;
import com.ycs.base.exception.HiBusinessReturnException;
import com.ycs.base.exception.HiException;
import com.ycs.coobo.compan.bo.CompaniesMngBo;
import com.ycs.coobo.compan.domain.dto.QryCompanInfoRequestDto;
import com.ycs.coobo.compan.domain.dto.QryCompanInfoResponseDto;
import com.ycs.coobo.compan.domain.po.CompaniesPo;
import com.ycs.sys.service.CompaniesMngService;

@Service
public class CompaniesMngServiceImpl implements CompaniesMngService {

	/**
	 * 运营公司BO
	 */
	@Autowired
    private CompaniesMngBo companiesMngBo;
	
	
	@Override
	@Transactional(readOnly = true)
	public QryCompanInfoResponseDto qryCompanInfo(QryCompanInfoRequestDto request) throws HiBusinessAbortException, HiBusinessReturnException {
        CompaniesPo companiesPo = companiesMngBo.qryCompanInfo(request.getCotldNo());
        if(null == companiesPo){
            throw new HiBusinessReturnException(HiMsgCdConstants.GET_COMPANINFO_FAIL, "查询运营公司信息失败");
        }
        
        QryCompanInfoResponseDto response = new QryCompanInfoResponseDto();
        try {
            response = BaseDto.entity2Trans(companiesPo, QryCompanInfoResponseDto.class);
        } catch (HiException e) {
            e.printStackTrace();
        }
        return response;
	}

	
	
	
	
}
