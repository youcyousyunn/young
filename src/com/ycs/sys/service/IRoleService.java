package com.ycs.sys.service;

import java.util.Date;

import com.ycs.base.exception.HiBusinessReturnException;
import com.ycs.sys.domain.dto.AddRoleRequestDto;
import com.ycs.sys.domain.dto.AddRoleResponseDto;
import com.ycs.sys.domain.dto.DeleteRoleRequestDto;
import com.ycs.sys.domain.dto.DeleteRoleResponseDto;
import com.ycs.sys.domain.dto.QueryAllRoleResponseDto;
import com.ycs.sys.domain.dto.QueryRoleExistsRequestDto;
import com.ycs.sys.domain.dto.QueryRoleRequestDto;
import com.ycs.sys.domain.dto.QueryRoleResponseDto;
import com.ycs.sys.domain.dto.QueryRolesResponseDto;
import com.ycs.sys.domain.dto.UpdateRoleRequestDto;
import com.ycs.sys.domain.dto.UpdateRoleResponseDto;

/**
 * 角色Service接口
 * @author youcyousyunn
 * @date 2018年3月10日
 */
public interface IRoleService {

	/**
     * 分页查询角色
     * @param roleNo 编号
     * @param status 状态
     * @param name 名称
     * @param createDate 创建时间
     * @param sort 排序列
     * @param sortName 排序方式(DESC,ASC)默认DESC
     * @param currentPage 当前页
     * @param pageSize 页大小
     * @return 响应
     * @throws HiBusinessReturnException 异常
     */
	QueryRolesResponseDto pageQuery(String roleNo, String status, String name, Date createDate, String search, String sort,
            String sortName, Integer currentPage, Integer pageSize) throws HiBusinessReturnException;

	/**
     * 查询角色信息
     * @param request 请求
     * @return 响应
     * @throws HiBusinessReturnException 异常
     */
	QueryRoleResponseDto queryRole(QueryRoleRequestDto request) throws HiBusinessReturnException;

	/**
     * 更新角色信息
     * @param request 请求
     * @return 响应
     * @throws HiBusinessReturnException 异常
     */
	UpdateRoleResponseDto updateRole(UpdateRoleRequestDto request) throws HiBusinessReturnException;

	/**
     * 删除角色
     * @param request 请求
     * @return 响应
     * @throws HiBusinessReturnException 异常
     */
	DeleteRoleResponseDto deleteRoles(DeleteRoleRequestDto request) throws HiBusinessReturnException;

	/**
     * 添加角色
     * @param request 请求
     * @return 响应
     * @throws HiBusinessReturnException 异常
     */
	AddRoleResponseDto addRole(AddRoleRequestDto request) throws HiBusinessReturnException;

	/**
     * 根据角色编号查询角色是否存在
     * @param request 请求
     * @return 是否存在
     * @throws HiBusinessReturnException 异常
     */
	boolean roleExists(QueryRoleExistsRequestDto request) throws HiBusinessReturnException;

	/**
     * 查询所有角色
     * @return 响应
     * @throws HiBusinessReturnException 异常
     */
	QueryAllRoleResponseDto queryRole() throws HiBusinessReturnException;

	
	
	
	
	
	
	
}
