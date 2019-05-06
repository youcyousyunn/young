package com.ycs.sys.bo.impl;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ycs.sys.bo.ProvCtiyBo;
import com.ycs.sys.dao.ProvCtiyDao;
import com.ycs.sys.domain.po.ProvCityPo;

@Component
public class ProvCtiyBoImpl implements ProvCtiyBo {
	
	@Autowired
	private ProvCtiyDao provCtiyDao;

	@Override
	public List<ProvCityPo> cityLst(LinkedHashMap<String, Object> paramMap) {
		return provCtiyDao.cityLst(paramMap);
	}
	
	

}
