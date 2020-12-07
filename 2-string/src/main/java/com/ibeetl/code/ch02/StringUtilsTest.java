package com.ibeetl.code.ch02;

import org.apache.commons.lang3.StringUtils;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * 第三方工具不错,多使用大厂的工具
 * @author 公众号 java系统优化
 */
@BenchmarkMode(Mode.AverageTime)
@Warmup(iterations = 10)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Threads(1)
@Fork(1)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Benchmark)
public class StringUtilsTest {

  static String str = "1234$789$abc";
  @Benchmark
  public String[] split(){

    String[] array = StringUtils.split(str,"$");
    return array;

  }

  @Benchmark
  public String[] splitRegex(){
    String[] array = str.split("\\$");
    return array;

  }

  public static void main(String[] args) throws RunnerException {

    Options opt = new OptionsBuilder()
      .include(StringUtilsTest.class.getSimpleName())
      .forks(1)
      .build();
    new Runner(opt).run();


  }
}
