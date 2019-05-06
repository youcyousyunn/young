package com.ycs.base.log4j;

import com.ycs.base.property.SystemPropertyConfigure;

public class LogFileName extends AbstractFileName implements IFileName {

	public LogFileName(String name) {
		super(name);
	}
	
	public LogFileName(String name, int lineLength) {
		super(name, lineLength);
	}

	@Override
	public String getTrcDir() {
		return SystemPropertyConfigure.getLogDir() + this._name;
	}

}
