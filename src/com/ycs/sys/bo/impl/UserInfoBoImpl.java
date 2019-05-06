package com.ycs.sys.bo.impl;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ycs.sys.bo.IUserInfoBo;
import com.ycs.sys.dao.IUserInfoDao;
import com.ycs.sys.domain.enums.EnumUserStatus;
import com.ycs.sys.domain.po.UserInfoPo;

/**
 * 用户信息BO实现类
 * @author youcyousyunn
 * @date 2018年1月28日
 */
@Component
public class UserInfoBoImpl implements IUserInfoBo {

	/**
	 * 用户信息Dao
	 */
	@Autowired 
	IUserInfoDao userInfoDao;

	@Override
	public int addUserInfo(UserInfoPo userInfo) {
		return userInfoDao.addUserInfo(userInfo);
	}

	@Override
	public UserInfoPo queryByLoginName(String loginNm) {
		return userInfoDao.queryByLoginName(loginNm);
	}

	@Override
	public UserInfoPo queryUserInfo(String usrNo) {
		return userInfoDao.queryUserInfo(usrNo);
	}

	@Override
	public int getCount(Map<String, Object> parameter, String sort, String sortName) {
		parameter.put("sort", sort);
		parameter.put("sortName", sortName);
		parameter.put("status", EnumUserStatus.D.toString());
		return userInfoDao.getCount(parameter);
	}

	@Override
	public List<UserInfoPo> queryPage(Map<String, Object> parameter, String sort, String sortName, Integer startRow,
			Integer offset) {
		parameter.put("sort", null != sort ? sort : "usr_no");
		parameter.put("sortName", null != sortName ? sortName : "DESC");
		parameter.put("startRow", startRow);
		parameter.put("offset", offset);
		return userInfoDao.queryPage(parameter);
	}

	@Override
	public int updateUserInfo(UserInfoPo userInfo) {
		return userInfoDao.updateUserInfo(userInfo);
	}

	@Override
	public int mobileExists(String mobileNo) {
		return userInfoDao.mobileExists(mobileNo);
	}

	@Override
	public int deleteUserInfo(Set<String> usrNos) {
		List<Object> requestList = new LinkedList<>();
		for (String usrNo : usrNos){
			Map<String, Object> map = new LinkedHashMap<>();
			map.put("usrSts", EnumUserStatus.D.name().toString());
			map.put("usrNo", usrNo);
			requestList.add(map);
		}
		return userInfoDao.deleteUserInfo(requestList);
	}

	@Override
	public boolean updateDeptmentByNo(Integer deptmentNo, String deptmentName) {
		boolean result = false;
		Map<String, Object> requestMap = new LinkedHashMap<>();
		requestMap.put("deptmentNo", deptmentNo);
		requestMap.put("deptmentName", deptmentName);
		int result_ = userInfoDao.updateDeptmentByNo(requestMap);
		if (result_ >0){
			result = true;
		}
		return result;
	}

	@Override
	public List<UserInfoPo> queryUserInfos(String usrNo, String usrNm) {
		Map<String, Object> requestMap = new LinkedHashMap<>();
		requestMap.put("usrNo", usrNo);
		requestMap.put("usrNm", usrNm);
		requestMap.put("usrSts", EnumUserStatus.D.name().toString());
		return userInfoDao.queryUserInfos(requestMap);
	}

	@Override
	public boolean updateUserDeptment(Integer deparNo, String deparNm, String mngerUsrNo) {
		boolean result = false;
		Map<String, Object> requestMap = new LinkedHashMap<>();
		requestMap.put("deparNo", deparNo);
		requestMap.put("deparNm", deparNm);
		requestMap.put("usrNo", mngerUsrNo);
		int result_ = userInfoDao.updateUserDeptment(requestMap);
		if (result_ > 0){
			result = true;
		}
		return result;
	}

	@Override
	public int updateSelfInfo(UserInfoPo userInfo) {
		return userInfoDao.updateSelfInfo(userInfo);
	}

	@Override
	public List<UserInfoPo> qryAllUserByFDept(String deparNo) {
		Map<String, Object> requestMap = new HashMap<>();
		requestMap.put("deparNo", deparNo);
		return userInfoDao.qryAllUserByFDept(requestMap);
	}

	@Override
	public boolean updateSelfPwd(UserInfoPo userInfo) {
		int result = -1;
		result = userInfoDao.updateSelfPwd(userInfo);
		if (result == 1){
			return true;
		}
		return false;
	}

	@Override
	public boolean register(UserInfoPo userInfo) {
		int result = -1;
		result = userInfoDao.register(userInfo);
		if (result == 1){
			return true;
		}
		return false;
	}
	
	
}
