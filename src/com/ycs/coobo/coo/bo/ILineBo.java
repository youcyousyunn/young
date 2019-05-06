package com.ycs.coobo.coo.bo;

import com.ycs.base.annotation.HiBoMethod;
import com.ycs.coobo.coo.domain.po.LineInfoPo;

/**
 * 线路BO
 * @author youcyousyunn
 * @date 2018年6月11日
 */
public interface ILineBo {

	/**
	 * 根据仓库号，门店号查询线路信息
	 * @param 
	 * @return LineInfoPo
	 */
	@HiBoMethod
	LineInfoPo qryLineInfoByShop(String stohNo, String shopNo);

	
	
}
