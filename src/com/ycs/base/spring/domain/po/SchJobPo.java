package com.ycs.base.spring.domain.po;

import java.sql.Timestamp;

import com.ycs.base.domain.po.BasePo;

public class SchJobPo extends BasePo {
	private String appId;
	private String modId;
	private String jobId;
	private String jobDesc;
	private String txFlg;
	private String logFlg;
	private Timestamp tmSmp;
	
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getModId() {
		return modId;
	}
	public void setModId(String modId) {
		this.modId = modId;
	}
	public String getJobId() {
		return jobId;
	}
	public void setJobId(String jobId) {
		this.jobId = jobId;
	}
	public String getJobDesc() {
		return jobDesc;
	}
	public void setJobDesc(String jobDesc) {
		this.jobDesc = jobDesc;
	}
	public String getTxFlg() {
		return txFlg;
	}
	public void setTxFlg(String txFlg) {
		this.txFlg = txFlg;
	}
	public String getLogFlg() {
		return logFlg;
	}
	public void setLogFlg(String logFlg) {
		this.logFlg = logFlg;
	}
	public Timestamp getTmSmp() {
		return tmSmp;
	}
	public void setTmSmp(Timestamp tmSmp) {
		this.tmSmp = tmSmp;
	}
	
	
	
	
}
