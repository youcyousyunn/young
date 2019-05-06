package com.ycs.coobo.coo.bo.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ycs.coobo.coo.bo.IProductTypeBo;
import com.ycs.coobo.coo.dao.IProductTypeDao;
import com.ycs.coobo.coo.domain.po.ProductTypePo;

@Component
public class IProductTypeBoImpl implements IProductTypeBo {

	/**
     * 产品类型DAO
     */
    @Autowired
    private IProductTypeDao productTypeDao;

	@Override
	public List<ProductTypePo> queryAllProductsType(List<String> types) {
		return productTypeDao.queryAllProductsType(types);
	}
    
    

}
