package com.ycs.base.spring.domain.pojo;

public class ScheduleJobPojo {
	private String appId;
	private String modId;
	private String jobId;
	private String classUrl;
	private String txFlg;
	private String logFlg;
	private String runMod;
	private String cronExpression;
	private String desc;
	
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
	public String getClassUrl() {
		return classUrl;
	}
	public void setClassUrl(String classUrl) {
		this.classUrl = classUrl;
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
	public String getRunMod() {
		return runMod;
	}
	public void setRunMod(String runMod) {
		this.runMod = runMod;
	}
	public String getCronExpression() {
		return cronExpression;
	}
	public void setCronExpression(String cronExpression) {
		this.cronExpression = cronExpression;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	
	

}
