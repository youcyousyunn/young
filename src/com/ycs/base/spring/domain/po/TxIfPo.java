package com.ycs.base.spring.domain.po;

import java.sql.Timestamp;

import com.ycs.base.domain.po.BasePo;

public class TxIfPo extends BasePo {
	private String appId;
	private String modId;
	private String url;
	private String urlDesc;
	private String txFlg;
	private String logFlg;
	private String pwdFlg;
	private String urlAuth;
	private Timestamp tmSmp;
	
	/**
	 * @return the appId
	 */
	public String getAppId() {
		return appId;
	}
	/**
	 * @param appId the appId to set
	 */
	public void setAppId(String appId) {
		this.appId = appId;
	}
	/**
	 * @return the modId
	 */
	public String getModId() {
		return modId;
	}
	/**
	 * @param modId the modId to set
	 */
	public void setModId(String modId) {
		this.modId = modId;
	}
	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * @return the urlDesc
	 */
	public String getUrlDesc() {
		return urlDesc;
	}
	/**
	 * @param urlDesc the urlDesc to set
	 */
	public void setUrlDesc(String urlDesc) {
		this.urlDesc = urlDesc;
	}
	/**
	 * @return the txFlg
	 */
	public String getTxFlg() {
		return txFlg;
	}
	/**
	 * @param txFlg the txFlg to set
	 */
	public void setTxFlg(String txFlg) {
		this.txFlg = txFlg;
	}
	/**
	 * @return the logFlg
	 */
	public String getLogFlg() {
		return logFlg;
	}
	/**
	 * @param logFlg the logFlg to set
	 */
	public void setLogFlg(String logFlg) {
		this.logFlg = logFlg;
	}
	/**
	 * @return the pwdFlg
	 */
	public String getPwdFlg() {
		return pwdFlg;
	}
	/**
	 * @param pwdFlg the pwdFlg to set
	 */
	public void setPwdFlg(String pwdFlg) {
		this.pwdFlg = pwdFlg;
	}
	/**
	 * @return the urlAuth
	 */
	public String getUrlAuth() {
		return urlAuth;
	}
	/**
	 * @param urlAuth the urlAuth to set
	 */
	public void setUrlAuth(String urlAuth) {
		this.urlAuth = urlAuth;
	}
	/**
	 * @return the tmSmp
	 */
	public Timestamp getTmSmp() {
		return tmSmp;
	}
	/**
	 * @param tmSmp the tmSmp to set
	 */
	public void setTmSmp(Timestamp tmSmp) {
		this.tmSmp = tmSmp;
	}
	
}
