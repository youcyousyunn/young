package com.ycs.sys.domain.po;

import com.ycs.base.domain.po.BasePo;

/**
 * 权限PO
 * @author youcyousyunn
 * @date 2018年2月11日
 */
public class PermissionPo extends BasePo {

	/**
     * 权限号
     */
    private Integer perNo;

    /**
     * 权限级别：1一级权限，2二级权限，3三级权限
     */
    private Integer perLv;

    /**
     * 类型(MENU菜单,COMMON权限)
     */
    private String perType;

    /**
     * 父级权限号
     */
    private Integer fPerNo;

    /**
     * 权限状态：S生效：F失效
     */
    private String perSts;

    /**
     * 权限编号
     */
    private String urlAuth;

    /**
     * 权限图标
     */
    private String perIco;

    /**
     * 权限链接
     */
    private String perUrl;

    /**
     * 权限名称
     */
    private String perNm;

    /**
     * 权限描述
     */
    private String perDesc;

    /**
     * 构造函数
     */
    public PermissionPo() {
    }
    
    /**
     * 构造函数
     * 
     * @param perNo 主键
     * @param perType 类型
     * @param perSts 状态
     * @param perIco 图标
     * @param perUrl 地址
     * @param perNm 名称
     * @param perDesc 描述
     */
    public PermissionPo(Integer perNo, String perType, String perSts, String perIco, String perUrl, String perNm, String perDesc) {
        this.perNo = perNo;
        this.perType = perType;
        this.perSts = perSts;
        this.perIco = perIco;
        this.perUrl = perUrl;
        this.perNm = perNm;
        this.perDesc = perDesc;
    }
    
    /**
     * 构造函数
     * 
     * @param perLv 级别
     * @param perType 类型
     * @param fPerNo 父ID
     * @param perSts 状态
     * @param perIco 图标
     * @param perUrl 地址
     * @param perNm 名称
     * @param perDesc 描述
     * @param urlAuth 权限编号
     */
    public PermissionPo(Integer perLv, String perType, Integer fPerNo, String perSts, String perIco, String perUrl,
            String perNm, String perDesc, String urlAuth) {
        this.perLv = perLv;
        this.perType = perType;
        this.fPerNo = fPerNo;
        this.perSts = perSts;
        this.perIco = perIco;
        this.perUrl = perUrl;
        this.perNm = perNm;
        this.perDesc = perDesc;
        this.urlAuth = urlAuth;
    }

    
	public Integer getPerNo() {
		return perNo;
	}

	public void setPerNo(Integer perNo) {
		this.perNo = perNo;
	}

	public Integer getPerLv() {
		return perLv;
	}

	public void setPerLv(Integer perLv) {
		this.perLv = perLv;
	}

	public String getPerType() {
		return perType;
	}

	public void setPerType(String perType) {
		this.perType = perType;
	}

	public Integer getfPerNo() {
		return fPerNo;
	}

	public void setfPerNo(Integer fPerNo) {
		this.fPerNo = fPerNo;
	}

	public String getPerSts() {
		return perSts;
	}

	public void setPerSts(String perSts) {
		this.perSts = perSts;
	}

	public String getUrlAuth() {
		return urlAuth;
	}

	public void setUrlAuth(String urlAuth) {
		this.urlAuth = urlAuth;
	}

	public String getPerIco() {
		return perIco;
	}

	public void setPerIco(String perIco) {
		this.perIco = perIco;
	}

	public String getPerUrl() {
		return perUrl;
	}

	public void setPerUrl(String perUrl) {
		this.perUrl = perUrl;
	}

	public String getPerNm() {
		return perNm;
	}

	public void setPerNm(String perNm) {
		this.perNm = perNm;
	}

	public String getPerDesc() {
		return perDesc;
	}

	public void setPerDesc(String perDesc) {
		this.perDesc = perDesc;
	}
    
    
    

}
