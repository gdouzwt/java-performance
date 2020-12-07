package com.ibeetl.code.ch05;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

/**
 * 一个int转string的优化例子
 * @author 公众号 java系统优化
 */
@BenchmarkMode(Mode.Throughput)
@Warmup(iterations = 3)
@Measurement(iterations = 3, time = 1, timeUnit = TimeUnit.SECONDS)
@Threads(1)
@Fork(1)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)
public class Int2StringTest {

  @Param({"1", "31", "65", "101", "103","4575"})
  int status = 1;

  @Benchmark
  public String int2String() {
    return String.valueOf(status);
  }
  @Benchmark
  public String int2StringByCache(){
    return CommonUtil.int2String(status);
  }


  public static void main(String[] args) throws RunnerException {
    Options opt = new OptionsBuilder()
      .include(Int2StringTest.class.getSimpleName())
      .build();
    new Runner(opt).run();
  }

  public static class  CommonUtil{
    static int cacheSize = 1024;
    static String[] caches = new String[cacheSize];
    static {
      for(int i=0;i<cacheSize;i++){
        caches[i] = String.valueOf(i);
      }
    }
    public static String int2String(int data) {
      if (data < cacheSize) {
        return caches[data];
      } else {
        return String.valueOf(data);
      }
    }
  }
}
