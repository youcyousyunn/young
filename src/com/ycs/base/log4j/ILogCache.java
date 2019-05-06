package com.ycs.base.log4j;

import java.io.IOException;

public interface ILogCache {
	void put(LogInfo arg0) throws IOException;
	void clear();
	void flush() throws IOException;
	void close() throws IOException;
	
}
