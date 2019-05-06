package com.ycs.coobo.coo.dao;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.ycs.coobo.coo.domain.po.ProductPo;

public interface IProductDao {

	int qryProductlstCount(LinkedHashMap<String, Object> paramMap);

	List<ProductPo> qryProductlst(LinkedHashMap<String, Object> paramMap);

	List<ProductPo> qryAllProductLst(LinkedHashMap<String, Object> paramMap);

	List<Map<String, Object>> countProductTypeNum(LinkedHashMap<String, Object> paramMap);

	List<ProductPo> qryProductsByCodeLst(List<String> barCodeLst);
	
	
	

}
