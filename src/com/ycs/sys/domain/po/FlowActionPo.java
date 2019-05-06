package com.ycs.sys.domain.po;

import com.ycs.base.domain.po.BasePo;

/**
 * 流程动作PO
 * @author youcyousyunn
 * @date 2018年5月18日
 */
public class FlowActionPo extends BasePo {
	private String actionNo;//动作序号
    private String nodeNo;//节点序号
    private String server;//动作服务
    private String method;//动作方法
    private String actionNm;//动作名称
    private Integer deparNo;//动作部门
    private String deparNm;//动作名称
    private Integer postId;//动作岗位
    private String postNm;//岗位名称
    private String upStep;//上一步骤
    private String downStep;//下一步骤
    private String abreast;//是否可并行(同部门同角色人员是否同时收到消息)
    
	/**
	 * @return the actionNo
	 */
	public String getActionNo() {
		return actionNo;
	}
	/**
	 * @param actionNo the actionNo to set
	 */
	public void setActionNo(String actionNo) {
		this.actionNo = actionNo;
	}
	/**
	 * @return the nodeNo
	 */
	public String getNodeNo() {
		return nodeNo;
	}
	/**
	 * @param nodeNo the nodeNo to set
	 */
	public void setNodeNo(String nodeNo) {
		this.nodeNo = nodeNo;
	}
	/**
	 * @return the server
	 */
	public String getServer() {
		return server;
	}
	/**
	 * @param server the server to set
	 */
	public void setServer(String server) {
		this.server = server;
	}
	/**
	 * @return the method
	 */
	public String getMethod() {
		return method;
	}
	/**
	 * @param method the method to set
	 */
	public void setMethod(String method) {
		this.method = method;
	}
	/**
	 * @return the actionNm
	 */
	public String getActionNm() {
		return actionNm;
	}
	/**
	 * @param actionNm the actionNm to set
	 */
	public void setActionNm(String actionNm) {
		this.actionNm = actionNm;
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
	 * @return the deparNm
	 */
	public String getDeparNm() {
		return deparNm;
	}
	/**
	 * @param deparNm the deparNm to set
	 */
	public void setDeparNm(String deparNm) {
		this.deparNm = deparNm;
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
	 * @return the upStep
	 */
	public String getUpStep() {
		return upStep;
	}
	/**
	 * @param upStep the upStep to set
	 */
	public void setUpStep(String upStep) {
		this.upStep = upStep;
	}
	/**
	 * @return the downStep
	 */
	public String getDownStep() {
		return downStep;
	}
	/**
	 * @param downStep the downStep to set
	 */
	public void setDownStep(String downStep) {
		this.downStep = downStep;
	}
	/**
	 * @return the abreast
	 */
	public String getAbreast() {
		return abreast;
	}
	/**
	 * @param abreast the abreast to set
	 */
	public void setAbreast(String abreast) {
		this.abreast = abreast;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "FlowActionPo [actionNo=" + actionNo + ", nodeNo=" + nodeNo + ", server=" + server + ", method=" + method
				+ ", actionNm=" + actionNm + ", deparNo=" + deparNo + ", deparNm=" + deparNm + ", postId=" + postId
				+ ", postNm=" + postNm + ", upStep=" + upStep + ", downStep=" + downStep + ", abreast=" + abreast + "]";
	}
    
    
    

}
