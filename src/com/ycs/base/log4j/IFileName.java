package com.ycs.base.log4j;

public interface IFileName {
	String getTrcDir();
	int getLineLength();
	void setLineLength(int arg0);
	String name();
	boolean isFixedSizeable();
	void setFixedSizeable(boolean arg0);
	
}
