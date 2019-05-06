package com.ycs.coobo.shop.domain.dto;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.ycs.base.domain.dto.BaseRequestDto;
import com.ycs.coobo.stoh.domain.po.StoHoseOrdrDetailPo;


/**
 * @description 采购合并门店订单提交请求DTO
 * @author youcyousyunn
 * @date 2018年11月11日
 */
public class ShopOrdrCombinSubmitRequestDto extends BaseRequestDto {
    //仓库号
    private String stohNo;
    //订单类型
    private String ordTyp;
    //产品申请列表数据
    private List<StoHoseOrdrDetailPo> stoHoseOrdrDetailPoLst;
    /**
     * @return the stohNo
     */
    public String getStohNo() {
        return stohNo;
    }
    /**
     * @param stohNo the stohNo to set
     */
    public void setStohNo(String stohNo) {
        this.stohNo = stohNo;
    }
    /**
	 * @return the ordTyp
	 */
	public String getOrdTyp() {
		return ordTyp;
	}
	/**
	 * @param ordTyp the ordTyp to set
	 */
	public void setOrdTyp(String ordTyp) {
		this.ordTyp = ordTyp;
	}
	/**
	 * @return the stoHoseOrdrDetailPoLst
	 */
	public List<StoHoseOrdrDetailPo> getStoHoseOrdrDetailPoLst() {
		return stoHoseOrdrDetailPoLst;
	}
	/**
	 * @param stoHoseOrdrDetailPoLst the stoHoseOrdrDetailPoLst to set
	 */
	public void setStoHoseOrdrDetailPoLst(List<StoHoseOrdrDetailPo> stoHoseOrdrDetailPoLst) {
		this.stoHoseOrdrDetailPoLst = stoHoseOrdrDetailPoLst;
	}
	/**
	 * 接口报文检查
	 * @return boolean
	 */
    public boolean checkRequestDto() {
        if (null == stoHoseOrdrDetailPoLst || 0 >= stoHoseOrdrDetailPoLst.size()) {
            return false;
        }
        if (null == stohNo || StringUtils.isBlank(stohNo)) {
            return false;
        }
        return true;
    }
    /* （non Javadoc）
     * Title: toString<br/>
     * <p>Description: <br/>
     * @return
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "ShopOrdrCombinSubmitRequestDto [ordTyp=" + ordTyp + ", stoHoseOrdrDetailPoLst=" + stoHoseOrdrDetailPoLst
                + "]";
    }
}
