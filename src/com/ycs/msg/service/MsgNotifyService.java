package com.ycs.msg.service;

import java.util.LinkedHashMap;

import com.ycs.base.exception.HiBusinessAbortException;
import com.ycs.base.exception.HiBusinessReturnException;
import com.ycs.msg.domain.dto.NotifyRequestDto;
import com.ycs.msg.domain.dto.NotifyResponseDto;
import com.ycs.msg.domain.dto.QryNotifyRequestDto;
import com.ycs.msg.domain.dto.QryNotifyResponseDto;
import com.ycs.work.domain.dto.IsReadMsgRequestDto;
import com.ycs.work.domain.dto.IsReadMsgResponseDto;
import com.ycs.work.domain.dto.QryMsgNotifyRequestDto;
import com.ycs.work.domain.dto.QryMsgNotifyResponseDto;

/**
 * 消息通知Service接口
 * @author youcyousyunn
 * @date 2018年6月21日
 */
public interface MsgNotifyService {

	/**
	 * 发送websocket消息到对应用户工作台
	 * @param pramMap
	 * @return void
	 */
	public void sendSocketMsgToUser(LinkedHashMap<String, Object> pramMap) throws HiBusinessAbortException,HiBusinessReturnException;

	/**
	 * 查询工作台消息
	 * @param request
	 * @return QryMsgNotifyResponseDto
	 * @throws HiBusinessAbortException,HiBusinessReturnException
	 */
	public QryMsgNotifyResponseDto qryMsgNotify(QryMsgNotifyRequestDto request)throws HiBusinessAbortException,HiBusinessReturnException;

	/**
	 * 阅读消息请求
	 * @param request
	 * @return IsReadMsgResponseDto
	 * @throws HiBusinessAbortException,HiBusinessReturnException
	 */
	public IsReadMsgResponseDto isReadMsg(IsReadMsgRequestDto request)throws HiBusinessAbortException,HiBusinessReturnException;

	/**
	 * 创建公告消息
	 * @param
	 * @return NotifyResponseDto
	 * @throws 
	 */
	public NotifyResponseDto addAnnounce(NotifyRequestDto request)throws HiBusinessAbortException,HiBusinessReturnException;

	/**
	 * 查找公告消息
	 * @param
	 * @return NotifyResponseDto
	 * @throws 
	 */
	public QryNotifyResponseDto qryNotify(QryNotifyRequestDto request)throws HiBusinessAbortException,HiBusinessReturnException;
	
}
