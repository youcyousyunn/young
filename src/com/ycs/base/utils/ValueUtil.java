package com.ycs.base.utils;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ycs.base.log4j.logger.HiBizLogger;


/**
 * 值处理工具类
 * @author youcyousyunn
 * @date 2018年6月28日
 */
public final class ValueUtil {

    /**
     * 构造函数
     */
    private ValueUtil() {
    }

    /**
     * 将对象转换成字符串
     * @param obj 对象
     * @param defaultValue 默认值，当字符串为空时返回默认值
     * @return 字符串
     */
    public static String getString(Object obj, String defaultValue) {
        if (obj != null && obj.toString().length() > 0) {
            return obj.toString().trim();
        }
        return defaultValue;
    }

    /**
     * 将对象转换成字符串
     * @param obj 对象
     * @return 字符串
     */
    public static String getString(Object obj) {
        return getString(obj, "");
    }

    /**
     * 将对象转换成布尔值
     * @param obj 对象
     * @param defaultValue 默认值,当传入对象为null时返回默认值
     * @return 布尔值
     */
    public static boolean getBoolean(Object obj, boolean defaultValue) {
        if (obj != null) {
            if (obj instanceof Boolean) {
                return ((Boolean) obj).booleanValue();
            } else {
                String str = obj.toString().trim();
                return "Y".equalsIgnoreCase(str) || "TRUE".equalsIgnoreCase(str) || "1".equals(str);
            }
        }
        return defaultValue;
    }

    /**
     * 将对象转换成布尔值
     * @param obj 对象
     * @return 布尔值
     */
    public static boolean getBoolean(Object obj) {
        return getBoolean(obj, false);
    }

    /**
     * 将对象转换成数字
     * 
     * @param obj 对象
     * @param defaultValue 默认值，当传入对象为null时返回默认值
     * @return 数字
     */
    public static int getInteger(Object obj, int defaultValue) {
        if (obj != null) {
            if (obj instanceof Integer) {
                return ((Integer) obj).intValue();
            } else {
                try {
                    return Integer.parseInt(obj.toString().trim());
                } catch (NumberFormatException e) {
                    HiBizLogger.error("将对象转换成数字异常", null, e);
                }
            }
        }
        return defaultValue;
    }

    /**
     * 将对象转换成数字
     * @param obj 对象
     * @return 数字
     */
    public static int getInteger(Object obj) {
        return getInteger(obj, 0);
    }

    /**
     * 将对象转换成长整型
     * @param obj 对象
     * @param defaultValue 默认值，当传入对象为null时返回默认值
     * @return 长整型数字
     */
    public static long getLong(Object obj, long defaultValue) {
        if (obj != null) {
            if (obj instanceof Long) {
                return ((Long) obj).longValue();
            } else {
                try {
                    return Long.valueOf(obj.toString().trim());
                } catch (NumberFormatException e) {
                }
            }
        }
        return defaultValue;
    }

    /**
     * 将对象转换成长整型
     * @param obj 对象
     * @return 长整型数字
     */
    public static long getLong(Object obj) {
        return getLong(obj, 0L);
    }

    /**
     * 将对象转换成双精度型小数
     * @param obj 对象
     * @param defaultValue 默认值，当传入对象为null时返回默认值
     * @return 双精度型小数
     */
    public static double getDouble(Object obj, double defaultValue) {
        if (obj != null) {
            if (obj instanceof Double) {
                return ((Double) obj).doubleValue();
            } else {
                try {
                    return Double.valueOf(obj.toString().trim());
                } catch (NumberFormatException e) {
                    HiBizLogger.error("", "", e);
                }
            }
        }
        return defaultValue;
    }

    /**
     * 将对象转换成双精度型小数
     * @param obj 对象
     * @return 双精度型小数
     */
    public static double getDouble(Object obj) {
        return getDouble(obj, 0d);
    }

    /**
     * 将空值转换为0
     * @param value 转换前值
     * @return 转换后值
     */
    public static long nullToZero(Long value) {
        return null == value ? 0 : value.longValue();
    }
    
    /**
     * 列表转map
     * @param converLst
     * @param mapKey
     * @return Map<String,Object> 转换后值
     */
    public static Map<String, Object> converToMapLst(List<?> converLst,String mapKey){
        if(null==converLst)
            return null;
        Map<String, Object> converMap = new HashMap<String, Object>();
        for (int i = 0; i < converLst.size(); i++) {
            Object object = converLst.get(i);
            try {
                String key = getFieldValueByName(mapKey, object).toString();
                converMap.put(key, object);
            } catch (SecurityException e) {
                e.printStackTrace();
            }
        }
        return converMap;
    }
    
    /**
     * 根据属性名获取属性值
     * @param
     * @return Object
     */
    public static Object getFieldValueByName(String fieldName, Object o) {
        try {
            String firstLetter = fieldName.substring(0, 1).toUpperCase();
            String getter = "get" + firstLetter + fieldName.substring(1);
            Method method = o.getClass().getMethod(getter, new Class[] {});
            return method.invoke(o, new Object[] {});
        } catch (Exception e) {
            return null;
        }
    }
}
