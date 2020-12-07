package com.ibeetl.code.ch05;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

/**
 * 验证try catch 代价
 * @author 公众号 java系统优化
 */
@BenchmarkMode(Mode.Throughput)
@Warmup(iterations = 10)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Threads(1)
@Fork(1)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)
public class TryCatch2Test {

  int status = 0;

  @Benchmark
  public boolean trycatch(){
    try{
       if(status==2){
       	//不可能执行到这里
       	throw new IllegalStateException();
	   }
      return true;
    }catch(Exception ex){
      return false;
    }
  }
  @Benchmark
  public boolean errorCode(){
	  if(status==2){
		  throw new IllegalStateException();
	  }
	  return true;
  }




  public static void main(String[] args) throws RunnerException {
    Options opt = new OptionsBuilder()
      .include(TryCatch2Test.class.getSimpleName())
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
