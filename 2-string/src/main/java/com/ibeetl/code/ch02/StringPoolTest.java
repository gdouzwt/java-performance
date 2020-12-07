package com.ibeetl.code.ch02;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

/**
 * 演示了一个过时的intern方法，intern本意用来节约内存，复用字符串，但性能呢很慢，
 * JDK 提供了新的复用字符串的办法
 * @author 公众号 java系统优化
 */
@BenchmarkMode(Mode.AverageTime)
@Warmup(iterations = 10)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Threads(1)
@Fork(1)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Benchmark)
public class StringPoolTest {

  String status = "1";


  @Benchmark
  public String intern(){
   String str =  status.intern();
   return str;

  }




  public static void main(String[] args) throws RunnerException {

    Options opt = new OptionsBuilder()
      .include(StringPoolTest.class.getSimpleName())
      .forks(1)
      .build();
    new Runner(opt).run();
  }
}

