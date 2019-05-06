package com.ycs.coobo.coo.bo.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.ycs.coobo.coo.bo.IProductBo;
import com.ycs.coobo.coo.dao.IProductDao;
import com.ycs.coobo.coo.domain.po.ProductPo;

@Component
public class ProductBoImpl implements IProductBo {

	@Autowired
    private IProductDao productDao;

	@Override
	public int qryProductlstCount(LinkedHashMap<String, Object> paramMap) {
		return productDao.qryProductlstCount(paramMap);
	}

	@Override
	public List<ProductPo> qryProductlst(LinkedHashMap<String, Object> paramMap, Integer startRow, Integer offset) {
		paramMap.put("startRow", startRow);
		paramMap.put("offset", offset);
		return productDao.qryProductlst(paramMap);
	}

	@Override
	public List<ProductPo> qryAllProductLst(LinkedHashMap<String, Object> paramMap) {
		return productDao.qryAllProductLst(paramMap);
	}

	@Override
	public HashMap<String, Integer> countProductTypeNum(String prov, String city, List<String> statuses) {
		LinkedHashMap<String, Integer> resultMap = new LinkedHashMap<>();
		LinkedHashMap<String, Object> paramMap = new LinkedHashMap<>();
		paramMap.put("prov", prov);
		paramMap.put("city", city);
		paramMap.put("statuses", statuses);
		List<Map<String, Object>> resultList = productDao.countProductTypeNum(paramMap);
        if (!CollectionUtils.isEmpty(resultList)) {
        	for (Map<String, Object> item : resultList) {
        		String key = String.valueOf(item.get("classId"));
        		int value = ((Long)item.get("count")).intValue();
        		resultMap.put(key, value);
        	}
        }
		return resultMap;
	}

	@Override
	public List<ProductPo> qryProductsByCodeLst(List<String> barCodeLst) {
		return productDao.qryProductsByCodeLst(barCodeLst);
	}
	
	

}
