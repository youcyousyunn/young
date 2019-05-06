package com.ycs.sys.domain.dto;

import java.util.Set;

import com.ycs.base.domain.dto.BaseRequestDto;

/**
 * 添加角色请求DTO
 * @author youcyousyunn
 * @date 2018年3月11日
 */
public class AddRoleRequestDto extends BaseRequestDto {

	/**
     * 编号
     */
    private String roleNo;

    /**
     * 名称
     */
    private String roleNm;

    /**
     * 状态
     */
    private String roleSts;

    /**
     * 描述
     */
    private String roleDesc;

    /**
     * 权限id集合
     */
    private Set<Integer> permissionNos;

	public String getRoleNo() {
		return roleNo;
	}

	public void setRoleNo(String roleNo) {
		this.roleNo = roleNo;
	}

	public String getRoleNm() {
		return roleNm;
	}

	public void setRoleNm(String roleNm) {
		this.roleNm = roleNm;
	}

	public String getRoleSts() {
		return roleSts;
	}

	public void setRoleSts(String roleSts) {
		this.roleSts = roleSts;
	}

	public String getRoleDesc() {
		return roleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}

	public Set<Integer> getPermissionNos() {
		return permissionNos;
	}

	public void setPermissionNos(Set<Integer> permissionNos) {
		this.permissionNos = permissionNos;
	}

    
    
    
    

}
