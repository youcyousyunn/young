package com.ycs.sys.domain.dto;

import com.ycs.base.domain.dto.BaseResponseDto;

/**
 * 添加角色响应DTO
 * @author youcyousyunn
 * @date 2018年3月11日
 */
public class AddRoleResponseDto extends BaseResponseDto {

	/**
     * 构造函数
     */
    public AddRoleResponseDto() {
    }

    /**
     * 构造函数
     * @param responseCode 响应代码
     * @param message 响应消息
     */
    public AddRoleResponseDto(String responseCode, String message) {
        this.rspCd = responseCode;
        this.rspInf = message;
    }
    
    

}
