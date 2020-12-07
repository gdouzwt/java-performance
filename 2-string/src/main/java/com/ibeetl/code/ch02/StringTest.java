package com.ibeetl.code.ch02;

/**
 *  -XX:+UnlockDiagnosticVMOptions  -XX:+LogCompilation -XX:LogFile=jmv.log -XX:+PrintAssembly
 */
public class StringTest {
  public static void main(String[] args){
    int max = 100000000;
    String ret = null;
    long time = System.currentTimeMillis();


    for(int i=0;i<max;i++){
      String a = "a";
      String b = "b";

      ret = call2(a,b);
    }
    int len = ret.length();
    long end  = System.currentTimeMillis();
//    for(int i=0;i<max;i++){
//      String a = "a";
//      String b = "b";
//      ret = call1(a,b);
//
//    }

    long end2  = System.currentTimeMillis();
    System.out.println((end2-end)+" vs "+(end-time));
  }

  public static String  call1(String a,String b){
    String c = a+b;
    return c ;

  }

  public static String call2(String a,String b){
    StringBuilder c = new StringBuilder();
    c.append(a).append(b);
    return c.toString();
  }
}
