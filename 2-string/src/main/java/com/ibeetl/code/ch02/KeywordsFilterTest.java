package com.ibeetl.code.ch02;

import com.ibeetl.code.ch02.tree.KeywordSearch;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 *  前缀树过滤,一个好的算法提升性能关键，互联网关键字有上万，前缀树的性能表现非常好
 * @author 公众号 java系统优化
 */
@BenchmarkMode(Mode.AverageTime)
@Warmup(iterations = 10)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Threads(1)
@Fork(1)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Benchmark)
public class KeywordsFilterTest {

  static String str = "你好，小狗，小猫，今天天气真的很好";
  static List<String> keys = Arrays.asList("猪狗","小狗","小猫","小鸭","小鸡","小鹅");
  KeywordSearch tree;

  @Benchmark
  public String  tree(){
	  return tree.filter(str);

  }

  @Benchmark
  public String replace(){
  	String temp = str;
	for(String key:keys){
		str = str.replace(key,"***");
	}
	return str;
  }

  @Setup
  public void init(){
  	tree =  new KeywordSearch();
  	for(String key:keys){
		tree.addWord(key);
	}

  }

  public static void main(String[] args) throws RunnerException {


    Options opt = new OptionsBuilder()
      .include(KeywordsFilterTest.class.getSimpleName())
      .forks(1)
      .build();
    new Runner(opt).run();


  }
}
