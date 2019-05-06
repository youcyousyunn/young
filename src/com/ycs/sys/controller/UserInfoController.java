package com.ycs.sys.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ycs.basbo.constants.Constants;
import com.ycs.basbo.constants.HiMsgCdConstants;
import com.ycs.base.annotation.HiOperLog;
import com.ycs.base.controller.IBaseController;
import com.ycs.base.domain.dto.BaseResponseDto;
import com.ycs.base.exception.HiBusinessAbortException;
import com.ycs.base.exception.HiBusinessReturnException;
import com.ycs.base.exception.HiException;
import com.ycs.base.log4j.logger.HiBizLogger;
import com.ycs.base.property.SystemPropertyConfigure;
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
import com.ycs.sys.domain.dto.SendIdentifyCodeRequestDto;
import com.ycs.sys.domain.dto.SendIdentifyCodeResponseDto;
import com.ycs.sys.domain.dto.UpdateSelfInfoRequestDto;
import com.ycs.sys.domain.dto.UpdateUserInfoRequestDto;
import com.ycs.sys.service.IUserInfoService;

/**
 * 用户信息Controller
 * @author youcyousyunn
 * @date 2018年3月13日
 */
@Controller
@RequestMapping(value="/user/v1")
public class UserInfoController extends IBaseController {
	
	/**
	 * 用户信息service
	 */
	@Autowired
    private IUserInfoService userInfoService;
	
	
	/**
     * 添加用户信息
     * @param request 请求
     * @return 响应
     */
	@ResponseBody
    @RequestMapping("/add.do")
	@HiOperLog(title = "添加用户请求", action = "添加用户操作", isSaveData = true, channel = "WEB")
    public AddUserInfoResponseDto addUserInfo(@RequestBody AddUserInfoRequestDto request) {
        try {
            return userInfoService.addUserInfo(request);
        } catch (HiBusinessReturnException e) {
            return new AddUserInfoResponseDto(e.getCode(), e.getMessage());
        }
    }
	
	/**
     * 删除用户
     * @param request 请求
     * @return 响应
     */
    @ResponseBody
    @RequestMapping(value = "/delete.do", method = { RequestMethod.POST })
    @HiOperLog(title = "删除用户", action = "删除操作", isSaveData = true, channel = "WEB")
    public BaseResponseDto deleteUser(@RequestBody DeleteUserRequestDto request) {
        try {
            return userInfoService.deleteUserInfo(request);
        } catch (HiBusinessReturnException e) {
            return new BaseResponseDto(e.getCode(), e.getMessage());
        }
    }
	
	/**
     * 更新用户
     * @param request 请求
     * @return 响应
     */
    @ResponseBody
    @RequestMapping(value = "/update.do", method = { RequestMethod.POST })
    @HiOperLog(title = "更新用户", action = "更新操作", isSaveData = true, channel = "WEB")
    public BaseResponseDto updateUserInfo(@RequestBody UpdateUserInfoRequestDto request) throws HiException{
        //接口报文检查
        if(!request.checkRequestDto()){
            HiBizLogger.info("接口请求报文异常" + request.toString());
            throw new HiBusinessReturnException(HiMsgCdConstants.TX_REQUESTBODY_FAIL, "接口请求报文异常 ");
        }
        BaseResponseDto responseDto = new BaseResponseDto();
        try {
            responseDto = userInfoService.updateUserInfo(request);
        } catch (HiBusinessAbortException e) {
            // 业务处理终止异常HiBusinessAbortException捕获后，需将异常码设置到传输对象，并返回
            // 业务处理终止异常意为，该错误导致业务无法继续进行
            responseDto.setRspCd(e.getCode());
            return responseDto;
        } catch (HiBusinessReturnException e) {
            // 业务处理返回异常HiBusinessReturnException补货后，需检查code，根据返回做特殊处理，比如：忽略错误等
            responseDto.setRspCd(e.getCode());
            return responseDto;
        }
        responseDto.setRspCd(HiMsgCdConstants.SUCCESS);
        return responseDto;
    }
    
    /**
     * 发送验证码操作
     * @param sendIdentifyCode
     * @return 发送验证码响应
     */
    @ResponseBody
    @RequestMapping("/sendIdentifyCode.do")
    @HiOperLog(title = "发送验证码请求", action="发送验证码操作", isSaveData=true, channel="WEB")
    public SendIdentifyCodeResponseDto sendIdentifyCode(@RequestBody SendIdentifyCodeRequestDto sendIdentifyCodeRequestDto, HttpSession session) throws HiException {
        SendIdentifyCodeResponseDto sendIdentifyCodeResponseDto = new SendIdentifyCodeResponseDto();
        try {
            // 发送验证码
        	sendIdentifyCodeResponseDto = userInfoService.sendIdentifyCode(session);
        } catch (HiBusinessReturnException e) {
            return new SendIdentifyCodeResponseDto(HiMsgCdConstants.SEND_IDENTIFY_CODE_FAIL, "注册用户发送验证码失败！");
        }
        sendIdentifyCodeResponseDto.setRspCd(HiMsgCdConstants.SUCCESS);
        return sendIdentifyCodeResponseDto;
    }
    
    /**
     * 注册
     * @param registerRequest 注册请求
     * @return 注册响应
     */
    @ResponseBody
    @RequestMapping("/register.do")
    @HiOperLog(title = "注册请求", action="注册操作", isSaveData=true, channel="WEB")
    public RegisterResponseDto register(@RequestBody RegisterRequestDto registerRequest) throws HiException {
        if (!registerRequest.checkRequestDto()) {
        	HiBizLogger.info("接口请求报文异常" + registerRequest.toString());
            throw new HiBusinessReturnException(HiMsgCdConstants.TX_REQUESTBODY_FAIL, "接口请求报文异常 ");
        }
        RegisterResponseDto registerResponse = new RegisterResponseDto();
        try {
            // 获取注册信息
        	registerResponse = userInfoService.register(registerRequest);
        } catch (HiBusinessReturnException e) {
            return new RegisterResponseDto(HiMsgCdConstants.USERNAME_EXISTED_INVALID, "此用户名已存在！");
        }
        registerResponse.setRspCd(HiMsgCdConstants.SUCCESS);
        return registerResponse;
    }
	
	/**
     * 登录
     * @param loginRequest 登录请求
     * @return 登录响应
     */
    @ResponseBody
    @RequestMapping("/login.do")
    @HiOperLog(title = "登录请求", action = "登录操作", isSaveData = true, channel = "WEB")
    public LoginResponseDto login(@RequestBody LoginRequestDto loginRequest, HttpSession session) throws HiException {
    	if (!loginRequest.checkRequestDto()) {
    		HiBizLogger.info("接口请求报文异常" + loginRequest.toString());
            throw new HiBusinessReturnException(HiMsgCdConstants.TX_REQUESTBODY_FAIL, "接口请求报文异常 ");
    	}
    	LoginResponseDto loginResponse = new LoginResponseDto();
        try {
            // 获取登录信息
            loginResponse = userInfoService.login(loginRequest);
            session.setAttribute(Constants.USER_SESSION_INFO, setLoginSession(loginResponse));
        } catch (HiBusinessReturnException e) {
            return new LoginResponseDto(HiMsgCdConstants.USERNAME_OR_PASSWORD_INVALID, "用户名或密码错误");
        }
        loginResponse.setRspCd(HiMsgCdConstants.SUCCESS);
        return loginResponse;
    }
    
    /**
     * 保存用户信息
     * @param request,session
     * @return LoginResponseDto
     * @throws 
     * youcyousyunn
     * 2019年3月3日
     */
    @ResponseBody
    @RequestMapping("/initUserInfo.do")
    @HiOperLog(title = "保存用户信息请求", action = "保存用户信息操作", isSaveData = true, channel = "WEB")
    public String initUserInfo(HttpServletRequest request) throws HiException {
    	LoginResponseDto loginResponse = new LoginResponseDto();
        loginResponse.setUsrNm(request.getParameter("usrNm"));
		loginResponse.setUsrNo(request.getParameter("usrNo"));
		loginResponse.setMblNo(request.getParameter("mblNo"));
		// 保存用户信息
		HttpSession session = request.getSession(true);
		session.setAttribute(Constants.USER_SESSION_INFO, setLoginSession(loginResponse));
        return "success";
    }
    
    /**
     * 退出登录
     * @param session
     * @return BaseResponseDto
     */
    /*@ResponseBody
    @RequestMapping("/logout.do")
    @HiOperLog(title = "退出登录", action = "退出登录操作", isSaveData = true, channel = "WEB")
    public BaseResponseDto logout(HttpSession session) {
        session.removeAttribute(Constants.USER_SESSION_INFO);
        return new BaseResponseDto(HiMsgCdConstants.SUCCESS);
    }*/

    /**
     * 分页查询用户信息
     * @param request 请求
     * @return 响应
     * @throws HiException 异常
     */
    @RequestMapping("/page/query.do")
    @ResponseBody
    @HiOperLog(title = "分页查询用户", action = "查询操作", isSaveData = true, channel = "WEB")
    public PageUserInfoResponseDto queryPage(@RequestBody PageUserInfoRequestDto request) throws HiException {
        if (null != request) {
            try {
                return userInfoService.queryPage(request);
            } catch (HiBusinessReturnException e) {
            	return new PageUserInfoResponseDto(HiMsgCdConstants.QUERY_USERINFO_FAIL, "查询用户信息失败");
            }
        }
        return new PageUserInfoResponseDto(HiMsgCdConstants.QUERY_USERINFO_FAIL, "查询用户信息失败");
    }
	
    /**
     * 查询用户信息
     * @param request 请求
     * @return 响应
     */
    @ResponseBody
    @RequestMapping(value = "/query.do")
    @HiOperLog(title = "查询用户信息", action = "查询操作", isSaveData = true, channel = "WEB")
    public QueryUserInfoResponseDto queryUserInfo(@RequestBody QueryUserInfoRequestDto request) {
        try {
            return userInfoService.queryUserInfo(request);
        } catch (HiBusinessReturnException e) {
            return new QueryUserInfoResponseDto(e.getCode(), e.getMessage());
        }
    }
    
    /**
     * 查询手机号是否存在
     * @param request 请求
     * @return 响应
     */
    @ResponseBody
    @RequestMapping(value = "/exists/mobile.do")
    @HiOperLog(title = "查询手机号是否存在", action = "查询操作", isSaveData = true, channel = "WEB")
    public QueryMobileExistsResponseDto mobileExists(@RequestBody QueryMobileExistsRequestDto request) {
        try {
            return userInfoService.mobileExists(request);
        } catch (HiBusinessReturnException e) {
            return new QueryMobileExistsResponseDto(e.getCode(), e.getMessage());
        }
    }
    
    /**
     * 查询所有用户
     * @param session session
     * @return 响应
     */
    @ResponseBody
    @RequestMapping("/all.do")
    @HiOperLog(title = "查询所有用户", action = "查询操作", isSaveData = true, channel = "WEB")
    public QueryAllUserInfoResponseDto queryAllUserInfo(@RequestBody QueryAllUserInfoRequestDto request) {
        try {
            return userInfoService.queryUserInfos(request);
        } catch (HiBusinessReturnException e) {
            return new QueryAllUserInfoResponseDto(e.getCode(), e.getMessage());
        }
    }
    
    
    /**
     * 获取登录人的所有信息
     * @param session session
     * @return 响应
     */
    @ResponseBody
    @RequestMapping("/userInfo.do")
    @HiOperLog(title = "个人信息查看请求", action = "个人信息查看操作", isSaveData = true, channel = "WEB")
    public QuerySelfInfoResponseDto userInfo(@RequestBody QuerySelfInfoRequestDto request) throws HiException {
        try {
            return userInfoService.queryUserInfo(request);
        } catch (HiBusinessReturnException e) {
            return new QuerySelfInfoResponseDto(e.getCode(), e.getMessage());
        }
    }
    
    /**
     * 当前登录用户信息更新
     * @param reques 请求
     * @return 响应
     */
    @ResponseBody
    @RequestMapping(value = "/updateSelfInfo.do", method = { RequestMethod.POST })
    @HiOperLog(title = "更新用户", action = "更新操作", isSaveData = true, channel = "WEB")
    public BaseResponseDto updateSelfInfo(@RequestBody UpdateSelfInfoRequestDto request) {
        try {
            return userInfoService.updateSelfInfo(request);
        } catch (HiBusinessReturnException e) {
            return new BaseResponseDto(HiMsgCdConstants.UPDATE_USER_INFO_FAIL, "update user info faild");
        }
    }
    
    /**
     * 用户头像更新
     * @param file 
     * @param request
     * @return BaseResponseDto
     */
    @ResponseBody
    @RequestMapping(value = "/updateSelfHead.do", method = { RequestMethod.POST })
    @HiOperLog(title = "更新当前用户头像", action = "更新操作", isSaveData = true, channel = "WEB")
    public BaseResponseDto updateSelfHead(@RequestBody MultipartFile file, HttpServletRequest request) {
    	if (!file.isEmpty()) {
    		String storePath = request.getSession().getServletContext().getRealPath("../")
    				+ SystemPropertyConfigure.getParams(Constants.IMG_UPLOAD);
    		String fileName = file.getOriginalFilename();
    		File filePath = new File(storePath, fileName);
    		if (!filePath.getParentFile().exists()) {
    			filePath.getParentFile().mkdirs(); // 创建目录
    		}
    		try {
				file.transferTo(new File(storePath + File.separator + fileName)); // 将文件写入目标目录
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
    		return new BaseResponseDto(HiMsgCdConstants.SUCCESS);
    	} else {
    		return new BaseResponseDto(HiMsgCdConstants.UPDATE_USER_HEAD_FAIL, "update user head faild");
    	}
    }
    
    /**
     * 当前登录用户更改密码
     * @param request 请求
     * @return BaseResponseDto 响应
     */
    @ResponseBody
    @RequestMapping(value = "/updateSelfPwd.do", method = { RequestMethod.POST })
    @HiOperLog(title = "更新当前用户密码", action = "更新操作", isSaveData = true, channel = "WEB")
    public BaseResponseDto updateSelfPwd(@RequestBody UpdateSelfInfoRequestDto request) {
        try {
            return userInfoService.updateUserPwd(request);
        } catch (HiBusinessReturnException e) {
            return new BaseResponseDto(HiMsgCdConstants.UPDATE_USER_PASSWORD_FAIL, "update user password faild");
        }
    }
    
    /**
	 * 系统注销操作
	 * @param request
	 * @return void
	 * @throws IOException 
	 */
	@ResponseBody
	@RequestMapping(value={"/logout.do"})
	@HiOperLog(title = "注销请求", action = "注销操作", isSaveData = false, channel = "WEB")
	public void logout(HttpServletRequest request) throws IOException{
		HttpSession session = request.getSession();
	    if (session != null) {
	        session.invalidate();
	    }
	}

}
