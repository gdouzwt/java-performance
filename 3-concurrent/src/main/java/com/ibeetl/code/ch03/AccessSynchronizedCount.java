package com.ibeetl.code.ch03;

public class AccessSynchronizedCount {
	int count = 0;
	public  synchronized int add(){
		count++;
		return count;
	}
	public int getTotal(){
		return count;
	}
}
