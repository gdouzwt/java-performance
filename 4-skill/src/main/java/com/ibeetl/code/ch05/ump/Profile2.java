package com.ibeetl.code.ch05.ump;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class Profile2 {

  static Map<Integer, AtomicInteger> countMap = new ConcurrentHashMap<>();
  static final int MAX  = 32;
  static AtomicInteger[] counts = new AtomicInteger[MAX];
  static{
    for(int i=0;i<MAX;i++){
      counts[i] = new  AtomicInteger();
    }
  }

    /**
     * 对调用时间计数
   * @param watch
   */
  public static void addWatch(Watch watch){
    int consumeTime = (int)watch.millis;
    if(consumeTime<MAX){
      counts[consumeTime].incrementAndGet();
      return ;
    }

    //原有的Profile逻辑
    AtomicInteger  count  = countMap.get(consumeTime);
    if(count==null){
      count = new AtomicInteger();
      AtomicInteger old  = countMap.putIfAbsent(consumeTime,count);
      if(old!=null){
          count = old;
      }
    }
    count.incrementAndGet();

  }

	public static  void init(){

		countMap = new ConcurrentHashMap<>();
		counts = new AtomicInteger[MAX];
		for(int i=0;i<MAX;i++){
			counts[i] = new  AtomicInteger();
		}
	}


}
