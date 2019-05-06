package com.ycs.sys.bo.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.ycs.base.bo.BaseBo;
import com.ycs.base.utils.DateUtil;
import com.ycs.sys.bo.FlowBo;
import com.ycs.sys.dao.FlowDao;
import com.ycs.sys.domain.po.FlowActionPo;
import com.ycs.work.domain.po.FlowInfoPo;
import com.ycs.work.domain.po.FlowJrnPo;
import com.ycs.work.domain.po.FlowOrdPo;

@Component
public class FlowBoImpl extends BaseBo implements FlowBo {
	
	@Autowired
    private FlowDao flowDao;

	@Override
	public int qryFlowActionLstCount(LinkedHashMap<String, Object> paramMap) {
		return flowDao.qryFlowActionLstCount(paramMap);
	}

	@Override
	public List<FlowActionPo> qryFlowActionLst(LinkedHashMap<String, Object> paramMap, Integer startRow,
			Integer offset) {
		paramMap.put("startRow", startRow);
		paramMap.put("offset", offset);
		return flowDao.qryFlowActionLst(paramMap);
	}

	@Override
	public boolean updFlowAction(LinkedHashMap<String, Object> paramMap) {
		boolean result = false;
		int result_ = flowDao.updFlowAction(paramMap);
		if (result_ > 0){
			result = true;
		}
		return result;
	}

	@Override
	public FlowInfoPo queryFlowInfo(String flowNo) {
		FlowInfoPo flowInfoPo = new FlowInfoPo();
		List<Map<String, Object>> resultList = flowDao.queryFlowInfo(flowNo);
		if (!CollectionUtils.isEmpty(resultList)) {
			for (Map<String, Object> item : resultList){
				flowInfoPo.setFlowNo(item.get("flowNo").toString());
        		flowInfoPo.setFlowNm(item.get("flowNm").toString());
        		flowInfoPo.setFlowDesc(item.get("flowDesc").toString());
        		flowInfoPo.setActionNo(item.get("actionNo").toString());
        		flowInfoPo.setFlowTyp(item.get("flowTyp").toString());
        		flowInfoPo.setAttach(item.get("attach").toString());
        		flowInfoPo.setStaNode(item.get("staNode").toString());
        		flowInfoPo.setEndNode(item.get("endNode").toString());
        		flowInfoPo.setFlowSts(item.get("flowSts").toString());
        		flowInfoPo.setCreDt(item.get("creDt").toString());
        		flowInfoPo.setUpdDt(item.get("updDt").toString());
        		flowInfoPo.setUpdDesc(item.get("updDesc").toString());
			}
        }
		return flowInfoPo;
	}

	@Override
	public FlowActionPo queryFlowAction(String actionNo, String nodeNo) {
		Map<String, Object> paramMap = new LinkedHashMap<>();
		paramMap.put("actionNo", actionNo);
		paramMap.put("nodeNo", nodeNo);
		return flowDao.queryFlowAction(paramMap);
	}

	@Override
	public boolean addFlowOrd(FlowOrdPo flowOrdPo) {
		int result = -1;
		Map<String, Object> paramMap = null;
		try {
			paramMap = BeanUtils.describe(flowOrdPo);
		} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
			e.printStackTrace();
		}
		result = flowDao.addFlowOrd(paramMap);
		if (1 == result){
			return true;
		}
		return false;
	}

	@Override
	public boolean addFlowJrn(FlowJrnPo flowJrnPo) {
		String txSts = StringUtils.isBlank(flowJrnPo.getTxSts()) ? "W" : flowJrnPo.getTxSts();
		flowJrnPo.setTxSts(txSts);
		int result = -1;
		Map<String, Object> paramMap = null;
		try {
			paramMap = BeanUtils.describe(flowJrnPo);
		} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
			e.printStackTrace();
		}
		result = flowDao.addFlowJrn(paramMap);
		if (1 == result){
			return true;
		}
		return false;
	}

	@Override
	public FlowJrnPo queryFlowJrn(String jrnNo) {
		Map<String, Object> paramMap = new LinkedHashMap<>();
		paramMap.put("jrnNo", jrnNo);
		FlowJrnPo flowJrnPo = new FlowJrnPo();
		List<Map<String, Object>> resultList = flowDao.queryFlowJrn(paramMap);
		if (!CollectionUtils.isEmpty(resultList)) {
			for (Map<String, Object> item : resultList){
				flowJrnPo.setJrnNo(item.get("jrnNo").toString());
        		flowJrnPo.setTxDt(item.get("txDt").toString());
        		flowJrnPo.setTxTm(item.get("txTm").toString());
        		flowJrnPo.setFlowOrd(item.get("flowOrd").toString());
        		flowJrnPo.setOrdNo(item.get("ordNo").toString());
        		flowJrnPo.setNodeNo(item.get("nodeNo").toString());
        		flowJrnPo.setUpStep(item.get("upStep").toString());
        		flowJrnPo.setDownStep(item.get("downStep").toString());
        		flowJrnPo.setTxSts(item.get("txSts").toString());
        		flowJrnPo.setTxUsrNo(item.get("txUsrNo").toString());
        		flowJrnPo.setTxUsrNm(item.get("txUsrNm").toString());
        		flowJrnPo.setTxDeparNo(Integer.valueOf((String) item.get("txDeparNo")));
        		flowJrnPo.setTxType(item.get("txType").toString());
        		flowJrnPo.setTxCoent(item.get("txCoent").toString());
			}
        }
		return flowJrnPo;
	}

	@Override
	public boolean updateFlowOrdRecevUsr(String recevUsrNo, String flowOrdNo) {
		int result = -1;
		Map<String, Object> paramMap = new LinkedHashMap<>();
		paramMap.put("flowOrdNo", flowOrdNo);
		paramMap.put("recevUsrNo", recevUsrNo);
		result = flowDao.updateFlowOrdRecevUsr(paramMap);
		if (1 == result){
			return true;
		}
		return false;
	}

	@Override
	public FlowOrdPo queryFlowOrd(String flowOrdNo) {
		return flowDao.queryFlowOrd(flowOrdNo);
	}

	@Override
	public FlowOrdPo queryFlowOrdByOrdNo(String ordNo, String ordSts) {
		Map<String, Object> requestMap = new HashMap<>();
		requestMap.put("ordNo", ordNo);
		requestMap.put("ordSts", ordSts);
		return flowDao.queryFlowOrdByOrdNo(requestMap);
	}

	@Override
	public List<FlowJrnPo> qryFlowJrnLst(String jrnNo, String ordNo, String flowOrd) {
		Map<String, Object> paramMap = new LinkedHashMap<>();
		paramMap.put("jrnNo", jrnNo);
		paramMap.put("ordNo", ordNo);
		paramMap.put("flowOrd", flowOrd);
		return flowDao.qryFlowJrnLst(paramMap);
	}

	@Override
	public boolean updFlowOrdStep(String actionStep, String downStep, String flowOrdNo) {
		int result = -1;
		HashMap<String, Object> paramMap = new LinkedHashMap<>();
		paramMap.put("actionStep", actionStep);
		paramMap.put("downStep", downStep);
		paramMap.put("flowOrdNo", flowOrdNo);
		result = flowDao.updFlowOrdStep(paramMap);
		if (1 == result) {
			return true;
		}
		return false;
	}

	@Override
	public boolean updFlowJrnSts(String flowJrnSts, String flowJrnNo) {
		int result = -1;
		HashMap<String, Object> paramMap = new LinkedHashMap<>();
		paramMap.put("flowJrnSts", flowJrnSts);
		paramMap.put("flowJrnNo", flowJrnNo);
		result = flowDao.updFlowJrnSts(paramMap);
		if (1 == result) {
			return true;
		}
		return false;
	}

	@Override
	public boolean updFlowOrdSts(String flowOrdNo, String flowSts, boolean isEnd) {
		int result = -1;
		HashMap<String, Object> paramMap = new LinkedHashMap<>();
		paramMap.put("flowOrdNo", flowOrdNo);
		paramMap.put("flowSts", flowSts);
		paramMap.put("isEnd", isEnd);
		String endDt = DateUtil.getDateStringByFormatString(DateUtil.dataFormatyyyy_MM_dd);
		String endTm = DateUtil.getDateStringByFormatString(DateUtil.dataFormatHH_mm_ss);
		paramMap.put("endDt", endDt);
		paramMap.put("endTm", endTm);
		result = flowDao.updFlowOrdSts(paramMap);
		if (1 == result) {
			return true;
		}
		return false;
	}


}
