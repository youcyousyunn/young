package com.ycs.base.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * @description 金额计算
 * @author youcyousyunn
 * @date 2018年9月16日
 */
public class AmonutCalcuUtil {
    static DecimalFormat df = new DecimalFormat("######0.00");
    static DecimalFormat df1 = new DecimalFormat("0.00%");// ##.00%
    static DecimalFormat df2 = new DecimalFormat("######");
                                                          // 百分比格式，后面不足2位的用0补齐

    /**
     * 
     * Title: getPlusNum<br/>
     * Description: 两个字符串数据进行相减运算<br/>
     * 
     * @author lzy
     * @date 2014年12月20日下午2:18:46
     *
     * @param str1
     * @param str2
     * @return
     */
    public static String getPlusNum(String str1, String str2) {
        BigDecimal num1 = new BigDecimal(str1);
        BigDecimal num2 = new BigDecimal(str2);
        BigDecimal plus = num1.subtract(num2);
        return String.valueOf(df.format(plus));
    }
    
    /**
     * Title getPlusNum2
     * Description 两个字符串数据进行相减运算<br/>
     * @param @param str1
     * @param @param str2
     * @param @return
     * @return String
     * @throws
     */
    public static String getPlusNum2(String str1, String str2) {
        BigDecimal num1 = new BigDecimal(str1);
        BigDecimal num2 = new BigDecimal(str2);
        BigDecimal plus = num1.subtract(num2);
        return String.valueOf(df2.format(plus));
    }
    
    /**
     * 
     * Title: getAddNum<br/>
     * Description: 两个字符串数据进行相加运算<br/>
     * 
     * @author lzy
     * @date 2014年12月20日下午2:18:46
     *
     * @param str1
     * @param str2
     * @return
     */
    public static String getAddNum(String str1, String str2) {
        BigDecimal num1 = new BigDecimal(str1);
        BigDecimal num2 = new BigDecimal(str2);
        BigDecimal plus = num1.add(num2);
        return String.valueOf(df.format(plus));
    }

    /**
     * 
     * Title: getPercentNum<br/>
     * Description: 两个字符串数据进行相除运算的百分比<br/>
     * 
     * @author lzy
     * @date 2014年12月20日下午2:57:53
     *
     * @param str1
     * @param str2
     * @return
     */
    public static String getPercentNum(String str1, String str2) {
        String result = "";// 接受百分比的值
        BigDecimal x_double = new BigDecimal(str1);
        BigDecimal total = new BigDecimal(str2);

        BigDecimal tempresult = x_double.divide(total, 4,
                BigDecimal.ROUND_HALF_UP);    
        return tempresult.toString();
    }

    public static boolean isNumeric(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * 
     * Title: getPercentNum<br/>
     * Description: 两个字符串数据进行乘法运算的百分比<br/>
     * 
     * @author yuezy
     * @date 2014年12月27日下午2:57:53
     *
     * @param str1 乘数1 
     * @param str2 乘数2
     * @return 
     * @return
     */
    public static String mul(String d1, String d2) { // 进行乘法运算
        BigDecimal b1 = new BigDecimal(d1);
        BigDecimal b2 = new BigDecimal(d2);
        BigDecimal zero = new BigDecimal(0);
        if (zero.compareTo(b1) == 0) {
            return "0";
        }
        if (zero.compareTo(b2) == 0) {
            return "0";
        }
        return df.format(b1.multiply(b2));
    }
    
    /**
     * Title: fixMul<br/>
     * Description: 乘法四舍五入<br/>
     * @param d1
     * @param d2
     * @param len
     * @return
     */
    public static String fixMul(String d1, String d2, int len) { // 进行乘法运算
        BigDecimal b1 = new BigDecimal(d1);
        BigDecimal b2 = new BigDecimal(d2);
        BigDecimal zero = new BigDecimal(0);
        if (zero.compareTo(b1) == 0) {
            return "0";
        }
        if (zero.compareTo(b2) == 0) {
            return "0";
        }
        return String.valueOf(b1.multiply(b2).setScale(len, BigDecimal.ROUND_HALF_UP));
    }
    
    /**
     * 
     * Title: div<br/>
     * Description:大数据类型除法计算  本方法采用四舍五入的方式保留小数<br/>
     * @author yuezy
     * @date 2014年12月27日下午1:50:18
     *
     * @param d1 除数
     * @param d2 被除数
     * @param len保留几位小数
     */
    public static String div(String d1,String d2,int len) {// 进行除法运算 
        BigDecimal b1 = new BigDecimal(d1);
        BigDecimal b2 = new BigDecimal(d2);
        BigDecimal zero = new BigDecimal(0);
        if(zero.compareTo(b1)==0){
            return "0";
        }
        if(zero.compareTo(b2)==0){
            return "0";
        }
        return String.valueOf(b1.divide(b2,len,BigDecimal.ROUND_HALF_UP)); 
    }
    
    /**
     * Title: fixDiv<br/>
     * Description: 除法四舍五入d1/d2<br/>
     * @param d1
     * @param d2
     * @param len
     * @return
     */
    public static String fixDiv(String d1,String d2,int len) {// 进行除法运算 
        BigDecimal b1 = new BigDecimal(d1); 
        BigDecimal b2 = new BigDecimal(d2); 
        BigDecimal zero = new BigDecimal(0);
        if(zero.compareTo(b1)==0){
            return "0";
        }
        if(zero.compareTo(b2)==0){
            return "0";
        }
       return String.valueOf(b1.divide(b2).setScale(len, BigDecimal.ROUND_HALF_UP)); 
   }
    /**
     * 
     * Title: mod<br/>
     * Description: 两个字符串数据进行求余运算<br/>
     * @author lzy
     * @date 2014年12月29日下午7:16:08
     *
     * @param d1
     * @param d2
     * @return
     */
    public static String mod(String d1, String d2) {// 进行求余运算
        BigDecimal b1 = new BigDecimal(d1);
        BigDecimal b2 = new BigDecimal(d2);
        BigDecimal bg3 = b2.divideAndRemainder(b1)[1];
        return String.valueOf(bg3.compareTo(BigDecimal.ZERO));
    }
    public static void main(String[] args) {
        BigDecimal x_double = new BigDecimal("12.440");
        System.out.println(div("2","9",2));
        System.out.println(mul("2.1234","2.0000"));
        
        System.out.println("四舍五入取整:(2)=" + new BigDecimal("2").setScale(0, BigDecimal.ROUND_HALF_UP)); 
        System.out.println("四舍五入取整:(2.1)=" + new BigDecimal("2.1").setScale(0, BigDecimal.ROUND_HALF_UP)); 
        System.out.println("四舍五入取整:(2.5)=" + new BigDecimal("2.5").setScale(0, BigDecimal.ROUND_HALF_UP)); 
        System.out.println("四舍五入取整:(2.9)=" + new BigDecimal("2.9").setScale(0, BigDecimal.ROUND_HALF_UP));
    }
}
