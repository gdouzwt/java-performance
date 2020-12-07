package com.ibeetl.code.ch05;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;
/**
 * swtich 和 ifelse，包含了lookup 和 tableswitch，还有ifelse
 * @author 公众号 java系统优化
 */
@BenchmarkMode(Mode.Throughput)
@Warmup(iterations = 5)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Threads(1)
@Fork(1)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)
public class SwitchTest {
  int a = 5;
  @Benchmark
  public int testTableSwitch(){
    int c=0;
    switch(a){
      case 1:c=a;break;
      case 2:c=a;break;
      case 3:c=a;break;
      case 5:c=a;break;
      case 9:c=a;break;

    }
    return c;

  }
  @Benchmark
  public int testLookupSwitch(){
    int c=0;
    switch(a){
      case 1:c=a;break;
      case 2:c=a;break;
      case 3:c=a;break;
      case 5:c=a;break;
      case 119:c=a;break;

    }
    return c ;
  }

  @Benchmark
  public int  testIf(){
    int c=0;
    if(a==1){
      c=a;
    }else if(a==2){
      c=a;
    }else if(a==3){
      c=a;
    }else if(a==5){
      c=a;
    }else if(a==9){
      c=a;
    }

    return c ;

  }

  public static void main(String[] args) throws RunnerException {
    Options opt = new OptionsBuilder()
      .include(SwitchTest.class.getSimpleName())
      .forks(1)
      .build();
    new Runner(opt).run();
  }
}
