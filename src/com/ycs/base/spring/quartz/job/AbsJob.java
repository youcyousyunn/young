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
import com.ycs.base.spring.domain.po.SchJobPo;
import com.ycs.base.spring.jms.producer.LogJnlProducer;

@DisallowConcurrentExecution
public abstract class AbsJob extends QuartzJobBean {
	public String jobId;
	
	@Autowired
	public SchJobBo schJobBo;
	@Autowired
	public PubMsgBo pubMsgBo;
	@Autowired
	private LogJnlProducer logJnlProducer;
	
	protected abstract String executeProcess(JobExecutionContext context)
			throws HiBusinessReturnException, HiBusinessAbortException;

	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		if (this.beforeExec(context)) {
			String msgCd = null;
			try {
				HiJobLogger.infoJob("start");
				msgCd = this.executeProcess(context);
			} catch (HiBusinessReturnException e) {
				msgCd = e.getCode();
				HiJobLogger.error(msgCd, e);
			} catch (HiBusinessAbortException e) {
				msgCd = e.getCode();
				HiJobLogger.error(msgCd, e);
			} finally {
				this.afterExec(msgCd, context);
			}
		}
	}

	protected boolean beforeExec(JobExecutionContext context) {
		HiContext hiContext = HiContext.createContext("currentContext", (HiContext) null);
		HiContext.pushCurrentContext(hiContext);
		this.initReqInfo(hiContext.getRequestId());
		HiTransactionInfo transactionInfo = this.initSchJob();
		return null != transactionInfo;
	}

	protected void afterExec(String msgCd, JobExecutionContext context) {
		String msgInf = null;
		if (null != msgCd) {
			PubMsgPo pubMsgPo = this.pubMsgBo.queryMsgInf(msgCd);
			if (null != pubMsgPo) {
				msgInf = pubMsgPo.getMsgInf();
			}
		}

		HiRequestInfo reqInfo = HiRequestInfo.getCurInstance();
		reqInfo.setResponseTime(System.currentTimeMillis());
		long totTime = reqInfo.getResponseTime() - reqInfo.getRequestTime();
		reqInfo.setMsgCd(msgCd);
		reqInfo.setMsgInf(msgInf);
		reqInfo.setNodId(SystemPropertyConfigure.getNodId());
		reqInfo.setInsId(SystemPropertyConfigure.getInsId());
		this.logJnlProducer.log(reqInfo);
		HiJobLogger.infoJob("Process[" + reqInfo.getModId() + "|" + reqInfo.getSysCnl() + "|" + reqInfo.getCode()
				+ "|" + reqInfo.getDesc() + "|" + reqInfo.getMsgCd() + "|" + reqInfo.getMsgInf() + "|" + totTime
				+ "]");
		HiContext currentContext = HiContext.popCurrentContext();
		currentContext.clear();
		currentContext = null;
	}

	private HiTransactionInfo initSchJob() {
		SchJobPo schJobPo = this.schJobBo.querySchJob(this.jobId);
		if (null == schJobPo) {
			HiJobLogger.infoJob("Has not found the job porperties in table t_pub_schjob ");
			return null;
		} else if (schJobPo.getTxFlg().toUpperCase().equals("N")) {
			HiJobLogger.infoJob("Job is closed...");
			return null;
		} else {
			HiTransactionInfo transactionInfo = new HiTransactionInfo();
			transactionInfo.setAppId(schJobPo.getAppId());
			transactionInfo.setModId(schJobPo.getModId());
			transactionInfo.setTxSwitch(schJobPo.getTxFlg());
			transactionInfo.setLogSwitch(schJobPo.getLogFlg());
			transactionInfo.setCode(this.jobId);
			transactionInfo.setDesc(schJobPo.getJobDesc());
			HiRequestInfo.getCurInstance().setDesc(transactionInfo.getDesc());
			HiRequestInfo.getCurInstance().setModId(transactionInfo.getModId());
			HiTransactionInfo.setCurInstance(transactionInfo);
			return transactionInfo;
		}
	}

	private HiRequestInfo initReqInfo(String requestId) {
		HiRequestInfo reqInf = new HiRequestInfo();
		reqInf.setMsgId(requestId);
		reqInf.setCode(this.jobId);
		reqInf.setRequestTime(System.currentTimeMillis());
		reqInf.setSysCnl("SYS");
		HiRequestInfo.setCurInstance(reqInf);
		return reqInf;
	}
	
	public String getJobId() {
		return this.jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

}
