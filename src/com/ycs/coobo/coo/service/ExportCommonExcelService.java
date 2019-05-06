package com.ycs.coobo.coo.service;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ycs.base.exception.HiBusinessAbortException;
import com.ycs.base.exception.HiBusinessReturnException;

public interface ExportCommonExcelService {

	public HttpServletResponse CommonExport(String downType, Map<String, String[]> parameterMap,
			HttpServletRequest httpRequest, HttpServletResponse httpResponse) throws HiBusinessAbortException,HiBusinessReturnException,IOException;

	
	
}
