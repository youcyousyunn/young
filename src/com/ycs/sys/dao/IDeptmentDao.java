package com.ycs.sys.dao;

import java.util.List;
import java.util.Map;

import com.ycs.sys.domain.po.DeptmentPo;

/**
 * 部门接口DAO
 * @author youcyousyunn
 * @date 2018年3月13日
 */
public interface IDeptmentDao {

	/**
     * 查询所有部门
     * @return 部门集合
     */
	List<DeptmentPo> queryDeptmentLst(Map<String, Object> requestMap);

	/**
     * 查询部门信息
     * @param deptmentNo 部门编号
     * @return 部门信息
     */
	DeptmentPo queryDeptment(Integer deptmentNo);

	/**
     * 更新部门名称
     * @param requestMap
     * @return 是否成功(1成功、0失败)
     */
	int renameDeptment(Map<String, Object> requestMap);

	/**
     * 更新部门信息
     * @param deptment 部门PO
     * @return 是否成功(1成功、0失败)
     */
	int updateDeptment(DeptmentPo deptment);

	/**
	 * 查询上级部门
	 * @param deparNo部门编号
	 * @return DeptmentPo 部门信息
	 */
	DeptmentPo queryParentDeptment(Integer deparNo);

	
	
	
	
	
	
}
