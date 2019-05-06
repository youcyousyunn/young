package com.ycs.sys.domain.dto;

import java.util.Date;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ycs.base.domain.dto.BaseRequestDto;

/**
 * 更新用户信息请求DTO
 * @author youcyousyunn
 * @date 2018年3月14日
 */
public class UpdateUserInfoRequestDto extends BaseRequestDto {

	/**
     * 内部用户号
     */
    private String userNo;

    /**
     * 工号
     */
    private String jobNo;

    /**
     * 用户名称
     */
    private String usrNm;

    /**
     * 头像照片
     */
    private String picUrl;

    /**
     * 性别
     */
    private String sex;

    /**
     * 职务
     */
    private String duties;

    /**
     * 用户状态
     */
    private String usrSts;

    /**
     * 出生日期
     */
    @JsonFormat(pattern="yyyy-MM-dd", timezone="GMT+8")
    private Date birthday;

    /**
     * 工作部门id
     */
    private String deparNo;

    /**
     * 工作部门名称
     */
    private String deparNm;

    /**
     * 入职时间
     */
    @JsonFormat(pattern="yyyy-MM-dd", timezone="GMT+8")
    private Date entryDt;

    /**
     * 离职时间
     */
    @JsonFormat(pattern="yyyy-MM-dd", timezone="GMT+8")
    private Date leaveDt;
    
    /**
     * 手机号
     */
    private String mblNo;

    /**
     * 微信
     */
    private String wechat;

    /**
     * QQ
     */
    private String qq;

    /**
     * 角色编号集合
     */
    private Set<String> roleNos;

	public String getUserNo() {
		return userNo;
	}

	public void setUserNo(String userNo) {
		this.userNo = userNo;
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

	public String getMblNo() {
		return mblNo;
	}

	public void setMblNo(String mblNo) {
		this.mblNo = mblNo;
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

	public Set<String> getRoleNos() {
		return roleNos;
	}

	public void setRoleNos(Set<String> roleNos) {
		this.roleNos = roleNos;
	}
    
	/**
	 * 接口报文请求检查
	 * @param
	 * @return boolean
	 */
	public boolean checkRequestDto() {
        if (null == userNo || StringUtils.isBlank(userNo)) {
            return false;
        }
        return true;
    }
	
	

}
