package com.ibeetl.code.ch02;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

/**
 * 了解一下构造String的代码，分布式&微服务系统里，通过byte[] 构造String占了消耗的大量CPU
 * @author 公众号 java系统优化
 */
@BenchmarkMode(Mode.AverageTime)
@Warmup(iterations = 10)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Threads(1)
@Fork(1)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Benchmark)
public class NewStringTest {


  String str= "你好，String";
  char[] chars = str.toCharArray();


  @Benchmark
  public String string(){
    return new String(str);
  }

  @Benchmark
  public String stringByCharArray(){
    return new String(chars);
  }

  byte[] bs = null;

  @Benchmark
  public String stringByByteArray() throws Exception{
    return new String(bs,"UTF-8");
  }

  @Setup
  public void init() throws Exception{
    bs = str.getBytes("UTF-8");
  }

  public static void main(String[] args) throws RunnerException {
    Options opt = new OptionsBuilder()
      .include(NewStringTest.class.getSimpleName())
      .forks(1)
      .build();
    new Runner(opt).run();
  }
}

