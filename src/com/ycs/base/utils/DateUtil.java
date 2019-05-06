package com.ycs.base.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * 日期处理工具类
 * @author youcyousyunn
 * @date 2018年3月12日
 */
public class DateUtil {
	
    /**
     * 时间单位
     */
    public static final String YEAR = "YEAR";
    public static final String MONTH = "MONTH";
    public static final String DAY = "DAY";
    public static final String HOUR = "HOUR";
    public static final String MINUTE = "MINUTE";
    public static final String SECOND = "SECOND";

    /**
     * yyyyMMdd格式字符
     */
    public static final String dataFormatyyyyMMdd = "yyyyMMdd";

    /**
     * HHmmss格式字符
     */
    public static final String dataFormatHHmmss = "HHmmss";

    /**
     * yyyyMMddHHmmss格式字符
     */
    public static final String dataFormatyyyyMMddHHmmss = "yyyyMMddHHmmss";
    
    /**
     * YYYY-MM-dd HH:mm:ss格式字符
     */
    public static final String dataFormatyyyy_MM_dd_HH_mm_ss = "yyyy-MM-dd H:m:s";
    
    /**
     * yyyy-MM-dd格式字符
     */
    public static final String dataFormatyyyy_MM_dd = "yyyy-MM-dd";

    /**
     * HH:mm:ss格式字符
     */
    public static final String dataFormatHH_mm_ss = "HH:mm:ss";

    /**
     * 利用时间差来记录时间<br>
     * 平台时间比本地时间快多少(可能为负),<br>
     * 若没设置则span = 0,认为本地时间就是平台时间
     */
    private static long span = 0;

    /**
     * 获取当前时间 
     * @param
     * @return Date
     */
    public static Date getDate() {
        Date nowAndroid = new Date();
        long nowPlant = nowAndroid.getTime() + span;
        Date nowPlantDate = new Date(nowPlant);
        return nowPlantDate;
    }
    
    /**
     * 获取当前时间 
     * @param
     * @return String
     */
    public static String getStringDate() {
        Date nowAndroid = new Date();
        long nowPlant = nowAndroid.getTime() + span;
        Date nowPlantDate = new Date(nowPlant);
        SimpleDateFormat df = new SimpleDateFormat(dataFormatyyyy_MM_dd);
        return df.format(nowPlantDate);
    }
    
    public static Date getAddTime(long addTime) {
        Date nowAndroid = new Date();
        long nowPlant = nowAndroid.getTime() +addTime+ span;
        Date nowPlantDate = new Date(nowPlant);
        return nowPlantDate;
    }

    public Calendar getCalendar() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(getDate());
        return cal;
    }

    /**
     * 根据format串返回当前日期串
     * @param fomatString
     * @return
     */
    public static String getDateStringByFormatString(String fomatString) {
        Date date = getDate();
        SimpleDateFormat df = new SimpleDateFormat(fomatString);
        return df.format(date);
    }
    
    /**
     * 根据format串返回当前日期
     * @param
     * @return Date
     */
    public static Date getDateByFormatString(String fomatString) {
        Date date = getDate();
        SimpleDateFormat df = new SimpleDateFormat(fomatString);
        String dfDate = df.format(date);
        try {
			return df.parse(dfDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
    }
    
    /**
     * Title: getAddDateStringByFormatString<br/>
     * Description: 得到一段时间之后的时间<br/>
     * @author dengwenjun
     * @date 2015年8月12日下午10:00:09
     *
     * @param addDate
     * @param fomatString
     * @return
     */
    public static String getAddTimeStringByFormatString(long addDate, String fomatString) {
        Date date = getAddTime(addDate);
        SimpleDateFormat df = new SimpleDateFormat(fomatString);
        return df.format(date);
    }
    
    /**
     * Title getAddDayStringByFormatString
     * Description 获取指定天数后的日期
     * @param @param addDate
     * @param @param fomatString
     * @param @return
     * @return String
     * @throws
     */
    public static String getAddDayStringByFormatString(int addDate, String fomatString) {
        SimpleDateFormat sdf = new SimpleDateFormat(fomatString);
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, addDate);
        return sdf.format(calendar.getTime());
    }

    /**
     * 获取指定日期时间的增加偏移量后格式化的字符串
     * 
     * @param minute
     *            分钟偏移量
     * @return 格式化的日期时间字符串
     */
    public String getSpecifyDateStringByMinuteOffset(int value) {
        Calendar calendar = getCalendar();
        calendar.add(Calendar.MINUTE, value);

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:00");
        return df.format(calendar.getTime());
    }
    
    /**
     * Title: convertDateToString<br/>
     * Description: 把data转为制定格式的字符串<br/>
     * 
     * @author lai_jj
     * @date 2014年12月17日下午3:31:14
     * 
     * @param date
     * @param format
     * @return
     */
    public String convertDateToString(Date date, String format) {
        if (date == null) {
            return null;
        }
        if (format == null) {
            format = "yyyyMMddHHmmss";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        sdf.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        String result = sdf.format(date);
        return result;
    }

    /**
     * 根据long类型的时间转出format的时间
     * 
     * @param timeLongVal
     * @param descFormat
     * @return
     */
    public static String formatDateLongToString(Long timeLongVal,
            String descFormat) {
        Date date = new Date(timeLongVal);
        SimpleDateFormat df = new SimpleDateFormat(descFormat);
        return df.format(date);
    }

    /**
     * 
     * Title: convertFormatDataToString<br/>
     * Description: 从yyyy-MM-dd HH:mm:ss 格式的时间里面取出 ddHHmmss<br/>
     * 
     * @author lai_jj
     * @date 2014年12月17日下午3:39:45
     * 
     * @param date
     * @return
     */
    public String convertFormatDataToString(String date) {
        String[] datesplite = date.split(" ");
        String[] before = datesplite[0].split("-");
        String[] after = datesplite[1].split(":");

        StringBuilder builder = new StringBuilder();
        builder.append(before[2]).append(after[0]).append(after[1])
                .append(after[2]);
        return builder.toString();
    }

    /**
     * 
     * Title: convertStringDateToFormatString<br/>
     * Description: 时间字段转换<br/>
     * 
     * @author lai_jj
     * @date 2014年12月25日下午3:14:16
     * 
     * @param srcDateStr
     *            源时间字符
     * @param srcFormat
     *            源时间格式
     * @param descFormat
     *            目标时间格式
     * @return
     */
    public static String convertStringDateToFormatString(String srcDateStr,
            String srcFormat, String descFormat) {
        if (srcDateStr == null || "".equals(srcDateStr)) {
            return "";
        } else {
            long longTime = formatDateStringToLong(srcDateStr, srcFormat);
            return formatDateLongToString(longTime, descFormat);
        }
    }

    /**
     * 将formatStr的时间 转成 long
     * 
     * @param date
     * @return
     */
    public static Long formatDateStringToLong(String date, String formatStr) {
        SimpleDateFormat formatter = new SimpleDateFormat(formatStr);
        try {
            formatter.setTimeZone(TimeZone.getTimeZone("GMT+8"));
            return formatter.parse(date).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0l;
    }

    /**
     * 计算时间间隔
     * 
     * @param lastEndTime
     *            上一次结束时间
     * @param currentBeginTime
     *            这一次开始时间
     * @param timeFormatStr
     *            时间格式
     * @return
     * @Author:Junjie.Lai bpascal.cn@gmail.com
     * @Since:2013-2-24
     * @Version:Digi Portal Demo
     */
    public static long getIntervalSecond(String lastEndTime, String currentBeginTime,
            String timeFormatStr) {
        long dlast, dbegin, dInterval;
        SimpleDateFormat df = new SimpleDateFormat(timeFormatStr);
        try {
            dlast = df.parse(lastEndTime).getTime();
            dbegin = df.parse(currentBeginTime).getTime();
            dInterval = Math.abs((dbegin - dlast) / 1000);// 时间间隔
        } catch (Exception e) {
            dInterval = 0;
            e.printStackTrace();
        }
        return dInterval;
    }

    /**
     * 根据秒数，计算时间
     * 
     * @param time
     * @return
     * @Author:Junjie.Lai bpascal.cn@gmail.com
     * @Since:2013-2-25
     * @Version:Digi Portal Demo
     */
    public String computSecond(long time) {
        long minute = time % 3600 / 60;// 分
        long second = time % 60;// 秒
        return String.valueOf((minute < 10 ? ("0" + minute) : minute) + ":"
                + (second < 10 ? ("0" + second) : second));
    }

    /**
     * 根据秒数，计算时间
     * 
     * @param time
     * @return
     * @Author:Junjie.Lai bpascal.cn@gmail.com
     * @Since:2013-2-25
     * @Version:Digi Portal Demo
     */
    public String computSecondToFullTime(long time) {
        long hour = time / 3600;// 时
        long minute = time % 3600 / 60;// 分
        long second = time % 60;// 秒
        return String.valueOf((hour < 10 ? ("0" + hour) : hour) + ":"
                + (minute < 10 ? ("0" + minute) : minute) + ":"
                + (second < 10 ? ("0" + second) : second));
    }

    /**
     * 对日期进行计算
     * 
     * @param date
     *            日期
     * @param unit
     *            计算单位
     * @param offset
     *            偏移量
     * @return
     */
    public static String calculateDate(String date, String unit, int offset, String dateTyp) {
        // 设置日期格式
        SimpleDateFormat sdf = new SimpleDateFormat(dateTyp);

        Date beginDt = null;
        try {
            beginDt = sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // 设置计算方式
        int type;
        if (unit.equals(DateUtil.YEAR)) {
            type = Calendar.YEAR;
        } else if (unit.equals(DateUtil.MONTH)) {
            type = Calendar.MONTH;
        } else if (unit.equals(DateUtil.DAY)) {
            type = Calendar.DAY_OF_YEAR;
        } else if(unit.equals(DateUtil.HOUR)){
            type = Calendar.HOUR_OF_DAY;
        } else if(unit.equals(DateUtil.MINUTE)){
            type = Calendar.MINUTE;
        }else {
            return "";
        }

        // 计算日期
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(beginDt);
        rightNow.add(type, offset);

        Date endDt = rightNow.getTime();
        String reStr = sdf.format(endDt);
        return reStr;
    }

    /**
     * 
     * Title: calculateTime<br/>
     * Description: 对时间进行计算<br/>
     * @param time 时间
     * @param unit 单位
     * @param offset 偏移量
     * @return
     */
    public static String calculateTime(String time, String unit, int offset) {
        // 设置日期格式
        SimpleDateFormat sdf = new SimpleDateFormat("HHmmss");

        Date beginTm = null;
        try {
            beginTm = sdf.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // 设置计算方式
        int type;
        if (unit.equals(DateUtil.HOUR)) {
            type = Calendar.HOUR_OF_DAY;
        } else if (unit.equals(DateUtil.MINUTE)) {
            type = Calendar.MINUTE;
        } else if (unit.equals(DateUtil.SECOND)) {
            type = Calendar.SECOND;
        } else {
            return "";
        }

        // 计算日期
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(beginTm);
        rightNow.add(type, offset);

        Date endTm = rightNow.getTime();
        String reStr = sdf.format(endTm);
        return reStr;
    }

    /**
     * 
     * Title: calculateDateTime<br/>
     * Description: 日期时间计算 <br/>
     * @param dateTime
     * @param unit
     * @param offset
     * @return
     */
    public static String calculateDateTime(String dateTime, String unit,
            int offset) {
        // 设置日期格式
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");

        Date beginTm = null;
        try {
            beginTm = sdf.parse(dateTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // 设置计算方式
        int type;
        if (unit.equals(DateUtil.HOUR)) {
            type = Calendar.HOUR_OF_DAY;
        } else if (unit.equals(DateUtil.MINUTE)) {
            type = Calendar.MINUTE;
        } else if (unit.equals(DateUtil.SECOND)) {
            type = Calendar.SECOND;
        } else if (unit.equals(DateUtil.HOUR)) {
            type = Calendar.HOUR_OF_DAY;
        } else if (unit.equals(DateUtil.MINUTE)) {
            type = Calendar.MINUTE;
        } else if (unit.equals(DateUtil.SECOND)) {
            type = Calendar.SECOND;
        } else {
            return "";
        }

        // 计算日期
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(beginTm);
        rightNow.add(type, offset);

        Date endTm = rightNow.getTime();
        String reStr = sdf.format(endTm);
        return reStr;
    }
    
    /**
     *计算两个日期之间相差的天数 
     * @throws ParseException 
     */
    public static int calBetweenDays(String date1,String date2) throws ParseException{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        
        Calendar cal = Calendar.getInstance();
        Date d1 = sdf.parse(date1);
        Date d2 = sdf.parse(date2);
        
        cal.setTime(d1);
        long t1 = cal.getTimeInMillis();
        cal.setTime(d2);
        long t2 = cal.getTimeInMillis();
        long days = (t2-t1)/(1000*3600*24);
        int a = (int) days;
        return a;
    }
    
    /**
     * Title: nowMonthLastDay<br/>
     * Description: 获取当前输入日期月份最后一天<br/>
     * @param dateTyp
     * @param date
     * @return
     * @throws ParseException
     */
    public static String nowMonthLastDay(String dateTyp, String date){
        try {
            SimpleDateFormat format = new SimpleDateFormat(dateTyp);
            Date offdate = format.parse(date);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(offdate);
            int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
            calendar.add(Calendar.DATE, 1 - dayOfMonth);
            calendar.add(Calendar.MONTH,   1); 
            calendar.add(Calendar.DATE, -1);
            return format.format(calendar.getTime());
        } catch (ParseException e) {
            return null;
        }
    }
    
    /**
     * Title: nowMonthFistDay<br/>
     * Description: 获取当前输入日期月份第一天<br/>
     * @param dateTyp
     * @param date
     * @return
     * @throws ParseException
     */
    public static String nowMonthFistDay(String dateTyp, String date){
        try {
            SimpleDateFormat format = new SimpleDateFormat(dateTyp);
            Date offdate = format.parse(date);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(offdate);
            int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
            calendar.add(Calendar.DATE, 1 - dayOfMonth);
            return format.format(calendar.getTime());
        } catch (ParseException e) {
            return null;
        }
    }
    
    /**
     * Title: upMonthFistDay<br/>
     * Description: 获取上个月份第一天<br/>
     * @param dateTyp
     * @return
     * @throws ParseException
     */
    public static String upMonthFistDay(String dateTyp){
        SimpleDateFormat format = new SimpleDateFormat(dateTyp);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return format.format(calendar.getTime());
    }
    
    /**
     * Title: upMonthLastDay<br/>
     * Description: 获取上个月份最后一天<br/>
     * @param dateTyp
     * @return
     */
    public static String upMonthLastDay(String dateTyp){
        SimpleDateFormat format = new SimpleDateFormat(dateTyp);
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 1); 
        calendar.add(Calendar.DATE, -1);
        return format.format(calendar.getTime());
    }
    
    /**
     * Title: getWeekFistDay<br/>
     * Description: 获取指定日期周第一天<br/>
     * @param date
     * @param dateTyp
     * @return
     */
    public static String getWeekFistDay(String date, String dateTyp) {
        try {
            SimpleDateFormat format = new SimpleDateFormat(dateTyp);
            Date offdate = format.parse(date);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(offdate);
            calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            return format.format(calendar.getTime());
        } catch (ParseException e) {
            return null;
        }
    }
    
    /**
     * 
     * Title: getUpWeekDay<br/>
     * Description: 获取上一周指定周几日期<br/>
     * @param dateTyp
     * @param offset
     * @return
     */
    public static String getUpWeekDay(String dateTyp, int offset){
        SimpleDateFormat format = new SimpleDateFormat(dateTyp);
        Calendar cal = Calendar.getInstance();  
        int year = cal.get(Calendar.YEAR);  
        int month = cal.get(Calendar.MONTH);  
        int day = cal.get(Calendar.DAY_OF_MONTH);  
        cal.set(year, month, day);
        cal.add(Calendar.WEEK_OF_MONTH, -1);
        cal.add(Calendar.DATE, -1 * cal.get(Calendar.DAY_OF_WEEK) + 2 + offset-1);
        return format.format(cal.getTime());
    }
    
    /**
     * Title: getWeekDay<br/>
     * Description: 获取本周指定周几日期<br/>
     * @param dateTyp
     * @param offset
     * @return
     */
    public static String getWeekDay(String dateTyp, int offset){
        SimpleDateFormat format = new SimpleDateFormat(dateTyp);
        Calendar cal = Calendar.getInstance();  
        int year = cal.get(Calendar.YEAR);  
        int month = cal.get(Calendar.MONTH);  
        int day = cal.get(Calendar.DAY_OF_MONTH);  
        cal.set(year, month, day);
        cal.add(Calendar.WEEK_OF_MONTH,0);
        cal.add(Calendar.DATE, -1 * cal.get(Calendar.DAY_OF_WEEK) + 2 + offset-1);
        return format.format(cal.getTime());
    }
    
    /**
     * Title: getWeekByYear<br/>
     * Description: 获取当前日期是本年第几周<br/>
     * @param dateStr
     * @return
     */
    public static String getWeekByYear(String dateStr){
        try{
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date date = format.parse(dateStr);
            Calendar calendar = Calendar.getInstance();
            calendar.setFirstDayOfWeek(Calendar.MONDAY);
            calendar.setTime(date);
            return String.valueOf(calendar.get(Calendar.WEEK_OF_YEAR));
        }catch(ParseException e){
            return null;
        }
    }
    
    /**
     * Title: getWeekOfDate<br/>
     * Description: 获取当前日期为星期几数字表达形式<br/>
     * @return
     */
    public static String getWeekOfDate() {
        String[] weekDays = {"7", "1", "2", "3", "4", "5", "6"};
        Calendar cal = Calendar.getInstance();
        Date dt = new Date();
        cal.setTime(dt);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return weekDays[w];
    }
    
    /**
     * Title: compareDate<br/>
     * Description: 比较两个日期大小<br/>
     * @param DATE1
     * @param DATE2
     * @return  1=DATE1>DATE2; -1=DATE1<DATE2; 0=DATE1=DATE2;
     */
    public static int compareDate(String DATE1, String DATE2, String fomatString) {
        SimpleDateFormat df = new SimpleDateFormat(fomatString);
        try {
            Date dt1 = df.parse(DATE1);
            Date dt2 = df.parse(DATE2);
            if (dt1.getTime() > dt2.getTime()) {
                return 1;
            } else if (dt1.getTime() < dt2.getTime()) {
                return -1;
            } else {
                return 0;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }

    public static void main(String[] args) {
//    	System.out.println(formatDateLongToString((long)1417878071, dataFormatyyyy_MM_dd));
//        System.out.println(DateUtil.calculateDate("17:27:59", DateUtil.MINUTE, 1, DateUtil.dataFormatHH_mm_ss));
//        try {
//            System.out.println(DateUtil.nowMonthLastDay(DateUtil.dataFormatyyyy_MM_dd, "2015-08-1"));
//            System.out.println(DateUtil.nowMonthFistDay(DateUtil.dataFormatyyyy_MM_dd, "2015-08-3"));
//        } catch (ParseException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
        System.out.println(DateUtil.getWeekOfDate());
        System.out.println(DateUtil.getUpWeekDay(DateUtil.dataFormatyyyy_MM_dd, 7));
        System.out.println(DateUtil.getWeekDay(DateUtil.dataFormatyyyy_MM_dd, 0));
        System.out.println(DateUtil.upMonthFistDay(DateUtil.dataFormatyyyy_MM_dd));
        System.out.println(DateUtil.nowMonthFistDay(DateUtil.dataFormatyyyy_MM_dd, "2017-05-11"));
        System.out.println(DateUtil.upMonthLastDay(DateUtil.dataFormatyyyy_MM_dd));
        System.out.println(DateUtil.getWeekByYear("2017-01-12"));
        System.out.println(DateUtil.getWeekFistDay("2017-01-12",DateUtil.dataFormatyyyy_MM_dd));
        System.out.println(DateUtil.compareDate("17:08:12", "17:08:12", DateUtil.dataFormatHH_mm_ss));
    }
    
}
