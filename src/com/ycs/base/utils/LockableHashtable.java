package com.ycs.base.utils;

import java.util.HashSet;
import java.util.Set;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

public class LockableHashtable extends ConcurrentHashMap {

	Vector lockedEntries;
	private ConcurrentHashMap parent = null;

	public LockableHashtable() {
	}

	public LockableHashtable(int p1, float p2) {
		super(p1, p2);
	}

	public LockableHashtable(ConcurrentHashMap p1) {
		super(p1);
	}

	public LockableHashtable(int p1) {
		super(p1);
	}

	public void setParent(ConcurrentHashMap parent) {
		this.parent = parent;
	}

	public ConcurrentHashMap getParent() {
		return this.parent;
	}

	public Set getAllKeys() {
		HashSet set = new HashSet();
		set.addAll(super.keySet());
		ConcurrentHashMap p = this.parent;

		while (p != null) {
			set.addAll(p.keySet());
			if (p instanceof LockableHashtable) {
				p = ((LockableHashtable) p).getParent();
			} else {
				p = null;
			}
		}

		return set;
	}

	public Object get(Object key) {
		Object ret = super.get(key);
		if (ret == null && this.parent != null) {
			ret = this.parent.get(key);
		}

		return ret;
	}

	public Object put(Object p1, Object p2, boolean locked) {
		if (this.lockedEntries != null && this.containsKey(p1) && this.lockedEntries.contains(p1)) {
			return null;
		} else {
			if (locked) {
				if (this.lockedEntries == null) {
					this.lockedEntries = new Vector();
				}

				this.lockedEntries.add(p1);
			}

			return super.put(p1, p2);
		}
	}

	public Object put(Object p1, Object p2) {
		return this.put(p1, p2, false);
	}

	public Object remove(Object p1) {
		return this.lockedEntries != null && this.lockedEntries.contains(p1) ? null : super.remove(p1);
	}

	public boolean isKeyLocked(Object key) {
		return this.lockedEntries != null && this.lockedEntries.contains(key);
	}
	
}
