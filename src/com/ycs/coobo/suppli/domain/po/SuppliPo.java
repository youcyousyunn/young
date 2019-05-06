package com.ycs.coobo.suppli.domain.po;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ycs.base.domain.po.BasePo;

/**
 * 供应商对象PO
 * @author youcyousyunn
 * @date 2018年3月17日
 */
public class SuppliPo extends BasePo {
    //供应商ID
    private String suppliNo;
    //供应商名称
    private String suppliNm;
    //对接人姓名
    private String accessNm;
    //对接人职务
    private String accessPost;
    //对接人电话
    private String accessPhone;
    //省份
    private String prov;
    //城市
    private String city;
    //区县
    private String district;
    //街道
    private String street;
    //门牌号
    private String hoseNo;
    //经度
    private String lngUde;
    //纬度
    private String latUde;
    //楼盘
    private String building;
    //楼座
    private String balcony;
    //楼层
    private String floor;
    //房间号
    private String roomNo;
    //供应商配送方式
    private String shipType;
    //配送车辆数
    private int delivCar;
    //仓库面积
    private String acreage;
    //收款账户名
    private String chequesNm;
    //收款账号
    private String chequesNo;
    //送货签章样本
    private String signaTure;
    //供应商状态
    private String suppliSts;
    //配送提前期
    private String delivDt;
    //账期类型
    private String payType;
    //账期长度
    private String payLong;
    //供应商扣点
    private String points;
    //已结算金额
    private String isStateAmt;
    //待结算金额
    private String sdStateAmt;
    //期初金额
    private String beginAmt;
    //失效日期
    @JsonFormat(pattern="yyyy-MM-dd", timezone="GMT+8")
    private Date expDt;
    //供货门店号
    private List<String> shopLst;
    //供货门店
    private String shopShow;
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
	public String getAccessNm() {
		return accessNm;
	}
	public void setAccessNm(String accessNm) {
		this.accessNm = accessNm;
	}
	public String getAccessPost() {
		return accessPost;
	}
	public void setAccessPost(String accessPost) {
		this.accessPost = accessPost;
	}
	public String getAccessPhone() {
		return accessPhone;
	}
	public void setAccessPhone(String accessPhone) {
		this.accessPhone = accessPhone;
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
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getHoseNo() {
		return hoseNo;
	}
	public void setHoseNo(String hoseNo) {
		this.hoseNo = hoseNo;
	}
	public String getLngUde() {
		return lngUde;
	}
	public void setLngUde(String lngUde) {
		this.lngUde = lngUde;
	}
	public String getLatUde() {
		return latUde;
	}
	public void setLatUde(String latUde) {
		this.latUde = latUde;
	}
	public String getBuilding() {
		return building;
	}
	public void setBuilding(String building) {
		this.building = building;
	}
	public String getBalcony() {
		return balcony;
	}
	public void setBalcony(String balcony) {
		this.balcony = balcony;
	}
	public String getFloor() {
		return floor;
	}
	public void setFloor(String floor) {
		this.floor = floor;
	}
	public String getRoomNo() {
		return roomNo;
	}
	public void setRoomNo(String roomNo) {
		this.roomNo = roomNo;
	}
	public String getShipType() {
		return shipType;
	}
	public void setShipType(String shipType) {
		this.shipType = shipType;
	}
	public int getDelivCar() {
		return delivCar;
	}
	public void setDelivCar(int delivCar) {
		this.delivCar = delivCar;
	}
	public String getAcreage() {
		return acreage;
	}
	public void setAcreage(String acreage) {
		this.acreage = acreage;
	}
	public String getChequesNm() {
		return chequesNm;
	}
	public void setChequesNm(String chequesNm) {
		this.chequesNm = chequesNm;
	}
	public String getChequesNo() {
		return chequesNo;
	}
	public void setChequesNo(String chequesNo) {
		this.chequesNo = chequesNo;
	}
	public String getSignaTure() {
		return signaTure;
	}
	public void setSignaTure(String signaTure) {
		this.signaTure = signaTure;
	}
	public String getSuppliSts() {
		return suppliSts;
	}
	public void setSuppliSts(String suppliSts) {
		this.suppliSts = suppliSts;
	}
	public String getDelivDt() {
		return delivDt;
	}
	public void setDelivDt(String delivDt) {
		this.delivDt = delivDt;
	}
	public String getPayType() {
		return payType;
	}
	public void setPayType(String payType) {
		this.payType = payType;
	}
	public String getPayLong() {
		return payLong;
	}
	public void setPayLong(String payLong) {
		this.payLong = payLong;
	}
	public String getPoints() {
		return points;
	}
	public void setPoints(String points) {
		this.points = points;
	}
	public String getIsStateAmt() {
		return isStateAmt;
	}
	public void setIsStateAmt(String isStateAmt) {
		this.isStateAmt = isStateAmt;
	}
	public String getSdStateAmt() {
		return sdStateAmt;
	}
	public void setSdStateAmt(String sdStateAmt) {
		this.sdStateAmt = sdStateAmt;
	}
	public String getBeginAmt() {
		return beginAmt;
	}
	public void setBeginAmt(String beginAmt) {
		this.beginAmt = beginAmt;
	}
	public Date getExpDt() {
		return expDt;
	}
	public void setExpDt(Date expDt) {
		this.expDt = expDt;
	}
	public List<String> getShopLst() {
		return shopLst;
	}
	public void setShopLst(List<String> shopLst) {
		this.shopLst = shopLst;
	}
	public String getShopShow() {
		return shopShow;
	}
	public void setShopShow(String shopShow) {
		this.shopShow = shopShow;
	}
    
    
    
    
    
    
    
    
}
