package com.ycs.base.spring.handler;

import java.lang.reflect.UndeclaredThrowableException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.ycs.basbo.constants.HiMsgCdConstants;
import com.ycs.base.exception.HiBusinessAbortException;
import com.ycs.base.exception.HiBusinessReturnException;
import com.ycs.base.exception.HiDatabaseException;
import com.ycs.base.exception.HiException;
import com.ycs.base.exception.HiRollbackException;
import com.ycs.base.log4j.logger.HiBizLogger;

/**
 * @description 总错误处理类
 * @author youcyousyunn
 * @date 2018年11月12日
 */
public class HiExceptionHandler implements HandlerExceptionResolver {
	private String defaultErrorView;

	public void setDefaultErrorView(String defaultErrorView) {
		this.defaultErrorView = defaultErrorView;
	}

	@Override
	public ModelAndView resolveException(HttpServletRequest arg0, HttpServletResponse arg1, Object handler,
			Exception ex) {
		Map<String, Object> model = new LinkedHashMap<String, Object>();
		model.put("rspTm", (new SimpleDateFormat("yyyyMMddHHmmss")).format(new Date()));
		HiBizLogger.error(HiMsgCdConstants.SYS_FAIL, "系统异常", ex);
		Object o = ex;
		if (ex instanceof UndeclaredThrowableException) {
			o = ((UndeclaredThrowableException) ex).getUndeclaredThrowable();
		}

		if (o instanceof HiBusinessAbortException) {
			model.put("code", ((HiBusinessAbortException) o).getCode());
			model.put("message", ((HiBusinessAbortException) o).getMessage());
		} else if (o instanceof HiBusinessReturnException) {
			model.put("code", ((HiBusinessReturnException) o).getCode());
			model.put("message", ((HiBusinessReturnException) o).getMessage());
		} else if (o instanceof HiDatabaseException) {
			model.put("code", ((HiDatabaseException) o).getCode());
			model.put("message", ((HiDatabaseException) o).getMessage());
		} else if (o instanceof HiException) {
			model.put("code", ((HiException) o).getCode());
			model.put("message", ((HiException) o).getMessage());
		} else if (o instanceof HiRollbackException) {
			model.put("code", ((HiRollbackException) o).getCode());
			model.put("message", ((HiException) o).getMessage());
		} else {
			model.put("code", HiMsgCdConstants.SYS_FAIL);
			model.put("message", "业务处理异常");
		}

		ModelAndView view = new ModelAndView(this.defaultErrorView);
		view.addObject("model", model);
		return view;
	}

}
