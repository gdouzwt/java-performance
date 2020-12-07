package com.ibeetl.code.ch05;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;
/**
 * hash够快，但也够慢，高性能不用hash，这不仅仅是java，也是其他语言的共识
 * @author 公众号 java系统优化
 */
public class ElapsedTime {

  public static void main(String... args) throws InterruptedException {
//    long startTime = System.nanoTime();
//    Thread.sleep(1002 * 2);
//    long difference = System.nanoTime() - startTime;
//    long millis =  TimeUnit.NANOSECONDS.toMillis(difference);
//    long seconds =  TimeUnit.NANOSECONDS.toSeconds(difference);
    TreeMap<Integer,String> map = new TreeMap<Integer,String>();
    map.put(1,"a");
    map.put(2,null);
    map.put(3,"c");
    String[] array = new String[map.size()];
    for(Map.Entry<Integer,String> entry:map.entrySet()){
      array[entry.getKey()-1] = entry.getValue();
    }
    System.out.println(Arrays.asList(array));

  }
}
