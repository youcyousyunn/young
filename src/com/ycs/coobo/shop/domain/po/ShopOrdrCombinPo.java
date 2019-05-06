package com.ycs.coobo.shop.domain.po;

import com.ycs.base.domain.po.BasePo;

/**
 * @description 采购合并门店订单对象PO
 * @author youcyousyunn
 * @date 2018年11月11日
 */
public class ShopOrdrCombinPo extends BasePo {
    //国际码
    private String barCode;
    //商品名称
    private String prdNm;
    //采购规格
    private String sourcSpec;
    //采购单位
    private String sourcUnit;
    //采购基数
    private String sourcCnt;
    //采购价格
    private String sourcPrice;
    //零售单位
    private String posUnit;
    //需求数量
    private String needCnt;
    //库存数量
    private String nowCnt;
    //上周销量
    private String upSellCnt;
    //上月销量
    private String upmSellCnt;
    //供应商
    private String suppliNm;
    //建议订单量
    private String suggCnt;
    //订单量
    private String ordCnt;
    
    /**
     * @return the barCode
     */
    public String getBarCode() {
        return barCode;
    }
    /**
     * @param barCode the barCode to set
     */
    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }
    /**
     * @return the prdNm
     */
    public String getPrdNm() {
        return prdNm;
    }
    /**
     * @param prdNm the prdNm to set
     */
    public void setPrdNm(String prdNm) {
        this.prdNm = prdNm;
    }
    /**
     * @return the sourcSpec
     */
    public String getSourcSpec() {
        return sourcSpec;
    }
    /**
     * @param sourcSpec the sourcSpec to set
     */
    public void setSourcSpec(String sourcSpec) {
        this.sourcSpec = sourcSpec;
    }
    /**
     * @return the sourcUnit
     */
    public String getSourcUnit() {
        return sourcUnit;
    }
    /**
     * @param sourcUnit the sourcUnit to set
     */
    public void setSourcUnit(String sourcUnit) {
        this.sourcUnit = sourcUnit;
    }
    /**
     * @return the sourcCnt
     */
    public String getSourcCnt() {
        return sourcCnt;
    }
    /**
     * @param sourcCnt the sourcCnt to set
     */
    public void setSourcCnt(String sourcCnt) {
        this.sourcCnt = sourcCnt;
    }
    /**
     * @return the sourcPrice
     */
    public String getSourcPrice() {
        return sourcPrice;
    }
    /**
     * @param sourcPrice the sourcPrice to set
     */
    public void setSourcPrice(String sourcPrice) {
        this.sourcPrice = sourcPrice;
    }
    /**
     * @return the posUnit
     */
    public String getPosUnit() {
        return posUnit;
    }
    /**
     * @param posUnit the posUnit to set
     */
    public void setPosUnit(String posUnit) {
        this.posUnit = posUnit;
    }
    /**
     * @return the needCnt
     */
    public String getNeedCnt() {
        return needCnt;
    }
    /**
     * @param needCnt the needCnt to set
     */
    public void setNeedCnt(String needCnt) {
        this.needCnt = needCnt;
    }
    /**
     * @return the nowCnt
     */
    public String getNowCnt() {
        return nowCnt;
    }
    /**
     * @param nowCnt the nowCnt to set
     */
    public void setNowCnt(String nowCnt) {
        this.nowCnt = nowCnt;
    }
    /**
     * @return the upSellCnt
     */
    public String getUpSellCnt() {
        return upSellCnt;
    }
    /**
     * @param upSellCnt the upSellCnt to set
     */
    public void setUpSellCnt(String upSellCnt) {
        this.upSellCnt = upSellCnt;
    }
    /**
     * @return the upmSellCnt
     */
    public String getUpmSellCnt() {
        return upmSellCnt;
    }
    /**
     * @param upmSellCnt the upmSellCnt to set
     */
    public void setUpmSellCnt(String upmSellCnt) {
        this.upmSellCnt = upmSellCnt;
    }
    /**
     * @return the suppliNm
     */
    public String getSuppliNm() {
        return suppliNm;
    }
    /**
     * @param suppliNm the suppliNm to set
     */
    public void setSuppliNm(String suppliNm) {
        this.suppliNm = suppliNm;
    }
    /**
     * @return the suggCnt
     */
    public String getSuggCnt() {
        return suggCnt;
    }
    /**
     * @param suggCnt the suggCnt to set
     */
    public void setSuggCnt(String suggCnt) {
        this.suggCnt = suggCnt;
    }
    /**
     * @return the ordCnt
     */
    public String getOrdCnt() {
        return ordCnt;
    }
    /**
     * @param ordCnt the ordCnt to set
     */
    public void setOrdCnt(String ordCnt) {
        this.ordCnt = ordCnt;
    }
    /* （non Javadoc）
     * Title: toString<br/>
     * <p>Description: <br/>
     * @return
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "ShopOrdrCombinPo [barCode=" + barCode + ", prdNm=" + prdNm + ", sourcSpec=" + sourcSpec
                + ", sourcUnit=" + sourcUnit + ", sourcCnt=" + sourcCnt + ", sourcPrice=" + sourcPrice + ", posUnit="
                + posUnit + ", needCnt=" + needCnt + ", nowCnt=" + nowCnt + ", upSellCnt=" + upSellCnt
                + ", upmSellCnt=" + upmSellCnt + ", suppliNm=" + suppliNm + ", suggCnt=" + suggCnt + ", ordCnt="
                + ordCnt + "]";
    }
}
