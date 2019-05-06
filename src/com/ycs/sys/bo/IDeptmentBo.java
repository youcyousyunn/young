package com.ycs.sys.bo;

import java.util.List;

import com.ycs.base.annotation.HiBoMethod;
import com.ycs.sys.domain.po.DeptmentPo;

/**
 * 部门BO接口
 * @author youcyousyunn
 * @date 2018年3月13日
 */
public interface IDeptmentBo {

	/**
     * 查询所有部门
     * @return 部门集合
     */
    @HiBoMethod
	List<DeptmentPo> queryDeptmentLst(Integer deparNo);

    /**
     * 查询部门信息
     * @param deptmentNo 部门编号
     * @return 部门信息
     */
    @HiBoMethod
	DeptmentPo queryDeptment(Integer deptmentNo);

    /**
     * 更新部门名称
     * @param deptmentNo 部门编号
     * @param deptmentName 部门名称
     * @return 是否成功(1成功、0失败)
     */
    @HiBoMethod
	int renameDeptment(Integer deptmentNo, String deptmentName);

    /**
     * 更新部门信息
     * @param deptment 部门PO
     * @return 是否成功(1成功、0失败)
     */
    @HiBoMethod
	int updateDeptment(DeptmentPo deptment);

	
    
    
    
    
	
	
	
}
