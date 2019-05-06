package com.ycs.coobo.compan.dao;

import java.util.Map;

import com.ycs.coobo.compan.domain.po.CompaniesPo;

/**
 * 运营公司管理相关Dao接口
 * @author youcyousyunn
 * @date 2018年3月16日
 */
public interface CompaniesMngDao {

	/**
     * 更新运营公司名字
     * @param requestMap
     * @return
     */
	int updCompaniesNm(Map<String, Object> requestMap);

	/**
	 * 更新运营公司
	 * @param
	 * @return boolean
	 * youcyousyunn
	 * 2018年3月17日
	 */
	int updCompanies(CompaniesPo companiesPo);

	/**
	 * 查询运营公司信息
	 * @param
	 * @return CompaniesPo
	 */
	CompaniesPo qryCompanInfo(String cotldNo);

	

	
	
	
	
	
	
}
