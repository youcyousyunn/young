package com.ycs.base.spring.quartz.job;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.ycs.base.context.HiContext;
import com.ycs.base.context.HiRequestInfo;
import com.ycs.base.context.HiTransactionInfo;
import com.ycs.base.exception.HiBusinessAbortException;
import com.ycs.base.exception.HiBusinessReturnException;
import com.ycs.base.log4j.logger.HiJobLogger;
import com.ycs.base.property.SystemPropertyConfigure;
import com.ycs.base.spring.bo.PubMsgBo;
import com.ycs.base.spring.bo.SchJobBo;
import com.ycs.base.spring.domain.po.PubMsgPo;
import com.ycs.base.spring.domain.pojo.ScheduleJobPojo;
import com.ycs.base.spring.jms.producer.LogJnlProducer;
import com.ycs.base.spring.quartz.SpringBeanDynJobFactory;

@DisallowConcurrentExecution
public abstract class DynJob extends QuartzJobBean {

	@Autowired
	private SchJobBo schJobBo;
	@Autowired
	private PubMsgBo pubMsgBo;
	@Autowired
	private LogJnlProducer logJnlProducer;
	protected ScheduleJobPojo scheduleJobPojo;
	
	protected boolean beforeExec(JobExecutionContext context) {
		HiContext ctx = HiContext.createContext("currentContext", (HiContext) null);
		HiContext.pushCurrentContext(ctx);
		this.initReqInfo(ctx.getRequestId());
		HiTransactionInfo transactionInfo = this.initSchJob();
		return null != transactionInfo;
	}

	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		this.scheduleJobPojo = (ScheduleJobPojo) context.getJobDetail().getJobDataMap().get("scheduleJob");
		if (this.beforeExec(context)) {
			String msgCd = null;
			try {
				HiJobLogger.infoJob("start");
				msgCd = this.executeProcess(context);
			} catch (HiBusinessReturnException arg7) {
				msgCd = arg7.getCode();
				HiJobLogger.error(msgCd, arg7);
			} catch (HiBusinessAbortException arg8) {
				msgCd = arg8.getCode();
				HiJobLogger.error(msgCd, arg8);
			} finally {
				this.afterExec(msgCd, context);
			}
		}
	}

	protected void executeAfter(JobExecutionContext context) {
		if ("1".equals(this.scheduleJobPojo.getRunMod())) {
			this.schJobBo.delDynJob(this.scheduleJobPojo.getJobId());
			SpringBeanDynJobFactory.delJob(this.scheduleJobPojo);
		}
	}

	protected void afterExec(String msgCd, JobExecutionContext context) {
		String msgInf = null;
		if (null != msgCd) {
			PubMsgPo reqInfo = this.pubMsgBo.queryMsgInf(msgCd);
			if (null != reqInfo) {
				msgInf = reqInfo.getMsgInf();
			}
		}
		HiRequestInfo reqInfo1 = HiRequestInfo.getCurInstance();
		reqInfo1.setResponseTime(System.currentTimeMillis());
		long totTime = reqInfo1.getResponseTime() - reqInfo1.getRequestTime();
		reqInfo1.setMsgCd(msgCd);
		reqInfo1.setMsgInf(msgInf);
		reqInfo1.setNodId(SystemPropertyConfigure.getNodId());
		reqInfo1.setInsId(SystemPropertyConfigure.getInsId());
		this.logJnlProducer.log(reqInfo1);
		HiJobLogger.infoJob("Process[" + reqInfo1.getModId() + "|" + reqInfo1.getSysCnl() + "|" + reqInfo1.getCode()
				+ "|" + reqInfo1.getDesc() + "|" + reqInfo1.getMsgCd() + "|" + reqInfo1.getMsgInf() + "|" + totTime
				+ "]");
		HiContext currentContext = HiContext.popCurrentContext();
		currentContext.clear();
		currentContext = null;
	}
	
	protected abstract String executeProcess(JobExecutionContext arg0)
			throws HiBusinessReturnException, HiBusinessAbortException;

	private HiTransactionInfo initSchJob() {
		if (null == this.scheduleJobPojo) {
			HiJobLogger.infoJob("Can not found the job porperties in t_pub_dynschjob.");
			return null;
		} else if (this.scheduleJobPojo.getTxFlg().toUpperCase().equals("N")) {
			HiJobLogger.infoJob("Job is closed.");
			return null;
		} else {
			HiTransactionInfo transactionInfo = new HiTransactionInfo();
			transactionInfo.setAppId(this.scheduleJobPojo.getAppId());
			transactionInfo.setModId(this.scheduleJobPojo.getModId());
			transactionInfo.setTxSwitch(this.scheduleJobPojo.getTxFlg());
			transactionInfo.setLogSwitch(this.scheduleJobPojo.getLogFlg());
			transactionInfo.setCode(this.scheduleJobPojo.getJobId());
			transactionInfo.setDesc(this.scheduleJobPojo.getDesc());
			HiRequestInfo.getCurInstance().setDesc(transactionInfo.getDesc());
			HiRequestInfo.getCurInstance().setModId(transactionInfo.getModId());
			HiTransactionInfo.setCurInstance(transactionInfo);
			return transactionInfo;
		}
	}

	private HiRequestInfo initReqInfo(String requestId) {
		HiRequestInfo reqInf = new HiRequestInfo();
		reqInf.setMsgId(requestId);
		reqInf.setCode(this.scheduleJobPojo.getJobId());
		reqInf.setRequestTime(System.currentTimeMillis());
		reqInf.setSysCnl("SYS");
		HiRequestInfo.setCurInstance(reqInf);
		return reqInf;
	}

	public ScheduleJobPojo getScheduleJobPojo() {
		return this.scheduleJobPojo;
	}

	public void setScheduleJobPojo(ScheduleJobPojo scheduleJobPojo) {
		this.scheduleJobPojo = scheduleJobPojo;
	}

}
