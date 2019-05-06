package com.ycs.base.spring.aspect;

import org.aspectj.lang.ProceedingJoinPoint;

import com.ycs.base.log4j.logger.HiBoLogger;

public class LogAspect {
	private HiBoLogger logger;

	public void setLogName(String logName) {
		this.logger = HiBoLogger.getLogger(logName);
	}

	public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
		long time = System.currentTimeMillis();
		Object[] args = pjp.getArgs();
		this.logger.infoEnter(pjp.toString(), args);
		Object retVal = pjp.proceed();
		time = System.currentTimeMillis() - time;
		this.logger.infoExit(pjp.toString(), retVal, time);
		return retVal;
	}

}
