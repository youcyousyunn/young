package com.ycs.sys.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.ycs.basbo.constants.HiMsgCdConstants;
import com.ycs.base.domain.dto.BaseResponseDto;
import com.ycs.base.exception.HiBusinessReturnException;
import com.ycs.base.log4j.logger.HiBizLogger;
import com.ycs.sys.bo.IPermissionBo;
import com.ycs.sys.bo.IRoleBo;
import com.ycs.sys.domain.dto.AddPermissionRequestDto;
import com.ycs.sys.domain.dto.AddPermissionResponseDto;
import com.ycs.sys.domain.dto.GetPermissionResponseDto;
import com.ycs.sys.domain.dto.UpdatePermissionRequestDto;
import com.ycs.sys.domain.dto.UpdatePermissionResponseDto;
import com.ycs.sys.domain.po.PermissionPo;
import com.ycs.sys.domain.vo.PermissionVO;
import com.ycs.sys.service.IPermissionService;

/**
 * 权限Service实现
 * @author youcyousyunn
 * @date 2018年2月11日
 */
@Service
public class PermissionServiceImpl implements IPermissionService {

	/**
     * 权限BO
     */
    @Autowired
    private IPermissionBo iPermissionBo;
    
    /**
     * 角色BO
     */
    @Autowired
    private IRoleBo iRoleBo;
	
    @Override
    @Transactional(readOnly = true)
    public GetPermissionResponseDto getMenus(String userNo) throws HiBusinessReturnException {
        if (null != userNo) {
        	List<String> roleNos = iRoleBo.queryUserRoles(userNo);
            List<PermissionPo> menuPos = iPermissionBo.queryMenu(roleNos);
            if (!CollectionUtils.isEmpty(menuPos)) {
                List<PermissionVO> menus = generatorPermissionTree(menuPos, 0);
                GetPermissionResponseDto response = new GetPermissionResponseDto(HiMsgCdConstants.SUCCESS, menus);
                return response;
            }
            HiBizLogger.error(HiMsgCdConstants.GET_MENUS_RETURN_EMPTY, "查询菜单返回数量为empty");
            throw new HiBusinessReturnException(HiMsgCdConstants.GET_MENUS_RETURN_EMPTY, "Get menus return empty");
        }
        HiBizLogger.error(HiMsgCdConstants.GET_MENUS_ERROR, "查询菜单失败,用户编号为空");
        throw new HiBusinessReturnException(HiMsgCdConstants.GET_MENUS_ERROR, "Get menus error userNo is null");
    }
    
    
    /**
     * 递归生成权限树
     * @param menus 权限PO集合
     * @param parentId 父ID
     * @return 权限树集合
     */
    private List<PermissionVO> generatorPermissionTree(List<PermissionPo> permissions, Integer parentId) {
        List<PermissionVO> perVoLst = new ArrayList<PermissionVO>();
        for (PermissionPo perPo : permissions) {
            PermissionVO perVo = convertToPermissionVO(perPo);
            if (perPo.getfPerNo().intValue() == parentId.intValue()) {
                List<PermissionVO> ps = generatorPermissionTree(permissions, perVo.getPerNo());
                perVo.setChildPermissions(ps);
                perVoLst.add(perVo);
            }
        }
        return perVoLst;
    }
    
    /**
     * 转换PermissionPo为PermissionVO
     * @param menuPo 权限PO
     * @return 权限VO
     */
    private PermissionVO convertToPermissionVO(PermissionPo perPo) {
        return new PermissionVO(perPo.getPerNo(), perPo.getPerLv(), perPo.getPerType(), perPo.getfPerNo(),
                perPo.getPerSts(), perPo.getPerIco(), perPo.getPerUrl(), perPo.getPerNm(), perPo.getPerDesc());
    }


	@Override
	@Transactional(readOnly = true)
	public GetPermissionResponseDto getPermissions() throws HiBusinessReturnException {
		List<PermissionPo> menuPos = iPermissionBo.queryPermissions(null, null, null);
        if (!CollectionUtils.isEmpty(menuPos)) {
            List<PermissionVO> menus = generatorPermissionTree(menuPos, 0);
            GetPermissionResponseDto response = new GetPermissionResponseDto(HiMsgCdConstants.SUCCESS, menus);
            return response;
        }
        HiBizLogger.error(HiMsgCdConstants.GET_PERMISSION_RETURN_EMPTY, "查询权限返回数量为empty");
        throw new HiBusinessReturnException(HiMsgCdConstants.GET_PERMISSION_RETURN_EMPTY, "Get menus return empty");
	}


	@Override
	@Transactional(rollbackFor = HiBusinessReturnException.class)
	public UpdatePermissionResponseDto updatePermission(UpdatePermissionRequestDto request)
			throws HiBusinessReturnException {
		if (null != request) {
            PermissionPo permissionPo = new PermissionPo(request.getId(), request.getType(), request.getStatus(),
                    request.getIcon(), request.getUrl(), request.getName(), request.getDesc());
            int result = iPermissionBo.updatePermission(permissionPo);
            // 是否成功(1成功、0失败)
            if (result > 0) {
                return new UpdatePermissionResponseDto(HiMsgCdConstants.SUCCESS);
            }
        }
        throw new HiBusinessReturnException(HiMsgCdConstants.UPDATE_PERMISSION_FAIL, "update permission fail");
	}

	@Override
	@Transactional(rollbackFor = HiBusinessReturnException.class)
	public AddPermissionResponseDto addPermission(AddPermissionRequestDto request) throws HiBusinessReturnException {
		if (null != request) {
            PermissionPo permission = new PermissionPo(request.getLevel(), request.getType(), request.getParentId(),
                    request.getStatus(), request.getIcon(), request.getUrl(), request.getName(), request.getDesc(), request.getUrlAuth());
            int result = iPermissionBo.addPermission(permission);
            // 如果主键大于0
            if (result > 0) {
                AddPermissionResponseDto response = new AddPermissionResponseDto();
                permission.setPerNo(result);
                response.setPermission(convertToPermissionVO(permission));
                response.setRspCd(HiMsgCdConstants.SUCCESS);
                return response;
            }
        }
        throw new HiBusinessReturnException(HiMsgCdConstants.ADD_PERMISSION_FAIL, "add permission fail");
	}

	@Override
	@Transactional(rollbackFor = { HiBusinessReturnException.class })
	public BaseResponseDto deletePermission(Set<Integer> permissionNos) throws HiBusinessReturnException {
		if (!CollectionUtils.isEmpty(permissionNos)) {
            int result = iPermissionBo.deletePermission(permissionNos);
            if (result == permissionNos.size()) {
                return new BaseResponseDto(HiMsgCdConstants.SUCCESS, "删除成功");
            }
        }
        throw new HiBusinessReturnException(HiMsgCdConstants.DELETE_PERMISSION_FAIL, "delete permission fail");
	}
    
    
    

}
