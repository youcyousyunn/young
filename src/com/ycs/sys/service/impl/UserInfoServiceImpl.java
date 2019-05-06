package com.ycs.sys.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.ycs.basbo.constants.Constants;
import com.ycs.basbo.constants.HiMsgCdConstants;
import com.ycs.basbo.utils.JrnGenerator;
import com.ycs.base.domain.dto.BaseRequestDto;
import com.ycs.base.domain.dto.BaseResponseDto;
import com.ycs.base.encrypt.EncryptUtil;
import com.ycs.base.exception.HiBusinessAbortException;
import com.ycs.base.exception.HiBusinessReturnException;
import com.ycs.base.exception.HiException;
import com.ycs.base.log4j.logger.HiBizLogger;
import com.ycs.base.utils.PageUtil;
import com.ycs.base.utils.RedisUtil;
import com.ycs.msg.utils.GenerateCodeUtil;
import com.ycs.msg.utils.MailInfo;
import com.ycs.msg.utils.SendEmailUtil;
import com.ycs.sys.bo.IRoleBo;
import com.ycs.sys.bo.IUserInfoBo;
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
import com.ycs.sys.domain.po.RolePo;
import com.ycs.sys.domain.po.UserInfoPo;
import com.ycs.sys.service.IUserInfoService;

/**
 * 用户信息Service实现类
 * @author youcyousyunn
 * @date 2018年1月28日
 */
@Service
public class UserInfoServiceImpl implements IUserInfoService {

	/**
	 * 用户信息BO
	 */
	@Autowired
    private IUserInfoBo userInfoBo;
	
	/**
     * 角色BO
     */
    @Autowired
    private IRoleBo roleBo;
    
    /**
     * Redis
     */
    @Autowired
    private RedisUtil redisUtil;
	
	
	@Override
	@Transactional(rollbackFor = { HiBusinessReturnException.class })
	public AddUserInfoResponseDto addUserInfo(AddUserInfoRequestDto request) throws HiBusinessReturnException {
		if (null != request) {
            try {
                // 转换成UserInfoPo
                UserInfoPo userInfo = BaseRequestDto.trans2Entity(request, UserInfoPo.class);
                // 生成用户编号
                userInfo.setUsrNo("USR" + JrnGenerator.genJrnNo());
                // 初始化密码
                userInfo.setUsrPwd("123456");
                int result = userInfoBo.addUserInfo(userInfo);
                if (result > 0) {
                    if (!CollectionUtils.isEmpty(request.getRoleNos())) {
                        // 添加角色关联
                        int roleResult = roleBo.addUserRoles(userInfo.getUsrNo(), request.getRoleNos());
                        if (0 == roleResult) {
                            throw new HiBusinessReturnException(HiMsgCdConstants.ADD_USER_ROLE_FAIL, "add user role faild");
                        }
                    }
                    return new AddUserInfoResponseDto(HiMsgCdConstants.SUCCESS, "添加用户成功");
                }
            } catch (HiException e) {
                throw new HiBusinessReturnException(HiMsgCdConstants.SYS_FAIL, "system error");
            }

        }
		throw new HiBusinessReturnException(HiMsgCdConstants.ADD_USER_FAIL, "add user fail request can not be null");
	}

	@Override
	@Transactional(rollbackFor = { HiBusinessReturnException.class })
	public RegisterResponseDto register(RegisterRequestDto registerRequest) throws HiBusinessReturnException {
		// 查询用户是否存在
        UserInfoPo userInfo = userInfoBo.queryByLoginName(registerRequest.getLoginNm());
        if (null != userInfo) {
        	throw new HiBusinessReturnException(HiMsgCdConstants.USERNAME_EXISTED_INVALID, "用户名已存在");
        } else {
        	try {
        		userInfo = new UserInfoPo();
        		userInfo.setUsrNm(registerRequest.getLoginNm());
        		// MD5加密
        		String password = EncryptUtil.encryptPwd(registerRequest.getPassword());
        		userInfo.setUsrPwd(password);
        		userInfo.setEmail(registerRequest.getEmail());
        		// 以下用户信息暂时后台默认
        		userInfo.setUsrNo(Constants.USER + JrnGenerator.genUsrNo());
        		userInfo.setDeparNo("17");
        		userInfo.setUsrSts("S");
        		boolean result = userInfoBo.register(userInfo);
        		if (!result) {
        			throw new HiBusinessReturnException(HiMsgCdConstants.SYS_FAIL, "Invalid login or password");
        		} else {
        			return new RegisterResponseDto(HiMsgCdConstants.SUCCESS, "注册成功");
        		}
            } catch (HiException e) {
                throw new HiBusinessReturnException(HiMsgCdConstants.SYS_FAIL, "Invalid login or password");
            }
        }
	}

	@Override
	@Transactional(readOnly = true)
	public LoginResponseDto login(LoginRequestDto loginRequest) throws HiBusinessReturnException {
        // 查询用户是否存在
        UserInfoPo userInfo = userInfoBo.queryByLoginName(loginRequest.getLoginNm());
        if (null != userInfo) {
            if(!"S".equals(userInfo.getUsrSts())){
                throw new HiBusinessReturnException(HiMsgCdConstants.USER_INFO_NOT_FOUND, "用户信息已注销");
            }
            // 查询到用户信息，比较密码是否正确
            String password = loginRequest.getPassword();
            if (EncryptUtil.encryptPwd(password).equals(userInfo.getUsrPwd())) {
                try {
                    LoginResponseDto response = BaseResponseDto.entity2Trans(userInfo, LoginResponseDto.class);
                    return response;
                } catch (HiException e) {
                    throw new HiBusinessReturnException(HiMsgCdConstants.SYS_FAIL, "Invalid login or password");
                }
            }
        }
        throw new HiBusinessReturnException(HiMsgCdConstants.QUERY_USERINFO_FAIL, "查询用户失败");
	}

	@Override
	@Transactional(readOnly = true)
	public PageUserInfoResponseDto queryPage(PageUserInfoRequestDto request) throws HiBusinessReturnException {
		if (null != request) {
            // 请求参数设置
            Map<String, Object> parameterMap = new HashMap<>();
            parameterMap.put("search", request.getSearch());
            parameterMap.put("sex", request.getSex());
            parameterMap.put("status", request.getStatus());
            UserInfoPo userInfoPo = userInfoBo.queryUserInfo(request.getUsrNo());
            if (null == userInfoPo) {
                throw new HiBusinessReturnException(HiMsgCdConstants.QUERY_USERINFO_FAIL, "查询用户失败");
            }
            
            //查询全部的时候只能查用户当前部门的以及子部门
            if (0 == request.getDeparNo()) {
                parameterMap.put("deparNo", userInfoPo.getDeparNo());
            } else {
                parameterMap.put("deparNo", request.getDeparNo());
            }
            
            // 删除parameter中值为空和null的参数
            Iterator<Entry<String, Object>> iterator = parameterMap.entrySet().iterator();
            while(iterator.hasNext()){
            	Map.Entry<String, Object> entry = iterator.next();
            	String key = entry.getKey();
            	if(null == parameterMap.get(key) || "".equals(parameterMap.get(key))){
            		iterator.remove();
            	}
            }
            
            // 查询共有几条记录
            int totalCount = userInfoBo.getCount(parameterMap, request.getSort(), request.getSortName());
            if (0 == totalCount) {
                return new PageUserInfoResponseDto(PageUtil.getTotalCount(), new ArrayList<>(), HiMsgCdConstants.SUCCESS);
            }
            if (totalCount > 0) {
                // 计算分页数据
                PageUtil.calculatePageInfo(totalCount, request.getPageSize(), request.getCurrentPage());
                // 分页查询
                List<UserInfoPo> userInfos = userInfoBo.queryPage(parameterMap, request.getSort(), request.getSortName(),
                        PageUtil.getStartRow(), PageUtil.getOffset());
                if (!CollectionUtils.isEmpty(userInfos)) {
                    return new PageUserInfoResponseDto(PageUtil.getTotalCount(), userInfos, HiMsgCdConstants.SUCCESS);
                }
            }
        }
        throw new HiBusinessReturnException(HiMsgCdConstants.QUERY_USERINFO_FAIL, "查询用户信息失败");
	}

	@Override
	@Transactional(readOnly = true)
	public QueryUserInfoResponseDto queryUserInfo(QueryUserInfoRequestDto request) throws HiBusinessReturnException {
		if (null != request) {
            UserInfoPo userInfo = userInfoBo.queryUserInfo(request.getUsrNo());
            if (null != userInfo) {
                Set<RolePo> roles = roleBo.queryRolesByUsrNo(userInfo.getUsrNo());
                return new QueryUserInfoResponseDto(HiMsgCdConstants.SUCCESS, userInfo, roles);
            }
        }
        throw new HiBusinessReturnException(HiMsgCdConstants.QUERY_USERINFO_FAIL, "query userinfo faild");
	}

	@Override
	@Transactional(rollbackFor = {HiBusinessReturnException.class,HiBusinessAbortException.class })
	public BaseResponseDto updateUserInfo(UpdateUserInfoRequestDto request) throws HiBusinessReturnException, HiBusinessAbortException {
        if(request.getUserNo().equals(request.getUsrNo())){
            throw new HiBusinessReturnException(HiMsgCdConstants.UPDATE_USER_INFO_FAIL,"自己不能更改自己部门");
        }
        UserInfoPo userInfo = new UserInfoPo();
        try {
            userInfo = BaseRequestDto.trans2Entity(request, UserInfoPo.class);
        } catch (HiException e) {
            HiBizLogger.error(HiMsgCdConstants.UPDATE_USER_INFO_FAIL, "trans2UserInfo faild", e);
            e.printStackTrace();
        }
        userInfo.setUsrNo(request.getUserNo());
        int result = userInfoBo.updateUserInfo(userInfo);
        if (result > 0) {
            // 删除用户角色
            int delete = roleBo.deleteUserRole(userInfo.getUsrNo());
            if (1 != delete) {
                throw new HiBusinessReturnException(HiMsgCdConstants.DELETE_USER_ROLE_FAIL,"删除用户角色失败");
            }
            
            // 重新添加用户角色
            if (!CollectionUtils.isEmpty(request.getRoleNos())) {
                int add = roleBo.addUserRoles(userInfo.getUsrNo(), request.getRoleNos());
                if (add == 0) {
                    throw new HiBusinessReturnException(HiMsgCdConstants.ADD_USER_ROLE_FAIL,"添加用户角色失败");
                }
            }
        }else{
            throw new HiBusinessReturnException(HiMsgCdConstants.UPDATE_USER_INFO_FAIL,"修改用户信息失败");
        }
        BaseResponseDto response = new BaseResponseDto();
        return response;
	}

	@Override
	@Transactional(readOnly = true)
	public QueryMobileExistsResponseDto mobileExists(QueryMobileExistsRequestDto request) throws HiBusinessReturnException {
		if (null != request) {
            int result = userInfoBo.mobileExists(request.getMobileNo());
            if (result != -1) {
                return new QueryMobileExistsResponseDto(HiMsgCdConstants.SUCCESS, 0 == result ? false : true);
            }
        }
        throw new HiBusinessReturnException(HiMsgCdConstants.QUERY_MOBILE_EXISTS_FAIL, "query mobile exists faild");
	}

	@Override
	@Transactional(rollbackFor = { HiBusinessReturnException.class })
	public BaseResponseDto deleteUserInfo(DeleteUserRequestDto request) throws HiBusinessReturnException {
		if (null != request) {
            int result = userInfoBo.deleteUserInfo(request.getUsrNos());
            if (result > 0) {
                return new BaseResponseDto(HiMsgCdConstants.SUCCESS);
            }
        }
        throw new HiBusinessReturnException(HiMsgCdConstants.DELETE_USER_INFO_FAIL, "delete user info faild");
	}

	@Override
	@Transactional(readOnly = true)
	public QueryAllUserInfoResponseDto queryUserInfos(QueryAllUserInfoRequestDto request) throws HiBusinessReturnException {
		if (null != request) {
            List<UserInfoPo> userInfos = userInfoBo.queryUserInfos(request.getUsrNo(), request.getUsrNm());
            if (null != userInfos) {
                return new QueryAllUserInfoResponseDto(HiMsgCdConstants.SUCCESS, userInfos);
            }
        }
        throw new HiBusinessReturnException(HiMsgCdConstants.QUERY_USERINFO_FAIL, "query userinfo faild");
	}

	@Override
	public QuerySelfInfoResponseDto queryUserInfo(QuerySelfInfoRequestDto request) throws HiBusinessReturnException {
		UserInfoPo userInfo = null;
		if (null != request) {
			// 先根据用户帐户从Redis缓存中查找用户信息
			if (redisUtil.exists(request.getUsrNo())){
				userInfo = (UserInfoPo) redisUtil.get(request.getUsrNo());
			} else{
				userInfo = userInfoBo.queryUserInfo(request.getUsrNo());
				redisUtil.set(request.getUsrNo(), userInfo, 1l, TimeUnit.MINUTES);
			}
            if (null != userInfo) {
                Set<RolePo> roles = roleBo.queryRolesByUsrNo(userInfo.getUsrNo());
                return new QuerySelfInfoResponseDto(HiMsgCdConstants.SUCCESS, userInfo, roles);
            }
        }
        throw new HiBusinessReturnException(HiMsgCdConstants.QUERY_USERINFO_FAIL, "query userinfo faild");
	}

	@Override
	@Transactional(rollbackFor = {HiBusinessReturnException.class })
	public BaseResponseDto updateSelfInfo(UpdateSelfInfoRequestDto request) throws HiBusinessReturnException {
		if (null != request) {
            try {
                UserInfoPo userInfo = BaseRequestDto.trans2Entity(request, UserInfoPo.class);
                userInfo.setUsrNo(request.getUsrNo());
                int result = userInfoBo.updateSelfInfo(userInfo);
                if (result > 0) {
                    return new BaseResponseDto(HiMsgCdConstants.SUCCESS);
                }
            } catch (HiException e) {
                HiBizLogger.error(HiMsgCdConstants.UPDATE_USER_INFO_FAIL, "trans2UserInfo faild", e);
            }
        }
        throw new HiBusinessReturnException(HiMsgCdConstants.UPDATE_USER_INFO_FAIL, "update user info faild");
	}

	@Override
	@Transactional(rollbackFor = { HiBusinessReturnException.class })
	public BaseResponseDto updateUserPwd(UpdateSelfInfoRequestDto request) throws HiBusinessReturnException {
		if (null != request) {
            try {
                UserInfoPo userInfo = BaseRequestDto.trans2Entity(request, UserInfoPo.class);
                userInfo.setUsrNo(request.getUsrNo());
                boolean result = userInfoBo.updateSelfPwd(userInfo);
                if (result) {
                    return new BaseResponseDto(HiMsgCdConstants.SUCCESS);
                }
            } catch (HiException e) {
                HiBizLogger.error(HiMsgCdConstants.UPDATE_USER_PASSWORD_FAIL, "trans2UserInfo faild", e);
            }

        }
        throw new HiBusinessReturnException(HiMsgCdConstants.UPDATE_USER_PASSWORD_FAIL, "update user password faild");
	}

	@Override
	public SendIdentifyCodeResponseDto sendIdentifyCode(HttpSession session)
			throws HiBusinessReturnException {
		MailInfo mailInfo = new MailInfo();
		mailInfo.setSubject("注册用户验证码");
		String code = GenerateCodeUtil.generateEmailCode();
		mailInfo.setText("请复制以下验证码: "+code);
		mailInfo.setTo("731781984@qq.com");
		mailInfo.setFrom("2033641350@qq.com");
		mailInfo.setCode(code);
		try {
			String result = SendEmailUtil.sendIdentifyCodeTextEmail(mailInfo);
			session.setMaxInactiveInterval(2*60); // 设置2分钟后失效
			session.setAttribute(session.getId(), result);
			if (!StringUtils.isEmpty(result)) {
                return new SendIdentifyCodeResponseDto(HiMsgCdConstants.SUCCESS);
            }
		} catch (Exception e) {
			HiBizLogger.error(HiMsgCdConstants.SEND_IDENTIFY_CODE_FAIL, "send identify code failed!!", e);
		}
		throw new HiBusinessReturnException(HiMsgCdConstants.SEND_IDENTIFY_CODE_FAIL, "send identify code failed!!");
	}
	
	
}
