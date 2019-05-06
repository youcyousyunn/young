package com.ycs.sys.service.impl;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.ycs.basbo.constants.HiMsgCdConstants;
import com.ycs.base.domain.dto.BaseRequestDto;
import com.ycs.base.exception.HiBusinessReturnException;
import com.ycs.base.exception.HiException;
import com.ycs.base.log4j.logger.HiBizLogger;
import com.ycs.base.utils.PageUtil;
import com.ycs.sys.bo.IPermissionBo;
import com.ycs.sys.bo.IRoleBo;
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
import com.ycs.sys.domain.po.RolePo;
import com.ycs.sys.service.IRoleService;

/**
 * 角色Service实现
 * @author youcyousyunn
 * @date 2018年3月10日
 */
@Service
public class RoleServiceImpl implements IRoleService {

	/**
     * roleBo 角色Bo
     */
    @Autowired
    private IRoleBo roleBo;
    
    /**
     * permissionBo 权限Bo
     */
    @Autowired
    private IPermissionBo permissionBo;

    
	@Override
	@Transactional(readOnly = true)
	public QueryRolesResponseDto pageQuery(String roleNo, String status, String name, Date createDate, String search, String sort,
            String sortName, Integer currentPage, Integer pageSize) throws HiBusinessReturnException {
		// 首先查询共有多少记录
        int totalCount = roleBo.getCount(roleNo, status, name, createDate, search, sort, sortName);
        // 计算分页信息
        PageUtil.calculatePageInfo(totalCount, pageSize, currentPage);
        // 分页查询
        List<RolePo> roles = roleBo.pageQuery(roleNo, status, name, createDate, search, sort, sortName, PageUtil.getStartRow(),
                PageUtil.getOffset());
        // 组装响应信息
        if (!CollectionUtils.isEmpty(roles)) {
            QueryRolesResponseDto response = new QueryRolesResponseDto();
            response.setRows(roles);
            response.setTotal(PageUtil.getTotalCount());
            response.setRspCd(HiMsgCdConstants.SUCCESS);
            return response;
        }
        throw new HiBusinessReturnException(HiMsgCdConstants.QUERY_ROLES_EMPTY, "查询角色数据返回为empty");
	}

	@Override
	@Transactional(readOnly = true)
	public QueryRoleResponseDto queryRole(QueryRoleRequestDto request) throws HiBusinessReturnException {
		if (null != request) {
            RolePo role = roleBo.queryRole(request.getRoleNo());
            if (null != role) {
                Set<String> roleNos = new HashSet<>();
                roleNos.add(request.getRoleNo());
                Set<Integer> permissionIds = permissionBo.queryPermissionsByRoleNos(roleNos);
                return new QueryRoleResponseDto(HiMsgCdConstants.SUCCESS, role, permissionIds);
            }
        }
        HiBizLogger.error(HiMsgCdConstants.QUERY_ROLE_FAIL, "query role faild");
        throw new HiBusinessReturnException(HiMsgCdConstants.QUERY_ROLE_FAIL, "query role faild");
	}

	@Override
	@Transactional(rollbackFor = { HiBusinessReturnException.class })
	public UpdateRoleResponseDto updateRole(UpdateRoleRequestDto request) throws HiBusinessReturnException {
		if (null != request) {
            try {
                RolePo role = BaseRequestDto.trans2Entity(request, RolePo.class);
                int result = roleBo.updateRole(role);
                if (result > 0) {
                    updateRolePermission(role.getRoleNo(), request.getPermissionNos());
                    return new UpdateRoleResponseDto(HiMsgCdConstants.SUCCESS);
                }
            } catch (HiException e) {
                HiBizLogger.error(HiMsgCdConstants.UPDATE_ROLE_FAIL, "trans2RolePo faild");
            }
        }
        throw new HiBusinessReturnException(HiMsgCdConstants.UPDATE_ROLE_FAIL, "update role faild");
	}
	
	/**
     * 更新角色的权限信息
     * @param roleNo 角色编号
     * @param permissionIds 权限编号集合
     * @throws HiBusinessReturnException 异常
     */
    private void updateRolePermission(String roleNo, Set<Integer> permissionNos) throws HiBusinessReturnException {
        // 角色权限不管是否为空全部删除重新添加
        Set<String> roleNos = new HashSet<>();
        roleNos.add(roleNo);
        int deleteresult = permissionBo.deleteRolePermission(roleNos);
        // 删除失败抛出异常
        if (deleteresult == -1) {
            HiBizLogger.error(HiMsgCdConstants.DELETE_ROLE_PERMISSION_FAIL, "delete role permission faild");
            throw new HiBusinessReturnException(HiMsgCdConstants.DELETE_ROLE_PERMISSION_FAIL,
                    "delete role permission faild");
        }
        // 添加失败抛出异常
        if (!CollectionUtils.isEmpty(permissionNos)) {
            int addresult = permissionBo.addRolePermission(roleNo, permissionNos);
            if (addresult == 0) {
                HiBizLogger.error(HiMsgCdConstants.ADD_ROLE_PERMISSION_FAIL, "add role permission faild");
                throw new HiBusinessReturnException(HiMsgCdConstants.ADD_ROLE_PERMISSION_FAIL,
                        "add role permission faild");
            }
        }
    }

	@Override
	@Transactional(rollbackFor = { HiBusinessReturnException.class })
	public DeleteRoleResponseDto deleteRoles(DeleteRoleRequestDto request) throws HiBusinessReturnException {
		if (null != request) {
            Set<String> roleNos = request.getRoleNos();
            // 已经有关系的角色
            Set<String> existsRoleNos = roleBo.queryUserRole(roleNos);
            // 如果角色全部被关联则直接提示失败
            if (!CollectionUtils.isEmpty(existsRoleNos) && roleNos.size() == existsRoleNos.size()) {
                throw new HiBusinessReturnException(HiMsgCdConstants.ALL_ROLE_RELATED_USER, "all role related user");
            }

            if (!CollectionUtils.isEmpty(existsRoleNos)) {
                // 在集合中删除已经被用户关联的角色
                roleNos.removeAll(existsRoleNos);
            }
            int r = permissionBo.deleteRolePermission(roleNos);
            if (r >= 0) {
                return deleteRoles(roleNos, existsRoleNos);
            } else {
                throw new HiBusinessReturnException(HiMsgCdConstants.DELETE_ROLE_PERMISSION_FAIL,
                        "delete role permission faild");
            }
        }
        throw new HiBusinessReturnException(HiMsgCdConstants.DELETE_ROLE_FAIL, "delete role faild");
	}

	/**
     * 删除角色
     * @param roleNos 角色编号集合
     * @param existsRoleNos 已经关联的角色
     * @return 响应
     * @throws HiBusinessReturnException 异常
     */
	private DeleteRoleResponseDto deleteRoles(Set<String> roleNos, Set<String> existsRoleNos) throws HiBusinessReturnException {
		// 删除数据库中未被关联的角色
        int result = roleBo.deleteRoles(roleNos);
        if (result > 0) {
            if (CollectionUtils.isEmpty(existsRoleNos)) {
                return new DeleteRoleResponseDto(HiMsgCdConstants.SUCCESS);
            }
            return new DeleteRoleResponseDto(HiMsgCdConstants.PARTIAL_SUCCESS, roleNos, existsRoleNos);
        }
        throw new HiBusinessReturnException(HiMsgCdConstants.DELETE_ROLE_FAIL, "delete role faild");
	}

	@Override
	@Transactional(rollbackFor = { HiBusinessReturnException.class })
	public AddRoleResponseDto addRole(AddRoleRequestDto request) throws HiBusinessReturnException {
		if (null != request) {
            try {
                RolePo role = BaseRequestDto.trans2Entity(request, RolePo.class);
                int result = roleBo.addRole(role);
                if (result > 0) {
                    // 添加角色权限
                    if (!CollectionUtils.isEmpty(request.getPermissionNos())) {
                        int rs = permissionBo.addRolePermission(role.getRoleNo(), request.getPermissionNos());
                        if (rs <= 0) {
                            throw new HiBusinessReturnException(HiMsgCdConstants.ADD_ROLE_PERMISSION_FAIL, "add role permission fail");
                        }
                    }
                    return new AddRoleResponseDto(HiMsgCdConstants.SUCCESS, "添加角色成功");
                }
            } catch (HiException e) {
                // 记录日志
                HiBizLogger.error(e.getCode(), e.getMessage(), e);
            }

        }
        throw new HiBusinessReturnException(HiMsgCdConstants.ADD_ROLE_FAIL, "add role fail");
	}

	@Override
	@Transactional(readOnly = true)
	public boolean roleExists(QueryRoleExistsRequestDto request) throws HiBusinessReturnException {
		if (null != request) {
            int count = roleBo.queryRoleExists(request.getRoleNo());
            if (count == 0) {
                return false;
            } else if (count > 0) {
                return true;
            }
        }
        throw new HiBusinessReturnException(HiMsgCdConstants.QUERY_ROLE_ISEXISTS_FAIL, "query role exists fail");
	}

	@Override
	@Transactional(readOnly = true)
	public QueryAllRoleResponseDto queryRole() throws HiBusinessReturnException {
		Set<RolePo> roles = roleBo.queryRoles();
        if (!CollectionUtils.isEmpty(roles)) {
            return new QueryAllRoleResponseDto(HiMsgCdConstants.SUCCESS, roles);
        }
        throw new HiBusinessReturnException(HiMsgCdConstants.QUERY_ROLE_FAIL, "query role faild");
	}
    
	
	
    
	
	
	

}
