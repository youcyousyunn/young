package com.ycs.coobo.shop.domain.po;

import com.ycs.base.domain.po.BasePo;

/**
 * 门店订单明细对象PO
 * @author youcyousyunn
 * @date 2018年5月19日
 */
public class ShopOrdrDetailPo extends BasePo {

	//流水号
    private String jrnNo;
    //订单号
    private String ordNo;
    //国际码
    private String barCode;
    //商品名
    private String prdNm;
    //供应商号
    private String suppliNo;
    //供应商名称
    private String suppliNm;
    //门店退货备注
    private String remk;
    //是否存在更多供应商
    private String moreSuppli;
    //商品class Id
    private Integer classId;
    //商品等级
    private Integer classLvl;
    //商品父级 Id
    private Integer classFId;
    //订单类别
    private String ordCls;
    //是否可拆
    private String isApart;
    //批发单位
    private String whsleUnit;
    //批发规格
    private String whsleSpec;
    //上周销量
    private String sellNum;
    //当前销量
    private String nowSellNum;
    //当前库存
    private String nowStockNum;
    //异常标识
    private String replTyp;
    //规格
    private String spec;
    //单价
    private String price;
    //总价
    private String totPice;
    //入库总价
    private String totStockPice;
    //重量
    private String wght;
    //总重
    private String totWght;
    //单位
    private String unit;
    //零售价格
    private String shopPrice;
    //零售单位
    private String shopUnit;
    //规格基数
    private String cnt;
    //税率
    private String tax;
    //下单数量
    private String ordCnt;
    //审核数量
    private String revwCnt;
    //收货数量
    private String recevCnt;
    //已配货数量
    private String distCnt;
    //剩余数量
    private String surplusCnt;
    //已配送数
    private String deliverCnt;
    //库存数
    private String stockCnt;
    
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
	 * @return the remk
	 */
	public String getRemk() {
		return remk;
	}
	/**
	 * @param remk the remk to set
	 */
	public void setRemk(String remk) {
		this.remk = remk;
	}
	/**
	 * @return the moreSuppli
	 */
	public String getMoreSuppli() {
		return moreSuppli;
	}
	/**
	 * @param moreSuppli the moreSuppli to set
	 */
	public void setMoreSuppli(String moreSuppli) {
		this.moreSuppli = moreSuppli;
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
	 * @return the classLvl
	 */
	public Integer getClassLvl() {
		return classLvl;
	}
	/**
	 * @param classLvl the classLvl to set
	 */
	public void setClassLvl(Integer classLvl) {
		this.classLvl = classLvl;
	}
	/**
	 * @return the classFId
	 */
	public Integer getClassFId() {
		return classFId;
	}
	/**
	 * @param classFId the classFId to set
	 */
	public void setClassFId(Integer classFId) {
		this.classFId = classFId;
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
	 * @return the sellNum
	 */
	public String getSellNum() {
		return sellNum;
	}
	/**
	 * @param sellNum the sellNum to set
	 */
	public void setSellNum(String sellNum) {
		this.sellNum = sellNum;
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
	 * @return the replTyp
	 */
	public String getReplTyp() {
		return replTyp;
	}
	/**
	 * @param replTyp the replTyp to set
	 */
	public void setReplTyp(String replTyp) {
		this.replTyp = replTyp;
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
	 * @return the totPice
	 */
	public String getTotPice() {
		return totPice;
	}
	/**
	 * @param totPice the totPice to set
	 */
	public void setTotPice(String totPice) {
		this.totPice = totPice;
	}
	/**
	 * @return the totStockPice
	 */
	public String getTotStockPice() {
		return totStockPice;
	}
	/**
	 * @param totStockPice the totStockPice to set
	 */
	public void setTotStockPice(String totStockPice) {
		this.totStockPice = totStockPice;
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
	 * @return the distCnt
	 */
	public String getDistCnt() {
		return distCnt;
	}
	/**
	 * @param distCnt the distCnt to set
	 */
	public void setDistCnt(String distCnt) {
		this.distCnt = distCnt;
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
	 * @return the deliverCnt
	 */
	public String getDeliverCnt() {
		return deliverCnt;
	}
	/**
	 * @param deliverCnt the deliverCnt to set
	 */
	public void setDeliverCnt(String deliverCnt) {
		this.deliverCnt = deliverCnt;
	}
	/**
	 * @return the stockCnt
	 */
	public String getStockCnt() {
		return stockCnt;
	}
	/**
	 * @param stockCnt the stockCnt to set
	 */
	public void setStockCnt(String stockCnt) {
		this.stockCnt = stockCnt;
	}
    
    
    

}
