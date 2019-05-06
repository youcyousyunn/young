/**
 * 
 */
package com.ycs.sys.bo.impl;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ycs.base.bo.BaseBo;
import com.ycs.sys.bo.IPermissionBo;
import com.ycs.sys.dao.IPermissionDao;
import com.ycs.sys.domain.po.PermissionPo;

/**权限Bo实现类
 * @author youcyousyunn
 * @date 2018年2月12日
 */
@Component
public class PermissionBoImpl extends BaseBo implements IPermissionBo {

	/**
     * 权限Dao
     */
    @Autowired
    private IPermissionDao permissionDao;
    

	@Override
	public List<PermissionPo> queryMenu(List<String> roleNoList) {
		return permissionDao.queryMenu(roleNoList);
	}

	@Override
	public List<PermissionPo> queryPermissions(String userNo, String type, String status) {
		Map<String, Object> requestMap = new LinkedHashMap<>();
		requestMap.put("userNo", userNo);
		requestMap.put("type", type);
		requestMap.put("status", status);
		return permissionDao.queryPermissions(requestMap);
	}

	@Override
	public int updatePermission(PermissionPo permissionPo) {
		return permissionDao.updatePermission(permissionPo);
	}

	@Override
	public int addPermission(PermissionPo permissionPo) {
		return permissionDao.addPermission(permissionPo);
	}

	@Override
	public int deletePermission(Set<Integer> permissionNos) {
		List<Object> requestList = new LinkedList<>();
		requestList.addAll(permissionNos);
		return permissionDao.deletePermission(requestList);
	}

	@Override
	public Set<Integer> queryPermissionsByRoleNos(Set<String> roleNos) {
		Set<Integer> set=new HashSet<>();
		List<Object> requestList = new LinkedList<>();
		requestList.addAll(roleNos);
		List<Integer> list = permissionDao.queryPermissionsByRoles(requestList);
		set.addAll(list);
		return set;
	}

	@Override
	public int deleteRolePermission(Set<String> roleNos) {
		List<Object> requestList = new LinkedList<>();
		requestList.addAll(roleNos);
		return permissionDao.deleteRolePermission(requestList);
	}

	@Override
	public int addRolePermission(String roleNo, Set<Integer> permissionNos) {
		List<Map<String, Object>> requestList = new LinkedList<>();
		for(Integer perNo : permissionNos){
			Map<String, Object> map = new LinkedHashMap<>();
			map.put("roleNo", roleNo);
			map.put("perNo", perNo);
			requestList.add(map);
		}
		return permissionDao.addRolePermission(requestList);
	}
	
	
	
	
	
	
	

}
