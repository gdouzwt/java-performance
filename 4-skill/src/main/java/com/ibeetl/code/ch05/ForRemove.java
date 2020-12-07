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
@BenchmarkMode(Mode.AverageTime)
@Warmup(iterations = 10)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Threads(1)
@Fork(1)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Benchmark)
public class ForRemove {
  int[] a = null;

  @Benchmark
  public int testFor(){
    int total =0;
    int len = a.length;

    for(int i=0;i<len;i++){
      total+=a[i];
    }
    return total;
  }

	/**
	 * 删除循环
	 * @return
	 */
  @Benchmark
  public int testForMerge(){
	  int total =0;
	  if(a.length==1){
	  	total = total +a[0];
	  	return total;
	  }else if(a.length==2){
	  	total = total+a[0]+a[1];
	  	return total;
	  }
	  return testFor();

  }



  @Setup
  public void init(){
    a= new int[2];
    for(int i=0;i<2;i++){
      a[i] = 1;
    }

  }


  public static void main(String[] args) throws RunnerException {
    Options opt = new OptionsBuilder()
      .include(ForRemove.class.getSimpleName())
      .forks(1)
      .build();
    new Runner(opt).run();
  }
}
