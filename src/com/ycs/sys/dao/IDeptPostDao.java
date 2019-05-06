package com.ycs.sys.dao;

import java.util.List;
import java.util.Map;

import com.ycs.sys.domain.po.DeptPostPo;

public interface IDeptPostDao {

	List<DeptPostPo> findDeptPost(Map<String, Object> paramMap);

	DeptPostPo queryAuditor(Map<String, Object> paramMap);

	
	
}
