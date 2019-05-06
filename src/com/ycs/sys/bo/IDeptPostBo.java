package com.ycs.sys.bo;

import java.util.List;

import com.ycs.base.annotation.HiBoMethod;
import com.ycs.sys.domain.po.DeptPostPo;

public interface IDeptPostBo {

	/**
	 * 查询部门岗位信息
	 * @param
	 * @return List<DeptPostPo>
	 */
	@HiBoMethod
	public List<DeptPostPo> findDeptPost(Integer deparNo);

	/**
     * 获取审核人员
     * @param deparNo 部门NO
     * @param postId 岗位ID(可以为空，如果为空返回默认审核人，否则返回 指定审核人)
     * @return DeptPostPo
     */
    @HiBoMethod
	public DeptPostPo queryAuditor(Integer deparNo, Integer postId);

	
	
}
