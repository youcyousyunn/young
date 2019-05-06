package com.ycs.base.spring.dao;

import com.ycs.base.spring.domain.po.OperlogPo;

/**
 * 系统操作日志接口Dao
 * @author youcyousyunn
 * @date 2018年6月30日
 */
public interface OperLogDao {
	int saveLogToDB(OperlogPo operlogPo);
	
}
