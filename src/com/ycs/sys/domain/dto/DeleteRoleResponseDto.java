package com.ycs.sys.domain.dto;

import java.util.Set;

import com.ycs.base.domain.dto.BaseResponseDto;

/**
 * 删除角色相应DTO
 * @author youcyousyunn
 * @date 2018年3月10日
 */
public class DeleteRoleResponseDto extends BaseResponseDto {

	/**
     * 成功角色编号集合
     */
    private Set<String> successRoleNos;

    /**
     * 失败角色编号集合
     */
    private Set<String> faildRoleNos;

    /**
     * 构造函数
     */
    public DeleteRoleResponseDto() {
    }

    /**
     * 构造函数
     * 
     * @param responseCode 响应代码
     * @param message 响应信息
     */
    public DeleteRoleResponseDto(String responseCode, String message) {
        this.rspCd = responseCode;
        this.rspInf = message;
    }

    /**
     * 构造函数
     * @param responseCode 响应代码
     */
    public DeleteRoleResponseDto(String responseCode) {
        this.rspCd = responseCode;
    }

    /**
     * 构造函数
     * @param responseCode 响应代码
     * @param successRoleNos 成功集合
     * @param faildRoleNos 失败集合
     */
    public DeleteRoleResponseDto(String responseCode, Set<String> successRoleNos, Set<String> faildRoleNos) {
        this.rspCd = responseCode;
        this.successRoleNos = successRoleNos;
        this.faildRoleNos = faildRoleNos;
    }

    
    public Set<String> getSuccessRoleNos() {
        return successRoleNos;
    }

    public void setSuccessRoleNos(Set<String> successRoleNos) {
        this.successRoleNos = successRoleNos;
    }

    public Set<String> getFaildRoleNos() {
        return faildRoleNos;
    }

    public void setFaildRoleNos(Set<String> faildRoleNos) {
        this.faildRoleNos = faildRoleNos;
    }
    
    

}
