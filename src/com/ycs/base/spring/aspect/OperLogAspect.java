package com.ycs.base.spring.aspect;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.ycs.basbo.constants.Constants;
import com.ycs.base.annotation.HiOperLog;
import com.ycs.base.context.HiContext;
import com.ycs.base.log4j.logger.HiBizLogger;
import com.ycs.base.property.SystemPropertyConfigure;
import com.ycs.base.spring.bo.OperLogBo;
import com.ycs.base.spring.domain.po.OperlogPo;

@Aspect
@Component
public class OperLogAspect {

	@Autowired
	private OperLogBo operLogBo;

	@Pointcut("@annotation(com.ycs.base.annotation.HiOperLog)")
	public void controllerAspect() {
	}

	@After("controllerAspect()")
	public void afterExec(JoinPoint joinPoint) {
	}

	@Around("controllerAspect()")
	public Object aroundExec(ProceedingJoinPoint pjp) throws Throwable {
		MethodSignature ms = (MethodSignature) pjp.getSignature();
		Method method = ms.getMethod();
		if (null != method && ((HiOperLog) method.getAnnotation(HiOperLog.class)).isSaveData()) {
			HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
					.getRequest();
			HttpSession session = request.getSession();
			@SuppressWarnings("rawtypes")
			HashMap sessionMap = (HashMap) session.getAttribute(Constants.USER_SESSION_INFO);
			OperlogPo operlogPo = new OperlogPo();
			operlogPo.setOperId(((HiOperLog) method.getAnnotation(HiOperLog.class)).operId());
			if (null != sessionMap && null != sessionMap.get(Constants.USER_SESSION_USRNO)) {
				operlogPo.setOperId((String) sessionMap.get(Constants.USER_SESSION_USRNO));
			}

			operlogPo.setNodeId("NULL");
			if (null != SystemPropertyConfigure.getNodId()) {
				operlogPo.setNodeId(SystemPropertyConfigure.getNodId());
			}

			operlogPo.setRegId(SystemPropertyConfigure.getInsId());
			operlogPo.setMsgId(HiContext.getCurrentContext().getRequestId());
			operlogPo.setTitle(((HiOperLog) method.getAnnotation(HiOperLog.class)).title());
			operlogPo.setAction(((HiOperLog) method.getAnnotation(HiOperLog.class)).action());
			operlogPo.setAppCnl(((HiOperLog) method.getAnnotation(HiOperLog.class)).channel());
			operlogPo.setReqDt((new SimpleDateFormat("yyyy-MM-dd")).format(new Date()));
			operlogPo.setReqTm((new SimpleDateFormat("HH:mm:ss")).format(new Date()));
			this.operLogBo.saveLogToDB(operlogPo);
			HiBizLogger.info("增加操作员" + operlogPo.getOperId() + "操作" + operlogPo.getAction() + "日志成功");
		}
		return pjp.proceed();
	}

}
