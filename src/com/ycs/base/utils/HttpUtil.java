package com.ycs.base.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class HttpUtil {
	
	/**
	 * 往SSO认证中心发送认证请求
	 * @param httpURL，params
	 * @return  String
	 * @throws Exception
	 */
	public static boolean sendVerifyRequest(String httpURL, Map<String, String> params) throws Exception {
		HttpURLConnection connection = null;
		boolean result = false;
		try {
            URL url = new URL(httpURL);
            //得到connection对象。
            connection = (HttpURLConnection) url.openConnection();
            //设置请求方式
            connection.setRequestMethod("POST");
            //连接
            connection.setDoOutput(true);
            connection.setDoInput(true);
            if (null != params && params.size() > 0) {
    			StringBuilder sb = new StringBuilder();
    			Iterator<Entry<String, String>> iter = params.entrySet().iterator();
    			while(iter.hasNext()){
    	            Map.Entry<String, String> entry = iter.next();
    	            sb.append("&").append((String) entry.getKey()).append("=")
    				.append((String) entry.getValue());
    	        }
    			connection.getOutputStream().write(sb.substring(1).toString().getBytes("utf-8"));
    		}
            connection.connect();
            //得到响应码
            int responseCode = connection.getResponseCode();
            if(responseCode == HttpURLConnection.HTTP_OK){
                //得到响应流
                InputStream inputStream = connection.getInputStream();
                //获取响应
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                String str = "";
                while ((str = reader.readLine()) != null){
                    if ("true".equals(str)) {
                    	result = true;
                    }
                }
                reader.close();
                inputStream.close();
            }
 
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	if (null != connection) {
        		connection.disconnect();
        	}
        }
		return result;
	}
	
	/**
	 * 往SSO认证中心发送注册子系统请求
	 * @param httpURL 发送请求的地址
	 * @param params 
	 * @return
	 * @throws Exception
	 */
	public static boolean sendRegistRequest(String httpURL, Map<String, String> params){
		HttpURLConnection connection = null;
		boolean result = false;
		try {
            URL url = new URL(httpURL);
            //得到connection对象。
            connection = (HttpURLConnection) url.openConnection();
            //设置请求方式
            connection.setRequestMethod("POST");
            //连接
            connection.setDoOutput(true);
            connection.setDoInput(true);
            if (null != params && params.size() > 0) {
    			StringBuilder sb = new StringBuilder();
    			Iterator<Entry<String, String>> iter = params.entrySet().iterator();
    			while(iter.hasNext()){
    	            Map.Entry<String, String> entry = iter.next();
    	            sb.append("&").append((String) entry.getKey()).append("=")
    				.append((String) entry.getValue());
    	        }
    			connection.getOutputStream().write(sb.substring(1).toString().getBytes("utf-8"));
    		}
            connection.connect();
            //得到响应码
            int responseCode = connection.getResponseCode();
            if(responseCode == HttpURLConnection.HTTP_OK){
                //得到响应流
                InputStream inputStream = connection.getInputStream();
                //获取响应
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                String str = "";
                while ((str = reader.readLine()) != null){
                    if ("true".equals(str)) {
                    	result = true;
                    }
                }
                reader.close();
                inputStream.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	if (null != connection) {
        		connection.disconnect();
        	}
        }
		return result;
	}
	
}
