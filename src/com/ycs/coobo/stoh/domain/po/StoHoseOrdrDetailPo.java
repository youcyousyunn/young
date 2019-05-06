package com.ycs.coobo.stoh.domain.po;

import com.ycs.base.domain.po.BasePo;

/**
 * @description 仓库订单详情PO
 * @author youcyousyunn
 * @date 2018年11月11日
 */
public class StoHoseOrdrDetailPo extends BasePo {
    //流水号
    private String jrnNo;
    //订单号
    private String ordNo;
    //仓库号
    private String stohNo;
    //仓库名称
    private String stohNm;
    //供应商号
    private String suppliNo;
    //供应商名称
    private String suppliNm;
    //国际码
    private String barCode;
    //商品名称
    private String prdNm;
    //规格
    private String spec;
    //单价
    private String price;
    //总价
    private String totPrice;
    //单位
    private String unit;
    //是否可拆
    private String isApart;
    //批发单位
    private String whsleUnit;
    //批发规格
    private String whsleSpec;
    //规格基数
    private String cnt;
    //重量
    private String wght;
    //总重
    private String totWght;
    //税率
    private String tax;
    //下单数量
    private String ordCnt;
    //审核数量
    private String revwCnt;
    //收货数量
    private String recevCnt;
    //剩余数量
    private String surplusCnt;
    //是否可直配门店
    private String isLdist;
    //日期
    private String creDt;
    //时间
    private String creTm;
    
    /**
     * @return the jrnNo
     */
    public String getJrnNo() {
        return jrnNo;
    }
    /**
     * @param jrnNo the jrnNo to set
     */
    public void setJrnNo(String jrnNo) {
        this.jrnNo = jrnNo;
    }
    /**
     * @return the ordNo
     */
    public String getOrdNo() {
        return ordNo;
    }
    /**
     * @param ordNo the ordNo to set
     */
    public void setOrdNo(String ordNo) {
        this.ordNo = ordNo;
    }
    public String getStohNo() {
		return stohNo;
	}
	public void setStohNo(String stohNo) {
		this.stohNo = stohNo;
	}
	public String getStohNm() {
		return stohNm;
	}
	public void setStohNm(String stohNm) {
		this.stohNm = stohNm;
	}
	/**
     * @return the suppliNo
     */
    public String getSuppliNo() {
        return suppliNo;
    }
    /**
     * @param suppliNo the suppliNo to set
     */
    public void setSuppliNo(String suppliNo) {
        this.suppliNo = suppliNo;
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
     * @return the spec
     */
    public String getSpec() {
        return spec;
    }
    /**
     * @param spec the spec to set
     */
    public void setSpec(String spec) {
        this.spec = spec;
    }
    /**
     * @return the price
     */
    public String getPrice() {
        return price;
    }
    /**
     * @param price the price to set
     */
    public void setPrice(String price) {
        this.price = price;
    }
    /**
     * @return the totPrice
     */
    public String getTotPrice() {
        return totPrice;
    }
    /**
     * @param totPrice the totPrice to set
     */
    public void setTotPrice(String totPrice) {
        this.totPrice = totPrice;
    }
    /**
     * @return the unit
     */
    public String getUnit() {
        return unit;
    }
    /**
     * @param unit the unit to set
     */
    public void setUnit(String unit) {
        this.unit = unit;
    }
    /**
     * @return the isApart
     */
    public String getIsApart() {
        return isApart;
    }
    /**
     * @param isApart the isApart to set
     */
    public void setIsApart(String isApart) {
        this.isApart = isApart;
    }
    /**
     * @return the whsleUnit
     */
    public String getWhsleUnit() {
        return whsleUnit;
    }
    /**
     * @param whsleUnit the whsleUnit to set
     */
    public void setWhsleUnit(String whsleUnit) {
        this.whsleUnit = whsleUnit;
    }
    /**
     * @return the whsleSpec
     */
    public String getWhsleSpec() {
        return whsleSpec;
    }
    /**
     * @param whsleSpec the whsleSpec to set
     */
    public void setWhsleSpec(String whsleSpec) {
        this.whsleSpec = whsleSpec;
    }
    /**
     * @return the cnt
     */
    public String getCnt() {
        return cnt;
    }
    /**
     * @param cnt the cnt to set
     */
    public void setCnt(String cnt) {
        this.cnt = cnt;
    }
    /**
     * @return the wght
     */
    public String getWght() {
        return wght;
    }
    /**
     * @param wght the wght to set
     */
    public void setWght(String wght) {
        this.wght = wght;
    }
    /**
     * @return the totWght
     */
    public String getTotWght() {
        return totWght;
    }
    /**
     * @param totWght the totWght to set
     */
    public void setTotWght(String totWght) {
        this.totWght = totWght;
    }
    /**
     * @return the tax
     */
    public String getTax() {
        return tax;
    }
    /**
     * @param tax the tax to set
     */
    public void setTax(String tax) {
        this.tax = tax;
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
    /**
     * @return the revwCnt
     */
    public String getRevwCnt() {
        return revwCnt;
    }
    /**
     * @param revwCnt the revwCnt to set
     */
    public void setRevwCnt(String revwCnt) {
        this.revwCnt = revwCnt;
    }
    /**
     * @return the recevCnt
     */
    public String getRecevCnt() {
        return recevCnt;
    }
    /**
     * @param recevCnt the recevCnt to set
     */
    public void setRecevCnt(String recevCnt) {
        this.recevCnt = recevCnt;
    }
    /**
     * @return the surplusCnt
     */
    public String getSurplusCnt() {
        return surplusCnt;
    }
    /**
     * @param surplusCnt the surplusCnt to set
     */
    public void setSurplusCnt(String surplusCnt) {
        this.surplusCnt = surplusCnt;
    }
    /**
     * @return the isLdist
     */
    public String getIsLdist() {
        return isLdist;
    }
    /**
     * @param isLdist the isLdist to set
     */
    public void setIsLdist(String isLdist) {
        this.isLdist = isLdist;
    }
    /**
     * @return the creDt
     */
    public String getCreDt() {
        return creDt;
    }
    /**
     * @param creDt the creDt to set
     */
    public void setCreDt(String creDt) {
        this.creDt = creDt;
    }
    /**
     * @return the creTm
     */
    public String getCreTm() {
        return creTm;
    }
    /**
     * @param creTm the creTm to set
     */
    public void setCreTm(String creTm) {
        this.creTm = creTm;
    }
    /* （non Javadoc）
     * Title: toString<br/>
     * <p>Description: <br/>
     * @return
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "StoreHouseOrdrDetailPo [jrnNo=" + jrnNo + ", ordNo=" + ordNo + ", suppliNo=" + suppliNo + ", suppliNm="
                + suppliNm + ", barCode=" + barCode + ", prdNm=" + prdNm + ", spec=" + spec + ", price=" + price
                + ", totPrice=" + totPrice + ", unit=" + unit + ", isApart=" + isApart + ", whsleUnit=" + whsleUnit
                + ", whsleSpec=" + whsleSpec + ", cnt=" + cnt + ", wght=" + wght + ", totWght=" + totWght + ", tax="
                + tax + ", ordCnt=" + ordCnt + ", revwCnt=" + revwCnt + ", recevCnt=" + recevCnt + ", surplusCnt="
                + surplusCnt + ", isLdist=" + isLdist + ", creDt=" + creDt + ", creTm=" + creTm + "]";
    }
}
