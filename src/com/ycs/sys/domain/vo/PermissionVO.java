package com.ycs.sys.domain.vo;

import java.io.Serializable;
import java.util.List;

import org.springframework.util.CollectionUtils;

/**
 * 权限VO
 * @author youcyousyunn
 * @date 2018年2月11日
 */
public class PermissionVO implements Serializable {

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
    private String perName;

    /**
     * 权限描述
     */
    private String perDesc;

    /**
     * 权限编号
     */
    private String urlAuth;

    /**
     * 是否有子权限
     */
    private boolean hasChild;

    /**
     * 子权限
     */
    private List<PermissionVO> childPermissions;

    /**
     * 权限
     */
    private List<PermissionVO> permissions;

    /**
     * 构造函数
     */
    public PermissionVO() {
    }
    
    /**
     * 构造函数
     * 
     * @param perNo 权限编号
     * @param perLv 权限级别
     * @param perType 类型
     * @param fMenuNo 父权限编号
     * @param perSts 权限状态
     * @param perIco 权限图标
     * @param perUrl 权限URL
     * @param perName 权限名称
     * @param perDesc 权限描述
     */
    public PermissionVO(Integer perNo, Integer perLv, String perType, Integer fPerNo, String perSts, String perIco,
            String perUrl, String perName, String perDesc) {
        this.perNo = perNo;
        this.perLv = perLv;
        this.perType = perType;
        this.fPerNo = fPerNo;
        this.perSts = perSts;
        this.perIco = perIco;
        this.perUrl = perUrl;
        this.perName = perName;
        this.perDesc = perDesc;
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

	public String getPerName() {
		return perName;
	}

	public void setPerName(String perName) {
		this.perName = perName;
	}

	public String getPerDesc() {
		return perDesc;
	}

	public void setPerDesc(String perDesc) {
		this.perDesc = perDesc;
	}

	public String getUrlAuth() {
		return urlAuth;
	}

	public void setUrlAuth(String urlAuth) {
		this.urlAuth = urlAuth;
	}

	public boolean isHasChild() {
		return hasChild;
	}

	public void setHasChild(boolean hasChild) {
		this.hasChild = hasChild;
	}

	public List<PermissionVO> getChildPermissions() {
		return childPermissions;
	}

	public void setChildPermissions(List<PermissionVO> childPermissions) {
		if (!CollectionUtils.isEmpty(childPermissions)) {
            hasChild = true;
        }
        this.childPermissions = childPermissions;
	}

	public List<PermissionVO> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<PermissionVO> permissions) {
		this.permissions = permissions;
	}
    
	
	
}
