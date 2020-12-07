package com.ibeetl.code.ch05;

import com.ibeetl.code.ch05.ump.Profile;
import com.ibeetl.code.ch05.ump.Profile2;
import com.ibeetl.code.ch05.ump.Profile3;
import com.ibeetl.code.ch05.ump.Watch;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

/**
 * hash虽然快，但并不是最快的查找方式，能用数组最好
 * @author 公众号 java系统优化
 */
@BenchmarkMode(Mode.AverageTime)
@Warmup(iterations = 5)
@Measurement(iterations = 5, time = 5, timeUnit = TimeUnit.SECONDS)
@Threads(2)
@Fork(1)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Benchmark)
public class WatchTest {

  Watch watch1 = null;
  Watch watch2 = null;
  Watch watch3 = null;


  @Benchmark
  public void general(){
    Profile.addWatch(watch1);
    Profile.addWatch(watch2);
    Profile.addWatch(watch3);
  }

  @Benchmark
	public void better(){
		Profile2.addWatch(watch1);
		Profile2.addWatch(watch2);
		Profile2.addWatch(watch3);
	}
	@Benchmark
	public void better2(){
		Profile3.addWatch(watch1);
		Profile3.addWatch(watch2);
		Profile3.addWatch(watch3);
	}

  @Setup(Level.Iteration)
  public void initWatch(){
    watch1 = Watch.instance("order");
    sleep(1);

    //模拟RPC调用耗时
    watch2 = Watch.instance("order-rpc");
    sleep(10);
    watch2.endWatch();

    //模拟计算订单耗时
    watch3 = Watch.instance("order-build");
    sleep(2);
    watch3.endWatch();

    watch1.endWatch();

	  Profile.init();
	  Profile2.init();
	  Profile3.init();
  }

  private void sleep(int time){
    try {
      Thread.sleep(time);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }


  public static void main(String[] args) throws RunnerException {
    WatchTest test = new WatchTest();
    test.initWatch();
    test.better();

    Options opt = new OptionsBuilder()
      .include(WatchTest.class.getSimpleName())
      .build();
    new Runner(opt).run();
  }

}
