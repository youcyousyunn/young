package com.ycs.sys.domain.dto;

import java.util.Set;

import com.ycs.base.domain.dto.BaseRequestDto;

/**
 * 删除角色请求DTO
 * @author youcyousyunn
 * @date 2018年3月10日
 */
public class DeleteRoleRequestDto extends BaseRequestDto {

	/**
     * 角色编号集合
     */
    private Set<String> roleNos;

	public Set<String> getRoleNos() {
		return roleNos;
	}

	public void setRoleNos(Set<String> roleNos) {
		this.roleNos = roleNos;
	}
    

	
}
