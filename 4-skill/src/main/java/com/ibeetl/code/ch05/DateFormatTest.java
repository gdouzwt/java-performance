package com.ibeetl.code.ch05;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.concurrent.TimeUnit;
/**
 * 日期格式化高性能的正确姿势
 * @author 公众号 java系统优化
 */
@BenchmarkMode(Mode.Throughput)
@Warmup(iterations = 5)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Threads(5)
@Fork(1)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)
public class DateFormatTest {
  private Date date = new Date();
  LocalDateTime now = LocalDateTime.now();


  @Benchmark
  public String format(){
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    String str = sdf.format(date);
    return str;
  }

  @Benchmark
  public String formatThreadLocal(){
    String str = CommonUtil.foramtDate(date);
    return str;
  }

  @Benchmark
  public String formatJdk8(){
    DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    String str = format.format(now);
    return str;
  }



  public static void main(String[] args) throws RunnerException {
    Options opt = new OptionsBuilder()
      .include(DateFormatTest.class.getSimpleName())
      .forks(1)
      .build();
    new Runner(opt).run();
  }

  static class CommonUtil{
    private static ThreadLocal<SimpleDateFormat> threadlocal = new ThreadLocal<SimpleDateFormat>(){
      public SimpleDateFormat initialValue(){
          SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
          return sdf;
      }
    };

    public  static String foramtDate(Date d){
      SimpleDateFormat sdf = getDateFormat();
      return sdf.format(d);
    }

    private static SimpleDateFormat getDateFormat() {
      return threadlocal.get();
    }
  }

}
