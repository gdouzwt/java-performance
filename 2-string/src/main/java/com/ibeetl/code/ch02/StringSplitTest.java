package com.ibeetl.code.ch02;

import org.apache.commons.lang3.StringUtils;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.concurrent.TimeUnit;

/**
 * 字符串分割
 * @author 公众号 java系统优化
 */
@BenchmarkMode(Mode.AverageTime)
@Warmup(iterations = 10)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Threads(1)
@Fork(1)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Benchmark)
public class StringSplitTest {

  String str = "hello,world";


  @Benchmark
  public String[] split(){
   String[] arrays  =  str.split(",");
   return arrays;

  }



  @Benchmark
  public List token(){
    StringTokenizer st = new StringTokenizer(str,",");
    List<String> list = new ArrayList<>();
    while (st.hasMoreTokens()) {
      list.add(st.nextToken());
    }
    return list;
  }




  @Benchmark
  public List indexOf(){
    int pos = 0, end;
    List<String> list = new ArrayList<>();
    while ((end = str.indexOf(',', pos)) >= 0) {
      list.add(str.substring(pos, end));
      pos = end + 1;
    }
    if(pos!=str.length()-1){
      list.add(str.substring(pos));
    }
    return list;
  }


  @Benchmark
  public String[] stringUtils(){

    String[] array = StringUtils.split(str,",");
    return array;
  }


  public static void main(String[] args) throws RunnerException {

    Options opt = new OptionsBuilder()
      .include(StringSplitTest.class.getSimpleName())
      .forks(1)
      .build();
    new Runner(opt).run();
  }
}

