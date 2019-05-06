package com.ycs.coobo.coo.bo.impl;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ycs.coobo.coo.bo.ProductPriceBo;
import com.ycs.coobo.coo.dao.ProductPriceDao;
import com.ycs.coobo.coo.domain.po.WhslePricePo;
import com.ycs.coobo.suppli.domain.po.SuppliPricePo;

@Component
public class ProductPriceBoImpl implements ProductPriceBo {

	@Autowired
    private ProductPriceDao productPriceDao;

	@Override
	public List<WhslePricePo> qryWhslePriceByCodeLst(List<String> barCodeLst, String prov, String city,
			String shopType) {
		LinkedHashMap<String, Object> paramMap = new LinkedHashMap<>();
		paramMap.put("barCodeLst", barCodeLst);
		paramMap.put("prov", prov);
		paramMap.put("city", city);
		paramMap.put("shopType", shopType);
		return productPriceDao.qryWhslePriceByCodeLst(paramMap);
	}

	@Override
	public List<SuppliPricePo> qrySuppliPriceByCodeLst(List<String> barCodeLst) {
		LinkedHashMap<String, Object> paramMap = new LinkedHashMap<>();
		paramMap.put("barCodeLst", barCodeLst);
		return productPriceDao.qrySuppliPriceByCodeLst(paramMap);
	}
	
	
	
}
