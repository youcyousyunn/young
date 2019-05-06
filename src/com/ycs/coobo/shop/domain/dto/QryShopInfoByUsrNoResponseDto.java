package com.ycs.coobo.shop.domain.dto;

import com.ycs.base.domain.dto.BaseResponseDto;

/**
 * 根据内部用户号查询单个门店信息返回DTO
 * @author youcyousyunn
 * @date 2018年5月19日
 */
public class QryShopInfoByUsrNoResponseDto extends BaseResponseDto {

	//门店号
    private String shopNo;
    //所属运营公司号
    private String cotldNo;
    //所属运营公司名称
    private String cotldNm;
    //供货仓库
    private String stohNo;
    //供货仓库名称
    private String stohNm;
    //门店代码
    private String shopCode;
    //门店类型
    private String shopType;
    //门店状态
    private String shopSts;
    //门店名称
    private String shopNm;
    //门店负责人
    private String mnger;
    //负责人内部用户号
    private String mngUsrNo;
    //门店电话
    private String shopTel;
    //门店省份
    private String prov;
    //门店城市
    private String city;
    //门店区县
    private String district;
    //门店街道
    private String street;
    //门店门牌号
    private String hoseNo;
    //门店楼盘
    private String building;
    //门店楼座
    private String balcony;
    //门店楼层
    private String floor;
    //门店房间号
    private String roomNo;
    //门店面积
    private String acreage;
    //门店配送费
    private String distAmt;
    //经度
    private String lngUde;
    //纬度
    private String latUde;
    //是否可入地下车库
    private String isCarport;
    //接货楼层
    private String acpfloor;
    //创建日期
    private String creDt;
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
	 * @return the cotldNo
	 */
	public String getCotldNo() {
		return cotldNo;
	}
	/**
	 * @param cotldNo the cotldNo to set
	 */
	public void setCotldNo(String cotldNo) {
		this.cotldNo = cotldNo;
	}
	/**
	 * @return the cotldNm
	 */
	public String getCotldNm() {
		return cotldNm;
	}
	/**
	 * @param cotldNm the cotldNm to set
	 */
	public void setCotldNm(String cotldNm) {
		this.cotldNm = cotldNm;
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
	 * @return the shopCode
	 */
	public String getShopCode() {
		return shopCode;
	}
	/**
	 * @param shopCode the shopCode to set
	 */
	public void setShopCode(String shopCode) {
		this.shopCode = shopCode;
	}
	/**
	 * @return the shopType
	 */
	public String getShopType() {
		return shopType;
	}
	/**
	 * @param shopType the shopType to set
	 */
	public void setShopType(String shopType) {
		this.shopType = shopType;
	}
	/**
	 * @return the shopSts
	 */
	public String getShopSts() {
		return shopSts;
	}
	/**
	 * @param shopSts the shopSts to set
	 */
	public void setShopSts(String shopSts) {
		this.shopSts = shopSts;
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
	 * @return the mnger
	 */
	public String getMnger() {
		return mnger;
	}
	/**
	 * @param mnger the mnger to set
	 */
	public void setMnger(String mnger) {
		this.mnger = mnger;
	}
	/**
	 * @return the mngUsrNo
	 */
	public String getMngUsrNo() {
		return mngUsrNo;
	}
	/**
	 * @param mngUsrNo the mngUsrNo to set
	 */
	public void setMngUsrNo(String mngUsrNo) {
		this.mngUsrNo = mngUsrNo;
	}
	/**
	 * @return the shopTel
	 */
	public String getShopTel() {
		return shopTel;
	}
	/**
	 * @param shopTel the shopTel to set
	 */
	public void setShopTel(String shopTel) {
		this.shopTel = shopTel;
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
	 * @return the street
	 */
	public String getStreet() {
		return street;
	}
	/**
	 * @param street the street to set
	 */
	public void setStreet(String street) {
		this.street = street;
	}
	/**
	 * @return the hoseNo
	 */
	public String getHoseNo() {
		return hoseNo;
	}
	/**
	 * @param hoseNo the hoseNo to set
	 */
	public void setHoseNo(String hoseNo) {
		this.hoseNo = hoseNo;
	}
	/**
	 * @return the building
	 */
	public String getBuilding() {
		return building;
	}
	/**
	 * @param building the building to set
	 */
	public void setBuilding(String building) {
		this.building = building;
	}
	/**
	 * @return the balcony
	 */
	public String getBalcony() {
		return balcony;
	}
	/**
	 * @param balcony the balcony to set
	 */
	public void setBalcony(String balcony) {
		this.balcony = balcony;
	}
	/**
	 * @return the floor
	 */
	public String getFloor() {
		return floor;
	}
	/**
	 * @param floor the floor to set
	 */
	public void setFloor(String floor) {
		this.floor = floor;
	}
	/**
	 * @return the roomNo
	 */
	public String getRoomNo() {
		return roomNo;
	}
	/**
	 * @param roomNo the roomNo to set
	 */
	public void setRoomNo(String roomNo) {
		this.roomNo = roomNo;
	}
	/**
	 * @return the acreage
	 */
	public String getAcreage() {
		return acreage;
	}
	/**
	 * @param acreage the acreage to set
	 */
	public void setAcreage(String acreage) {
		this.acreage = acreage;
	}
	/**
	 * @return the distAmt
	 */
	public String getDistAmt() {
		return distAmt;
	}
	/**
	 * @param distAmt the distAmt to set
	 */
	public void setDistAmt(String distAmt) {
		this.distAmt = distAmt;
	}
	/**
	 * @return the lngUde
	 */
	public String getLngUde() {
		return lngUde;
	}
	/**
	 * @param lngUde the lngUde to set
	 */
	public void setLngUde(String lngUde) {
		this.lngUde = lngUde;
	}
	/**
	 * @return the latUde
	 */
	public String getLatUde() {
		return latUde;
	}
	/**
	 * @param latUde the latUde to set
	 */
	public void setLatUde(String latUde) {
		this.latUde = latUde;
	}
	/**
	 * @return the isCarport
	 */
	public String getIsCarport() {
		return isCarport;
	}
	/**
	 * @param isCarport the isCarport to set
	 */
	public void setIsCarport(String isCarport) {
		this.isCarport = isCarport;
	}
	/**
	 * @return the acpfloor
	 */
	public String getAcpfloor() {
		return acpfloor;
	}
	/**
	 * @param acpfloor the acpfloor to set
	 */
	public void setAcpfloor(String acpfloor) {
		this.acpfloor = acpfloor;
	}
	/**
	 * @return the creDt
	 */
	public String getCreDt() {
		return creDt;
	}
	/**
	 * @param creDt the creDt to set
	 */
	public void setCreDt(String creDt) {
		this.creDt = creDt;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("QryShopInfoByUsrNoResponseDto [shopNo=").append(shopNo).append(", cotldNo=").append(cotldNo)
				.append(", cotldNm=").append(cotldNm).append(", stohNo=").append(stohNo).append(", stohNm=")
				.append(stohNm).append(", shopCode=").append(shopCode).append(", shopType=").append(shopType)
				.append(", shopSts=").append(shopSts).append(", shopNm=").append(shopNm).append(", mnger=")
				.append(mnger).append(", mngUsrNo=").append(mngUsrNo).append(", shopTel=").append(shopTel)
				.append(", prov=").append(prov).append(", city=").append(city).append(", district=").append(district)
				.append(", street=").append(street).append(", hoseNo=").append(hoseNo).append(", building=")
				.append(building).append(", balcony=").append(balcony).append(", floor=").append(floor)
				.append(", roomNo=").append(roomNo).append(", acreage=").append(acreage).append(", distAmt=")
				.append(distAmt).append(", lngUde=").append(lngUde).append(", latUde=").append(latUde)
				.append(", isCarport=").append(isCarport).append(", acpfloor=").append(acpfloor).append(", creDt=")
				.append(creDt).append("]");
		return builder.toString();
	}
    
    
    

}
