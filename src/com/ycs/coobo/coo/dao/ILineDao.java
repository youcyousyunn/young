package com.ycs.coobo.coo.dao;

import java.util.LinkedHashMap;

import com.ycs.coobo.coo.domain.po.LineInfoPo;

public interface ILineDao {

	/**
	 * 根据仓库号，门店号查询线路信息
	 * @param paramMap
	 * @return LineInfoPo
	 */
	LineInfoPo qryLineInfoByShop(LinkedHashMap<String, Object> paramMap);

}
