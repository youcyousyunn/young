package com.ycs.coobo.shop.domain.po;

import java.util.List;

import com.ycs.base.domain.po.BasePo;

/**
 * 门店订单PO
 * @author youcyousyunn
 * @date 2018年5月19日
 */
public class ShopOrdrPo extends ShopOrdrDetailPo {
	//订单号
    private String ordNo;
    //订单渠道
    private String ordCnl;
    //订单类型
    private String ordTyp;
    //订单分类
    private String ordCls;
    //门店号
    private String shopNo;
    //门店名称
    private String shopNm;
    //仓库号
    private String stohNo;
    //仓库名称
    private String stohNm;
    //线路号
    private String lineNo;
    //线路名称
    private String lineNm;
    //省份
    private String prov;
    //城市
    private String city;
    //区县
    private String district;
    //订单状态
    private String ordSts;
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
    //送货日期
    private String sendDt;
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
    //订单入库金额
    private String ordStockAmt;
    //税率金额
    private String taxAmt;
    //申请人内部用户号
    private String usrNo;
    //申请人姓名
    private String usrNm;
    //订单描述
    private String ordDesc;
    //订单明细列表
    private List<ShopOrdrDetailPo> shopOrdrDetailLst;
    // 配货次数
    private String distNum;
    //下单数量
    private String ordCnt;
    //审核数量
    private String revwCnt;
    
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
	 * @return the shopNm
	 */
	public String getShopNm() {
		return shopNm;
	}
	/**
	 * @param shopNm the shopNm to set
	 */
	public void setShopNm(String shopNm) {
		this.shopNm = shopNm;
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
	 * @return the lineNo
	 */
	public String getLineNo() {
		return lineNo;
	}
	/**
	 * @param lineNo the lineNo to set
	 */
	public void setLineNo(String lineNo) {
		this.lineNo = lineNo;
	}
	/**
	 * @return the lineNm
	 */
	public String getLineNm() {
		return lineNm;
	}
	/**
	 * @param lineNm the lineNm to set
	 */
	public void setLineNm(String lineNm) {
		this.lineNm = lineNm;
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
	 * @return the sendDt
	 */
	public String getSendDt() {
		return sendDt;
	}
	/**
	 * @param sendDt the sendDt to set
	 */
	public void setSendDt(String sendDt) {
		this.sendDt = sendDt;
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
	 * @return the ordStockAmt
	 */
	public String getOrdStockAmt() {
		return ordStockAmt;
	}
	/**
	 * @param ordStockAmt the ordStockAmt to set
	 */
	public void setOrdStockAmt(String ordStockAmt) {
		this.ordStockAmt = ordStockAmt;
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
	 * @return the shopOrdrDetailLst
	 */
	public List<ShopOrdrDetailPo> getShopOrdrDetailLst() {
		return shopOrdrDetailLst;
	}
	/**
	 * @param shopOrdrDetailLst the shopOrdrDetailLst to set
	 */
	public void setShopOrdrDetailLst(List<ShopOrdrDetailPo> shopOrdrDetailLst) {
		this.shopOrdrDetailLst = shopOrdrDetailLst;
	}
	/**
	 * @return the distNum
	 */
	public String getDistNum() {
		return distNum;
	}
	/**
	 * @param distNum the distNum to set
	 */
	public void setDistNum(String distNum) {
		this.distNum = distNum;
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
    
    

}
