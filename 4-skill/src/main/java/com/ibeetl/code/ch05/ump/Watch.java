package com.ibeetl.code.ch05.ump;

import java.util.concurrent.TimeUnit;

public class Watch {
  String key;
  long start;
  long millis =-1;
  private Watch(String key){
    this.key = key;
    this.start = System.nanoTime();
  }
  public static Watch instance(String key){
    return new Watch(key);
  }

  public Watch endWatch(){
    millis = millisConsume();
    return this;

  }

  /**
   * 返回方法调用消耗的毫秒
   * @return
   */
    private long millisConsume(){
      return  TimeUnit.NANOSECONDS.toMillis(System.nanoTime()-start);
    }

}
