package com.ycs.coobo.coo.domain.po;

import com.ycs.base.domain.po.BasePo;

/**
 * 批发价格PO
 * @author youcyousyunn
 * @date 2018年6月11日
 */
public class WhslePricePo extends BasePo {
    // 国际码
    private String barCode;
    // 商品名称
    private String prdNm;
    // 省份
    private String prov;
    // 城市
    private String city;
    // 区县
    private String district;
    // 批发类型
    private String whsleTyp;
    // 批发价格
    private String whslePrice;
    // 波动次数
    private String fluctNum;
    // 描述
    private String whsleDesc;
    // 批发重量
    private String wght;

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
     * @return the whsleTyp
     */
    public String getWhsleTyp() {
        return whsleTyp;
    }

    /**
     * @param whsleTyp the whsleTyp to set
     */
    public void setWhsleTyp(String whsleTyp) {
        this.whsleTyp = whsleTyp;
    }

    /**
     * @return the whslePrice
     */
    public String getWhslePrice() {
        return whslePrice;
    }

    /**
     * @param whslePrice the whslePrice to set
     */
    public void setWhslePrice(String whslePrice) {
        this.whslePrice = whslePrice;
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
     * @return the whsleDesc
     */
    public String getWhsleDesc() {
        return whsleDesc;
    }

    /**
     * @param whsleDesc the whsleDesc to set
     */
    public void setWhsleDesc(String whsleDesc) {
        this.whsleDesc = whsleDesc;
    }

    public String getWght() {
        return wght;
    }

    public void setWght(String wght) {
        this.wght = wght;
    }

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "WhslePricePo [barCode=" + barCode + ", prdNm=" + prdNm + ", prov=" + prov + ", city=" + city
				+ ", district=" + district + ", whsleTyp=" + whsleTyp + ", whslePrice=" + whslePrice + ", fluctNum="
				+ fluctNum + ", whsleDesc=" + whsleDesc + ", wght=" + wght + "]";
	}

    
}
