package com.ycs.sys.domain.po;

import com.ycs.base.domain.po.BasePo;

/**
 * 角色PO
 * @author youcyousyunn
 * @date 2018年2月11日
 */
public class RolePo extends BasePo {

	/**
     * 编号
     */
    private String roleNo;

    /**
     * 状态
     */
    private String roleSts;

    /**
     * 名称
     */
    private String roleNm;

    /**
     * 描述
     */
    private String roleDesc;


	public String getRoleNo() {
		return roleNo;
	}

	public void setRoleNo(String roleNo) {
		this.roleNo = roleNo;
	}

	public String getRoleSts() {
		return roleSts;
	}

	public void setRoleSts(String roleSts) {
		this.roleSts = roleSts;
	}

	public String getRoleNm() {
		return roleNm;
	}

	public void setRoleNm(String roleNm) {
		this.roleNm = roleNm;
	}

	public String getRoleDesc() {
		return roleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}

    

	
}
