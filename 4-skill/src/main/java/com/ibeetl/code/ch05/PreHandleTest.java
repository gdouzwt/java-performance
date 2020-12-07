package com.ibeetl.code.ch05;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;
/**
 * 一个简单的例子，这种优化很常用
 * @author 公众号 java系统优化
 */
@BenchmarkMode(Mode.Throughput)
@Warmup(iterations = 5)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Threads(1)
@Fork(1)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)
public class PreHandleTest {
  ServiceConfig  serviceConfig ;
  NewServiceConfig newConfig;
  String requestSource = "sy";

  @Benchmark
  public boolean testGeneral(){
      String black = serviceConfig.getBlack();
      Set<String> str = new HashSet<String>(Arrays.asList(black.split(",")));
      return str.contains(requestSource);

  }
  @Benchmark
  public boolean testPrefer(){
      return newConfig.getBlackSet().contains(requestSource);

  }


  @Setup
  public void init(){
    String black = "ac,sy,dsy";
    serviceConfig = new ServiceConfig(black);
    newConfig = new NewServiceConfig(black);
  }

  public static void main(String[] args) throws RunnerException {
    Options opt = new OptionsBuilder()
      .include(PreHandleTest.class.getSimpleName())
      .forks(1)
      .build();
    new Runner(opt).run();
  }


  static  class ServiceConfig{
    String black = null;
    public ServiceConfig(String black){
      this.black = black;
    }

      public String getBlack() {
          return black;
      }
  }

	/**
	 * 一次初始化好，方便后续查询
	 */
	static  class NewServiceConfig{
    String black = null;
    Set<String> blackSet = new HashSet<>();
    public NewServiceConfig(String black){
      this.black = black;
      this.blackSet.addAll(Arrays.asList(black.split(",")));
    }

    public Set<String> getBlackSet() {
      return blackSet;
    }

    public void setBlackSet(Set<String> blackSet) {
      this.blackSet = blackSet;
    }
  }

}
