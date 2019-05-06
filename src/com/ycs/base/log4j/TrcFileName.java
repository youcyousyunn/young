package com.ycs.base.log4j;

import com.ycs.base.property.SystemPropertyConfigure;

public class TrcFileName extends AbstractFileName implements IFileName {

	public TrcFileName(String name) {
		super(name);
	}

	public TrcFileName(String name, int lineLength) {
		super(name, lineLength);
	}

	public TrcFileName(String name, int lineLength, boolean fixSizeable) {
		super(name, lineLength, fixSizeable);
	}

	@Override
	public String getTrcDir() {
		return SystemPropertyConfigure.getTrcDir() + this._name;
	}

}
