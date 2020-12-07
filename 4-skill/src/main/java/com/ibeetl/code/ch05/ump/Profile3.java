package com.ibeetl.code.ch05.ump;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;

import static com.ibeetl.code.ch05.ump.Profile2.counts;

/**
 * 不理想
 */
public class Profile3 {

  static Map<Integer, AtomicInteger> countMap = new ConcurrentHashMap<>();

  static final int MAX  = 32;
  static AtomicIntegerArray  array = new AtomicIntegerArray(MAX);


    /**
     * 对调用时间计数
   * @param watch
   */
  public static void addWatch(Watch watch){
    int consumeTime = (int)watch.millis;
    if(consumeTime<MAX){
		array.incrementAndGet(consumeTime);
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

		array = new AtomicIntegerArray(MAX);
	}


}
