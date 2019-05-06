package com.ycs.coobo.compan.bo.impl;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ycs.base.bo.BaseBo;
import com.ycs.coobo.compan.bo.CompaniesMngBo;
import com.ycs.coobo.compan.dao.CompaniesMngDao;
import com.ycs.coobo.compan.domain.po.CompaniesPo;

/**
 * 运营公司管理相关Bo接口实现
 * @author youcyousyunn
 * @date 2018年3月16日
 */
@Component
public class CompaniesMngBoImpl extends BaseBo implements CompaniesMngBo {

	@Autowired
    private CompaniesMngDao companiesMngDao;
	
	@Override
	public boolean updCompaniesNm(String cotldNm, String cotldNo) {
		boolean result = false;
		Map<String, Object> requestMap = new LinkedHashMap<>();
		requestMap.put("cotldNm", cotldNm);
		requestMap.put("cotldNo", cotldNo);
		int result_ = companiesMngDao.updCompaniesNm(requestMap);
		if (result_ > 0){
			result = true;
		}
		return result;
	}

	@Override
	public boolean updCompanies(CompaniesPo companiesPo) {
		boolean result = false;
		int result_ = companiesMngDao.updCompanies(companiesPo);
		if (result_ > 0){
			result = true;
		}
		return result;
	}

	@Override
	public CompaniesPo qryCompanInfo(String cotldNo) {
		return companiesMngDao.qryCompanInfo(cotldNo);
	}

	
	
	
	
	
	
	
	
	
	
}
