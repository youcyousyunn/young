package com.ycs.work.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ycs.basbo.constants.Constants;
import com.ycs.basbo.constants.HiMsgCdConstants;
import com.ycs.basbo.enums.TargetTypEnum;
import com.ycs.basbo.utils.JrnGenerator;
import com.ycs.base.domain.pojo.JmsPojo;
import com.ycs.base.encrypt.EncryptUtil;
import com.ycs.base.exception.HiBusinessAbortException;
import com.ycs.base.exception.HiBusinessReturnException;
import com.ycs.base.property.SystemPropertyConfigure;
import com.ycs.base.spring.jms.producer.HiCommonProducer;
import com.ycs.base.utils.DateUtil;
import com.ycs.base.utils.JsonUtils;
import com.ycs.msg.bo.MsgInfoBo;
import com.ycs.msg.domain.po.NotifyCmmParamPo;
import com.ycs.sys.bo.FlowBo;
import com.ycs.sys.bo.IDeptPostBo;
import com.ycs.sys.bo.IUserInfoBo;
import com.ycs.sys.domain.po.DeptPostPo;
import com.ycs.sys.domain.po.FlowActionPo;
import com.ycs.sys.domain.po.UserInfoPo;
import com.ycs.work.bo.PosWeChatTplMsgBo;
import com.ycs.work.domain.po.FlowInfoPo;
import com.ycs.work.domain.po.FlowJrnPo;
import com.ycs.work.domain.po.FlowOrdPo;
import com.ycs.work.domain.po.WechatMsgPo;
import com.ycs.work.domain.po.WorkFlowCmmParamPo;
import com.ycs.work.service.WorkFlowCmmService;

@Service
public class WorkFlowCmmServiceImpl implements WorkFlowCmmService {

	@Autowired
    private FlowBo flowBo;
	@Autowired
    private MsgInfoBo msgInfoBo;
	@Autowired
	private IUserInfoBo iUserInfoBo;
	@Autowired
    private IDeptPostBo ideptPostBo;
	@Autowired
    private HiCommonProducer hiCommonProducer;
	@Autowired
    private PosWeChatTplMsgBo posWeChatTplMsgBo;
	
	
	@Override
	@Transactional(rollbackFor={HiBusinessAbortException.class, HiBusinessReturnException.class})
	public boolean workFlowStart(WorkFlowCmmParamPo workFlowCmmParamPo) throws HiBusinessAbortException, HiBusinessReturnException{
		/**为用户进行订阅操作 */
        if(!msgInfoBo.querySubscribe(workFlowCmmParamPo.getTargetId(), workFlowCmmParamPo.getTargetTyp(), workFlowCmmParamPo.getActionNo(), workFlowCmmParamPo.getLunchUsrNo())){
            msgInfoBo.createSubscribe(workFlowCmmParamPo.getTargetId(), workFlowCmmParamPo.getTargetTyp(), workFlowCmmParamPo.getActionNo(), workFlowCmmParamPo.getLunchUsrNo());
        }
        
        /**判读流程状态是否失效*/
        FlowInfoPo flowInfoPo = flowBo.queryFlowInfo(workFlowCmmParamPo.getFlowNo());
        if("F".equals(flowInfoPo.getFlowSts())){
            return false;
        }
        
        String txDt = DateUtil.getDateStringByFormatString(DateUtil.dataFormatyyyy_MM_dd);
        String txTm = DateUtil.getDateStringByFormatString(DateUtil.dataFormatHH_mm_ss);
        String expDt = DateUtil.calculateDate(txDt, DateUtil.DAY, 1, DateUtil.dataFormatyyyy_MM_dd);//默认失效日期为一天
        
        /**发起审核流程*/
        FlowActionPo flowActionPo = flowBo.queryFlowAction(workFlowCmmParamPo.getActionNo(), workFlowCmmParamPo.getNodeNo());
        
        // 1、生成流程唯一审核单据
        FlowOrdPo flowOrdPo = new FlowOrdPo();
        flowOrdPo.setFlowOrd(Constants.FLOW_ID+JrnGenerator.genJrnNo());
        flowOrdPo.setOrdNo(workFlowCmmParamPo.getTargetId());
        flowOrdPo.setFlowNo(workFlowCmmParamPo.getFlowNo()); //流程号跟数据库中一一对应
        flowOrdPo.setFlowAction(workFlowCmmParamPo.getActionNo());
        flowOrdPo.setLunchUsrNo(workFlowCmmParamPo.getLunchUsrNo());
        flowOrdPo.setTxDt(txDt);
        flowOrdPo.setTxTm(txTm);
        flowOrdPo.setActionStep(flowActionPo.getNodeNo());
        flowOrdPo.setDownStep(flowActionPo.getDownStep());
        flowOrdPo.setSendCnl("SYS"); //流程消息默认为系统渠道发起
        flowOrdPo.setAccpCnl(workFlowCmmParamPo.getTargetTyp());
        flowOrdPo.setFlowSts(Constants.FLOW_STS_C1);
        flowOrdPo.setExpDt(expDt);
        flowOrdPo.setExpTm(txTm);
        workFlowCmmParamPo.setFlowOrdNo(flowOrdPo.getFlowOrd());
        if (flowBo.addFlowOrd(flowOrdPo)) {
            UserInfoPo userInfoPo = iUserInfoBo.queryUserInfo(workFlowCmmParamPo.getLunchUsrNo());
            FlowJrnPo flowJrnPo = new FlowJrnPo();
            flowJrnPo.setJrnNo(JrnGenerator.genJrnNo());
            flowJrnPo.setFlowOrd(flowOrdPo.getFlowOrd());
            flowJrnPo.setOrdNo(workFlowCmmParamPo.getTargetId());
            flowJrnPo.setTxUsrNo(workFlowCmmParamPo.getLunchUsrNo());
            flowJrnPo.setTxDeparNo(Integer.parseInt(userInfoPo.getDeparNo()));
            flowJrnPo.setTxDt(txDt);
            flowJrnPo.setTxTm(txTm);
            flowJrnPo.setTxType(Constants.FLOW_TX_TYPE_CRE);
            flowJrnPo.setTxCoent(workFlowCmmParamPo.getContent());// 交易内容自定义
            flowJrnPo.setNodeNo(flowActionPo.getNodeNo());
            flowJrnPo.setUpStep(flowActionPo.getUpStep());
            flowJrnPo.setDownStep(flowActionPo.getDownStep());

            // 2、生成单据流水信息
            if (flowBo.addFlowJrn(flowJrnPo)) {
                //获取审核人
                String reviewUsrNo = getReviewUsrNo(workFlowCmmParamPo);
                
                // 3、更新流程下一步奏审核人
                flowBo.updateFlowOrdRecevUsr(reviewUsrNo, workFlowCmmParamPo.getFlowOrdNo());
                
                //消息2.0实现
                workFlowCmmParamPo.setFlowOrdNo(flowOrdPo.getFlowOrd());
                workFlowCmmParamPo.setFlowJrn(flowJrnPo.getJrnNo());
                List<String> recvUsrNoLst = new ArrayList<String>();
                recvUsrNoLst.add(reviewUsrNo);
                workFlowCmmParamPo.setRecvUsrNo(reviewUsrNo);
                
                Map<String, Object> extJsonMap = new LinkedHashMap<String, Object>();
                extJsonMap.put("flowOrd", workFlowCmmParamPo.getFlowOrdNo());
                extJsonMap.put("ordNo", workFlowCmmParamPo.getTargetId());
                extJsonMap.put("flowJrn", workFlowCmmParamPo.getFlowJrn());
                
                if(!msgInfoBo.createRemind(workFlowCmmParamPo.getTargetId(), workFlowCmmParamPo.getTargetTyp(), workFlowCmmParamPo.getActionNo(), recvUsrNoLst, workFlowCmmParamPo.getLunchUsrNo(), workFlowCmmParamPo.getTitle(), workFlowCmmParamPo.getContent(), workFlowCmmParamPo.getLinkUrl(), extJsonMap)){
                    throw new HiBusinessReturnException(HiMsgCdConstants.ADD_FLOWINFO_FAIL, "创建消息增加流程失败");
                }
                
                // 发送websocket消息到工作台
                Map<String, Object> transMap = new LinkedHashMap<String, Object>();
                NotifyCmmParamPo notifyCmmPramPo = new NotifyCmmParamPo();
                notifyCmmPramPo.setContent(workFlowCmmParamPo.getContent()+" 请注意查收处理！！！");
                notifyCmmPramPo.setRecvUsrNo(reviewUsrNo);
                transMap.put("MSGCMMPARAM", notifyCmmPramPo);
                
                JmsPojo jmsPojo = new JmsPojo();
                jmsPojo.setService("msgNotifyServiceImpl");
                jmsPojo.setMethod("sendSocketMsgToUser");
                jmsPojo.setTransData(transMap);
                hiCommonProducer.invokeService("sys", jmsPojo);
                
                //发送消息到微信公众号(通过POS系统发送)
                /*transMap.put("USRNO", reviewUsrNo);
                transMap.put("TEMPID", Constants.ORD_STS_NOTIFY);
                Map<String, Object> dataMap = new HashMap<String, Object>();
                dataMap.put("first", notifyCmmPramPo.getContent());
                dataMap.put("keyword1", workFlowCmmParamPo.getTargetId());
                dataMap.put("keyword2", workFlowCmmParamPo.getTitle());
                dataMap.put("keyword3", txDt+" "+txTm);
                dataMap.put("remark", "预知更多详情，请登录灯塔系统进行查看处理！！！");
                transMap.put("DATA", dataMap);
                jmsPojo.setService("workFlowCmmServiceImpl");
                jmsPojo.setMethod("sendWeChatMsgByPos");
                jmsPojo.setTransData(transMap);
                hiCommonProducer.invokeService("sys", jmsPojo);*/
            } else {
                throw new HiBusinessReturnException(HiMsgCdConstants.ADD_FLOWINFO_FAIL, "创建流水信息增加流程失败");
            }
        } else {
            throw new HiBusinessReturnException(HiMsgCdConstants.ADD_FLOWINFO_FAIL, "创建流程单增加流程失败");
        }
        return true;
	}
	
	/**
	 * 获取流程审核人
	 * @param workFlowCmmParamPo
	 * @return String
	 */
	private String getReviewUsrNo(WorkFlowCmmParamPo workFlowCmmParamPo){
        if (null != workFlowCmmParamPo.getRecvUsrNo()) {
            return workFlowCmmParamPo.getRecvUsrNo();
        }
        FlowActionPo  flowActionPo = flowBo.queryFlowAction(workFlowCmmParamPo.getActionNo(), workFlowCmmParamPo.getNodeNo());
        // 如果部门为0则传当前发起流程用户所属部门
        FlowJrnPo flowJrnPo = null;
        if (0 == flowActionPo.getDeparNo()) {
            flowJrnPo = flowBo.queryFlowJrn(workFlowCmmParamPo.getFlowJrn());
            flowActionPo.setDeparNo(flowJrnPo.getTxDeparNo());
        }
        String reviewUsrNo = null;
        DeptPostPo deptPostPo = ideptPostBo.queryAuditor(flowActionPo.getDeparNo(), flowActionPo.getPostId());
        if (null == deptPostPo) {
            boolean ifBreak = true;
            // 防止死循环控制
            int num = 0;
            while (ifBreak) {
                num += 1;
                if (5 == num) {
                    ifBreak = false;
                }
                // 如果下一个节点为空则没任何意义了，审核人就是他本人
                if (StringUtils.isBlank(flowActionPo.getDownStep())) {
                    reviewUsrNo = workFlowCmmParamPo.getLunchUsrNo();
                    ifBreak = false;
                }

                // 如果部门为0则传当前发起流程用户所属部门
                flowActionPo = flowBo.queryFlowAction(workFlowCmmParamPo.getActionNo(), flowActionPo.getDownStep());
                if (0 == flowActionPo.getDeparNo()) {
                    flowActionPo.setDeparNo(flowJrnPo.getTxDeparNo());
                }
                deptPostPo = ideptPostBo.queryAuditor(flowActionPo.getDeparNo(), flowActionPo.getPostId());
                if (null != deptPostPo) {
                    reviewUsrNo = deptPostPo.getUsrNo();
                    ifBreak = false;
                }
            }
        } else {
            reviewUsrNo = deptPostPo.getUsrNo();
        }
        return reviewUsrNo;
    }

	@Override
	public boolean workFlowEnd(WorkFlowCmmParamPo workFlowCmmParamPo) throws HiBusinessAbortException, HiBusinessReturnException{
		//1、代办人进行审核完毕，关闭整个流程
        //获取步骤信息
        FlowActionPo flowActionPo = flowBo.queryFlowAction(workFlowCmmParamPo.getActionNo(), workFlowCmmParamPo.getNodeNo());
        
        //更新流程步骤
        flowBo.updFlowOrdStep(flowActionPo.getNodeNo(), flowActionPo.getDownStep(), workFlowCmmParamPo.getFlowOrdNo());
        //更新流程流水信息为成功
        flowBo.updFlowJrnSts("S", workFlowCmmParamPo.getFlowJrn());
        //更新流程单据信息为完成
        flowBo.updFlowOrdSts(workFlowCmmParamPo.getFlowOrdNo(), Constants.FLOW_STS_S1, true);
        
        //获取用户信息
        UserInfoPo userInfoPo = iUserInfoBo.queryUserInfo(workFlowCmmParamPo.getLunchUsrNo());
        //2、生成单据流水信息
        FlowJrnPo newflowJrnPo = new FlowJrnPo();
        newflowJrnPo.setJrnNo(JrnGenerator.genJrnNo());
        newflowJrnPo.setFlowOrd(workFlowCmmParamPo.getFlowOrdNo());
        newflowJrnPo.setOrdNo(workFlowCmmParamPo.getTargetId());
        newflowJrnPo.setTxUsrNo(workFlowCmmParamPo.getLunchUsrNo());
        newflowJrnPo.setTxDt(DateUtil.getDateStringByFormatString(DateUtil.dataFormatyyyy_MM_dd));
        newflowJrnPo.setTxTm(DateUtil.getDateStringByFormatString(DateUtil.dataFormatHH_mm_ss));
        newflowJrnPo.setTxType(Constants.FLOW_TX_TYPE_VER);
        newflowJrnPo.setTxCoent(workFlowCmmParamPo.getContent());//交易内容自定义
        newflowJrnPo.setNodeNo(workFlowCmmParamPo.getNodeNo());
        newflowJrnPo.setUpStep(flowActionPo.getUpStep());
        newflowJrnPo.setDownStep(flowActionPo.getDownStep());
        newflowJrnPo.setTxDeparNo(Integer.parseInt(userInfoPo.getDeparNo()));
        newflowJrnPo.setTxSts("S");
        flowBo.addFlowJrn(newflowJrnPo);
        
        //添加到用户消息
        FlowOrdPo flowOrdPo = flowBo.queryFlowOrd(workFlowCmmParamPo.getFlowOrdNo());
        //消息2.0实现
        LinkedHashMap<String, Object> extJsonMap = new LinkedHashMap<String, Object>();
        extJsonMap.put("flowOrd", workFlowCmmParamPo.getFlowOrdNo());
        extJsonMap.put("ordNo", workFlowCmmParamPo.getTargetId());
        extJsonMap.put("flowJrn", workFlowCmmParamPo.getFlowJrn());
        
        List<String> recvUsrNoLst = new ArrayList<String>();
        recvUsrNoLst.add(flowOrdPo.getLunchUsrNo());
        if(!msgInfoBo.createRemind(workFlowCmmParamPo.getTargetId(), workFlowCmmParamPo.getTargetTyp(), workFlowCmmParamPo.getActionNo(), recvUsrNoLst, workFlowCmmParamPo.getLunchUsrNo(), workFlowCmmParamPo.getTitle(), workFlowCmmParamPo.getContent(), workFlowCmmParamPo.getLinkUrl(), extJsonMap)){
            throw new HiBusinessReturnException(HiMsgCdConstants.ADD_FLOWINFO_FAIL, "创建消息增加流程失败");
        }
        
        //发送websocket消息到工作台
        LinkedHashMap<String, Object> transMap = new LinkedHashMap<String, Object>();
        NotifyCmmParamPo notifyCmmParamPo = new NotifyCmmParamPo();
        notifyCmmParamPo.setContent(workFlowCmmParamPo.getContent()+" 请注意查收！！！");
        notifyCmmParamPo.setRecvUsrNo(flowOrdPo.getLunchUsrNo());
        transMap.put("MSGCMMPARAM", notifyCmmParamPo);
        
        JmsPojo jmsPojo = new JmsPojo();
        jmsPojo.setService("msgNotifyServiceImpl");
        jmsPojo.setMethod("sendSocketMsgToUser");
        jmsPojo.setTransData(transMap);
        hiCommonProducer.invokeService("sys", jmsPojo);
        
        //发送消息到微信公众号(通过POS系统发送)
        transMap.put("USRNO", flowOrdPo.getLunchUsrNo());
        transMap.put("TEMPID", Constants.ORD_ACCEPT_NOTIFY);
        HashMap<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put("first", notifyCmmParamPo.getContent());
        dataMap.put("storeName", workFlowCmmParamPo.getInitiator());
        dataMap.put("orderId", workFlowCmmParamPo.getTargetId());
        dataMap.put("orderType", TargetTypEnum.getName(workFlowCmmParamPo.getTargetTyp()));
        dataMap.put("remark", "预知更多详情，请登录young系统进行查看处理！！！");
        transMap.put("DATA", dataMap);
        jmsPojo.setService("workFlowCmmServiceImpl");
        jmsPojo.setMethod("sendWeChatMsgByPos");
        jmsPojo.setTransData(transMap);
        hiCommonProducer.invokeService("sys", jmsPojo);
        return true;
	}
	
	@Override
    public void sendWeChatMsgByPos(LinkedHashMap<String, Object> pramMap) throws HiBusinessAbortException,
            HiBusinessReturnException {
        //初始化请求参数
        String userNo = (String) pramMap.get("USRNO");
        String tempId = (String) pramMap.get("TEMPID");
        Map<String, Object> dataMap = (HashMap<String, Object>) pramMap.get("DATA");
        
        //获取模板对象信息
        WechatMsgPo wechatMsgPo = posWeChatTplMsgBo.qryWechatMsg(tempId);
        
        //获取接受人手机号
        String mblNo = iUserInfoBo.queryUserInfo(userNo).getMblNo();
        
        //开发环境发给指定的人
        if(Constants.SYS_ENV_DEV.equals(SystemPropertyConfigure.getParams(Constants.SYS_ENV))){
            mblNo = "13041391046";
        }
        
        //报文体主体
        LinkedHashMap<String, Object> rootMap = new LinkedHashMap<String, Object>();
        rootMap.put("msgId", wechatMsgPo.getTempId());
        rootMap.put("msgNm", wechatMsgPo.getTempNm());
        rootMap.put("phone", mblNo);
        
        //构造报文体
        LinkedHashMap<String, Object> tempMap = new LinkedHashMap<String, Object>();
        for (int i = 0; i < wechatMsgPo.getData().size(); i++) {
            String key = wechatMsgPo.getData().get(i).getArrtNm();
            tempMap.put(key, dataMap.get(key));
        }
        long requestTime = System.currentTimeMillis();
        rootMap.put("content", tempMap);
        rootMap.put("request_time", requestTime);
        rootMap.put("vcode", EncryptUtil.getMD5ByApche(mblNo+requestTime+"justsendDTkey", "utf-8"));
        posWeChatTplMsgBo.sendWeChatMsg(JsonUtils.map2json(rootMap));
    }

	@Override
	public boolean workFlowOnIng(WorkFlowCmmParamPo workFlowCmmParamPo)
			throws HiBusinessAbortException, HiBusinessReturnException {
		//查询动作节点信息
        FlowActionPo flowActionPo = flowBo.queryFlowAction(workFlowCmmParamPo.getActionNo(), workFlowCmmParamPo.getNodeNo());
        
        //变更上一个步骤的流水状态为成功
        flowBo.updFlowJrnSts("S", workFlowCmmParamPo.getFlowJrn());
        //变更流程单下一个步奏和当前已经执行的步奏
        flowBo.updFlowOrdStep(flowActionPo.getNodeNo(), flowActionPo.getDownStep(), workFlowCmmParamPo.getFlowOrdNo());
        
        // 获取审核人
        // 如果部门为空则传当前用户所属部门
        FlowJrnPo flowJrnPo = null;
        if (0 == flowActionPo.getDeparNo()) {
            flowJrnPo = flowBo.queryFlowJrn(workFlowCmmParamPo.getFlowJrn());
            flowActionPo.setDeparNo(flowJrnPo.getTxDeparNo());
        }
        String reviewUsrNo = getReviewUsrNo(workFlowCmmParamPo);
        flowBo.updateFlowOrdRecevUsr(reviewUsrNo, workFlowCmmParamPo.getFlowOrdNo());

        //获取用户信息
        UserInfoPo userInfoPo = iUserInfoBo.queryUserInfo(workFlowCmmParamPo.getLunchUsrNo());
        
        //2、生成单据流水信息
        FlowJrnPo newflowJrnPo = new FlowJrnPo();
        newflowJrnPo.setJrnNo(JrnGenerator.genJrnNo());
        newflowJrnPo.setFlowOrd(workFlowCmmParamPo.getFlowOrdNo());
        newflowJrnPo.setOrdNo(workFlowCmmParamPo.getTargetId());
        newflowJrnPo.setTxUsrNo(workFlowCmmParamPo.getLunchUsrNo());
        newflowJrnPo.setTxDt(DateUtil.getDateStringByFormatString(DateUtil.dataFormatyyyy_MM_dd));
        newflowJrnPo.setTxTm(DateUtil.getDateStringByFormatString(DateUtil.dataFormatHH_mm_ss));
        newflowJrnPo.setTxType(Constants.FLOW_TX_TYPE_VER);
        newflowJrnPo.setTxCoent(workFlowCmmParamPo.getContent());//交易内容自定义
        newflowJrnPo.setNodeNo(workFlowCmmParamPo.getNodeNo());
        newflowJrnPo.setUpStep(flowActionPo.getUpStep());
        newflowJrnPo.setDownStep(flowActionPo.getDownStep());
        newflowJrnPo.setTxDeparNo(Integer.parseInt(userInfoPo.getDeparNo()));
        flowBo.addFlowJrn(newflowJrnPo);
        workFlowCmmParamPo.setFlowJrn(newflowJrnPo.getJrnNo());
        
        //1、发送信息给选择的交易人进行代办
        //消息2.0实现
        LinkedHashMap<String, Object> extJsonMap = new LinkedHashMap<String, Object>();
        extJsonMap.put("flowOrd", workFlowCmmParamPo.getFlowOrdNo());
        extJsonMap.put("ordNo", workFlowCmmParamPo.getTargetId());
        extJsonMap.put("flowJrn", workFlowCmmParamPo.getFlowJrn());
        List<String> recvUsrNoLst = new ArrayList<String>();
        recvUsrNoLst.add(reviewUsrNo);
        if(!msgInfoBo.createRemind(workFlowCmmParamPo.getTargetId(), workFlowCmmParamPo.getTargetTyp(), workFlowCmmParamPo.getActionNo(), recvUsrNoLst, workFlowCmmParamPo.getLunchUsrNo(), workFlowCmmParamPo.getTitle(), workFlowCmmParamPo.getContent(), workFlowCmmParamPo.getLinkUrl(), extJsonMap)){
            throw new HiBusinessReturnException(HiMsgCdConstants.ADD_FLOWINFO_FAIL, "创建消息增加流程失败");
        }
        
        //发送websocket消息到工作台
        LinkedHashMap<String, Object> transMap = new LinkedHashMap<String, Object>();
        NotifyCmmParamPo notifyCmmPramPo = new NotifyCmmParamPo();
        notifyCmmPramPo.setContent(workFlowCmmParamPo.getContent()+" 请注意查收处理！！！");
        notifyCmmPramPo.setRecvUsrNo(reviewUsrNo);
        transMap.put("MSGCMMPRAM", notifyCmmPramPo);
        
        JmsPojo jmsPojo = new JmsPojo();
        jmsPojo.setService("msgNotifyServiceImpl");
        jmsPojo.setMethod("sendSocketMsgToUser");
        jmsPojo.setTransData(transMap);
        hiCommonProducer.invokeService("mng", jmsPojo);
        
        //发送消息到微信公众号(通过POS系统发送)
        transMap.put("USRNO", reviewUsrNo);
        transMap.put("TEMPID", Constants.ORD_ACCEPT_NOTIFY);
        HashMap<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put("first", notifyCmmPramPo.getContent());
        dataMap.put("storeName", workFlowCmmParamPo.getInitiator());
        dataMap.put("orderId", workFlowCmmParamPo.getTargetId());
        dataMap.put("orderType", TargetTypEnum.getName(workFlowCmmParamPo.getTargetTyp()));
        dataMap.put("remark", "预知更多详情，请登录young系统进行查看处理！！！");
        transMap.put("DATA", dataMap);
        jmsPojo.setService("workFlowCmmServiceImpl");
        jmsPojo.setMethod("sendWeChatMsgByPos");
        jmsPojo.setTransData(transMap);
        hiCommonProducer.invokeService("mng", jmsPojo);
        return true;
	}
	
	
	

}
