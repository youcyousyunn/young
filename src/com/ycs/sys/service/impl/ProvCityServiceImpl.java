package com.ycs.sys.service.impl;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ycs.base.exception.HiBusinessAbortException;
import com.ycs.base.exception.HiBusinessReturnException;
import com.ycs.sys.bo.ProvCtiyBo;
import com.ycs.sys.domain.dto.QryCityLstRequestDto;
import com.ycs.sys.domain.dto.QryCityLstResponseDto;
import com.ycs.sys.domain.po.ProvCityPo;
import com.ycs.sys.service.ProvCityService;

@Service
public class ProvCityServiceImpl implements ProvCityService {

	@Autowired
    private ProvCtiyBo provCtiyBo;
	
	
	@Override
	public QryCityLstResponseDto getCityLst(QryCityLstRequestDto request) throws HiBusinessAbortException, 
			HiBusinessReturnException {
		LinkedHashMap<String, Object> paramMap = new LinkedHashMap<>();
        String provCityCd = request.getProvCityCd();
        paramMap.put("provCityCd", provCityCd);
        QryCityLstResponseDto responseDto = new QryCityLstResponseDto();
        List<ProvCityPo> row = provCtiyBo.cityLst(paramMap);
        responseDto.setRow(row);
        return responseDto;
	}

}
