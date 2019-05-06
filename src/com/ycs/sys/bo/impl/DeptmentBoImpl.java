package com.ycs.sys.bo.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ycs.base.bo.BaseBo;
import com.ycs.base.utils.DateUtil;
import com.ycs.sys.bo.IDeptmentBo;
import com.ycs.sys.dao.IDeptmentDao;
import com.ycs.sys.domain.po.DeptmentPo;

/**
 * 部门BO实现
 * @author youcyousyunn
 * @date 2018年3月13日
 */
@Component
public class DeptmentBoImpl extends BaseBo implements IDeptmentBo {

	/**
     * 部门DAO
     */
    @Autowired
    private IDeptmentDao deptmentDao;
	
	
	@Override
	public List<DeptmentPo> queryDeptmentLst(Integer deparNo) {
		Map<String, Object> requestMap = new LinkedHashMap<>();
		requestMap.put("deparNo", deparNo);
		return deptmentDao.queryDeptmentLst(requestMap);
	}


	@Override
	public DeptmentPo queryDeptment(Integer deptmentNo) {
		return deptmentDao.queryDeptment(deptmentNo);
	}

	@Override
	public int renameDeptment(Integer deptmentNo, String deptmentName) {
		Map<String, Object> requestMap = new LinkedHashMap<>();
		requestMap.put("deptmentNo", deptmentNo);
		requestMap.put("deptmentName", deptmentName);
		return deptmentDao.renameDeptment(requestMap);
	}

	@Override
	public int updateDeptment(DeptmentPo deptment) {
		deptment.setUpdDt(DateUtil.getStringDate());
		return deptmentDao.updateDeptment(deptment);
	}
	
	
	
	
	
	
	
	
	
	
	

}
