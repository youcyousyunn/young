package com.ycs.base.spring.bo.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ycs.base.annotation.HiBoMethod;
import com.ycs.base.spring.bo.SchJobBo;
import com.ycs.base.spring.dao.SchJobDao;
import com.ycs.base.spring.domain.po.SchJobPo;
import com.ycs.base.spring.domain.pojo.ScheduleJobPojo;

@Component
public class SchJobBoImpl implements SchJobBo {

	@Autowired
	private SchJobDao schJobDao;

	@HiBoMethod
	public SchJobPo querySchJob(String jobId) {
		return schJobDao.querySchJob(jobId);
	}

	@HiBoMethod
	public boolean delDynJob(String jobId) {
		return schJobDao.delDynJob(jobId);
	}

	@HiBoMethod
	public List<ScheduleJobPojo> queryDynSchJob() {
		return schJobDao.queryDynSchJob();
	}

}
