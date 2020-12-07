package com.ibeetl.code.ch05;

import java.util.Random;

/**
 * 参考 {@code ForDeadCodeTest},循环嵌套
 * @author 公众号 java系统优化
 */
public class ForTest {
  static int[] array = new int[]{0,1,2,3,4,5,6,7,8,9};
  public static void  main(String[] args){
    int a,b,c;
    for(int i=0;i<10;i++){
      base();
      test2();
      test();
//      System.out.println(a+","+b+","+c);
    }


  }

  public static void  test(){
    int c = 0;
    long startTime = System.nanoTime();
    for (int i = 0; i < 100_000_00; i++) {
      for (int j = 0; j < 10; j++) {
//        c=call(j);
      }
    }
    long endTime = System.nanoTime();
    System.out.println("test:"+(endTime-startTime));
//    return c;
  }

  public static void   test2(){
    int c = 0;
    long startTime = System.nanoTime();
    for (int i = 0; i <10 ; i++) {
      for (int j = 0; j < 100_000_00; j++) {
//        c=call(i);
      }
    }
    long endTime = System.nanoTime();

    System.out.println("test2:"+(endTime-startTime));
  }

  public static void    base(){
    int c = 0;
    long startTime = System.nanoTime();
    c= c+1;
//    c=call(1);
    long endTime = System.nanoTime();
    System.out.println("base:"+(endTime-startTime));
//    return c;
  }

  public static int  call(int i){
    int a =1;
    int b = 2;
    int c =array[i];
    return a+b+c+i;
  }
}
