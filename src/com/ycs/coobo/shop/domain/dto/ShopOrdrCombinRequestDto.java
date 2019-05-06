package com.ycs.coobo.shop.domain.dto;

import java.util.List;

import com.ycs.base.domain.dto.BaseRequestDto;

/**
 * @description 采购合并门店订单请求DTO
 * @author youcyousyunn
 * @date 2018年11月11日
 */
public class ShopOrdrCombinRequestDto extends BaseRequestDto {
    //门店订单列表
    private List<String> shopOrdNoLst;
    
    /**
     * @return the shopOrdNoLst
     */
    public List<String> getShopOrdNoLst() {
        return shopOrdNoLst;
    }
    /**
     * @param shopOrdNoLst the shopOrdNoLst to set
     */
    public void setShopOrdNoLst(List<String> shopOrdNoLst) {
        this.shopOrdNoLst = shopOrdNoLst;
    }
    /* （non Javadoc）
     * Title: toString<br/>
     * <p>Description: <br/>
     * @return
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "ShopOrdrCombinRequestDto [shopOrdNoLst=" + shopOrdNoLst + "]";
    }
    /**
     * 接口报文检查
     * @return boolean
     */
    public boolean checkRequestDto() {
        if (null == shopOrdNoLst || 0 >= shopOrdNoLst.size()) {
            return false;
        }
        return true;
    }
}
