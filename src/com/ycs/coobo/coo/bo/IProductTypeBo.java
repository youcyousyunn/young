package com.ycs.coobo.coo.bo;

import java.util.List;

import com.ycs.base.annotation.HiBoMethod;
import com.ycs.coobo.coo.domain.po.ProductTypePo;

public interface IProductTypeBo {

	/**
	 * 查询所有产品分类
	 * @param type 
	 * @return List<ProductTypePo>
	 */
	@HiBoMethod
	public List<ProductTypePo> queryAllProductsType(List<String> types);

	
}
