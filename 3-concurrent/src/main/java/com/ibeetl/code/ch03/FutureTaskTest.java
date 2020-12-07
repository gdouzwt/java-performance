package com.ibeetl.code.ch03;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * 一个简单使用实例
 * @author 公众号 java系统优化
 */
public class FutureTaskTest {
  public static void main(String[] args) throws Exception{
    int a=10;
    int b= 22;
    int c =3;
    int d= 9;
    //求和a+b+c+d,可以分成俩个FutureTask，分别通过来个线程计算a+b和c+d,执行完毕后再求和
    FutureTask<Integer>[] tasks = new FutureTask[2];
    tasks[0] = new FutureTask<Integer>(new Sum(a,b));
    tasks[1]  = new FutureTask<Integer>(new Sum(c,d));
    //其他俩个线程执行FutureTask
    Thread[] threads = new Thread[2];
    threads[0] = new Thread(tasks[0]);
    threads[1] = new Thread(tasks[1]);
    //分别计算
    for(Thread t:threads){
      t.start();
    }
    //获取执行结果
    int total = 0;
    for(FutureTask<Integer> task:tasks){
      task.get(10, TimeUnit.MILLISECONDS);
      total=total+task.get();
    }

    System.out.println(total);




  }

  static class Sum implements Callable<Integer> {
    private int x;
    private int y;
    public Sum(int x,int y){
      this.x = x;
      this.y = y;
    }
    @Override
    public Integer call() throws Exception {
      return x+y;
    }

  }
}
