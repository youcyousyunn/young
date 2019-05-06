package com.ycs.coobo.coo.bo;

import java.util.List;

import com.ycs.base.annotation.HiBoMethod;
import com.ycs.coobo.coo.domain.po.WhslePricePo;
import com.ycs.coobo.suppli.domain.po.SuppliPricePo;

public interface ProductPriceBo {

	/**
	 * 根据国际码列表和门店类型查询商品批发价格
	 * @param
	 * @return List<WhslePricePo>
	 */
	List<WhslePricePo> qryWhslePriceByCodeLst(List<String> barCodeLst, String prov, String city, String shopType);

	/**
	 * 根据商品国际码列表和省份城市查询采购定价
	 * @param barCodeLst
	 * @return List<SuppliPricePo>
	 */
    @HiBoMethod
	List<SuppliPricePo> qrySuppliPriceByCodeLst(List<String> barCodeLst);

}
