package com.ycs.sys.domain.dto;

import java.util.Set;

import com.ycs.base.domain.dto.BaseRequestDto;

/**
 * 删除用户请求DTO
 * @author youcyousyunn
 * @date 2018年3月15日
 */
public class DeleteUserRequestDto extends BaseRequestDto {

	/**
     * 用户编号集合
     */
    private Set<String> usrNos;

	public Set<String> getUsrNos() {
		return usrNos;
	}

	public void setUsrNos(Set<String> usrNos) {
		this.usrNos = usrNos;
	}

    
    
    
    
    

}
