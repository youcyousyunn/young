package com.ycs.msg.service.impl;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.socket.TextMessage;

import com.ycs.basbo.constants.Constants;
import com.ycs.basbo.constants.HiMsgCdConstants;
import com.ycs.basbo.utils.JrnGenerator;
import com.ycs.base.exception.HiBusinessAbortException;
import com.ycs.base.exception.HiBusinessReturnException;
import com.ycs.base.spring.handler.SystemWebSocketHandler;
import com.ycs.base.utils.DateUtil;
import com.ycs.base.utils.JsonUtils;
import com.ycs.base.utils.PageUtil;
import com.ycs.msg.bo.MsgNotifyBo;
import com.ycs.msg.bo.UserNotifyBo;
import com.ycs.msg.domain.dto.NotifyRequestDto;
import com.ycs.msg.domain.dto.NotifyResponseDto;
import com.ycs.msg.domain.dto.QryNotifyRequestDto;
import com.ycs.msg.domain.dto.QryNotifyResponseDto;
import com.ycs.msg.domain.po.MsgNotifyPo;
import com.ycs.msg.domain.po.NotifyCmmParamPo;
import com.ycs.msg.domain.po.NotifyPo;
import com.ycs.msg.service.MsgNotifyService;
import com.ycs.work.domain.dto.IsReadMsgRequestDto;
import com.ycs.work.domain.dto.IsReadMsgResponseDto;
import com.ycs.work.domain.dto.QryMsgNotifyRequestDto;
import com.ycs.work.domain.dto.QryMsgNotifyResponseDto;

@Service
public class MsgNotifyServiceImpl implements MsgNotifyService {
	@Autowired(required=false)
    private SystemWebSocketHandler systemWebSocketHandler;
	@Autowired
	private MsgNotifyBo msgNotifyBo;
	@Autowired
    private UserNotifyBo userNotifyBo;
	
	@Override
	public void sendSocketMsgToUser(LinkedHashMap<String, Object> map)throws HiBusinessAbortException, HiBusinessReturnException {
		@SuppressWarnings("unchecked")
        Map<String,Object> paramMap = (LinkedHashMap<String, Object>) map.get("MSGCMMPARAM");
        NotifyCmmParamPo notifyCmmParamPo = null;
        try {
            notifyCmmParamPo = (NotifyCmmParamPo) JsonUtils.Map2Bean(NotifyCmmParamPo.class, paramMap);
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException | IntrospectionException e) {
            e.printStackTrace();
        }
        // 发送消息给指定的人
        TextMessage message = new TextMessage(notifyCmmParamPo.getContent());
        systemWebSocketHandler.sendMessageToUser(notifyCmmParamPo.getRecvUsrNo(), message);
	}


	@Override
	public QryMsgNotifyResponseDto qryMsgNotify(QryMsgNotifyRequestDto request)throws HiBusinessAbortException, HiBusinessReturnException {
		Map<String, Object> paramMap = new LinkedHashMap<String, Object>();
        if("Y".equals(request.getIsMyLunch())){
            paramMap.put("lunchUsrNo", request.getUsrNo());
        }else{
            paramMap.put("recvUsrNo", request.getUsrNo());
        }
        paramMap.put("isStar", request.getIsStar());
        paramMap.put("msgTyp", request.getMsgTyp());
        paramMap.put("title", request.getTitle());
        
        // 首先查询共有多少记录
        int totalCount = msgNotifyBo.qryMsgNotifyCount(paramMap);
        // 计算分页信息
        PageUtil.calculatePageInfo(totalCount, request.getPageSize(), request.getCurrentPage());
        
        // 分页查询
        List<NotifyPo> notifyPoLst = msgNotifyBo.qryMsgNotifyPage(paramMap, PageUtil.getStartRow(), PageUtil.getOffset());
        
        QryMsgNotifyResponseDto response = new QryMsgNotifyResponseDto();
        response.setRows(notifyPoLst);
        response.setTotal(totalCount);
        return response;
	}

	@Override
	public IsReadMsgResponseDto isReadMsg(IsReadMsgRequestDto request)throws HiBusinessAbortException, HiBusinessReturnException {
		MsgNotifyPo msgNotifyPo = msgNotifyBo.qryMsgNotifyByMsgId(request.getMsgId());
		if (null != msgNotifyPo){
			if(!userNotifyBo.isReadMsg(request.getMsgId(), request.getUsrNo())){
				//更新消息是否已读失败
				throw new HiBusinessReturnException(HiMsgCdConstants.UPD_MSG_USER_NOTIFY_FAIL, "更新消息是否已读失败");
			}
		}
        IsReadMsgResponseDto response = new IsReadMsgResponseDto();
        return response;
	}

	@Override
	@Transactional(rollbackFor = { HiBusinessReturnException.class })
	public NotifyResponseDto addAnnounce(NotifyRequestDto request)throws HiBusinessAbortException, HiBusinessReturnException {
		NotifyResponseDto responseDto = new NotifyResponseDto();
		//添加未读消息记录
        MsgNotifyPo msgNotifyPo = new MsgNotifyPo();
        msgNotifyPo.setMsgId(JrnGenerator.genJrnNo());
        msgNotifyPo.setMsgTitle(request.getTitle());
        msgNotifyPo.setMsgContent(request.getContent());
        msgNotifyPo.setMsgTyp(Constants.MSG_TYP_ANNOUNCE);
        msgNotifyPo.setLunchUsrNo(request.getUsrNo());
        msgNotifyPo.setSendTyp("FAST");//立即发送
        msgNotifyPo.setActionTyp("POST");
        msgNotifyPo.setExtJson(null);
        msgNotifyPo.setLinkUrl(null);
        msgNotifyPo.setCreDt(DateUtil.getDateStringByFormatString(DateUtil.dataFormatyyyy_MM_dd));
        msgNotifyPo.setCreTm(DateUtil.getDateStringByFormatString(DateUtil.dataFormatHH_mm_ss));
        if (msgNotifyBo.addMsgNotify(msgNotifyPo)){
        	responseDto.setRspCd(HiMsgCdConstants.SUCCESS);
        	// 发送消息给所有用户
            TextMessage message = new TextMessage(request.getContent());
        	systemWebSocketHandler.sendMessageToUsers(message);
        } else {
        	throw new HiBusinessReturnException(HiMsgCdConstants.ADD_ANNOUNCE_FAIL, "add announce fail");
        }
        return responseDto;
	}

	@Override
	@Transactional(readOnly = true)
	public QryNotifyResponseDto qryNotify(QryNotifyRequestDto request)throws HiBusinessAbortException, HiBusinessReturnException {
		Map<String, Object> paramMap = new LinkedHashMap<String, Object>();
        paramMap.put("msgTyp", request.getMsgTyp());
        
        // 首先查询共有多少记录
        int totalCount = msgNotifyBo.qryNotifyCount(paramMap);
        // 计算分页信息
        PageUtil.calculatePageInfo(totalCount, request.getPageSize(), request.getCurrentPage());
        
        // 分页查询
        List<MsgNotifyPo> notifyLst = msgNotifyBo.qryNotifyPage(paramMap, PageUtil.getStartRow(), PageUtil.getOffset());
        
        QryNotifyResponseDto response = new QryNotifyResponseDto();
        response.setRows(notifyLst);
        response.setTotal(totalCount);
        return response;
	}
	
}
