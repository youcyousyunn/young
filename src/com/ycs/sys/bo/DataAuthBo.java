package com.ycs.sys.bo;

import java.util.List;
import java.util.Map;

import com.ycs.base.annotation.HiBoMethod;
import com.ycs.sys.domain.po.DataAuthPo;

/**
 * 数据权限BO接口
 * @author youcyousyunn
 * @date 2018年5月2日
 */
public interface DataAuthBo {

	/**
	 * 分页查询数据权限总数
	 * @param
	 * @return int
	 */
	@HiBoMethod
	int qryAuthDataLstCount(Map<String, Object> paramMap);

	/**
	 * 分页查询数据权限列表
	 * @param
	 * @return List<DataAuthPo>
	 */
	@HiBoMethod
	List<DataAuthPo> qryAuthDataLst(Map<String, Object> paramMap, Integer startRow, Integer offset);

	/**
	 * 查询用户数据权限
	 * @param
	 * @return DataAuthPo
	 */
	@HiBoMethod
	DataAuthPo qryDataAuthByUsrNo(String usrNo);

	
	
	
	
	
}
