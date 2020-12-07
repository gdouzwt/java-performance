package com.ibeetl.code.ch03.pool;

public class PoolManager {
  static PoolManager poolManager = new PoolManager();
  QueryTaskThreadPoolExecutor  queryPool = null;

  private PoolManager(){
    queryPool = new QueryTaskThreadPoolExecutor();
    queryPool.init();
  }

  public static PoolManager instance(){
    return poolManager;
  }

  public QueryTaskThreadPoolExecutor getQueryPool() {
    return queryPool;
  }
}
