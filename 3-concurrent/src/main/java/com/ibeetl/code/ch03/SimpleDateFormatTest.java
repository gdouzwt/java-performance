package com.ibeetl.code.ch03;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * SimpleDateFormat 非线程安全类
 * @author 公众号 java系统优化
 */
public class SimpleDateFormatTest {


  public static void main(String[] args) throws  Exception {
    FormatTaskThread[] ts = new FormatTaskThread[2];

    for(int i=0;i<ts.length;i++){
      String expetected ="2011-2-"+i+"1" ;
      ts[i] = new FormatTaskThread(CommonUtil.parse(expetected));
      ts[i].setName("thread-"+i);
    }
    //并发
    for(int i=0;i<ts.length;i++){
      ts[i].start();

    }

  }




  static class FormatTaskThread extends  Thread{
    private Date date = null;
    private String expected = null;
    public FormatTaskThread(Date date){
      this.date = date;
      this.expected = CommonUtil.format(date);

    }
    public void run(){
      while(true){
        String str = CommonUtil.format(date);
        if(!str.equals(expected)){
          //线程非安全
          System.out.println(this.getName()+" expected "+expected+" return "+str );
        }
      }
    }
  }

  static class CommonUtil {
    //错误使用，SimpleDateFormat非线程安全
    static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    public static String format(Date d){
      return sdf.format(d);
    }

    public static Date parse(String str){
      try{
        return sdf.parse(str);
      }catch(Exception ex){
        throw new IllegalArgumentException(str);
      }
    }
  }


}
