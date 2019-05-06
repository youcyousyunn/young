package com.ycs.sys.domain.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ycs.base.domain.dto.BaseRequestDto;

/**
 * 用户信息更新请求DTO
 * @author youcyousyunn
 * @date 2018年3月26日
 */
public class UpdateSelfInfoRequestDto extends BaseRequestDto {
	
    /**
     * 用户名称
     */
    private String usrNm;
    
    /**
     * 手机号码
     */
    private String mblNo;
    
    /**
     * 工号
     */
    private String jobNo;
    
    /**
     * 职务
     */
    private String duties;
    
    /**
     * 入职日期
     */
    @JsonFormat(pattern="yyyy-MM-dd", timezone="GMT+8")
    private Date entryDt;
    
    /**
     * 出生日期
     */
    @JsonFormat(pattern="yyyy-MM-dd", timezone="GMT+8")
    private Date birthday;
    
    /**
     * 微信
     */
    private String wechat;
    
    /**
     * 用户密码
     */
    private  String usrPwd;
   
    /**
     * QQ
     */
    private String qq;

	public String getUsrNm() {
		return usrNm;
	}

	public void setUsrNm(String usrNm) {
		this.usrNm = usrNm;
	}

	public String getMblNo() {
		return mblNo;
	}

	public void setMblNo(String mblNo) {
		this.mblNo = mblNo;
	}

	public String getJobNo() {
		return jobNo;
	}

	public void setJobNo(String jobNo) {
		this.jobNo = jobNo;
	}

	public String getDuties() {
		return duties;
	}

	public void setDuties(String duties) {
		this.duties = duties;
	}

	public Date getEntryDt() {
		return entryDt;
	}

	public void setEntryDt(Date entryDt) {
		this.entryDt = entryDt;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getWechat() {
		return wechat;
	}

	public void setWechat(String wechat) {
		this.wechat = wechat;
	}

	public String getUsrPwd() {
		return usrPwd;
	}

	public void setUsrPwd(String usrPwd) {
		this.usrPwd = usrPwd;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}
	
	
	

}
