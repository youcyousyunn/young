package com.ycs.sys.domain.po;

import com.ycs.base.domain.po.BasePo;

/**
 * 省份城市PO
 * @author youcyousyunn
 * @date 2018年5月15日
 */
public class ProvCityPo extends BasePo {
	//地市类型
    private String areaTyp;
    //编码
    private String areaId;
    //名称
    private String areaNm;
    //父编码
    private String fAreaId;
    //上级城市名称
    private String sortId;
    /**
     * @return the areaTyp
     */
    public String getAreaTyp() {
        return areaTyp;
    }
    /**
     * @param areaTyp the areaTyp to set
     */
    public void setAreaTyp(String areaTyp) {
        this.areaTyp = areaTyp;
    }
    /**
     * @return the areaId
     */
    public String getAreaId() {
        return areaId;
    }
    /**
     * @param areaId the areaId to set
     */
    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }
    /**
     * @return the areaNm
     */
    public String getAreaNm() {
        return areaNm;
    }
    /**
     * @param areaNm the areaNm to set
     */
    public void setAreaNm(String areaNm) {
        this.areaNm = areaNm;
    }
    /**
     * @return the fAreaId
     */
    public String getfAreaId() {
        return fAreaId;
    }
    /**
     * @param fAreaId the fAreaId to set
     */
    public void setfAreaId(String fAreaId) {
        this.fAreaId = fAreaId;
    }
    /**
     * @return the sortId
     */
    public String getSortId() {
        return sortId;
    }
    /**
     * @param sortId the sortId to set
     */
    public void setSortId(String sortId) {
        this.sortId = sortId;
    }
    
	@Override
	public String toString() {
		return "ProvCityPo [areaTyp=" + areaTyp + ", areaId=" + areaId + ", areaNm=" + areaNm + ", fAreaId=" + fAreaId
				+ ", sortId=" + sortId + "]";
	}

    
}
