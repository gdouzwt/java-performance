package com.ibeetl.code.ch05;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

/**
 * 循环合并，创建for循环耗时，for循环过多也耗时，可以避免for循环（比如为空，或者集合只有一个元素）
 * 或者合并循环
 * @author 公众号 java系统优化
 */
@BenchmarkMode(Mode.Throughput)
@Warmup(iterations = 10)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Threads(1)
@Fork(1)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)
public class ForIntTest {
  int[] a = null;
  int[] b = null;


  @Benchmark
  public int testFor(){
    int[] local = a;
    int total = 0;
    for(int i=0;i<local.length;i++){
      total+=local[i];
    }
    return total;
  }



  @Benchmark
  public int testForMerge(){

    int[] local = b;
    int total = 0;

    for(int i=0;i<local.length;i++){
      total+=local[i++];
      total+=local[i++];
      total+=local[i++];
      total+=local[i++];
    }
    return total;
  }



  @Setup
  public void init(){
    int first = 24;
    int max = 24;
    a= new int[first];
    b= new int[max];
    for(int i=0;i<first;i++){
      a[i] = 1;
    }
    for(int i=0;i<first;i++){
      b[i] = 1;
    }
    for(int i=first;i<max;i++){
      b[i] = 0;
    }
  }


  public static void main(String[] args) throws RunnerException {
    Options opt = new OptionsBuilder()
      .include(ForIntTest.class.getSimpleName())
      .forks(1)
      .build();
    new Runner(opt).run();
  }
}
