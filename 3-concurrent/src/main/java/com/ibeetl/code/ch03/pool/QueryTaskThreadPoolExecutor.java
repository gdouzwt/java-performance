package com.ibeetl.code.ch03.pool;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class QueryTaskThreadPoolExecutor {

  private ThreadPoolExecutor pool = null;

  public void init() {
    pool = new ThreadPoolExecutor(
      5,
      10,
      1,
      TimeUnit.MINUTES,
      new ArrayBlockingQueue<Runnable>(100),
      new CustomThreadFactory(),
      new CustomRejectedExecutionHandler());
  }


  private class CustomThreadFactory implements ThreadFactory {

    private AtomicInteger count = new AtomicInteger(0);

    @Override
    public Thread newThread(Runnable r) {
      Thread t = new Thread(r);
      String threadName = QueryTaskThreadPoolExecutor.class.getSimpleName() + count.addAndGet(1);
      t.setName(threadName);
      return t;
    }
  }

  private class CustomRejectedExecutionHandler extends ThreadPoolExecutor.AbortPolicy {

    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
      //报警日志,说明线程池负载已经最大了
      System.out.println("error.............");
      super.rejectedExecution(r,executor);
    }
  }

  public ThreadPoolExecutor getCustomThreadPoolExecutor() {
    return this.pool;
  }

}
