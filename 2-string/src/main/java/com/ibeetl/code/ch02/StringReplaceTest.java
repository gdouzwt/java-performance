package com.ibeetl.code.ch02;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

/**
 * 替换，正则替换并不慢，主要是因为替换后，需要生成新的字符串，这个会慢。哈哈
 * @author 公众号 java系统优化
 *
 */
@BenchmarkMode(Mode.AverageTime)
@Warmup(iterations = 10)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Threads(1)
@Fork(1)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Benchmark)
public class StringReplaceTest {

  String str = "为了保证服务质量，知识星球采用收费模式，限定星球人数，6月30号以前优惠价，150元/一年 ，7月以后299元/年 会费，微信扫描二维码， 成为会员，了解Java系统微优化。";

  Pattern pattern = Pattern.compile("150");
  @Benchmark
  public String replace(){
   String str =  this.str.replace("150","199");
   return str;

  }



  @Benchmark
  public String replaceByRegex(){
    String str =  this.str.replaceAll("150","199");
    return str;
  }

  @Benchmark
  public String replaceByCompileRegex(){
    String str =  pattern.matcher(this.str).replaceAll("199");
    return str;
  }



  @Setup
  public void init(){

  }



  public static void main(String[] args) throws RunnerException {

//	  StringReplaceTest test = new StringReplaceTest();
//	  System.out.println(test.replace());
//	  System.out.println(test.replaceByRegex());
//	  System.out.println(test.replaceByCompileRegex());



    Options opt = new OptionsBuilder()
      .include(StringReplaceTest.class.getSimpleName())
      .forks(1)
      .build();
    new Runner(opt).run();
  }
}

