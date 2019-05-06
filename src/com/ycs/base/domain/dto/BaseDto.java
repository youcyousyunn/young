package com.ycs.base.domain.dto;

import org.springframework.beans.BeanUtils;

import com.ycs.base.domain.po.BasePo;
import com.ycs.base.exception.HiException;

public abstract class BaseDto {
	public static <T> T trans2Entity(BaseRequestDto reqBean, Class<T> entityClazz) throws HiException {
		if (null == reqBean) {
			return null;
		} else {
			T entity;
			try {
				entity = entityClazz.newInstance();
			} catch (InstantiationException arg3) {
				throw new HiException(arg3);
			} catch (IllegalAccessException arg4) {
				throw new HiException(arg4);
			}

			BeanUtils.copyProperties(reqBean, entity);
			return entity;
		}
	}

	public static <T> T entity2Trans(BasePo entity, Class<T> transResClazz) throws HiException {
		if (null == entity) {
			return null;
		} else {
			T resBean;
			try {
				resBean = transResClazz.newInstance();
			} catch (InstantiationException arg3) {
				throw new HiException(arg3);
			} catch (IllegalAccessException arg4) {
				throw new HiException(arg4);
			}

			BeanUtils.copyProperties(entity, resBean);
			return resBean;
		}
	}
	

}
