package com.ycs.sys.job;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.JobExecutionContext;

import com.ycs.basbo.constants.HiMsgCdConstants;
import com.ycs.base.exception.HiBusinessAbortException;
import com.ycs.base.exception.HiBusinessReturnException;
import com.ycs.base.spring.quartz.job.AbsJob;

public class DemoJob extends AbsJob {
	
    @Override
    protected String executeProcess(JobExecutionContext context) throws HiBusinessReturnException, HiBusinessAbortException {
        // TODO 定时任务
        System.out.println("this is a demo job !!!"
                + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        return HiMsgCdConstants.SUCCESS;
    }
    
}
