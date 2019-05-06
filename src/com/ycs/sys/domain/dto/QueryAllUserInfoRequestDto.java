package com.ycs.sys.domain.dto;

import com.ycs.base.domain.dto.BaseRequestDto;

/**
 * 查询所有用户请求DTO
 * @author youcyousyunn
 * @date 2018年3月17日
 */
public class QueryAllUserInfoRequestDto extends BaseRequestDto {

	/**
     * 用户名
     */
    private String usrNm;

	public String getUsrNm() {
		return usrNm;
	}

	public void setUsrNm(String usrNm) {
		this.usrNm = usrNm;
	}

    
    
}
