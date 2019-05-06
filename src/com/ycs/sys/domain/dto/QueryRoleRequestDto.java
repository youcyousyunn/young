package com.ycs.sys.domain.dto;

import com.ycs.base.domain.dto.BaseRequestDto;

/**
 * 角色查询请求DTO
 * @author youcyousyunn
 * @date 2018年3月10日
 */
public class QueryRoleRequestDto extends BaseRequestDto {

	/**
     * 角色编号
     */
    private String roleNo;

	public String getRoleNo() {
		return roleNo;
	}

	public void setRoleNo(String roleNo) {
		this.roleNo = roleNo;
	}
    
    

}
