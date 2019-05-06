package com.ycs.sys.domain.po;

import com.ycs.base.domain.po.BasePo;

/**
 * 部门岗位PO
 * @author youcyousyunn
 * @date 2018年5月18日
 */
public class DeptPostPo extends BasePo {
	/**
     * 关系编号
     */
    private Integer bearId;

    /**
     * 用户编号
     */
    private String usrNo;

    /**
     * 用户名称
     */
    private String usrNm;

    /**
     * 部门编号
     */
    private Integer deparNo;

    /**
     * 岗位编号
     */
    private Integer postId;

    /**
     * 岗位名称
     */
    private String postNm;

    /**
     * 是否默认审核人
     */
    private String isDefReview;

    /**
     * 是否兼部门
     */
    private String isPartDept;

    /**
     * 是否兼岗
     */
    private String isPartPost;

    /**
     * 状态
     */
    private String sts;

	/**
	 * @return the bearId
	 */
	public Integer getBearId() {
		return bearId;
	}

	/**
	 * @param bearId the bearId to set
	 */
	public void setBearId(Integer bearId) {
		this.bearId = bearId;
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
	 * @return the deparNo
	 */
	public Integer getDeparNo() {
		return deparNo;
	}

	/**
	 * @param deparNo the deparNo to set
	 */
	public void setDeparNo(Integer deparNo) {
		this.deparNo = deparNo;
	}

	/**
	 * @return the postId
	 */
	public Integer getPostId() {
		return postId;
	}

	/**
	 * @param postId the postId to set
	 */
	public void setPostId(Integer postId) {
		this.postId = postId;
	}

	/**
	 * @return the postNm
	 */
	public String getPostNm() {
		return postNm;
	}

	/**
	 * @param postNm the postNm to set
	 */
	public void setPostNm(String postNm) {
		this.postNm = postNm;
	}

	/**
	 * @return the isDefReview
	 */
	public String getIsDefReview() {
		return isDefReview;
	}

	/**
	 * @param isDefReview the isDefReview to set
	 */
	public void setIsDefReview(String isDefReview) {
		this.isDefReview = isDefReview;
	}

	/**
	 * @return the isPartDept
	 */
	public String getIsPartDept() {
		return isPartDept;
	}

	/**
	 * @param isPartDept the isPartDept to set
	 */
	public void setIsPartDept(String isPartDept) {
		this.isPartDept = isPartDept;
	}

	/**
	 * @return the isPartPost
	 */
	public String getIsPartPost() {
		return isPartPost;
	}

	/**
	 * @param isPartPost the isPartPost to set
	 */
	public void setIsPartPost(String isPartPost) {
		this.isPartPost = isPartPost;
	}

	/**
	 * @return the sts
	 */
	public String getSts() {
		return sts;
	}

	/**
	 * @param sts the sts to set
	 */
	public void setSts(String sts) {
		this.sts = sts;
	}
    

}
