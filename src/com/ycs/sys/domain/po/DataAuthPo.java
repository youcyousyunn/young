package com.ycs.sys.domain.po;

import java.util.List;

import com.ycs.base.domain.po.BasePo;

/**
 * 数据权限PO对象
 * @author youcyousyunn
 * @date 2018年5月2日
 */
public class DataAuthPo extends BasePo {
	private String usrNo;
    private String usrNm;
    private String ruleSts;
    private String prov;
    private String city;
    private List<String> shop;
    private List<String> stoh;
    private List<String> suppli;
    
	public String getUsrNo() {
		return usrNo;
	}
	public void setUsrNo(String usrNo) {
		this.usrNo = usrNo;
	}
	public String getUsrNm() {
		return usrNm;
	}
	public void setUsrNm(String usrNm) {
		this.usrNm = usrNm;
	}
	public String getRuleSts() {
		return ruleSts;
	}
	public void setRuleSts(String ruleSts) {
		this.ruleSts = ruleSts;
	}
	public String getProv() {
		return prov;
	}
	public void setProv(String prov) {
		this.prov = prov;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public List<String> getShop() {
		return shop;
	}
	public void setShop(List<String> shop) {
		this.shop = shop;
	}
	public List<String> getStoh() {
		return stoh;
	}
	public void setStoh(List<String> stoh) {
		this.stoh = stoh;
	}
	public List<String> getSuppli() {
		return suppli;
	}
	public void setSuppli(List<String> suppli) {
		this.suppli = suppli;
	}
	
	
	@Override
	public String toString() {
		return "DataAuthPo [usrNo=" + usrNo + ", usrNm=" + usrNm + ", ruleSts=" + ruleSts + ", prov=" + prov + ", city="
				+ city + ", shop=" + shop + ", stoh=" + stoh + ", suppli=" + suppli + "]";
	}
    
	

}
