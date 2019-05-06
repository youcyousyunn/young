package com.ycs.base.context;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Set;
import java.util.Stack;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.commons.lang.StringUtils;

import com.ycs.base.property.SystemPropertyConfigure;
import com.ycs.base.utils.JavaUtil;
import com.ycs.base.utils.LockableHashtable;

public class HiContext {
	private String requestId;
	protected static ThreadLocal currentContext = new ThreadLocal();
	public static AtomicLong msgIdSeq = new AtomicLong(0L);
	protected static final HiContext RootContext = new HiContext("RootContext");
	protected String id;
	protected final LockableHashtable bag;
	protected HiContext parent;
	protected HiContext firstChild;
	protected HiContext nextBrother;
	
	protected HiContext(String id) {
		this.bag = new LockableHashtable();
		this.parent = null;
		this.id = id;
	}

	protected HiContext(String id, HiContext parent) {
		this.requestId = this.genMsgId();
		this.bag = new LockableHashtable();
		if (parent == null) {
			parent = RootContext;
		}

		this.bag.setParent(parent.bag);
		this.parent = parent;
		this.id = id;
		parent.addChild(this);
	}

	public static void pushCurrentContext(HiContext hc) {
		Stack s;
		if (currentContext.get() == null) {
			s = new Stack();
			currentContext.set(s);
		} else {
			s = (Stack) currentContext.get();
		}

		s.push(hc);
	}

	public static HiContext popCurrentContext() {
		if (currentContext.get() != null) {
			Stack s = (Stack) currentContext.get();
			return (HiContext) s.pop();
		} else {
			return null;
		}
	}

	public static HiContext getCurrentContext() {
		if (currentContext.get() != null) {
			Stack s = (Stack) currentContext.get();
			return s != null && s.size() != 0 ? (HiContext) s.peek() : null;
		} else {
			return null;
		}
	}

	public static void setCurrentContext(HiContext hc) {
		Stack s;
		if (currentContext.get() == null) {
			s = new Stack();
			currentContext.set(s);
		} else {
			s = (Stack) currentContext.get();
		}

		if (!s.isEmpty()) {
			s.pop();
		}

		s.push(hc);
	}

	public static void removeCurrentContext() {
		currentContext.remove();
	}

	public static HiContext getRootContext() {
		return RootContext;
	}

	public static HiContext createContext(String id, HiContext parent) {
		if (parent == null) {
			parent = RootContext;
		}

		return new HiContext(id, parent);
	}

	public static HiContext createContext(HiContext parent) {
		if (parent == null) {
			parent = RootContext;
		}

		return new HiContext((String) null, parent);
	}

	public static HiContext createAndPushContext() {
		HiContext parent = getCurrentContext();
		if (parent == null) {
			parent = RootContext;
		}

		HiContext mc = new HiContext((String) null, parent);
		pushCurrentContext(mc);
		return mc;
	}

	public String getStrProp(String propName) {
		return (String) this.getProperty(propName);
	}

	public String getStrProp(String key1, String key2) {
		String key = key1 + "." + key2;
		return (String) this.getProperty(key);
	}

	public boolean isPropertyTrue(String propName) {
		return this.isPropertyTrue(propName, false);
	}

	public boolean isPropertyTrue(String propName, boolean defaultVal) {
		return JavaUtil.isTrue(this.getProperty(propName), defaultVal);
	}

	public void setProperty(String name, Object value) {
		if (name != null && value != null) {
			this.bag.put(name.toUpperCase(), value);
		}
	}

	public void delProperty(String name) {
		this.bag.remove(name.toUpperCase());
	}

	public void setProperty(String key1, String key2, Object value) {
		this.setProperty(key1 + "." + key2, value);
	}

	public void setProperty(String name, Object value, boolean locked) {
		if (name != null && value != null) {
			this.bag.put(name.toUpperCase(), value, locked);
		}
	}

	public void setProperty(String key1, String key2, Object value, boolean locked) {
		this.setProperty(key1 + key2, value, locked);
	}

	public boolean containsProperty(String name) {
		Object propertyValue = this.getProperty(name);
		return propertyValue != null;
	}

	public Iterator getPropertyNames() {
		return this.bag.keySet().iterator();
	}

	public Iterator getAllPropertyNames() {
		return this.bag.getAllKeys().iterator();
	}

	public Object getProperty(String name) {
		return name != null ? (this.bag == null ? null : this.bag.get(name.toUpperCase())) : null;
	}

	public Object getProperty(String key1, String key2) {
		String key = key1 + "." + key2;
		return this.getProperty(key);
	}

	public HiContext getFirstChild() {
		return this.firstChild;
	}

	public HiContext getNextBrother() {
		return this.nextBrother;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return this.id;
	}

	protected void addChild(HiContext child) {
		child.nextBrother = null;
		if (this.firstChild == null) {
			this.firstChild = child;
		} else {
			HiContext lastChild;
			for (lastChild = this.firstChild; lastChild.nextBrother != null; lastChild = lastChild.nextBrother) {
				;
			}

			lastChild.nextBrother = child;
		}

	}

	public String toString() {
		StringBuffer rs = new StringBuffer();
		rs.append("id=" + this.id);
		rs.append(";");
		Set set = this.bag.entrySet();
		Iterator iter = set.iterator();

		while (iter.hasNext()) {
			rs.append(iter.next());
			rs.append(";");
		}

		if (this.parent != null) {
			rs.append("parent=" + this.parent.id);
			rs.append(";");
		}

		if (this.firstChild != null) {
			rs.append("firstChild=" + this.firstChild.id);
			rs.append(";");
		}

		if (this.nextBrother != null) {
			rs.append("nextBrother=" + this.nextBrother.id);
			rs.append(";");
		}

		return rs.toString();
	}

	public HiContext getParent() {
		return this.parent;
	}

	public void clear() {
		if (this.parent != null) {
			this.parent.delchild(this);
		}
	}

	private void delchild(HiContext context) {
		HiContext before = null;
		if (this.firstChild != null) {
			HiContext lastChild = this.firstChild;

			do {
				if (lastChild == context) {
					if (before == null) {
						this.firstChild = lastChild.nextBrother;
					} else {
						before.nextBrother = lastChild.nextBrother;
					}

					lastChild.nextBrother = null;
				}

				before = lastChild;
				lastChild = lastChild.nextBrother;
			} while (lastChild != null);
		}
	}

	public String getRequestId() {
		return this.requestId;
	}

	private String genMsgId() {
		String insId = SystemPropertyConfigure.getInsId();
		String nodId = SystemPropertyConfigure.getNodId();
		long a = msgIdSeq.incrementAndGet();
		a %= 10000L;
		return StringUtils.trimToEmpty(insId) + StringUtils.trimToEmpty(nodId)
				+ (new SimpleDateFormat("yyyyMMddHHmmss")).format(Calendar.getInstance().getTime())
				+ StringUtils.leftPad(String.valueOf(a), 4, "0");
	}

}
