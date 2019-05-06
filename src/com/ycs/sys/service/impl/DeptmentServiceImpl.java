package com.ycs.sys.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.ycs.basbo.constants.Constants;
import com.ycs.basbo.constants.HiMsgCdConstants;
import com.ycs.base.domain.dto.BaseRequestDto;
import com.ycs.base.exception.HiBusinessAbortException;
import com.ycs.base.exception.HiBusinessReturnException;
import com.ycs.base.exception.HiException;
import com.ycs.coobo.compan.bo.CompaniesMngBo;
import com.ycs.coobo.compan.domain.po.CompaniesPo;
import com.ycs.sys.bo.IDeptmentBo;
import com.ycs.sys.bo.IUserInfoBo;
import com.ycs.sys.domain.dto.GetDeptmentTreeRequestDto;
import com.ycs.sys.domain.dto.GetDeptmentTreeResponseDto;
import com.ycs.sys.domain.dto.QueryAllDeptmentRequestDto;
import com.ycs.sys.domain.dto.QueryAllDeptmentResponseDto;
import com.ycs.sys.domain.dto.QueryDeptmentRequestDto;
import com.ycs.sys.domain.dto.QueryDeptmentResponseDto;
import com.ycs.sys.domain.dto.RenameDeptmentRequestDto;
import com.ycs.sys.domain.dto.RenameDeptmentResponseDto;
import com.ycs.sys.domain.dto.UpdateDeptmentRequestDto;
import com.ycs.sys.domain.dto.UpdateDeptmentResponseDto;
import com.ycs.sys.domain.po.DeptmentPo;
import com.ycs.sys.domain.po.UserInfoPo;
import com.ycs.sys.domain.vo.TreeVO;
import com.ycs.sys.service.IDeptmentService;

/**
 * 部门Service实现
 * @author youcyousyunn
 * @date 2018年3月13日
 */
@Service
public class DeptmentServiceImpl implements IDeptmentService {

	@Autowired
    private IDeptmentBo iDeptmentBo;
    @Autowired
    private IUserInfoBo iUserInfoBo;
    @Autowired
    private CompaniesMngBo companiesMngBo;
    
	@Override
	@Transactional(readOnly = true)
	public GetDeptmentTreeResponseDto queryDeptmentTree(GetDeptmentTreeRequestDto request)
			throws HiBusinessReturnException {
		/**
         * 1、修改成只能看到自己部门和子部门
         */
        UserInfoPo userInfoPo = iUserInfoBo.queryUserInfo(request.getUsrNo());
        if (null == userInfoPo) {
            throw new HiBusinessReturnException(HiMsgCdConstants.QUERY_USERINFO_FAIL, "查询用户失败");
        }
        List<DeptmentPo> deptmentLst = iDeptmentBo.queryDeptmentLst(Integer.parseInt(userInfoPo.getDeparNo()));
        if (!CollectionUtils.isEmpty(deptmentLst)) {
            List<TreeVO> deptmentTree = new ArrayList<>();
            //默认根节点
            TreeVO treeVo = new TreeVO(0, "全部", "#", "glyphicon glyphicon-user", null);
            deptmentTree.add(treeVo);
            
            for (DeptmentPo deptmentPo : deptmentLst) {
                TreeVO tree = new TreeVO(deptmentPo.getDeparNo(), deptmentPo.getDeparNm(), deptmentPo.getDeparNo()
                            .intValue()==Integer.valueOf(userInfoPo.getDeparNo())? 0 : deptmentPo.getfDeparNo(),"glyphicon glyphicon-user", deptmentPo.getDeparTyp());
                Map<String, Object> dataMap = new HashMap<String, Object>();
                dataMap.put(deptmentPo.getDeparTyp(), deptmentPo.getTypeNo());
                tree.setData(dataMap);
                deptmentTree.add(tree);
            }
            return new GetDeptmentTreeResponseDto(HiMsgCdConstants.SUCCESS, deptmentTree);
        }
        throw new HiBusinessReturnException(HiMsgCdConstants.QUERY_DEPTMENT_FAIL, "query deptment faild");
	}

	@Override
	@Transactional(readOnly = true)
	public QueryAllDeptmentResponseDto queryDeptment(QueryAllDeptmentRequestDto request) throws HiBusinessReturnException {
		UserInfoPo userInfoPo = iUserInfoBo.queryUserInfo(request.getUsrNo());
        if (null == userInfoPo) {
            throw new HiBusinessReturnException(HiMsgCdConstants.QUERY_USERINFO_FAIL, "查询用户失败");
        }
        List<DeptmentPo> deptments = iDeptmentBo.queryDeptmentLst(Integer.parseInt(userInfoPo.getDeparNo()));
        if (!CollectionUtils.isEmpty(deptments)) {
            return new QueryAllDeptmentResponseDto(HiMsgCdConstants.SUCCESS, deptments);
        }
        throw new HiBusinessReturnException(HiMsgCdConstants.QUERY_DEPTMENT_FAIL, "query deptment faild");
	}

	@Override
	@Transactional(rollbackFor = { HiBusinessReturnException.class })
	public RenameDeptmentResponseDto renameDeptment(RenameDeptmentRequestDto request) throws HiBusinessReturnException {
		DeptmentPo deptmentPo = iDeptmentBo.queryDeptment(request.getDeptmentNo());
        if (null == deptmentPo) {
            throw new HiBusinessReturnException(HiMsgCdConstants.QUERY_DEPTMENT_FAIL, "查询部门信息失败");
        }
        int result = iDeptmentBo.renameDeptment(request.getDeptmentNo(), request.getDeptmentName());
        if (result > 0) {
            boolean userResult = iUserInfoBo.updateDeptmentByNo(request.getDeptmentNo(), request.getDeptmentName());
            if (!userResult) {
                throw new HiBusinessReturnException(HiMsgCdConstants.RENAME_DEPTMENT_FAIL, "修改部门名称失败");
            }
        } else {
            throw new HiBusinessReturnException(HiMsgCdConstants.RENAME_DEPTMENT_FAIL, "修改部门名称失败 ");
        }
        switch (deptmentPo.getDeparTyp()) {
        case Constants.D_COMPAN:
            if (!companiesMngBo.updCompaniesNm(request.getDeptmentName(), deptmentPo.getTypeNo())){
            	throw new HiBusinessReturnException(HiMsgCdConstants.UPD_COMPAN_FAIL, "更新运营公司名称失败");
            }
            break;
        /*case Constants.D_STOH:
            // 仓库修改
            if (!stoHoseMngBo.updStoHoseNm(request.getDeptmentName(), deptmentPo.getTypeNo()))
                throw new HiBusinessReturnException(HiMsgCdConstants.UPD_STOHOSE_FAIL, "更新仓库名失败");

            break;
        case Constants.D_SHOP:
            // 门店修改
            if (!storeMngBo.updStoreNm(request.getDeptmentName(), deptmentPo.getTypeNo()))
                throw new HiBusinessReturnException(HiMsgCdConstants.UPD_STORE_FAIL, "更新门店名失败");
            break;*/
        default:
            break;
        }
        RenameDeptmentResponseDto response = new RenameDeptmentResponseDto();
        return response;
	}

	@Override
	@Transactional(readOnly = true)
	public QueryDeptmentResponseDto queryDeptment(QueryDeptmentRequestDto request) throws HiBusinessReturnException {
		if (null != request) {
            DeptmentPo deptment = iDeptmentBo.queryDeptment(request.getDeparNo());
            if (null != deptment) {
                return new QueryDeptmentResponseDto(HiMsgCdConstants.SUCCESS, deptment);
            }
        }
        throw new HiBusinessReturnException(HiMsgCdConstants.QUERY_DEPTMENT_FAIL, "query deptment faild");
	}

	@Override
	@Transactional(rollbackFor = { HiBusinessReturnException.class,HiBusinessAbortException.class })
	public UpdateDeptmentResponseDto updateDeptment(UpdateDeptmentRequestDto request) throws HiBusinessReturnException, HiBusinessAbortException {
		DeptmentPo deptment = null;
        try {
            deptment = BaseRequestDto.trans2Entity(request, DeptmentPo.class);
        } catch (HiException e) {
            e.printStackTrace();
        }
        //更新部门信息
        int result = iDeptmentBo.updateDeptment(deptment);
        if (result > 0) {
            //更新用户表用户归属部门信息
            boolean userResult = iUserInfoBo.updateUserDeptment(deptment.getDeparNo(), deptment.getDeparNm(), deptment.getMngerUsrNo());
            if (!userResult) {
                throw new HiBusinessReturnException(HiMsgCdConstants.UPDATE_DEPTMENT_FAIL, "修改用户部门信息失败");
            }
        }else{
            throw new HiBusinessReturnException(HiMsgCdConstants.UPDATE_DEPTMENT_FAIL, "修改部门信息失败");
        }
        
        switch (request.getDeparTyp()) {
            case Constants.D_COMPAN:
                CompaniesPo companiesPo = request.getCompaniesPo();
                companiesPo.setCotldNo(request.getTypeNo());
                if(companiesPo.getCotldSts().equals("F")){
                    companiesPo.setCotldSts("2");
                }else if(companiesPo.getCotldSts().equals("S")){
                    companiesPo.setCotldSts("0");
                }
                //运营公司修改
                if(!companiesMngBo.updCompanies(companiesPo)){
                	throw new HiBusinessReturnException(HiMsgCdConstants.ADD_COMPAN_FAIL, "更新运营公司信息失败");
                }
                break;
            default:
                break;
        }
        
        UpdateDeptmentResponseDto response = new UpdateDeptmentResponseDto();
        return response;
	}
    
    
    
    
	
	
	
	
	
	
    
    
    

}
