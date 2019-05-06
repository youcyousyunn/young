package com.ycs.sys.service;

import java.util.Set;

import com.ycs.base.domain.dto.BaseResponseDto;
import com.ycs.base.exception.HiBusinessReturnException;
import com.ycs.sys.domain.dto.AddPermissionRequestDto;
import com.ycs.sys.domain.dto.AddPermissionResponseDto;
import com.ycs.sys.domain.dto.GetPermissionResponseDto;
import com.ycs.sys.domain.dto.UpdatePermissionRequestDto;
import com.ycs.sys.domain.dto.UpdatePermissionResponseDto;

/**
 * 菜单Service接口
 * @author youcyousyunn
 * @date 2018年2月11日
 */
public interface IPermissionService {
	
	/**
     * 根据用户编号查询菜单
     * @param userNo 用户编号
     * @return 获取菜单响应
     * @throws HiBusinessReturnException 异常
     */
    GetPermissionResponseDto getMenus(String userNo) throws HiBusinessReturnException;
    
    /**
     * 获取权限集合
     * @return GetPermissionResponseDto 获取权限响应
     * @throws HiBusinessReturnException 异常
     */
	GetPermissionResponseDto getPermissions() throws HiBusinessReturnException;

	/**
	 * 修改权限
	 * @param request 请求
	 * @return UpdatePermissionResponseDto 修改权限响应
	 * @throws HiBusinessReturnException 异常
	 */
	UpdatePermissionResponseDto updatePermission(UpdatePermissionRequestDto request) throws HiBusinessReturnException;

	/**
	 * 添加权限
	 * @param request 请求
	 * @return AddPermissionResponseDto 添加权限响应
	 */
	AddPermissionResponseDto addPermission(AddPermissionRequestDto request) throws HiBusinessReturnException;

	/**
	 * 删除权限
	 * @param permissionNos 请求
	 * @return BaseResponseDto 删除权限响应
	 */
	BaseResponseDto deletePermission(Set<Integer> permissionNos) throws HiBusinessReturnException;
    
    
	
    
    
	
}
