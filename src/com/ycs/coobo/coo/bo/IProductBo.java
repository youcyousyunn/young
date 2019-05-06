package com.ycs.coobo.coo.bo;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import com.ycs.base.annotation.HiBoMethod;
import com.ycs.coobo.coo.domain.po.ProductPo;

public interface IProductBo {

	@HiBoMethod
	public int qryProductlstCount(LinkedHashMap<String, Object> paramMap);

	@HiBoMethod
	public List<ProductPo> qryProductlst(LinkedHashMap<String, Object> paramMap, Integer startRow, Integer offset);

	@HiBoMethod
	public List<ProductPo> qryAllProductLst(LinkedHashMap<String, Object> paramMap);

	@HiBoMethod
	public HashMap<String, Integer> countProductTypeNum(String prov, String city, List<String> statuses);

	/**
	 * 根据商品条码集合获取商品信息
	 * @param
	 * @return List<ProductPo>
	 */
	@HiBoMethod
	public List<ProductPo> qryProductsByCodeLst(List<String> barCodeLst);
	

}
