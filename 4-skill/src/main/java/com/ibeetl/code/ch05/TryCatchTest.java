package com.ibeetl.code.ch05;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import javax.management.MXBean;
import java.util.concurrent.TimeUnit;
/**
 * 验证try catch 代价
 * @author xiandafu@126.com ,公众号 java系统优化
 */
@BenchmarkMode(Mode.Throughput)
@Warmup(iterations = 10)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Threads(1)
@Fork(1)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)
public class TryCatchTest {

  int status = 0;
  public static  final int SUCCESS = 1;
  public static  final int FAILURE = 0;


  @Benchmark
  public boolean catchException(){
    try{
      business(status);
      return true;
    }catch(Exception ex){
      return false;
    }
  }
  @Benchmark
  public boolean errorCode(){
    int retCode = businessWitErrorCode(status);
    return retCode==SUCCESS;
  }


  protected  void business(int input){
    if(input==0){
      throw new IllegalArgumentException("模拟业务抛出异常");
//      throw new LightException("模拟业务抛出异常");
    }
    //模拟正常
    return ;

  }


  protected  int businessWitErrorCode(int input){
    if(input==0){
      return FAILURE;
    }
    //模拟正常
    return SUCCESS;
  }

  public static void main(String[] args) throws RunnerException {
    Options opt = new OptionsBuilder()
      .include(TryCatchTest.class.getSimpleName())
      .forks(1)
      .build();
    new Runner(opt).run();
  }


  static class LightException extends  RuntimeException{

    public LightException(String msg){
      super(msg);
    }
    public synchronized Throwable fillInStackTrace() {
      this.setStackTrace(new StackTraceElement[0]);
      return this;
    }
  }


}
