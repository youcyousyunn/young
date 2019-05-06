package com.ycs.base.spring.bo.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ycs.base.annotation.HiBoMethod;
import com.ycs.base.spring.bo.LogJnlBo;
import com.ycs.base.spring.dao.LogJnlDao;
import com.ycs.base.spring.domain.po.LogJnlPo;

@Component
public class LogJnlBoImpl implements LogJnlBo {
	@Autowired
	private LogJnlDao logJnlDao;

	@HiBoMethod
	public int saveLog(LogJnlPo logJnlPo) {
		return null != logJnlPo ? this.logJnlDao.saveLog(logJnlPo) : 0;
	}
}
