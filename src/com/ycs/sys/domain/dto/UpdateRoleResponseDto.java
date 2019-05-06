package com.ycs.sys.domain.dto;

import com.ycs.base.domain.dto.BaseResponseDto;

/**
 * 更新角色响应DTO
 * @author youcyousyunn
 * @date 2018年3月10日
 */
public class UpdateRoleResponseDto extends BaseResponseDto {

	/**
     * 构造函数
     */
    public UpdateRoleResponseDto() {
    }

    /**
     * 构造函数
     * @param responseCode 响应代码
     * @param responseInfo 响应信息
     */
    public UpdateRoleResponseDto(String responseCode, String responseInfo) {
        super(responseCode, responseInfo);
    }

    /**
     * 构造函数
     * @param responseCode 响应代码
     */
    public UpdateRoleResponseDto(String responseCode) {
        super(responseCode);
    }
    
    

}
