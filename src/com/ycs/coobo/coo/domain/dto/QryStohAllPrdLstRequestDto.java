package com.ycs.coobo.coo.domain.dto;

import org.apache.commons.lang.StringUtils;

import com.ycs.base.domain.dto.BaseRequestDto;


/**
 * 查询仓库可供产品信息请求DTO
 * @author youcyousyunn
 * @date 2018年5月24日
 */
public class QryStohAllPrdLstRequestDto extends BaseRequestDto {
    //省份
    private String prov;
    //城市
    private String city;
    //门店类型
    private String shopTyp;
    //门店号
    private String shopNo;
    //订单号
    private String ordNo;
    //查询请求
    private String search;
    //产品分类
    private String classId;
    //分类类型
    private String classTyp;
    
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
     * @return the shopTyp
     */
    public String getShopTyp() {
        return shopTyp;
    }
    /**
     * @param shopTyp the shopTyp to set
     */
    public void setShopTyp(String shopTyp) {
        this.shopTyp = shopTyp;
    }
    public String getOrdNo() {
		return ordNo;
	}
	public void setOrdNo(String ordNo) {
		this.ordNo = ordNo;
	}
	/**
	 * @return the search
	 */
	public String getSearch() {
		return search;
	}
	/**
	 * @param search the search to set
	 */
	public void setSearch(String search) {
		this.search = search;
	}
	/**
     * @return the shopNo
     */
    public String getShopNo() {
        return shopNo;
    }
    /**
     * @param shopNo the shopNo to set
     */
    public void setShopNo(String shopNo) {
        this.shopNo = shopNo;
    }
    /**
     * @return the classId
     */
    public String getClassId() {
        return classId;
    }
    /**
     * @param classId the classId to set
     */
    public void setClassId(String classId) {
        this.classId = classId;
    }
    /**
     * @return the classTyp
     */
    public String getClassTyp() {
        return classTyp;
    }
    /**
     * @param classTyp the classTyp to set
     */
    public void setClassTyp(String classTyp) {
        this.classTyp = classTyp;
    }
    
    /**
     * 接口报文检查
     * @return boolean
     */
    public boolean checkRequestDto() {
        if (null == prov || StringUtils.isBlank(prov)) {
            return false;
        }
        if (null == city || StringUtils.isBlank(city)) {
            return false;
        }
        if (null == shopTyp || StringUtils.isBlank(shopTyp)) {
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
        return "QryStohAllPrdLstRequestDto [prov=" + prov + ", city=" + city + ", shopTyp=" + shopTyp + ", shopNo="
                + shopNo + ", search=" + search + ", classId=" + classId + "]";
    }
    
}
