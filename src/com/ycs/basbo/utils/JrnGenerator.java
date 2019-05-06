package com.ycs.basbo.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.commons.lang.StringUtils;

import com.ycs.base.property.SystemPropertyConfigure;

/**
 * @description 流水号生成器
 * @author youcyousyunn
 * @date 2018年11月30日
 */
public class JrnGenerator {
	public static AtomicLong seq = new AtomicLong(0);
	public static AtomicLong msgIdSeq = new AtomicLong(0);
	
	public static String genJrnNo() {
        long a = seq.incrementAndGet();
        a %= 10000;
        return new SimpleDateFormat("yyyyMMddHHmmss").format(Calendar.getInstance().getTime())
                + StringUtils.leftPad(String.valueOf(a), 4, "0");
    }
	
	public static String genMsgId() {
        String insId = SystemPropertyConfigure.getInsId();
        String nodId = SystemPropertyConfigure.getNodId();
        long a = msgIdSeq.incrementAndGet();
        a %= 10000;
        return StringUtils.trimToEmpty(insId)
                + StringUtils.trimToEmpty(nodId)
                + new SimpleDateFormat("yyyyMMddHHmmss").format(Calendar
                        .getInstance().getTime())
                + StringUtils.leftPad(String.valueOf(a), 4, "0");
    }
    
    public static String genShotNo() {
        long a = seq.incrementAndGet();
        a %= 10000;
        return new SimpleDateFormat("yyyyMMddHHmmss").format(Calendar
                .getInstance().getTime())
                + StringUtils.leftPad(String.valueOf(a), 2, "0");
    }
    
    public static String genUsrNo() {
        long a = seq.incrementAndGet();
        a %= 10000;
        return new SimpleDateFormat("yyyyMMddHHmmss").format(Calendar
                .getInstance().getTime())
                + StringUtils.leftPad(String.valueOf(a), 4, "0");
    }
    
    public static void main(String[] args) {
    	String usrNo = genUsrNo();
    	System.out.println(usrNo);
	}

}
