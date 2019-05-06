package com.ycs.base.log4j;

import com.ycs.base.property.SystemPropertyConfigure;

public class HiLog {

	private static String procFileName(String fileNm) {
		int idx = fileNm.lastIndexOf(".");
		if (idx != -1 && SystemPropertyConfigure.getInsId() != null) {
			fileNm = fileNm.substring(0, idx) + "_" + SystemPropertyConfigure.getInsId() + fileNm.substring(idx);
		}

		return fileNm;
	}

	public static Logger getLogger(String fileNm) {
		return new Logger(procFileName(fileNm));
	}

}
