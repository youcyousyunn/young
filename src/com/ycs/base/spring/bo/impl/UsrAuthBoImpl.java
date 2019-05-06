package com.ycs.base.spring.bo.impl;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ycs.base.spring.bo.UsrAuthBo;
import com.ycs.base.spring.dao.UsrAuthDao;

@Component
public class UsrAuthBoImpl implements UsrAuthBo {

	@Autowired
	private UsrAuthDao usrAuthDao;
	
	@Override
	public boolean isPermission(String usrNo, String authUrl) {
		int result = -1;
		HashMap<String, Object> paramMap =new HashMap<>();
		paramMap.put("usrNo", usrNo);
		paramMap.put("authUrl", authUrl);
		result = usrAuthDao.isPermission(paramMap);
		if (1 == result) {
			return true;
		}
		return false;
	}

}
