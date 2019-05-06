package com.ycs.base.spring.bo.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ycs.base.spring.bo.OperLogBo;
import com.ycs.base.spring.dao.OperLogDao;
import com.ycs.base.spring.domain.po.OperlogPo;

@Component
public class OperLogBoImpl implements OperLogBo {

	@Autowired
	private OperLogDao operLogDao;
	
	@Override
	public int saveLogToDB(OperlogPo operlogPo) {
		return operLogDao.saveLogToDB(operlogPo);
	}

}
