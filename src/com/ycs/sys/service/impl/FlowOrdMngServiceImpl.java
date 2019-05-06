package com.ycs.sys.service.impl;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ycs.basbo.constants.HiMsgCdConstants;
import com.ycs.base.domain.dto.BaseDto;
import com.ycs.base.exception.HiBusinessAbortException;
import com.ycs.base.exception.HiBusinessReturnException;
import com.ycs.base.exception.HiException;
import com.ycs.base.utils.PageUtil;
import com.ycs.sys.bo.FlowBo;
import com.ycs.sys.domain.dto.QryFlowActionLstRequestDto;
import com.ycs.sys.domain.dto.QryFlowActionLstResponseDto;
import com.ycs.sys.domain.dto.QryFlowJrnLstRequestDto;
import com.ycs.sys.domain.dto.QryFlowJrnLstResponseDto;
import com.ycs.sys.domain.dto.QryFlowOrdInfoRequestDto;
import com.ycs.sys.domain.dto.QryFlowOrdInfoResponseDto;
import com.ycs.sys.domain.dto.UpdFlowActionRequestDto;
import com.ycs.sys.domain.dto.UpdFlowActionResponseDto;
import com.ycs.sys.domain.po.FlowActionPo;
import com.ycs.sys.service.FlowOrdMngService;
import com.ycs.work.domain.po.FlowJrnPo;
import com.ycs.work.domain.po.FlowOrdPo;

@Service
public class FlowOrdMngServiceImpl implements FlowOrdMngService {

	@Autowired
    private FlowBo flowBo;
	
	
	@Override
	@Transactional(readOnly=true)
	public QryFlowActionLstResponseDto qryFlowActionLst(QryFlowActionLstRequestDto request)
			throws HiBusinessAbortException, HiBusinessReturnException {
		LinkedHashMap<String, Object> paramMap = new LinkedHashMap<String, Object>();
        paramMap.put("actionNm", null != request.getActionNm() ? "%"+request.getActionNm()+"%" : null);
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
        int totalCount = flowBo.qryFlowActionLstCount(paramMap);
        // 计算分页信息
        PageUtil.calculatePageInfo(totalCount, request.getPageSize(), request.getCurrentPage());
        
        // 分页查询
        List<FlowActionPo> flowActionPoLst = flowBo.qryFlowActionLst(paramMap, PageUtil.getStartRow(), PageUtil.getOffset());
        // 组装响应信息
        QryFlowActionLstResponseDto response = new QryFlowActionLstResponseDto();
        response.setRows(flowActionPoLst);
        response.setTotal(totalCount);
        return response;
	}

	@Override
	@Transactional(rollbackFor = { HiBusinessReturnException.class })
	public UpdFlowActionResponseDto updFlowAction(UpdFlowActionRequestDto request)
			throws HiBusinessAbortException, HiBusinessReturnException {
		LinkedHashMap<String, Object> paramMap = new LinkedHashMap<>();
		paramMap.put("actionNo", request.getActionNo());
		paramMap.put("nodeNo", request.getNodeNo());
		paramMap.put("deparNo", request.getDeparNo());
		paramMap.put("postId", request.getPostId());
		if (!flowBo.updFlowAction(paramMap)) {
            throw new HiBusinessReturnException(HiMsgCdConstants.UPD_ACTION_INFO_FAIL, "更新流程动作节点信息失败");
        }
		UpdFlowActionResponseDto response = new UpdFlowActionResponseDto();
        return response;
	}

	@Override
	public QryFlowOrdInfoResponseDto getFlowOrdInfo(QryFlowOrdInfoRequestDto request)
			throws HiBusinessAbortException, HiBusinessReturnException {
		String ordNo = request.getOrdNo();
		String flowOrd = request.getFlowOrd();
        FlowOrdPo flowOrdPo = null;
        
        if(null == flowOrd || "".equals(flowOrd)){
            flowOrdPo = flowBo.queryFlowOrdByOrdNo(ordNo, "C1");//查询进行中的流程
            if(null == flowOrdPo){
                flowOrdPo = flowBo.queryFlowOrdByOrdNo(ordNo, "S1");//查询完成的流程
            }
        }else{
            flowOrdPo = flowBo.queryFlowOrd(flowOrd);
        }
        QryFlowOrdInfoResponseDto responseDto = new QryFlowOrdInfoResponseDto();
        try {
            responseDto = BaseDto.entity2Trans(flowOrdPo, QryFlowOrdInfoResponseDto.class);
        } catch (HiException e) {
            e.printStackTrace();
        }
        return responseDto;
	}

	@Override
	public QryFlowJrnLstResponseDto qryFlowJrnLst(QryFlowJrnLstRequestDto request)
			throws HiBusinessAbortException, HiBusinessReturnException {
		List<FlowJrnPo> flowJrnPoLst = flowBo.qryFlowJrnLst(request.getJrnNo(),request.getOrdNo(),request.getFlowOrd());
        QryFlowJrnLstResponseDto response = new QryFlowJrnLstResponseDto();
        response.setTotal(flowJrnPoLst.size());
        response.setRows(flowJrnPoLst);
        return response;
	}

	
	
	
	
}
