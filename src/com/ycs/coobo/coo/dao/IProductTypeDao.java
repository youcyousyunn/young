package com.ycs.coobo.coo.dao;

import java.util.List;

import com.ycs.coobo.coo.domain.po.ProductTypePo;

public interface IProductTypeDao {

	public List<ProductTypePo> queryAllProductsType(List<String> types);

	
}
