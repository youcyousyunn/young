package com.ycs.coobo.compan.bo;

import com.ycs.base.annotation.HiBoMethod;
import com.ycs.coobo.compan.domain.po.CompaniesPo;

/**
 * 运营公司管理相关Bo接口
 * @author youcyousyunn
 * @date 2018年3月16日
 */
public interface CompaniesMngBo {

	/**
     * 更新运营公司名称
     * @param cotldNm
     * @param cotldNo
     * @return
     */
    @HiBoMethod
	boolean updCompaniesNm(String cotldNm, String cotldNo);

    /**
     * 更新运营公司
     * @param
     * @return boolean
     * youcyousyunn
     * 2018年3月17日
     */
    @HiBoMethod
	boolean updCompanies(CompaniesPo companiesPo);

    /**
     * 查询运营公司信息
     * @param
     * @return CompaniesPo
     */
    @HiBoMethod
	CompaniesPo qryCompanInfo(String cotldNo);
	
	
	
	
	
	
	
}
