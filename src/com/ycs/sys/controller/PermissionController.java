package com.ycs.sys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ycs.basbo.constants.HiMsgCdConstants;
import com.ycs.base.annotation.HiOperLog;
import com.ycs.base.controller.IBaseController;
import com.ycs.base.domain.dto.BaseResponseDto;
import com.ycs.base.exception.HiBusinessReturnException;
import com.ycs.base.exception.HiException;
import com.ycs.sys.domain.dto.AddPermissionRequestDto;
import com.ycs.sys.domain.dto.AddPermissionResponseDto;
import com.ycs.sys.domain.dto.DeletePermissionRequestDto;
import com.ycs.sys.domain.dto.GetPermissionRequestDto;
import com.ycs.sys.domain.dto.GetPermissionResponseDto;
import com.ycs.sys.domain.dto.UpdatePermissionRequestDto;
import com.ycs.sys.domain.dto.UpdatePermissionResponseDto;
import com.ycs.sys.service.IPermissionService;

@Controller
@RequestMapping("/permission/v1")
public class PermissionController extends IBaseController {

	/**
     * 菜单service
     */
    @Autowired
    private IPermissionService permissionService;
    
    
    /**
     * 查询菜单
     * @param request 请求
     * @return GetPermissionResponseDto 查询响应
     * @throws HiException 异常
     */
    @ResponseBody
    @RequestMapping("/menu/query/all.do")
    @HiOperLog(title = "获取菜单", action = "查询菜单操作", isSaveData = true, channel = "WEB")
    public GetPermissionResponseDto getMenu(@RequestBody GetPermissionRequestDto request) throws HiException {
        try {
            return permissionService.getMenus(request.getUsrNo());
        } catch (HiBusinessReturnException e) {
            return new GetPermissionResponseDto(HiMsgCdConstants.GET_MENUS_ERROR, "获取菜单失败");
        }
    }
    
    /**
     * 添加权限
     * @param request 请求
     * @return AddPermissionResponseDto 添加响应
     */
    @ResponseBody
    @RequestMapping("/add.do")
    @HiOperLog(title = "添加权限", action = "新增权限操作", isSaveData = true, channel = "WEB")
    public AddPermissionResponseDto addPermission(@RequestBody AddPermissionRequestDto request) {
        try {
            return permissionService.addPermission(request);
        } catch (HiBusinessReturnException e) {
            return new AddPermissionResponseDto(e.getCode(), e.getMessage());
        }
    }
    
    /**
     * 删除权限
     * @param request 请求
     * @return BaseResponseDto 删除响应
     */
    @ResponseBody
    @RequestMapping(value = "delete.do", method = { RequestMethod.POST })
    @HiOperLog(title = "删除权限", action = "删除权限操作", isSaveData = true, channel = "WEB")
    public BaseResponseDto deletePermission(@RequestBody DeletePermissionRequestDto request) {
        try {
            return permissionService.deletePermission(request.getPermissionNos());
        } catch (HiBusinessReturnException e) {
            return new BaseResponseDto(e.getCode(), e.getMessage());
        }
    }
    
    /**
     * 修改权限
     * @param request 请求
     * @return UpdatePermissionResponseDto
     */
    @ResponseBody
    @RequestMapping("/update.do")
    @HiOperLog(title = "更新权限", action = "更新权限操作", isSaveData = true, channel = "WEB")
    public UpdatePermissionResponseDto updatePermission(@RequestBody UpdatePermissionRequestDto request) {
        try {
            return permissionService.updatePermission(request);
        } catch (HiBusinessReturnException e) {
            return new UpdatePermissionResponseDto(e.getCode(), e.getMessage());
        }
    }
    
    /**
     * 查询权限
     * @param request 请求
     * @return GetPermissionResponseDto 查询响应
     * @throws HiException 异常
     */
    @ResponseBody
    @RequestMapping("/query/all.do")
    @HiOperLog(title = "获取权限", action = "查询权限操作", isSaveData = true, channel = "WEB")
    public GetPermissionResponseDto getPermissions(@RequestBody GetPermissionRequestDto request) throws HiException {
        try {
            GetPermissionResponseDto response = permissionService.getPermissions();
            return response;
        } catch (HiBusinessReturnException e) {
            return new GetPermissionResponseDto(HiMsgCdConstants.GET_PERMISSION_RETURN_EMPTY, "获取权限失败");
        }
    }
	
	
	
	
	
}
