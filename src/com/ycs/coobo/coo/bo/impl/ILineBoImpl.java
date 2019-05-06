package com.ycs.coobo.coo.bo.impl;

import java.util.LinkedHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ycs.coobo.coo.bo.ILineBo;
import com.ycs.coobo.coo.dao.ILineDao;
import com.ycs.coobo.coo.domain.po.LineInfoPo;

@Component
public class ILineBoImpl implements ILineBo {

	@Autowired
    private ILineDao lineDao;
	
	@Override
	public LineInfoPo qryLineInfoByShop(String stohNo, String shopNo) {
		LinkedHashMap<String, Object> paramMap = new LinkedHashMap<>();
		paramMap.put("stohNo", stohNo);
		paramMap.put("shopNo", shopNo);
		return lineDao.qryLineInfoByShop(paramMap);
	}


}
