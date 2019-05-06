package com.ycs.sys.bo.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ycs.base.bo.BaseBo;
import com.ycs.sys.bo.DataAuthBo;
import com.ycs.sys.dao.DataAuthDao;
import com.ycs.sys.domain.po.DataAuthPo;

@Component
public class DataAuthBoImpl extends BaseBo implements DataAuthBo {

	@Autowired
    private DataAuthDao dataAuthDao;
	
	@Override
	public int qryAuthDataLstCount(Map<String, Object> paramMap) {
		return dataAuthDao.qryAuthDataLstCount(paramMap);
	}

	@Override
	public List<DataAuthPo> qryAuthDataLst(Map<String, Object> paramMap, Integer startRow, Integer offset) {
		paramMap.put("startRow", startRow);
		paramMap.put("offset", offset);
		return dataAuthDao.qryAuthDataLst(paramMap);
	}

	@Override
	public DataAuthPo qryDataAuthByUsrNo(String usrNo) {
		return dataAuthDao.qryDataAuthByUsrNo(usrNo);
	}
	
	
	
	

}
