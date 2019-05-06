package com.ycs.base.spring.quartz;

import java.util.List;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;

import com.ycs.base.log4j.logger.HiJobLogger;
import com.ycs.base.spring.bo.SchJobBo;
import com.ycs.base.spring.domain.pojo.ScheduleJobPojo;

public class SpringBeanDynJobFactory extends SpringBeanJobFactory
		implements ApplicationContextAware, ApplicationListener<ContextRefreshedEvent> {

	private ApplicationContext applicationContext;
	private static SchedulerFactory gSchedulerFactory = new StdSchedulerFactory();
	private static String JOB_GROUP_NAME = "EXTDYN_JOBGROUP_NAME";
	private static String TRIGGER_GROUP_NAME = "EXTDYN_TRIGGERGROUP_NAME";
	@Autowired
	private SchJobBo schJobBo;

	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	protected Object createJobInstance(TriggerFiredBundle bundle) throws Exception {
		Object jobInstance = super.createJobInstance(bundle);
		this.applicationContext.getAutowireCapableBeanFactory().autowireBean(jobInstance);
		return jobInstance;
	}

	protected void initDynamicJob() {
		List<ScheduleJobPojo> dynSchJobLst = this.schJobBo.queryDynSchJob();
		System.out.println(dynSchJobLst);
	}

	public void onApplicationEvent(ContextRefreshedEvent event) {
		if (event.getApplicationContext().getDisplayName().equals("Root WebApplicationContext")) {
			this.initDynamicJob();
		}
	}

	public static void addJob(ScheduleJobPojo job) {
		try {
			Scheduler e = gSchedulerFactory.getScheduler();
			String jobName = JOB_GROUP_NAME + job.getJobId();
			TriggerKey triggerKey = TriggerKey.triggerKey(jobName, TRIGGER_GROUP_NAME);
			CronTrigger trigger = (CronTrigger) e.getTrigger(triggerKey);
			if (null == trigger) {
				Class scheduleBuilder = Class.forName(job.getClassUrl());
				JobDetail jobDetail = JobBuilder.newJob(scheduleBuilder).withIdentity(jobName, TRIGGER_GROUP_NAME)
						.build();
				jobDetail.getJobDataMap().put("scheduleJob", job);
				CronScheduleBuilder scheduleBuilder1 = CronScheduleBuilder.cronSchedule(job.getCronExpression());
				trigger = (CronTrigger) TriggerBuilder.newTrigger().withIdentity(jobName, TRIGGER_GROUP_NAME)
						.withSchedule(scheduleBuilder1).build();
				e.scheduleJob(jobDetail, trigger);
			} else {
				CronScheduleBuilder scheduleBuilder2 = CronScheduleBuilder.cronSchedule(job.getCronExpression());
				trigger = (CronTrigger) trigger.getTriggerBuilder().withIdentity(triggerKey)
						.withSchedule(scheduleBuilder2).build();
				e.rescheduleJob(triggerKey, trigger);
			}

			if (!e.isShutdown()) {
				e.start();
			}
		} catch (ClassNotFoundException | SchedulerException arg7) {
			HiJobLogger.error("Add Job Exception", arg7);
		}
	}

	public static void delJob(ScheduleJobPojo job) {
		try {
			Scheduler e = gSchedulerFactory.getScheduler();
			JobKey jobKey = JobKey.jobKey(JOB_GROUP_NAME + job.getJobId(), TRIGGER_GROUP_NAME);
			e.deleteJob(jobKey);
		} catch (SchedulerException arg2) {
			HiJobLogger.error("Del Job Exception", arg2);
		}
	}

}
