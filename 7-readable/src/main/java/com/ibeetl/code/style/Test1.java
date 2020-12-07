package com.ibeetl.code.style;

import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicStampedReference;

public class Test1 {

  private  ThreadLocal<byte[]> cacheLocal = new ThreadLocal<byte[]>(){
    protected  byte[] initialValue(){
      return new byte[1024];
    }
  };

  enum Gender {MAN,GIRL}
  /** 性别，0表示性别为女，1表示为男，默认值是{@link Gender#MAN } */
  int gender = 0;
  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
  private static ThreadLocal<SimpleDateFormat> local = new ThreadLocal<SimpleDateFormat>(){
    protected  SimpleDateFormat initialValue(){
      return new SimpleDateFormat("yyyy-MM-dd");
    }
  };



  /**
   * 如果3>0,则抛出异常
   * @param d
   * @return
   * @throws Exception
   */
  public  String format(Date d) throws Exception{
    return local.get().format(d);
  }

  public static void ccc(List<String> strs){
    Charset utf8 =Charset.forName("utf-8");
    for(String str:strs){
      if(str!=null){
        byte[] bs = str.getBytes(utf8);

      }
    }
  }

  public static void main(String[] args){
    Config config = new Config();
    config.version = 1;

    AtomicIntegerFieldUpdater<Config> update = AtomicIntegerFieldUpdater.newUpdater(Config.class, "version");
    int newVersion = update.incrementAndGet(config);
    System.out.println(newVersion);
    System.out.println(config.version);

  }

  protected static boolean isSuccess(){
    return false ;
  }


  static public class Config{
    volatile  int version;
  }


  private ConcurrentHashMap<String, AtomicInteger> countMap = new ConcurrentHashMap<String, AtomicInteger> ();

//  public void add(String key)
//  {
//    Integer value = map.get(key);
//    if(value == null)
//    {
//      map.put(key, 1);
//    }
//    else
//    {
//      map.put(key, value + 1);
//    }
//  }

  public void add(String key)
  {
    AtomicInteger  count  = countMap.get(key);
    if(count==null){
      count = new AtomicInteger();
      AtomicInteger old  = countMap.putIfAbsent(key,count);
      if(old!=null){
        count = old;
      }
    }
    count.incrementAndGet();
  }

  enum TypeEnum{
    Order{
      @Override
      public void handle(Object o){

      }
    },Customer{
      public void handle(Object o){

      }
    };
    public abstract  void handle(Object o);
  }
}
