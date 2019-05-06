package com.ycs.base.utils;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import com.ycs.base.log4j.Logger;

/**
 * Redis工具类
 * @author youcyousyunn
 * @date 2018年5月17日
 */
@Component
public class RedisUtil {

	private static RedisTemplate<String, Object> redisTemplate;
    private Logger logger = Logger.getLogger(RedisUtil.class);
    
    public RedisUtil(){}

    /**
     * 批量删除key对应的value
     * @param keys
     */
    public void remove(final String... keys) {
        for (String key : keys) {
            remove(key);
        }
    }

    /**
     * 批量删除key对应的value
     * @param pattern
     */
    public void removePattern(final String pattern) {
        Set<String> keys = redisTemplate.keys(pattern);
        if (keys.size() > 0)
            redisTemplate.delete(keys);
    }

    /**
     * 删除该key对应的value
     * @param key
     */
    public void remove(final String key) {
        if (exists(key)) {
            redisTemplate.delete(key);
        }
    }

    /**
     * 判断缓存中是否有该key对应的value
     * @param key
     * @return
     */
    public boolean exists(final String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 读取缓存
     * @param key
     * @return
     */
    public Object get(final String key) {
        Object result = null;
        ValueOperations<String, Object> operations = redisTemplate.opsForValue();
        result = operations.get(key);
        return result;
    }

    /**
     * 写入缓存
     * @param key
     * @param value
     * @return boolean
     */
    public boolean set(final String key, Object value) {
        boolean result = false;
        try {
            ValueOperations<String, Object> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            result = true;
        } catch (Exception e) {
            logger.error("set cache error", e);
        }
        return result;
    }

    /**
     * 写入缓存
     * @param key
     * @param value
     * @param expireTime 
     * @return boolean
     */
    public boolean set(final String key, Object value, Long expireTime) {
        boolean result = false;
        try {
            ValueOperations<String, Object> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
            result = true;
        } catch (Exception e) {
            logger.error("set cache error", e);
        }
        return result;
    }
    
    /**
     * 写入缓存
     * @param key
     * @param value
     * @param expireTime
     * @param unit
     * @return boolean
     */
    public boolean set(final String key, Object value, Long expireTime, TimeUnit unit){
    	boolean result = false;
    	try {
    		ValueOperations<String, Object> operations = redisTemplate.opsForValue();
    		operations.set(key, value);
    		redisTemplate.expire(key, expireTime, unit);
    		result = true;
    	} catch (Exception e){
    		logger.error("set cache error", e);
    	}
    	return result;
    }
    
    /**
     * 以增量的方式将long值存储在变量中
     * @param
     * @return long
     */
    public long increment(final String key , long delta){
         return redisTemplate.opsForValue().increment(key, delta);
    }
    
    /**
     * 以增量的方式将double值存储在变量中
     * @param
     * @return double
     */
    public double increment(final String key, double delta){
    	return redisTemplate.opsForValue().increment(key, delta);
    }

	public static RedisTemplate<String, Object> getRedisTemplate() {
		return redisTemplate;
	}

	public static void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
		RedisUtil.redisTemplate = redisTemplate;
	}

    
}
