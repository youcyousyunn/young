package com.ycs.sys.domain.dto;

import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ycs.base.domain.dto.BaseRequestDto;
import com.ycs.coobo.compan.domain.po.CompaniesPo;
import com.ycs.coobo.shop.domain.po.ShopPo;
import com.ycs.coobo.stoh.domain.po.StoHosePo;

/**
 * 更新部门信息请求DTO
 * @author youcyousyunn
 * @date 2018年3月17日
 */
public class UpdateDeptmentRequestDto extends BaseRequestDto {

	/**
     * 编号
     */
    private Integer deparNo;

    /**
     * 名称
     */
    private String deparNm;

    /**
     * 负责人名称
     */
    private String mngerNm;

    /**
     * 负责人用户编号
     */
    private String mngerUsrNo;

    /**
     * 父级部门
     */
    private Integer fDeparNo;

    /**
     * 状态
     */
    private String deparSts;
    
    /**
     * 部门类型
     */
    private String deparTyp;
    
    /**
     * 类型关联号
     */
    private String typeNo;

    /**
     * 描述
     */
    private String deparDesc;
    
    /**
     * 排序
     */
    private String orderNum;
    
    /**
     * 创建日期
     */
	@JsonFormat(pattern="yyyy-MM-dd", timezone="GMT+8")
    private Date creDt;
    
    /**
     * 修改日期
     */
	@JsonFormat(pattern="yyyy-MM-dd", timezone="GMT+8")
    private Date updDt;
    
    //门店对象
    private ShopPo shopPo;
    //仓库对象
    private StoHosePo stoHosePo;
    //运营公司对象
    private CompaniesPo companiesPo;
	public Integer getDeparNo() {
		return deparNo;
	}
	public void setDeparNo(Integer deparNo) {
		this.deparNo = deparNo;
	}
	public String getDeparNm() {
		return deparNm;
	}
	public void setDeparNm(String deparNm) {
		this.deparNm = deparNm;
	}
	public String getMngerNm() {
		return mngerNm;
	}
	public void setMngerNm(String mngerNm) {
		this.mngerNm = mngerNm;
	}
	public String getMngerUsrNo() {
		return mngerUsrNo;
	}
	public void setMngerUsrNo(String mngerUsrNo) {
		this.mngerUsrNo = mngerUsrNo;
	}
	public Integer getfDeparNo() {
		return fDeparNo;
	}
	public void setfDeparNo(Integer fDeparNo) {
		this.fDeparNo = fDeparNo;
	}
	public String getDeparSts() {
		return deparSts;
	}
	public void setDeparSts(String deparSts) {
		this.deparSts = deparSts;
	}
	public String getDeparTyp() {
		return deparTyp;
	}
	public void setDeparTyp(String deparTyp) {
		this.deparTyp = deparTyp;
	}
	public String getTypeNo() {
		return typeNo;
	}
	public void setTypeNo(String typeNo) {
		this.typeNo = typeNo;
	}
	public String getDeparDesc() {
		return deparDesc;
	}
	public void setDeparDesc(String deparDesc) {
		this.deparDesc = deparDesc;
	}
	public String getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}
	public Date getCreDt() {
		return creDt;
	}
	public void setCreDt(Date creDt) {
		this.creDt = creDt;
	}
	public Date getUpdDt() {
		return updDt;
	}
	public void setUpdDt(Date updDt) {
		this.updDt = updDt;
	}
	public ShopPo getShopPo() {
		return shopPo;
	}
	public void setShopPo(ShopPo shopPo) {
		this.shopPo = shopPo;
	}
	public StoHosePo getStoHosePo() {
		return stoHosePo;
	}
	public void setStoHosePo(StoHosePo stoHosePo) {
		this.stoHosePo = stoHosePo;
	}
	public CompaniesPo getCompaniesPo() {
		return companiesPo;
	}
	public void setCompaniesPo(CompaniesPo companiesPo) {
		this.companiesPo = companiesPo;
	}
	
	/**
	 * 接口报文检查
	 * @return boolean
	 * youcyousyunn
	 * 2018年3月17日
	 */
    public boolean checkRequestDto() {
        if (null == deparNo) {
            return false;
        }
        if (StringUtils.isBlank(deparNm)) {
            return false;
        }
        if (StringUtils.isBlank(mngerNm)) {
            return false;
        }
        if (StringUtils.isBlank(deparTyp)) {
            return false;
        }
        if(null != shopPo || null != stoHosePo || null != companiesPo){
            if (StringUtils.isBlank(typeNo)) {
                return false;
            }
        }
        if (StringUtils.isBlank(mngerUsrNo)) {
            return false;
        }
        if (null == fDeparNo) {
            return false;
        }
        return true;
    }
    
	
	
	

}
