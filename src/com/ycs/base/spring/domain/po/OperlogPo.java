package com.ycs.base.spring.domain.po;

import com.ycs.base.domain.po.BasePo;

/**
 * 系统操作日志PO
 * @author youcyousyunn
 * @date 2018年6月30日
 */
public class OperlogPo extends BasePo {
	private String nodeId;
	private String regId;
	private String msgId;
	private String reqDt;
	private String reqTm;
	private String operId;
	private String title;
	private String action;
	private String appCnl;
	private String tmSmp;
	/**
	 * @return the nodeId
	 */
	public String getNodeId() {
		return nodeId;
	}
	/**
	 * @param nodeId the nodeId to set
	 */
	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}
	/**
	 * @return the regId
	 */
	public String getRegId() {
		return regId;
	}
	/**
	 * @param regId the regId to set
	 */
	public void setRegId(String regId) {
		this.regId = regId;
	}
	/**
	 * @return the msgId
	 */
	public String getMsgId() {
		return msgId;
	}
	/**
	 * @param msgId the msgId to set
	 */
	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}
	/**
	 * @return the reqDt
	 */
	public String getReqDt() {
		return reqDt;
	}
	/**
	 * @param reqDt the reqDt to set
	 */
	public void setReqDt(String reqDt) {
		this.reqDt = reqDt;
	}
	/**
	 * @return the reqTm
	 */
	public String getReqTm() {
		return reqTm;
	}
	/**
	 * @param reqTm the reqTm to set
	 */
	public void setReqTm(String reqTm) {
		this.reqTm = reqTm;
	}
	/**
	 * @return the operId
	 */
	public String getOperId() {
		return operId;
	}
	/**
	 * @param operId the operId to set
	 */
	public void setOperId(String operId) {
		this.operId = operId;
	}
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the action
	 */
	public String getAction() {
		return action;
	}
	/**
	 * @param action the action to set
	 */
	public void setAction(String action) {
		this.action = action;
	}
	/**
	 * @return the appCnl
	 */
	public String getAppCnl() {
		return appCnl;
	}
	/**
	 * @param appCnl the appCnl to set
	 */
	public void setAppCnl(String appCnl) {
		this.appCnl = appCnl;
	}
	/**
	 * @return the tmSmp
	 */
	public String getTmSmp() {
		return tmSmp;
	}
	/**
	 * @param tmSmp the tmSmp to set
	 */
	public void setTmSmp(String tmSmp) {
		this.tmSmp = tmSmp;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "OperlogPo [nodeId=" + nodeId + ", regId=" + regId + ", msgId=" + msgId + ", reqDt=" + reqDt + ", reqTm="
				+ reqTm + ", operId=" + operId + ", title=" + title + ", action=" + action + ", appCnl=" + appCnl
				+ ", tmSmp=" + tmSmp + "]";
	}
}
