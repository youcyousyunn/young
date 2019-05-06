package com.ycs.sys.service;

import javax.servlet.http.HttpSession;

import com.ycs.base.domain.dto.BaseResponseDto;
import com.ycs.base.exception.HiBusinessAbortException;
import com.ycs.base.exception.HiBusinessReturnException;
import com.ycs.sys.domain.dto.AddUserInfoRequestDto;
import com.ycs.sys.domain.dto.AddUserInfoResponseDto;
import com.ycs.sys.domain.dto.DeleteUserRequestDto;
import com.ycs.sys.domain.dto.LoginRequestDto;
import com.ycs.sys.domain.dto.LoginResponseDto;
import com.ycs.sys.domain.dto.PageUserInfoRequestDto;
import com.ycs.sys.domain.dto.PageUserInfoResponseDto;
import com.ycs.sys.domain.dto.QueryAllUserInfoRequestDto;
import com.ycs.sys.domain.dto.QueryAllUserInfoResponseDto;
import com.ycs.sys.domain.dto.QueryMobileExistsRequestDto;
import com.ycs.sys.domain.dto.QueryMobileExistsResponseDto;
import com.ycs.sys.domain.dto.QuerySelfInfoRequestDto;
import com.ycs.sys.domain.dto.QuerySelfInfoResponseDto;
import com.ycs.sys.domain.dto.QueryUserInfoRequestDto;
import com.ycs.sys.domain.dto.QueryUserInfoResponseDto;
import com.ycs.sys.domain.dto.RegisterRequestDto;
import com.ycs.sys.domain.dto.RegisterResponseDto;
import com.ycs.sys.domain.dto.SendIdentifyCodeResponseDto;
import com.ycs.sys.domain.dto.UpdateSelfInfoRequestDto;
import com.ycs.sys.domain.dto.UpdateUserInfoRequestDto;

/**
 * 用户信息Service接口
 * @author youcyousyunn
 * @date 2018年1月28日
 */
public interface IUserInfoService {

	/**
	 * 添加用户信息
	 * @param request 添加请求
	 * @return AddUserInfoResponseDto 添加结果
	 * @throws HiBusinessReturnException 运行时异常
	 */
	AddUserInfoResponseDto addUserInfo(AddUserInfoRequestDto request) throws HiBusinessReturnException;

	/**
	 * 注册
	 * @param registerRequest
	 * @return RegisterResponseDto
	 * @throws HiBusinessReturnException
	 */
	RegisterResponseDto register(RegisterRequestDto registerRequest) throws HiBusinessReturnException;
	
	/**
	 * 登录
	 * @param loginRequest 登录请求
	 * @return LoginResponseDto  登录结果
	 * @throws HiBusinessReturnException 运行时异常
	 */
	LoginResponseDto login(LoginRequestDto loginRequest) throws HiBusinessReturnException;

	/**
     * 分页查询用户信息
     * @param request 请求
     * @return 响应
     * @throws HiBusinessReturnException 异常
     */
	PageUserInfoResponseDto queryPage(PageUserInfoRequestDto request) throws HiBusinessReturnException;

	/**
     * 查询用户信息
     * @param request 请求
     * @return 响应
     * @throws HiBusinessReturnException 异常
     */
	QueryUserInfoResponseDto queryUserInfo(QueryUserInfoRequestDto request) throws HiBusinessReturnException;

	/**
     * 更新用户信息
     * @param request 请求
     * @return 响应
     * @throws HiBusinessReturnException 异常
     */
	BaseResponseDto updateUserInfo(UpdateUserInfoRequestDto request) throws HiBusinessReturnException, HiBusinessAbortException;

	/**
     * 查询手机号是否已存在
     * @param request 请求
     * @return 响应
     * @throws HiBusinessReturnException 异常
     */
	QueryMobileExistsResponseDto mobileExists(QueryMobileExistsRequestDto request) throws HiBusinessReturnException;

	/**
     * 删除用户信息
     * @param request 请求
     * @return 响应
     * @throws HiBusinessReturnException 异常
     */
	BaseResponseDto deleteUserInfo(DeleteUserRequestDto request) throws HiBusinessReturnException;

	/**
     * 查询所有用户
     * @param request 请求
     * @return 响应
     * @throws HiBusinessReturnException 异常
     */
	QueryAllUserInfoResponseDto queryUserInfos(QueryAllUserInfoRequestDto request) throws HiBusinessReturnException;

	/**
     * 查询当前登录用户所有信息
     * @param request 请求
     * @return 响应
     * @throws HiBusinessReturnException 异常
     */
	QuerySelfInfoResponseDto queryUserInfo(QuerySelfInfoRequestDto request) throws HiBusinessReturnException;

	/**
     * 更新当前登录用户信息
     * @param request 请求
     * @return 响应
     * @throws HiBusinessReturnException 异常
     */
	BaseResponseDto updateSelfInfo(UpdateSelfInfoRequestDto request) throws HiBusinessReturnException;

	/**
	 * 更新当前用户密码
	 * @param request
	 * @return BaseResponseDto
	 * @throws HiBusinessReturnException;
	 */
	BaseResponseDto updateUserPwd(UpdateSelfInfoRequestDto request) throws HiBusinessReturnException;

	/**
	 * 注册新用户发送验证码
	 * @param
	 * @return SendIdentifyCodeResponseDto
	 * @throws HiBusinessReturnException
	 */
	SendIdentifyCodeResponseDto sendIdentifyCode(HttpSession session) throws HiBusinessReturnException;
	
}
