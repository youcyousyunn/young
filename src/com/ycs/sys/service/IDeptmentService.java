package com.ycs.sys.service;

import com.ycs.base.exception.HiBusinessAbortException;
import com.ycs.base.exception.HiBusinessReturnException;
import com.ycs.sys.domain.dto.GetDeptmentTreeRequestDto;
import com.ycs.sys.domain.dto.GetDeptmentTreeResponseDto;
import com.ycs.sys.domain.dto.QueryAllDeptmentRequestDto;
import com.ycs.sys.domain.dto.QueryAllDeptmentResponseDto;
import com.ycs.sys.domain.dto.QueryDeptmentRequestDto;
import com.ycs.sys.domain.dto.QueryDeptmentResponseDto;
import com.ycs.sys.domain.dto.RenameDeptmentRequestDto;
import com.ycs.sys.domain.dto.RenameDeptmentResponseDto;
import com.ycs.sys.domain.dto.UpdateDeptmentRequestDto;
import com.ycs.sys.domain.dto.UpdateDeptmentResponseDto;

/**
 * 部门接口
 * @author youcyousyunn
 * @date 2018年3月13日
 */
public interface IDeptmentService {

	
	/**
     * 获取部门树形菜单
     * @param request 请求
     * @return 响应
     * @throws HiBusinessReturnException 异常
     */
	GetDeptmentTreeResponseDto queryDeptmentTree(GetDeptmentTreeRequestDto request) throws HiBusinessReturnException;

	/**
     * 查询所有部门
     * @return 响应
     * @throws HiBusinessReturnException 异常
     */
	QueryAllDeptmentResponseDto queryDeptment(QueryAllDeptmentRequestDto request) throws HiBusinessReturnException;

	/**
     * 更新部门名称
     * @param request 请求
     * @return 响应
     * @throws HiBusinessReturnException 异常
     */
	RenameDeptmentResponseDto renameDeptment(RenameDeptmentRequestDto request) throws HiBusinessReturnException;

	/**
     * 查询部门信息
     * @param request 请求
     * @return 响应
     * @throws HiBusinessReturnException 异常
     */
	QueryDeptmentResponseDto queryDeptment(QueryDeptmentRequestDto request) throws HiBusinessReturnException;

	/**
     * 更新部门
     * @param request 请求
     * @return 响应
     * @throws HiBusinessReturnException 异常
     */
	UpdateDeptmentResponseDto updateDeptment(UpdateDeptmentRequestDto request) throws HiBusinessReturnException,HiBusinessAbortException;
	
	
	
	
	
	
	
	
	
}
