package com.ycs.sys.dao;

import java.util.List;
import java.util.Map;

import com.ycs.sys.domain.po.DataAuthPo;

/**
 * 数据权限Dao接口
 * @author youcyousyunn
 * @date 2018年5月2日
 */
public interface DataAuthDao {

	/**
	 * 分页数据权限查询总数
	 * @param
	 * @return int
	 */
	int qryAuthDataLstCount(Map<String, Object> paramMap);

	/**
	 * 查询分页数据权限列表
	 * @param
	 * @return List<DataAuthPo>
	 */
	List<DataAuthPo> qryAuthDataLst(Map<String, Object> paramMap);

	/**
	 * 查询用户数据权限
	 * @param
	 * @return DataAuthPo
	 */
	DataAuthPo qryDataAuthByUsrNo(String usrNo);
	
	
	
	

}
