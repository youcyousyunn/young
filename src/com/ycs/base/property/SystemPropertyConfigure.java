package com.ycs.base.property;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import com.ycs.base.encrypt.XXTEA;

/**
 * 获取系统属性配置文件信息类
 * @author youcyousyunn
 * @date 2018年6月24日
 */
public class SystemPropertyConfigure extends PropertyPlaceholderConfigurer {
	private static Map<String, Object> ctxPropertiesMap = new HashMap<>();
	public static final String XXTEA_PREFIX = "XXTEA:"; // 数据库密码前缀
	public static final String XXTEA_KEY = "C54sZkfbk5A5MNg"; // 数据库密码加解密key

	/**
	 * 获取properties配置文件中key与value值
	 */
	protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties props)
			throws BeansException {
		try {
			this.decryptProperty(props, (String) null);
		} catch (IOException e) {
			try {
				throw new Exception("decryptProperty failed.", e);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		super.processProperties(beanFactoryToProcess, props);
		Iterator<Object> iter = props.keySet().iterator();
		while (iter.hasNext()) {
			Object key = iter.next();
			String keyStr = key.toString();
			String value = props.getProperty(keyStr);
			ctxPropertiesMap.put(keyStr, value);
		}
	}

	/**
	 * 解密
	 * @param
	 * @return void
	 * @throws IOException
	 */
	protected void decryptProperty(Properties props, String encoding) throws IOException {
		try {
			Enumeration<Object> ex = props.keys();
			while (ex.hasMoreElements()) {
				String key = (String) ex.nextElement();
				String value = props.getProperty(key);
				if (value.indexOf("XXTEA:") != -1) {
					value = new String(XXTEA.decryptWithBase64(value.substring("XXTEA:".length()), XXTEA_KEY.getBytes(), encoding == null ? "UTF-8" : encoding));
					props.put(key, value);
				}
			}
		} catch (IOException arg5) {
			throw arg5;
		}
	}

	public static void setProperty(String key, String value) {
		ctxPropertiesMap.put(key, value);
	}

	public static String getProperty(String name) {
		return (String) ctxPropertiesMap.get(name);
	}

	public static String getProperty(String name, String defaultValue) {
		String value = getProperty(name);
		return value == null ? defaultValue : value;
	}

	/**
	 * 获取跟踪日志文件夹路径
	 * @param
	 * @return String
	 */
	public static String getTrcDir() {
		String liveHome = getProperty("sys.home");
		if (System.getProperty("sys.trc") == null) {
			if (liveHome.endsWith(File.separator)) {
				setProperty("sys.trc", liveHome + "trc");
			} else {
				setProperty("sys.trc", liveHome + File.separator + "trc");
			}
		}

		StringBuffer path = new StringBuffer();
		path.append(getProperty("sys.trc"));
		path.append(File.separator);
		Calendar cal = Calendar.getInstance();
		int d = cal.get(5);
		if (d >= 10) {
			path.append(d);
		} else {
			path.append("0");
			path.append(d);
		}

		path.append(File.separator);
		return path.toString();
	}

	/**
	 * 获取系统日志文件夹
	 * @param
	 * @return String
	 */
	public static String getLogDir() {
		String liveHome = getProperty("sys.home");
		if (System.getProperty("sys.log") == null) {
			if (liveHome.endsWith(File.separator)) {
				setProperty("sys.log", liveHome + "log");
			} else {
				setProperty("sys.log", liveHome + File.separator + "log");
			}
		}
		StringBuffer path = new StringBuffer();
		path.append(getProperty("sys.log"));
		path.append(File.separator);
		Calendar cal = Calendar.getInstance();
		int d = cal.get(5);
		if (d >= 10) {
			path.append(d);
		} else {
			path.append("0");
			path.append(d);
		}

		path.append(File.separator);
		return path.toString();
	}

	/***
	 * 判断当前系统是否为正式环境
	 * @param
	 * @return boolean
	 */
	public static boolean isPrdEnv() {
		return StringUtils.equals(getProperty("sys.runmode"), "product");
	}

	/**
	 * 判断当前系统是否为开发环境
	 * @param
	 * @return boolean
	 */
	public static boolean isDevEnv() {
		return StringUtils.equals(getProperty("sys.runmode"), "develop");
	}

	/**
	 * 获取实例ID
	 * @param
	 * @return String
	 * @throws 
	 */
	public static String getInsId() {
		return getProperty("instance.id");
	}

	public static String getNodId() {
		return System.getenv("node_id");
	}

	public static String getParams(String key) {
		return getProperty(key);
	}

}
