package com.ibeetl.code.ch05;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
/**
 * EnumMap 演示
 * @author 公众号 java系统优化
 */
@BenchmarkMode(Mode.Throughput)
@Warmup(iterations = 3)
@Measurement(iterations = 3, time = 1, timeUnit = TimeUnit.SECONDS)
@Threads(1)
@Fork(1)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)
public class EnumMapTest {

  Map<Integer,String> genrealMap = null;
  Map<Status,String> enumMap =null;


  @Benchmark
  public void generalMap() {
     genrealMap.get(2);
     genrealMap.get(98);
  }
  @Benchmark
  public void enumMap(){
    enumMap.get(Status.FAIL);
    enumMap.get(Status.DEGRADE);

  }


  @Setup
  public void init(){
    initGenralMap();
    initEnumMap();
  }

  private void initGenralMap(){
    genrealMap = new HashMap<Integer,String>();
    for(Status status:Status.values()) {
      genrealMap.put(status.code,status.getMsg());
    }
  }


  private void initEnumMap(){
    enumMap = new EnumMap<Status,String>(Status.class);
    for(Status status:Status.values()) {
      enumMap.put(status,status.msg);
    }
  }






  public static void main(String[] args) throws RunnerException {
    Options opt = new OptionsBuilder()
      .include(EnumMapTest.class.getSimpleName())
      .build();
    new Runner(opt).run();
  }



  public static  enum Status {
    SUCCESS(1,"成功"),FAIL(2,"处理失败"),DEGRADE(98,"成功降级"),UNKOWN(99,"未知异常");

    private int code;String msg;
    Status(int code,String msg){
      this.code = code;
      this.msg = msg;
    }

    public int getCode() {
      return code;
    }

    public String getMsg() {
      return msg;
    }
  }
}


