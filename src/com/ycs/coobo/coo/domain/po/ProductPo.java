package com.ycs.coobo.coo.domain.po;

import com.ycs.base.domain.po.BasePo;

/**
 * 产品PO
 * @author youcyousyunn
 * @date 2018年5月15日
 */
public class ProductPo extends BasePo {

	/**
     * 条码
     */
    private String barCode;

    /**
     * 分类Id
     */
    private Integer classId;

    /**
     * 产品名称
     */
    private String prdNm;

    /**
     * 产品图片
     */
    private String prdImg;

    /**
     * 展示用图片地址
     */
    private String showPrdImg;

    /**
     * 品牌编号
     */
    private Integer brandNo;

    /**
     * 品牌名称
     */
    private String brandNm;

    /**
     * 产品代码
     */
    private String prdCode;

    /**
     * 保质期天数
     */
    private Integer expDay;

    /**
     * 产品描述
     */
    private String prdDesc;

    /**
     * 产品状态
     */
    private String prdSts;
    
    /**
     * 供应商号
     */
    private String suppliNo;
    
    /**
     * 供应商名称
     */
    private String suppliNm;

    /**
     * 采购规格
     */
    private String sourcSpec;

    /**
     * 采购基数
     */
    private String sourcCnt;

    /**
     * 采购长宽高
     */
    private String sourcWhd;
    /**
     * 建议采购价
     */
    private String sourcPrice;

    /**
     * 采购单位
     */
    private String sourcUnit;

    /**
     * 采购重量
     */
    private String sourcWght;
    
    /**
     * 批发价格类型
     */
    private String whlseType;

    /**
     * 批发规格
     */
    private String whsleSpec;

    /**
     * 批发单位
     */
    private String whsleUnit;

    /**
     * 批发重量
     */
    private String whsleWght;

    /**
     * 批发基数
     */
    private String whsleCnt;

    /**
     * 批发长宽高
     */
    private String whsleWhd;

    /**
     * 建议批发价
     */
    private String whslePrice;

    /**
     * 零售规格
     */
    private String posSpec;

    /**
     * 零售单位
     */
    private String posUnit;

    /**
     * 零售重量
     */
    private String posWght;

    /**
     * 零售基数
     */
    private String posCnt;

    /**
     * 零售长宽高
     */
    private String posWhd;

    /**
     * 物流规格
     */
    private String logisSpec;

    /**
     * 物流单位
     */
    private String logisUnit;

    /**
     * 物流重量
     */
    private String logisWght;

    /**
     * 物流基数
     */
    private String logisCnt;

    /**
     * 物流
     */
    private String logisWhd;

    /**
     * 建议零售价
     */
    private String retailPrice;

    /**
     * 是否可拆分
     */
    private String isApart;

    /**
     * 税率
     */
    private String taxes;

    /**
     * 当期库存
     */
    private String nowStock;

    /**
     * 零售价格
     */
    private String shopPrice;

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
     * @return the classId
     */
    public Integer getClassId() {
        return classId;
    }

    /**
     * @param classId the classId to set
     */
    public void setClassId(Integer classId) {
        this.classId = classId;
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
     * @return the prdImg
     */
    public String getPrdImg() {
        return prdImg;
    }

    /**
     * @param prdImg the prdImg to set
     */
    public void setPrdImg(String prdImg) {
        this.prdImg = prdImg;
    }

    /**
     * @return the showPrdImg
     */
    public String getShowPrdImg() {
        return showPrdImg;
    }

    /**
     * @param showPrdImg the showPrdImg to set
     */
    public void setShowPrdImg(String showPrdImg) {
        this.showPrdImg = showPrdImg;
    }

    /**
     * @return the brandNo
     */
    public Integer getBrandNo() {
        return brandNo;
    }

    /**
     * @param brandNo the brandNo to set
     */
    public void setBrandNo(Integer brandNo) {
        this.brandNo = brandNo;
    }

    /**
     * @return the brandNm
     */
    public String getBrandNm() {
        return brandNm;
    }

    /**
     * @param brandNm the brandNm to set
     */
    public void setBrandNm(String brandNm) {
        this.brandNm = brandNm;
    }

    /**
     * @return the prdCode
     */
    public String getPrdCode() {
        return prdCode;
    }

    /**
     * @param prdCode the prdCode to set
     */
    public void setPrdCode(String prdCode) {
        this.prdCode = prdCode;
    }

    /**
     * @return the expDay
     */
    public Integer getExpDay() {
        return expDay;
    }

    /**
     * @param expDay the expDay to set
     */
    public void setExpDay(Integer expDay) {
        this.expDay = expDay;
    }

    /**
     * @return the prdDesc
     */
    public String getPrdDesc() {
        return prdDesc;
    }

    /**
     * @param prdDesc the prdDesc to set
     */
    public void setPrdDesc(String prdDesc) {
        this.prdDesc = prdDesc;
    }

    /**
     * @return the prdSts
     */
    public String getPrdSts() {
        return prdSts;
    }

    /**
     * @param prdSts the prdSts to set
     */
    public void setPrdSts(String prdSts) {
        this.prdSts = prdSts;
    }

    public String getSuppliNo() {
		return suppliNo;
	}

	public void setSuppliNo(String suppliNo) {
		this.suppliNo = suppliNo;
	}

	public String getSuppliNm() {
		return suppliNm;
	}

	public void setSuppliNm(String suppliNm) {
		this.suppliNm = suppliNm;
	}

	public String getWhlseType() {
		return whlseType;
	}

	public void setWhlseType(String whlseType) {
		this.whlseType = whlseType;
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
     * @return the sourcWhd
     */
    public String getSourcWhd() {
        return sourcWhd;
    }

    /**
     * @param sourcWhd the sourcWhd to set
     */
    public void setSourcWhd(String sourcWhd) {
        this.sourcWhd = sourcWhd;
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
     * @return the sourcWght
     */
    public String getSourcWght() {
        return sourcWght;
    }

    /**
     * @param sourcWght the sourcWght to set
     */
    public void setSourcWght(String sourcWght) {
        this.sourcWght = sourcWght;
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
     * @return the whsleWght
     */
    public String getWhsleWght() {
        return whsleWght;
    }

    /**
     * @param whsleWght the whsleWght to set
     */
    public void setWhsleWght(String whsleWght) {
        this.whsleWght = whsleWght;
    }

    /**
     * @return the whsleCnt
     */
    public String getWhsleCnt() {
        return whsleCnt;
    }

    /**
     * @param whsleCnt the whsleCnt to set
     */
    public void setWhsleCnt(String whsleCnt) {
        this.whsleCnt = whsleCnt;
    }

    /**
     * @return the whsleWhd
     */
    public String getWhsleWhd() {
        return whsleWhd;
    }

    /**
     * @param whsleWhd the whsleWhd to set
     */
    public void setWhsleWhd(String whsleWhd) {
        this.whsleWhd = whsleWhd;
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
     * @return the posSpec
     */
    public String getPosSpec() {
        return posSpec;
    }

    /**
     * @param posSpec the posSpec to set
     */
    public void setPosSpec(String posSpec) {
        this.posSpec = posSpec;
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
     * @return the posWght
     */
    public String getPosWght() {
        return posWght;
    }

    /**
     * @param posWght the posWght to set
     */
    public void setPosWght(String posWght) {
        this.posWght = posWght;
    }

    /**
     * @return the posCnt
     */
    public String getPosCnt() {
        return posCnt;
    }

    /**
     * @param posCnt the posCnt to set
     */
    public void setPosCnt(String posCnt) {
        this.posCnt = posCnt;
    }

    /**
     * @return the posWhd
     */
    public String getPosWhd() {
        return posWhd;
    }

    /**
     * @param posWhd the posWhd to set
     */
    public void setPosWhd(String posWhd) {
        this.posWhd = posWhd;
    }

    /**
     * @return the logisSpec
     */
    public String getLogisSpec() {
        return logisSpec;
    }

    /**
     * @param logisSpec the logisSpec to set
     */
    public void setLogisSpec(String logisSpec) {
        this.logisSpec = logisSpec;
    }

    /**
     * @return the logisUnit
     */
    public String getLogisUnit() {
        return logisUnit;
    }

    /**
     * @param logisUnit the logisUnit to set
     */
    public void setLogisUnit(String logisUnit) {
        this.logisUnit = logisUnit;
    }

    /**
     * @return the logisWght
     */
    public String getLogisWght() {
        return logisWght;
    }

    /**
     * @param logisWght the logisWght to set
     */
    public void setLogisWght(String logisWght) {
        this.logisWght = logisWght;
    }

    /**
     * @return the logisCnt
     */
    public String getLogisCnt() {
        return logisCnt;
    }

    /**
     * @param logisCnt the logisCnt to set
     */
    public void setLogisCnt(String logisCnt) {
        this.logisCnt = logisCnt;
    }

    /**
     * @return the logisWhd
     */
    public String getLogisWhd() {
        return logisWhd;
    }

    /**
     * @param logisWhd the logisWhd to set
     */
    public void setLogisWhd(String logisWhd) {
        this.logisWhd = logisWhd;
    }

    /**
     * @return the retailPrice
     */
    public String getRetailPrice() {
        return retailPrice;
    }

    /**
     * @param retailPrice the retailPrice to set
     */
    public void setRetailPrice(String retailPrice) {
        this.retailPrice = retailPrice;
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
     * @return the taxes
     */
    public String getTaxes() {
        return taxes;
    }

    /**
     * @param taxes the taxes to set
     */
    public void setTaxes(String taxes) {
        this.taxes = taxes;
    }

    /**
     * @return the nowStock
     */
    public String getNowStock() {
        return nowStock;
    }

    /**
     * @param nowStock the nowStock to set
     */
    public void setNowStock(String nowStock) {
        this.nowStock = nowStock;
    }

    /**
     * @return the shopPrice
     */
    public String getShopPrice() {
        return shopPrice;
    }

    /**
     * @param shopPrice the shopPrice to set
     */
    public void setShopPrice(String shopPrice) {
        this.shopPrice = shopPrice;
    }
    
    
}
