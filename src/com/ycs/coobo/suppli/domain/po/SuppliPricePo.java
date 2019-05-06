package com.ycs.coobo.suppli.domain.po;

import com.ycs.base.domain.po.BasePo;

/**
 * @description 商品采购定价信息对象PO
 * @author youcyousyunn
 * @date 2018年11月11日
 */
public class SuppliPricePo extends BasePo {
    //国际码
    private String barCode;
    //商品名称
    private String prdNm;
    //省份
    private String prov;
    //城市
    private String city;
    //区县
    private String district;
    //供应商号
    private String suppliNo;
    //供应商名称
    private String suppliNm;
    //是否默认供应商
    private String isDefault;
    //采购价格
    private String suppliPrice;
    //税率
    private String suppliTax;
    //波动次数
    private String fluctNum;
    //描述
    private String suppliDesc;
    //修改日期
    private String updDt;
    
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
     * @return the isDefault
     */
    public String getIsDefault() {
        return isDefault;
    }
    /**
     * @param isDefault the isDefault to set
     */
    public void setIsDefault(String isDefault) {
        this.isDefault = isDefault;
    }
    /**
     * @return the suppliPrice
     */
    public String getSuppliPrice() {
        return suppliPrice;
    }
    /**
     * @param suppliPrice the suppliPrice to set
     */
    public void setSuppliPrice(String suppliPrice) {
        this.suppliPrice = suppliPrice;
    }
    /**
     * @return the suppliTax
     */
    public String getSuppliTax() {
        return suppliTax;
    }
    /**
     * @param suppliTax the suppliTax to set
     */
    public void setSuppliTax(String suppliTax) {
        this.suppliTax = suppliTax;
    }
    /**
     * @return the fluctNum
     */
    public String getFluctNum() {
        return fluctNum;
    }
    /**
     * @param fluctNum the fluctNum to set
     */
    public void setFluctNum(String fluctNum) {
        this.fluctNum = fluctNum;
    }
    /**
     * @return the suppliDesc
     */
    public String getSuppliDesc() {
        return suppliDesc;
    }
    /**
     * @param suppliDesc the suppliDesc to set
     */
    public void setSuppliDesc(String suppliDesc) {
        this.suppliDesc = suppliDesc;
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
    /* （non Javadoc）
     * Title: toString<br/>
     * <p>Description: <br/>
     * @return
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "SuppliPricePo [barCode=" + barCode + ", prdNm=" + prdNm + ", prov=" + prov + ", city=" + city
                + ", district=" + district + ", suppliNo=" + suppliNo + ", suppliNm=" + suppliNm + ", isDefault="
                + isDefault + ", suppliPrice=" + suppliPrice + ", suppliTax=" + suppliTax + ", fluctNum=" + fluctNum
                + ", suppliDesc=" + suppliDesc + ", updDt=" + updDt + "]";
    }
}
