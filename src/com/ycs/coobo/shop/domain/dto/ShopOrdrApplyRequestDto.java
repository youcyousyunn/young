package com.ycs.coobo.shop.domain.dto;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.ycs.base.domain.dto.BaseRequestDto;
import com.ycs.coobo.shop.domain.po.ShopOrdrDetailPo;

/**
 * 门店订单申请请求DTO
 * @author youcyousyunn
 * @date 2018年6月11日
 */
public class ShopOrdrApplyRequestDto extends BaseRequestDto {
	//订单类型
    private String ordrTyp;
    //订单分类
    private String ordCls;
    //订单日期
    private String ordDt;
    //调拨门店号
    private String excShopNo;
    //产品申请列表数据
    private List<ShopOrdrDetailPo> shopOrdrPrdLst;
    
	/**
	 * @return the ordrTyp
	 */
	public String getOrdrTyp() {
		return ordrTyp;
	}
	/**
	 * @param ordrTyp the ordrTyp to set
	 */
	public void setOrdrTyp(String ordrTyp) {
		this.ordrTyp = ordrTyp;
	}
	/**
	 * @return the ordCls
	 */
	public String getOrdCls() {
		return ordCls;
	}
	/**
	 * @param ordCls the ordCls to set
	 */
	public void setOrdCls(String ordCls) {
		this.ordCls = ordCls;
	}
	/**
	 * @return the ordDt
	 */
	public String getOrdDt() {
		return ordDt;
	}
	/**
	 * @param ordDt the ordDt to set
	 */
	public void setOrdDt(String ordDt) {
		this.ordDt = ordDt;
	}
	/**
	 * @return the excShopNo
	 */
	public String getExcShopNo() {
		return excShopNo;
	}
	/**
	 * @param excShopNo the excShopNo to set
	 */
	public void setExcShopNo(String excShopNo) {
		this.excShopNo = excShopNo;
	}
	/**
	 * @return the shopOrdrPrdLst
	 */
	public List<ShopOrdrDetailPo> getShopOrdrPrdLst() {
		return shopOrdrPrdLst;
	}
	/**
	 * @param shopOrdrPrdLst the shopOrdrPrdLst to set
	 */
	public void setShopOrdrPrdLst(List<ShopOrdrDetailPo> shopOrdrPrdLst) {
		this.shopOrdrPrdLst = shopOrdrPrdLst;
	}
	
	/**
	 * 接口请求报文检查
	 * @return boolean
	 */
    public boolean checkRequestDto() {
        if (null == ordrTyp || StringUtils.isBlank(ordrTyp)) {
            return false;
        }
        if (null == ordCls || StringUtils.isBlank(ordCls)) {
            return false;
        }
        if (0 == shopOrdrPrdLst.size()) {
            return false;
        }
        return true;
    }
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ShopOrdrApplyRequestDto [ordrTyp=" + ordrTyp + ", ordCls=" + ordCls + ", ordDt=" + ordDt
				+ ", excShopNo=" + excShopNo + ", shopOrdrPrdLst=" + shopOrdrPrdLst + "]";
	}
	
}
