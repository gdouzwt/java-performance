package com.ibeetl.code.ch03;

import java.util.concurrent.atomic.AtomicInteger;

public class AccessAtomicCount {
	AtomicInteger count = new AtomicInteger();
	public   int  add(){
		return count.incrementAndGet();
	}
	public int getTotal(){
		return count.get();
	}
}
