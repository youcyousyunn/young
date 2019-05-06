package com.ycs.sys.domain.dto;

import com.ycs.base.domain.dto.BaseResponseDto;

/**
 * 查询角色是否存在响应DTO
 * @author youcyousyunn
 * @date 2018年3月11日
 */
public class QueryRoleExistsResponseDto extends BaseResponseDto {

	/**
     * 是否存在，默认不存在
     */
    private boolean isExists = false;

    /**
     * 构造函数
     */
    public QueryRoleExistsResponseDto() {
    }

    /**
     * 构造函数
     * @param responseCode 响应代码
     * @param message 响应信息
     */
    public QueryRoleExistsResponseDto(String responseCode, String message) {
        this.rspCd = responseCode;
        this.rspInf = message;
    }

    /**
     * 构造函数
     * @param responseCode 响应代码
     * @param isExists 是否存在
     */
    public QueryRoleExistsResponseDto(String responseCode, boolean isExists) {
        this.rspCd = responseCode;
        this.isExists = isExists;
    }

    public boolean isExists() {
        return isExists;
    }

    public void setExists(boolean isExists) {
        this.isExists = isExists;
    }
    
    
    

}
