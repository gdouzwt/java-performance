package com.ibeetl.code.ch05;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;
/**
 * swtich 优化
 * @author 公众号 java系统优化
 */
@BenchmarkMode(Mode.Throughput)
@Warmup(iterations = 5)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Threads(1)
@Fork(1)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)
public class SwitchStringTest {
  String key = "e";
  @Benchmark
  public void testStringSwitch(){
    String c;
    switch(key){
      case "key":c= key;break;
      case "b":c= key;break;
      case "c":c= key;break;
      case "e":c= key;break;
      case "h":c= key;break;

    }

  }
  @Benchmark
  public void testString2IntSwitch(){
    String c;
    switch(key.hashCode()){
      case 97:{ //a
        c= key;
        break;
      }
      case 98:{ //b
        c= key;
        break;
      }
      case 99:{ //c
        c= key;
        break;
      }
      case 101:{ //e
        c= key;
        break;
      }
      case 104:{ //h
        c= key;
        break;
      }

    }
  }


  public static void main(String[] args) throws RunnerException {
    Options opt = new OptionsBuilder()
      .include(SwitchStringTest.class.getSimpleName())
      .forks(1)
      .build();
    new Runner(opt).run();
  }
}
