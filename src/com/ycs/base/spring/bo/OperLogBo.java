package com.ycs.base.spring.bo;

import com.ycs.base.spring.domain.po.OperlogPo;

public interface OperLogBo {
	int saveLogToDB(OperlogPo operlogPo);
	
}

