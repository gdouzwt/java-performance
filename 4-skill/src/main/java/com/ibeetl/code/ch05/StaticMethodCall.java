package com.ibeetl.code.ch05;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;
/**
 * 静态方法调用省去了虚方法查找，性能好
 * @author 公众号 java系统优化
 */
@BenchmarkMode(Mode.Throughput)
@Warmup(iterations = 5)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Threads(1)
@Fork(1)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@CompilerControl(CompilerControl.Mode.DONT_INLINE)
@State(Scope.Benchmark)
public class StaticMethodCall {
  CommonUtil util = new CommonUtil();
  @Benchmark
  public void instanceCall(){
    util.call();
  }

  @Benchmark

  public void staticCall(){
    CommonUtil.staticCall();
  }

  @Benchmark
  public void finalCall(){
    util.finalCall();
  }

  static  class CommonUtil{

    public  void call(){
    }

    public static void staticCall(){
    }
    public final void finalCall(){

    }
  }

  public static void main(String[] args) throws RunnerException {
    Options opt = new OptionsBuilder()
      .include(StaticMethodCall.class.getSimpleName())
      .forks(1)
      .build();
    new Runner(opt).run();
  }
}


