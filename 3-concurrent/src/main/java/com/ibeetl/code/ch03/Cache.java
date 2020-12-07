package com.ibeetl.code.ch03;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 一个简单的cache例子，不过，既没有容量限制，过期设置，更没有热点缓存！
 * @author 公众号 java系统优化
 * @param <T>
 */
class Cache<T> {
	private final Map<String, T> m = new HashMap<String, T>();
	private final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
	private final Lock r = rwl.readLock();
	private final Lock w = rwl.writeLock();

	public T get(String key) {
		r.lock();
		try { return m.get(key); }
		finally { r.unlock(); }
	}

	public T put(String key, T value) {
		w.lock();
		try { return m.put(key, value); }
		finally { w.unlock(); }
	}
	public void clear() {
		w.lock();
		try { m.clear(); }
		finally { w.unlock(); }
	}
}
