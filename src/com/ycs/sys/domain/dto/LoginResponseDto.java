package com.ycs.sys.domain.dto;

import java.io.Serializable;
import java.util.Date;

import com.ycs.base.domain.dto.BaseResponseDto;

/**
 * 用户登录返回信息DTO
 * @author youcyousyunn
 * @date 2018年2月9日
 */
public class LoginResponseDto extends BaseResponseDto implements Serializable {

	/**
	 * 序列化版本号
	 */
	private static final long serialVersionUID = -2251243263797848667L;

	/**
     * 内部用户号
     */
    private String usrNo;

    /**
     * 工号
     */
    private String jobNo;

    /**
     * 用户名称
     */
    private String usrNm;

    /**
     * 性别
     */
    private char sex;

    /**
     * 职务
     */
    private String duties;

    /**
     * 用户状态
     */
    private char usrSts;

    /**
     * 手机号码
     */
    private String mblNo;

    /**
     * 出生日期
     */
    private Date birthday;

    /**
     * 工作部门id
     */
    private Integer deparNo;

    /**
     * 工作部门名称
     */
    private String deparNm;

    /**
     * 入职时间
     */
    private Date entryDt;

    /**
     * 离职时间
     */
    private Date leaveDt;

    /**
     * 微信
     */
    private String wechat;

    /**
     * QQ
     */
    private String qq;

    /**
     * 构造函数
     */
    public LoginResponseDto() {
    }
    
    /**
     * 构造函数
     * @param responseCode 响应code
     * @param responseInfo 响应信息
     */
    public LoginResponseDto(String responseCode, String responseInfo) {
        this.rspCd = responseCode;
        this.rspInf = responseInfo;
    }

	public String getUsrNo() {
		return usrNo;
	}

	public void setUsrNo(String usrNo) {
		this.usrNo = usrNo;
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

	public char getSex() {
		return sex;
	}

	public void setSex(char sex) {
		this.sex = sex;
	}

	public String getDuties() {
		return duties;
	}

	public void setDuties(String duties) {
		this.duties = duties;
	}

	public char getUsrSts() {
		return usrSts;
	}

	public void setUsrSts(char usrSts) {
		this.usrSts = usrSts;
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
    

}
