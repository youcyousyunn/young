package com.ycs.coobo.coo.dao;

import java.util.LinkedHashMap;
import java.util.List;

import com.ycs.coobo.coo.domain.po.WhslePricePo;
import com.ycs.coobo.suppli.domain.po.SuppliPricePo;

public interface ProductPriceDao {

	List<WhslePricePo> qryWhslePriceByCodeLst(LinkedHashMap<String, Object> paramMap);
	
	List<SuppliPricePo> qrySuppliPriceByCodeLst(LinkedHashMap<String, Object> paramMap);

}
