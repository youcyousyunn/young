package com.ycs.base.log4j;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.util.StringUtils;

import com.ycs.base.property.SystemPropertyConfigure;

public class DirectLogCache implements ILogCache {
	private OutputStream os = null;
	private int limitsSize;
	private String currentFileName;

	public DirectLogCache(int limitsSize) {
		this.limitsSize = limitsSize;
	}

	@Override
	public void put(LogInfo info) throws IOException {
		this.log(info);
	}

	/**
	 * 日志写入目录
	 * @param LogInfo
	 * @return void
	 * @throws IOException
	 */
	protected synchronized void log(LogInfo info) throws IOException {
		OutputStream os = this.open(info);
		if (!StringUtils.isEmpty(SystemPropertyConfigure.getProperty("sys.encoding"))) {
			os.write(info.getBuf().toString().getBytes(SystemPropertyConfigure.getProperty("sys.encoding")));
		} else {
			os.write(info.getBuf().toString().getBytes());
		}

		if (SystemPropertyConfigure.isDevEnv()) {
			System.out.print(info.getBuf());
		}

		os.flush();
	}

	public OutputStream open(LogInfo info) throws IOException {
		String name = info.getName().getTrcDir();
		if (!name.equals(this.currentFileName)) {
			if (this.os != null) {
				this.os.close();
				this.os = null;
			}

			this.currentFileName = name;
		}

		File f1 = new File(name);
		if (f1.getParent() != null) {
			File l = new File(f1.getParent());
			if (!l.exists()) {
				l.mkdirs();
			}

			l = null;
		}

		if (!f1.exists() && this.os != null) {
			this.os.close();
			this.os = null;
		}

		long l1 = f1.length();
		if (info.getName().isFixedSizeable() && l1 > (long) this.limitsSize) {
			if (this.os != null) {
				this.os.close();
				this.os = null;
			}

			SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
			File f2 = new File(name + "." + df.format(new Date()));
			f1.renameTo(f2);
		}

		f1 = null;
		if (this.os == null) {
			this.os = new FileOutputStream(name, true);
		}

		return this.os;
	}

	@Override
	public void clear() {
	}

	@Override
	public void flush() throws IOException {
	}

	@Override
	public synchronized void close() throws IOException {
		if (this.os != null) {
			this.os.close();
			this.os = null;
		}
	}

}
