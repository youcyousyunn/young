package com.ycs.coobo.stoh.domain.po;

import com.ycs.base.domain.po.BasePo;

/**
 * @description 仓库订单对象PO
 * @author youcyousyunn
 * @date 2018年11月11日
 */
public class StoHoseOrdrPo extends BasePo {
    //订单号
    private String ordNo;
    //订单渠道
    private String ordCnl;
    //订单类型
    private String ordTyp;
    //订单分类
    private String ordCls;
    //仓库号
    private String stohNo;
    //仓库名称
    private String stohNm;
    //省份
    private String prov;
    //城市
    private String city;
    //区县
    private String district;
    //订单状态
    private String ordSts;
    //是否可直配
    private String isLdist;
    //门店订单号，当为直配的时候有值
    private String shopOrdNo;
    //订单日期
    private String ordDt;
    //订单时间
    private String ordTm;
    //订单有效天数
    private String effDay;
    //失效日期
    private String expDt;
    //失效时间
    private String expTm;
    //订单SKU数
    private String skuCnt;
    //满足SKU数
    private String satisSkuCnt;
    //满足SKU占比
    private String satisSkuPct;
    //满足商品占比
    private String satisPrdPct;
    //订单金额
    private String ordAmt;
    //税率金额
    private String taxAmt;
    //申请人
    private String usrNo;
    //申请人姓名
    private String usrNm;
    //订单描述
    private String ordDesc;
    //修改日期
    private String updDt;
    //修改时间
    private String updTm;
    
    /**
     * 调拨仓库名称
     */
    private String excStohNm;
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
    /**
     * @return the ordCnl
     */
    public String getOrdCnl() {
        return ordCnl;
    }
    /**
     * @param ordCnl the ordCnl to set
     */
    public void setOrdCnl(String ordCnl) {
        this.ordCnl = ordCnl;
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
     * @return the stohNm
     */
    public String getStohNm() {
        return stohNm;
    }
    /**
     * @param stohNm the stohNm to set
     */
    public void setStohNm(String stohNm) {
        this.stohNm = stohNm;
    }
    /**
     * @return the prov
     */
    public String getProv() {
        return prov;
    }
    /**
     * @param prov the prov to set
     */
    public void setProv(String prov) {
        this.prov = prov;
    }
    /**
     * @return the city
     */
    public String getCity() {
        return city;
    }
    /**
     * @param city the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }
    /**
     * @return the district
     */
    public String getDistrict() {
        return district;
    }
    /**
     * @param district the district to set
     */
    public void setDistrict(String district) {
        this.district = district;
    }
    /**
     * @return the ordSts
     */
    public String getOrdSts() {
        return ordSts;
    }
    /**
     * @param ordSts the ordSts to set
     */
    public void setOrdSts(String ordSts) {
        this.ordSts = ordSts;
    }
    /**
     * @return the isLdist
     */
    public String getIsLdist() {
        return isLdist;
    }
    /**
     * @return the shopOrdNo
     */
    public String getShopOrdNo() {
        return shopOrdNo;
    }
    /**
     * @param shopOrdNo the shopOrdNo to set
     */
    public void setShopOrdNo(String shopOrdNo) {
        this.shopOrdNo = shopOrdNo;
    }
    /**
     * @param isLdist the isLdist to set
     */
    public void setIsLdist(String isLdist) {
        this.isLdist = isLdist;
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
     * @return the ordTm
     */
    public String getOrdTm() {
        return ordTm;
    }
    /**
     * @param ordTm the ordTm to set
     */
    public void setOrdTm(String ordTm) {
        this.ordTm = ordTm;
    }
    /**
     * @return the effDay
     */
    public String getEffDay() {
        return effDay;
    }
    /**
     * @param effDay the effDay to set
     */
    public void setEffDay(String effDay) {
        this.effDay = effDay;
    }
    /**
     * @return the expDt
     */
    public String getExpDt() {
        return expDt;
    }
    /**
     * @param expDt the expDt to set
     */
    public void setExpDt(String expDt) {
        this.expDt = expDt;
    }
    /**
     * @return the expTm
     */
    public String getExpTm() {
        return expTm;
    }
    /**
     * @param expTm the expTm to set
     */
    public void setExpTm(String expTm) {
        this.expTm = expTm;
    }
    /**
     * @return the skuCnt
     */
    public String getSkuCnt() {
        return skuCnt;
    }
    /**
     * @param skuCnt the skuCnt to set
     */
    public void setSkuCnt(String skuCnt) {
        this.skuCnt = skuCnt;
    }
    /**
     * @return the satisSkuCnt
     */
    public String getSatisSkuCnt() {
        return satisSkuCnt;
    }
    /**
     * @param satisSkuCnt the satisSkuCnt to set
     */
    public void setSatisSkuCnt(String satisSkuCnt) {
        this.satisSkuCnt = satisSkuCnt;
    }
    /**
     * @return the satisSkuPct
     */
    public String getSatisSkuPct() {
        return satisSkuPct;
    }
    /**
     * @param satisSkuPct the satisSkuPct to set
     */
    public void setSatisSkuPct(String satisSkuPct) {
        this.satisSkuPct = satisSkuPct;
    }
    /**
     * @return the satisPrdPct
     */
    public String getSatisPrdPct() {
        return satisPrdPct;
    }
    /**
     * @param satisPrdPct the satisPrdPct to set
     */
    public void setSatisPrdPct(String satisPrdPct) {
        this.satisPrdPct = satisPrdPct;
    }
    /**
     * @return the ordAmt
     */
    public String getOrdAmt() {
        return ordAmt;
    }
    /**
     * @param ordAmt the ordAmt to set
     */
    public void setOrdAmt(String ordAmt) {
        this.ordAmt = ordAmt;
    }
    /**
     * @return the taxAmt
     */
    public String getTaxAmt() {
        return taxAmt;
    }
    /**
     * @param taxAmt the taxAmt to set
     */
    public void setTaxAmt(String taxAmt) {
        this.taxAmt = taxAmt;
    }
    /**
     * @return the usrNo
     */
    public String getUsrNo() {
        return usrNo;
    }
    /**
     * @param usrNo the usrNo to set
     */
    public void setUsrNo(String usrNo) {
        this.usrNo = usrNo;
    }
    /**
     * @return the usrNm
     */
    public String getUsrNm() {
        return usrNm;
    }
    /**
     * @param usrNm the usrNm to set
     */
    public void setUsrNm(String usrNm) {
        this.usrNm = usrNm;
    }
    /**
     * @return the ordDesc
     */
    public String getOrdDesc() {
        return ordDesc;
    }
    /**
     * @param ordDesc the ordDesc to set
     */
    public void setOrdDesc(String ordDesc) {
        this.ordDesc = ordDesc;
    }
    /**
     * @return the updDt
     */
    public String getUpdDt() {
        return updDt;
    }
    /**
     * @param updDt the updDt to set
     */
    public void setUpdDt(String updDt) {
        this.updDt = updDt;
    }
    /**
     * @return the updTm
     */
    public String getUpdTm() {
        return updTm;
    }
    /**
     * @param updTm the updTm to set
     */
    public void setUpdTm(String updTm) {
        this.updTm = updTm;
    }
    public String getExcStohNm() {
        return excStohNm;
    }
    public void setExcStohNm(String excStohNm) {
        this.excStohNm = excStohNm;
    }
    /* （non Javadoc）
     * Title: toString<br/>
     * <p>Description: <br/>
     * @return
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "StoreHouseOrdrPo [ordNo=" + ordNo + ", ordCnl=" + ordCnl + ", ordTyp=" + ordTyp + ", ordCls=" + ordCls
                + ", stohNo=" + stohNo + ", stohNm=" + stohNm + ", prov=" + prov + ", city=" + city + ", district="
                + district + ", ordSts=" + ordSts + ", isLdist=" + isLdist + ", shopOrdNo=" + shopOrdNo + ", ordDt="
                + ordDt + ", ordTm=" + ordTm + ", effDay=" + effDay + ", expDt=" + expDt + ", expTm=" + expTm
                + ", skuCnt=" + skuCnt + ", satisSkuCnt=" + satisSkuCnt + ", satisSkuPct=" + satisSkuPct
                + ", satisPrdPct=" + satisPrdPct + ", ordAmt=" + ordAmt + ", taxAmt=" + taxAmt + ", usrNo=" + usrNo
                + ", usrNm=" + usrNm + ", ordDesc=" + ordDesc + ", excStohNm=" + excStohNm + ", updDt=" + updDt
                + ", updTm=" + updTm + "]";
    }
}
