package com.ycs.sys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ycs.basbo.constants.HiMsgCdConstants;
import com.ycs.base.annotation.HiOperLog;
import com.ycs.base.controller.IBaseController;
import com.ycs.base.exception.HiBusinessReturnException;
import com.ycs.sys.domain.dto.AddRoleRequestDto;
import com.ycs.sys.domain.dto.AddRoleResponseDto;
import com.ycs.sys.domain.dto.DeleteRoleRequestDto;
import com.ycs.sys.domain.dto.DeleteRoleResponseDto;
import com.ycs.sys.domain.dto.QueryAllRoleRequestDto;
import com.ycs.sys.domain.dto.QueryAllRoleResponseDto;
import com.ycs.sys.domain.dto.QueryRoleExistsRequestDto;
import com.ycs.sys.domain.dto.QueryRoleExistsResponseDto;
import com.ycs.sys.domain.dto.QueryRoleRequestDto;
import com.ycs.sys.domain.dto.QueryRoleResponseDto;
import com.ycs.sys.domain.dto.QueryRolesRequestDto;
import com.ycs.sys.domain.dto.QueryRolesResponseDto;
import com.ycs.sys.domain.dto.UpdateRoleRequestDto;
import com.ycs.sys.domain.dto.UpdateRoleResponseDto;
import com.ycs.sys.service.IRoleService;

/**
 * 角色Controller
 * @author youcyousyunn
 * @date 2018年3月10日
 */
@Controller
@RequestMapping("/admin/v1/role")
public class RoleController extends IBaseController {

	/**
     * 角色service
     */
    @Autowired
    private IRoleService roleService;
	
    
    /**
     * 添加角色
     * @param request 请求
     * @return 响应
     */
    @ResponseBody
    @RequestMapping(value = "/add.do")
    @HiOperLog(title = "添加角色", action = "新增操作", isSaveData = true, channel = "WEB")
    public AddRoleResponseDto addRole(@RequestBody AddRoleRequestDto request) {
        try {
            return roleService.addRole(request);
        } catch (HiBusinessReturnException e) {
            return new AddRoleResponseDto(e.getCode(), e.getMessage());
        }
    }
    
    /**
     * 删除角色
     * @param request 请求
     * @return 响应
     */
    @ResponseBody
    @RequestMapping(value = "/delete.do")
    @HiOperLog(title = "删除角色", action = "删除操作", isSaveData = true, channel = "WEB")
    public DeleteRoleResponseDto deleteRoles(@RequestBody DeleteRoleRequestDto request) {
        try {
            return roleService.deleteRoles(request);
        } catch (HiBusinessReturnException e) {
            return new DeleteRoleResponseDto(e.getCode(), e.getMessage());
        }
    }
    
    /**
     * 更新角色
     * @param request 请求
     * @return 响应
     */
    @ResponseBody
    @RequestMapping(value = "/update.do")
    @HiOperLog(title = "更新角色", action = "更新操作", isSaveData = true, channel = "WEB")
    public UpdateRoleResponseDto updateRole(@RequestBody UpdateRoleRequestDto request) {
        try {
            return roleService.updateRole(request);
        } catch (HiBusinessReturnException e) {
            return new UpdateRoleResponseDto(HiMsgCdConstants.UPDATE_ROLE_FAIL, "update role faild");
        }
    }
	
    /**
     * 分页查询角色
     * @param request 请求
     * @return 响应
     */
    @ResponseBody
    @RequestMapping("/page/query.do")
    @HiOperLog(title = "分页查询角色", action = "查询操作", isSaveData = true, channel = "WEB")
    public QueryRolesResponseDto pageQuery(@RequestBody QueryRolesRequestDto request) {
        try {
            return roleService.pageQuery(null, null, null, null, request.getSearch(), request.getSort(), request.getSortName(),
                    request.getCurrentPage(), request.getPageSize());
        } catch (HiBusinessReturnException e) {
            return new QueryRolesResponseDto(e.getCode(), e.getMessage());
        } catch (Exception e) {
            return new QueryRolesResponseDto(HiMsgCdConstants.QUERY_ROLES_FAIL, "查询角色失败");
        }
    }
    
    /**
     * 查询角色
     * @param request 请求
     * @return QueryRoleResponseDto 响应
     */
    @ResponseBody
    @RequestMapping(value = "/query.do")
    @HiOperLog(title = "查询角色", action = "查询操作 ", isSaveData = true, channel = "WEB")
    public QueryRoleResponseDto queryRole(@RequestBody QueryRoleRequestDto request) {
        try {
            return roleService.queryRole(request);
        } catch (HiBusinessReturnException e) {
            return new QueryRoleResponseDto(e.getCode(), e.getMessage());
        }
    }
    
    /**
     * 查询角色是否存在
     * @param request 请求
     * @return 响应
     */
    @ResponseBody
    @RequestMapping(value = "/exists.do")
    @HiOperLog(title = "查询角色是否存在", action = "查询操作", isSaveData = true, channel = "WEB")
    public QueryRoleExistsResponseDto roleExists(@RequestBody QueryRoleExistsRequestDto request) {
        try {
            boolean isExists = roleService.roleExists(request);
            return new QueryRoleExistsResponseDto(HiMsgCdConstants.SUCCESS, isExists);
        } catch (HiBusinessReturnException e) {
            return new QueryRoleExistsResponseDto(e.getCode(), e.getMessage());
        }
    }
    
    /**
     * 查询所有角色
     * @param request 请求
     * @return 响应
     */
    @ResponseBody
    @RequestMapping(value = "/all.do")
    @HiOperLog(title = "查询所有角色", action = "查询操作", isSaveData = true, channel = "WEB")
    public QueryAllRoleResponseDto queryRole(@RequestBody QueryAllRoleRequestDto request) {
        try {
            return roleService.queryRole();
        } catch (HiBusinessReturnException e) {
            return new QueryAllRoleResponseDto(HiMsgCdConstants.QUERY_ROLE_FAIL, "query role faild");
        }
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
