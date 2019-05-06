package com.ycs.base.spring.bo;

import java.util.List;

import com.ycs.base.spring.domain.po.SchJobPo;
import com.ycs.base.spring.domain.pojo.ScheduleJobPojo;

public interface SchJobBo {
	SchJobPo querySchJob(String jobId);
	boolean delDynJob(String jobId);
	List<ScheduleJobPojo> queryDynSchJob();
	
}
