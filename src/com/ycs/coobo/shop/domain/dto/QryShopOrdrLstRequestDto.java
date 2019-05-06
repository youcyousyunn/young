package com.ycs.coobo.shop.domain.dto;

import com.ycs.base.domain.dto.BaseRequestDto;

/**
 * 查询门店订单列表数据请求DTO
 * @author youcyousyunn
 * @date 2018年5月19日
 */
public class QryShopOrdrLstRequestDto extends BaseRequestDto {

	//订单号
    private String ordNo;
    //省份
    private String prov;
    //城市
    private String city;
    //门店号
    private String shopNo;
    //门店名
    private String shopNm;
    //线路号
    private String lineNo;
    //订单日期
    private String ordDt;
    //开始日期
    private String startDt;
    //结束日期
    private String endDt;
    //出入库类型
    private String stkTyp;
    //送货日期
    private String sendDt;
    //订单状态
    private String ordSts;
    //订单类型
    private String ordTyp;
    //订单分类
    private String ordCls;
    //仓库号
    private String stohNo;
    //排序列
    private String sort;
    //排序方式(DESC、ASC)
    private String sortName;
    //当前页
    private Integer currentPage;
    //页大小
    private Integer pageSize;
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
	 * @return the startDt
	 */
	public String getStartDt() {
		return startDt;
	}
	/**
	 * @param startDt the startDt to set
	 */
	public void setStartDt(String startDt) {
		this.startDt = startDt;
	}
	/**
	 * @return the endDt
	 */
	public String getEndDt() {
		return endDt;
	}
	/**
	 * @param endDt the endDt to set
	 */
	public void setEndDt(String endDt) {
		this.endDt = endDt;
	}
	/**
	 * @return the stkTyp
	 */
	public String getStkTyp() {
		return stkTyp;
	}
	/**
	 * @param stkTyp the stkTyp to set
	 */
	public void setStkTyp(String stkTyp) {
		this.stkTyp = stkTyp;
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
	 * @return the sort
	 */
	public String getSort() {
		return sort;
	}
	/**
	 * @param sort the sort to set
	 */
	public void setSort(String sort) {
		this.sort = sort;
	}
	/**
	 * @return the sortName
	 */
	public String getSortName() {
		return sortName;
	}
	/**
	 * @param sortName the sortName to set
	 */
	public void setSortName(String sortName) {
		this.sortName = sortName;
	}
	/**
	 * @return the currentPage
	 */
	public Integer getCurrentPage() {
		return currentPage;
	}
	/**
	 * @param currentPage the currentPage to set
	 */
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	/**
	 * @return the pageSize
	 */
	public Integer getPageSize() {
		return pageSize;
	}
	/**
	 * @param pageSize the pageSize to set
	 */
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
    
	/**
	 * 接口报文检查
	 * @param
	 * @return boolean
	 */
	public boolean checkRequestDto() {
        if (null == pageSize) {
            return false;
        }
        if (null == currentPage) {
            return false;
        }
        return true;
    }
    

}
