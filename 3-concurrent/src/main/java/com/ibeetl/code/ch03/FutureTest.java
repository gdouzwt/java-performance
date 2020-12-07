package com.ibeetl.code.ch03;

import com.ibeetl.code.ch03.pool.PoolManager;

import java.io.InputStream;
import java.net.URL;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

/** 异步编程的编排实例
 * @author 公众号 java系统优化
 */
public class FutureTest {
  static ThreadPoolExecutor pool = PoolManager.instance().getQueryPool().getCustomThreadPoolExecutor();
  public static void main(String[] args) throws Exception{
    thenAccept();
  }


  public static void runAsync() throws Exception {
    CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
      System.out.println("run end ...");
    });

  }

  //有返回值
  public static void supplyAsync() throws Exception {
    CompletableFuture<Long> future = CompletableFuture.supplyAsync(() -> {
      return 1l;
    });

    long ret = future.get();
    System.out.println("ret = "+ret);
  }


  public static void whenComplete() throws Exception {
    int a=3,b=4,c=0;
    CompletableFuture<Integer> addFuture = CompletableFuture.supplyAsync(() -> {
        return a+b;
    });

    addFuture.whenComplete(new BiConsumer<Integer, Throwable>() {
      @Override
      public void accept(Integer t, Throwable action) {
        System.out.println("求和:"+t);
      }

    });



    CompletableFuture<Integer> divFuture = CompletableFuture.supplyAsync(() -> {
      return b/c;
    });

    divFuture.exceptionally(new Function<Throwable, Integer>() {
      @Override
      public Integer apply(Throwable t) {
        System.out.println("执行失败:"+t.getMessage());
        return null;
      }
    });

  }


  private static void thenApply() throws Exception {
    CompletableFuture<Double> future = CompletableFuture.supplyAsync(new Supplier<Long>() {
      @Override
      public Long get() {
        return 10l;
      }
    }).thenApplyAsync(new Function<Long, Double>() {
      @Override
      public Double apply(Long t) {
        double result = t*2.3;
        return result;
      }
    });

    double result = future.get();
    System.out.println(result);
  }

  public static void handle() throws Exception{
    CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
      int i= 10/0;
      return i;
    }).handle(new BiFunction<Integer, Throwable, Integer>() {
      @Override
      public Integer apply(Integer input, Throwable throwable) {

        if(throwable!=null){
           return -1;
        }
        return input*2;
      }
    });
    System.out.println(future.get());
  }

  public static void thenAccept() throws Exception{
    CompletableFuture<Void> future = CompletableFuture.supplyAsync(new Supplier<Integer>() {
      @Override
      public Integer get() {
        return 10;
      }
    }).thenAcceptAsync(integer -> {
      System.out.println(integer);
    }).thenAcceptAsync(Void->{
      System.out.println("结束");
    });

//    future.get();
  }

  public static void thenRun() throws Exception{
    CompletableFuture<Void> future = CompletableFuture.supplyAsync(new Supplier<Integer>() {
      @Override
      public Integer get() {
        return 10;
      }
    }).thenAcceptAsync(integer -> {
      System.out.println(integer);
    }).thenRun(()->{
      System.out.println("结束");
    });
    future.get();
  }


  private static void thenCombine() throws Exception {
    int a=3,b=4,c=6,d=11;
    CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(()->{
    return a+b;
    });
    CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(()->{
      return c+d;
    });
    //合并任务计算
    CompletableFuture<Integer> result = future1.thenCombine(future2, new BiFunction<Integer, Integer, Integer>() {
      @Override
      public Integer apply(Integer t, Integer u) {
        return t+u;
      }
    });
    System.out.println(result.get());
  }

  private static void thenAcceptBoth() throws Exception {
    int a=3,b=4,c=6,d=11;
    CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(()->{
      return a+b;
    });
    CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(()->{
      return c+d;
    });
    //合并任务计算
    future1.thenAcceptBoth(future2, new BiConsumer<Integer, Integer>() {
      @Override
      public void accept(Integer t, Integer u) {
        System.out.println(t+u);
      }
    });

  }


  private static void applyToEither() throws Exception {
    CompletableFuture<String> fastServer = CompletableFuture.supplyAsync(()->{
      try {
        TimeUnit.MILLISECONDS.sleep(10);
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
      return "192.168.0.1";
    });

    CompletableFuture<String> slow = CompletableFuture.supplyAsync(()->{
      try {
        TimeUnit.MILLISECONDS.sleep(100);
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
      return "192.168.0.12";
    });

    CompletableFuture<String> result = fastServer.applyToEither(slow, new Function<String, String>() {
      @Override
      public String apply(String ip) {
        System.out.println("更快响应ip "+ip);
        return ip;
      }
    });
    System.out.println(result.get());
  }

  private static void runAfterBoth() throws Exception {
    CompletableFuture<Boolean> f1 = CompletableFuture.supplyAsync(()->{
      return build(1);
    });

    CompletableFuture<Boolean> f2 = CompletableFuture.supplyAsync(()->{
      return build(2);
    });
    f1.runAfterBoth(f2, ()-> {
      System.out.println("都执行成功");
    });

  }

  public static boolean build(int para){
    return para%2==0;
  }

  public static  void allOf()throws Exception {
    CompletableFuture f1 = CompletableFuture.runAsync(()->{
      try {
        TimeUnit.MILLISECONDS.sleep(100);
        System.out.println("execute f1");
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    });
    CompletableFuture f2 = CompletableFuture.runAsync(()->{
      try {
        TimeUnit.MILLISECONDS.sleep(1000);
        System.out.println("execute f2");
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    });

    CompletableFuture all =  CompletableFuture.allOf(f1,f2);
    all.get();

//    CompletableFuture.allOf(f1,f2).join();
    System.out.println("execute all");

  }

  public static  void anyOf()throws Exception {
    CompletableFuture f1 = CompletableFuture.runAsync(()->{
      try {
        TimeUnit.MILLISECONDS.sleep(100);
        System.out.println("execute f1");
        throw new RuntimeException();
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    });
    CompletableFuture f2 = CompletableFuture.runAsync(()->{
      try {
        TimeUnit.MILLISECONDS.sleep(1000);
        System.out.println("execute f2");

      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    });


     CompletableFuture.anyOf(f1,f2).whenComplete((Object o,Throwable t)->{
       System.out.println("完成 "+t);
     });

    TimeUnit.MILLISECONDS.sleep(1000);

  }



}
