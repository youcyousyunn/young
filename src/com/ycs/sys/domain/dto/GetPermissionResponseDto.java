package com.ycs.sys.domain.dto;

import java.util.List;

import com.ycs.base.domain.dto.BaseResponseDto;
import com.ycs.sys.domain.vo.PermissionVO;

/**
 * 获取菜单DTO
 * @author youcyousyunn
 * @date 2018年2月11日
 */
public class GetPermissionResponseDto extends BaseResponseDto {

	/**
     * 菜单集合
     */
    private List<PermissionVO> menus;
	
    /**
     * 构造函数
     */
    public GetPermissionResponseDto(){
    }

    /**
     * 构造函数
     * 
     * @param responseCode 响应代码
     * @param responseInfo 响应信息
     */
    public GetPermissionResponseDto(String responseCode, String responseInfo) {
        this.rspCd = responseCode;
        this.rspInf = responseInfo;
    }
    
    /**
     * 构造函数
     * 
     * @param responseCode 响应代码
     * @param menus 菜单集合
     */
    public GetPermissionResponseDto(String responseCode, List<PermissionVO> menus) {
        this.rspCd = responseCode;
        this.menus = menus;
    }
    
	public List<PermissionVO> getMenus() {
		return menus;
	}

	public void setMenus(List<PermissionVO> menus) {
		this.menus = menus;
	}
	
	
	
}
