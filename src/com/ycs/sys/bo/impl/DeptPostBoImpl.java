package com.ycs.sys.bo.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ycs.base.bo.BaseBo;
import com.ycs.sys.bo.IDeptPostBo;
import com.ycs.sys.dao.IDeptPostDao;
import com.ycs.sys.dao.IDeptmentDao;
import com.ycs.sys.domain.po.DeptPostPo;
import com.ycs.sys.domain.po.DeptmentPo;

@Component
public class DeptPostBoImpl extends BaseBo implements IDeptPostBo {

	/**
     * 部门岗位DAO
     */
    @Autowired
    private IDeptPostDao deptPostDao;
    @Autowired
    private IDeptmentDao deptmentDao;

	@Override
	public List<DeptPostPo> findDeptPost(Integer deparNo) {
		Map<String, Object> paramMap = new LinkedHashMap<>();
		paramMap.put("deparNo", deparNo);
		return deptPostDao.findDeptPost(paramMap);
	}

	@Override
	public DeptPostPo queryAuditor(Integer deparNo, Integer postId) {
		// 先根据传入参数查询在该部门下是否有该岗位
		Map<String, Object> paramMap = new LinkedHashMap<>();
		paramMap.put("deparNo", deparNo);
		paramMap.put("postId", postId);
        DeptPostPo po = deptPostDao.queryAuditor(paramMap);
        // 如果在当前部门查询不到指定岗位则逐级向上查找，否则返回
        if (null == po && null != postId) {
            return auditor(deparNo, postId);
        }
        return po;
	}
	
	/**
     * 递归查询审核岗位
     * @param deparNo 部门编号
     * @param postId 岗位编号
     * @return 岗位PO
     */
    private DeptPostPo auditor(Integer deparNo, Integer postId) {
        // 递归查询上级部门是否有该岗位
        DeptmentPo deptment = deptmentDao.queryParentDeptment(deparNo);
        if (null != deptment) {
        	Map<String, Object> paramMap = new LinkedHashMap<>();
    		paramMap.put("deparNo", deptment.getDeparNo());
    		paramMap.put("postId", postId);
            DeptPostPo p = deptPostDao.queryAuditor(paramMap);
            return null != p ? p : auditor(deptment.getDeparNo(), postId);
        }
        return null;
    }
    
    
    

}
