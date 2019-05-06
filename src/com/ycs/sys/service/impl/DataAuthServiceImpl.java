package com.ycs.sys.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ycs.basbo.constants.HiMsgCdConstants;
import com.ycs.base.exception.HiBusinessAbortException;
import com.ycs.base.exception.HiBusinessReturnException;
import com.ycs.base.utils.PageUtil;
import com.ycs.sys.bo.DataAuthBo;
import com.ycs.sys.bo.IUserInfoBo;
import com.ycs.sys.domain.dto.QryAuthDataLstRequestDto;
import com.ycs.sys.domain.dto.QryAuthDataLstResponseDto;
import com.ycs.sys.domain.po.DataAuthPo;
import com.ycs.sys.domain.po.UserInfoPo;
import com.ycs.sys.service.DataAuthService;

@Service
public class DataAuthServiceImpl implements DataAuthService {

	@Autowired
    private DataAuthBo dataAuthBo;
    @Autowired
    private IUserInfoBo iUserInfoBo;
	
	
	@Override
	@Transactional(readOnly=true)
	public QryAuthDataLstResponseDto qryAuthDataLst(QryAuthDataLstRequestDto request)
			throws HiBusinessReturnException, HiBusinessAbortException {
		UserInfoPo userInfoPo = iUserInfoBo.queryUserInfo(request.getUsrNo());
        if (null == userInfoPo) {
            throw new HiBusinessReturnException(HiMsgCdConstants.QUERY_USERINFO_FAIL, "查询用户失败");
        }
        
        //查询下属部门所有用户
        List<UserInfoPo> userInfoPoLst = iUserInfoBo.qryAllUserByFDept(userInfoPo.getDeparNo());
        List<String> usrNoLst = new ArrayList<String>();
        usrNoLst.add(request.getUsrNo());
        if (null != userInfoPoLst) {
            for (UserInfoPo qryUserInfoPo : userInfoPoLst) {
                usrNoLst.add(qryUserInfoPo.getUsrNo());
            }
        }
        
        Map<String, Object> paramMap = new LinkedHashMap<String, Object>();
        paramMap.put("usrNoLst", usrNoLst);
        
        //删除对应的空Key为后面的DaoImpl参数赋值提供处理
        Iterator<Entry<String, Object>> iter = paramMap.entrySet().iterator();
        while(iter.hasNext()){
            Map.Entry<String, Object> entry = iter.next();
            Object key = entry.getKey();
            if(null == paramMap.get(key) || "".equals(paramMap.get(key))){
                iter.remove();
            }
        }
        
        // 首先查询共有多少记录
        int totalCount = dataAuthBo.qryAuthDataLstCount(paramMap);
        // 计算分页信息
        PageUtil.calculatePageInfo(totalCount, request.getPageSize(), request.getCurrentPage());
        
        List<DataAuthPo> dataAuthPoLst = dataAuthBo.qryAuthDataLst(paramMap, PageUtil.getStartRow(), PageUtil.getOffset());
        
        QryAuthDataLstResponseDto response = new QryAuthDataLstResponseDto();
        response.setRows(dataAuthPoLst);
        response.setTotal(totalCount);
        return response;
	}

	
	
	
	
}
