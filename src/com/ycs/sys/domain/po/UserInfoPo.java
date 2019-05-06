package com.ycs.sys.domain.po;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ycs.base.domain.po.BasePo;

public class UserInfoPo extends BasePo {
	private static final long serialVersionUID = 1138679384644633908L; //序列化ID
	private String usrNo;  //内部用户号
    private String usrPwd;  //密码
    private String jobNo;  //工号
    private String usrNm;  //用户名称
    private String picUrl;  //头像图片URL
    private String sex;  //性别
    private String duties;  //职务
    private String usrSts;  //用户状态
    private String isMnger;  //是否是负责人
    private String mblNo;  //手机号码
    @JsonFormat(pattern="yyyy-MM-dd", timezone="GMT+8")
    private Date birthday;  //出生日期
    private String deparNo;  //工作部门id
    private String deparNm;  //工作部门名称
    @JsonFormat(pattern="yyyy-MM-dd", timezone="GMT+8")
    private Date entryDt;  //入职时间
    @JsonFormat(pattern="yyyy-MM-dd", timezone="GMT+8")
    private Date leaveDt;  //离职时间
    private String wechat;  //微信
    private String qq;  //QQ
    private String email; //邮箱
    
	public String getUsrNo() {
		return usrNo;
	}
	public void setUsrNo(String usrNo) {
		this.usrNo = usrNo;
	}
	public String getUsrPwd() {
		return usrPwd;
	}
	public void setUsrPwd(String usrPwd) {
		this.usrPwd = usrPwd;
	}
	public String getJobNo() {
		return jobNo;
	}
	public void setJobNo(String jobNo) {
		this.jobNo = jobNo;
	}
	public String getUsrNm() {
		return usrNm;
	}
	public void setUsrNm(String usrNm) {
		this.usrNm = usrNm;
	}
	public String getPicUrl() {
		return picUrl;
	}
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getDuties() {
		return duties;
	}
	public void setDuties(String duties) {
		this.duties = duties;
	}
	public String getUsrSts() {
		return usrSts;
	}
	public void setUsrSts(String usrSts) {
		this.usrSts = usrSts;
	}
	public String getIsMnger() {
		return isMnger;
	}
	public void setIsMnger(String isMnger) {
		this.isMnger = isMnger;
	}
	public String getMblNo() {
		return mblNo;
	}
	public void setMblNo(String mblNo) {
		this.mblNo = mblNo;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getDeparNo() {
		return deparNo;
	}
	public void setDeparNo(String deparNo) {
		this.deparNo = deparNo;
	}
	public String getDeparNm() {
		return deparNm;
	}
	public void setDeparNm(String deparNm) {
		this.deparNm = deparNm;
	}
	public Date getEntryDt() {
		return entryDt;
	}
	public void setEntryDt(Date entryDt) {
		this.entryDt = entryDt;
	}
	public Date getLeaveDt() {
		return leaveDt;
	}
	public void setLeaveDt(Date leaveDt) {
		this.leaveDt = leaveDt;
	}
	public String getWechat() {
		return wechat;
	}
	public void setWechat(String wechat) {
		this.wechat = wechat;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
    
}
