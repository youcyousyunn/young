package com.ycs.sys.domain.po;

import com.ycs.base.domain.po.BasePo;

/**
 * 部门PO
 * @author youcyousyunn
 * @date 2018年3月13日
 */
public class DeptmentPo extends BasePo {

    /**
     * 部门编号
     */
    private Integer deparNo;

    /**
     * 部门名称
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
     * 部门描述
     */
    private String deparDesc;

    /**
     * 排序编号
     */
    private String orderNum;

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

    
    
    
}
