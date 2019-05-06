package com.ycs.coobo.coo.domain.po;

import com.ycs.base.domain.po.BasePo;

/**
 * 仓库产品PO
 * @author youcyousyunn
 * @date 2018年5月24日
 */
public class StohPrdPo extends BasePo {
    //国际码
    private String barCode;
    //商品名称
    private String prdNm;
    //品牌名称
    private String brandNm;
    //产品状态
    private String prdSts;
    //是否可拆
    private String isApart;
    //上周销量
    private String lastWeekSellNum;
    //当前销量
    private String nowSellNum;
    //批发价格
    private String whslePrice;
    //批发规格
    private String whsleSpec;
    //批发单位
    private String whsleUnit;
    //零售价格
    private String shopPrice;
    //零售单位
    private String shopUnit;
    //批发基数
    private String whsleCnt;
    //税率
    private String tax;
    //订单类别
    private String ordCls;
    //商品所在tree节点路径
    private String clsPath;
    //下单数
    private String ordCnt;
    //当前库存
    private String nowStockNum;
    //暂存库存
    private String cacheNum;
    //商品分类ID
    private String classId;
    
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
	 * @return the lastWeekSellNum
	 */
	public String getLastWeekSellNum() {
		return lastWeekSellNum;
	}
	/**
	 * @param lastWeekSellNum the lastWeekSellNum to set
	 */
	public void setLastWeekSellNum(String lastWeekSellNum) {
		this.lastWeekSellNum = lastWeekSellNum;
	}
	/**
	 * @return the nowSellNum
	 */
	public String getNowSellNum() {
		return nowSellNum;
	}
	/**
	 * @param nowSellNum the nowSellNum to set
	 */
	public void setNowSellNum(String nowSellNum) {
		this.nowSellNum = nowSellNum;
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
	/**
	 * @return the shopUnit
	 */
	public String getShopUnit() {
		return shopUnit;
	}
	/**
	 * @param shopUnit the shopUnit to set
	 */
	public void setShopUnit(String shopUnit) {
		this.shopUnit = shopUnit;
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
	 * @return the clsPath
	 */
	public String getClsPath() {
		return clsPath;
	}
	/**
	 * @param clsPath the clsPath to set
	 */
	public void setClsPath(String clsPath) {
		this.clsPath = clsPath;
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
	 * @return the nowStockNum
	 */
	public String getNowStockNum() {
		return nowStockNum;
	}
	/**
	 * @param nowStockNum the nowStockNum to set
	 */
	public void setNowStockNum(String nowStockNum) {
		this.nowStockNum = nowStockNum;
	}
	/**
	 * @return the cacheNum
	 */
	public String getCacheNum() {
		return cacheNum;
	}
	/**
	 * @param cacheNum the cacheNum to set
	 */
	public void setCacheNum(String cacheNum) {
		this.cacheNum = cacheNum;
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
    
    
}
