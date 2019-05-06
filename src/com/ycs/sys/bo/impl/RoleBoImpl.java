package com.ycs.sys.bo.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.ycs.base.bo.BaseBo;
import com.ycs.base.utils.DateUtil;
import com.ycs.sys.bo.IRoleBo;
import com.ycs.sys.dao.IRoleDao;
import com.ycs.sys.domain.po.RolePo;

@Component
public class RoleBoImpl extends BaseBo implements IRoleBo {

	/**
     * 角色Dao
     */
    @Autowired
    private IRoleDao roleDao;

    @Override
    public int getCount(String roleNo, String status, String name, Date createDate, String search, String sort, String sortName) {
    	Map<String, Object> requestMap = new LinkedHashMap<>();
		requestMap.put("roleNo", roleNo);
		requestMap.put("status", status);
		requestMap.put("name", name);
		requestMap.put("createDate", createDate);
		requestMap.put("search", search);
    	return roleDao.getCount(requestMap);
    }
    
    @Override
    public List<RolePo> pageQuery(String roleNo, String status, String name, Date createDate, String search, String sort,
            String sortName, Integer row, Integer offset) {
    	Map<String, Object> requestMap = new LinkedHashMap<>();
		requestMap.put("roleNo", roleNo);
		requestMap.put("status", status);
		requestMap.put("name", name);
		requestMap.put("createDate", createDate);
		requestMap.put("search", search);
		requestMap.put("sort", sort);
		requestMap.put("sortName", sortName);
		requestMap.put("row", row);
		requestMap.put("offset", offset);
        return roleDao.pageQuery(requestMap);
    }

    @Override
    public int addUserRoles(String usrNo, Set<String> roleNos) {
    	List<Map<String, Object>> requestList = new LinkedList<>();
		for (String roleNo : roleNos){
			Map<String, Object> map = new LinkedHashMap<>();
			map.put("usrNo", usrNo);
			map.put("roleNo", roleNo);
			requestList.add(map);
		}
        return roleDao.addUserRoles(requestList);
    }

    @Override
    public List<String> queryUserRoles(String userNo) {
    	List<String> roleNoList = new ArrayList<>();
    	Map<String, Object> requestMap = new HashMap<>();
		requestMap.put("usrNo", userNo);
		Map<String, Object> resultMap = roleDao.queryUserRoles(requestMap);
		if (!CollectionUtils.isEmpty(resultMap)) {
			Iterator<Entry<String, Object>> iter = resultMap.entrySet().iterator();
        	while(iter.hasNext()){
        		Map.Entry<String, Object> entry = iter.next();
        		String roleNo = (String) entry.getValue();
        		roleNoList.add(roleNo);
        	}
		}
        return roleNoList;
    }

    @Override
    public int addRole(RolePo role) {
    	role.setCreDt(DateUtil.getStringDate());
        return roleDao.addRole(role);
    }

    @Override
    public int queryRoleExists(String roleNo) {
    	Map<String, Object> requestMap = new LinkedHashMap<>();
		requestMap.put("roleNo", roleNo);
        return roleDao.queryRoleExists(requestMap);
    }

    @Override
    public int deleteRoles(Set<String> roleNos) {
    	List<Object> requestList = new LinkedList<>();
		requestList.addAll(roleNos);
        return roleDao.deleteRoles(requestList);
    }

    @Override
    public Set<String> queryUserRole(Set<String> roleNos) {
    	Set<String> result = new HashSet<>();
		List<Object> requestList = new LinkedList<>();
		requestList.addAll(roleNos);
		List<String> list = roleDao.queryUserRole(requestList);
		result.addAll(list);
		return result;
    }

    @Override
    public int updateRole(RolePo role) {
        return roleDao.updateRole(role);
    }

    @Override
    public int deleteUserRole(String usrNo) {
        return roleDao.deleteUserRole(usrNo);
    }

    @Override
    public RolePo queryRole(String roleNo) {
        return roleDao.queryRole(roleNo);
    }

    @Override
    public Set<RolePo> queryRolesByUsrNo(String usrNo) {
    	Set<RolePo> resultSet = new HashSet<>();
		List<RolePo> list = new LinkedList<>();
		Map<String, Object> requestMap = new LinkedHashMap<>();
		requestMap.put("usrNo", usrNo);
		list = roleDao.queryRolesByUsrNo(requestMap);
		resultSet.addAll(list);
        return resultSet;
    }

    @Override
    public Set<RolePo> queryRoles() {
    	Set<RolePo> resultSet = new HashSet<>();
		Map<String, Object> requestMap = new LinkedHashMap<>();
		List<RolePo> list = roleDao.queryRoles(requestMap);
		resultSet.addAll(list);
        return resultSet;
    }

}
