package com.ibeetl.code.ch01.com.ibeetl.code.ch01.jmh;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;
/**
 * 这是官网提供的不要在jmh里用上循环，因为jit会优化循环，我把关键英语翻译了一下
 * @author 公众号 java系统优化
 * @see  http://hg.openjdk.java.net/code-tools/jmh/file/tip/jmh-samples/src/main/java/org/openjdk/jmh/samples/
 */
@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class JMHSample_11_Loops {

  /*
   * It would be tempting for users to do loops within the benchmarked method.
   * (This is the bad thing Caliper taught everyone). These tests explain why
   * this is a bad idea.
   *
   * Looping is done in the hope of minimizing the overhead of calling the
   * test method, by doing the operations inside the loop instead of inside
   * the method call. Don't buy this argument; you will see there is more
   * magic happening when we allow optimizers to merge the loop iterations.
   */

  /*
   * Suppose we want to measure how much it takes to sum two integers:
   */

  int x = 1;
  int y = 2;

  /*
   * 正确做法
   */

  @Benchmark
  public int measureRight() {
    return (x + y);
  }

  /*
   * 错误的做法，看看后面的measureWrong_xxx就知道了
   *
   */
  private int reps(int reps) {
    int s = 0;
    for (int i = 0; i < reps; i++) {
      s += (x + y);
    }
    return s;
  }

  /*
   * 循环不同次数，得出来的reps平均的性能是不一样的
   *
   */

  @Benchmark
  @OperationsPerInvocation(1)
  public int measureWrong_1() {
    return reps(1);
  }

  @Benchmark
  @OperationsPerInvocation(10)
  public int measureWrong_10() {
    return reps(10);
  }

  @Benchmark
  @OperationsPerInvocation(100)
  public int measureWrong_100() {
    return reps(100);
  }

  @Benchmark
  @OperationsPerInvocation(1000)
  public int measureWrong_1000() {
    return reps(1000);
  }

  @Benchmark
  @OperationsPerInvocation(10000)
  public int measureWrong_10000() {
    return reps(10000);
  }

  @Benchmark
  @OperationsPerInvocation(100000)
  public int measureWrong_100000() {
    return reps(100000);
  }

  /*
   * ============================== HOW TO RUN THIS TEST: ====================================
   *
   * You might notice the larger the repetitions count, the lower the "perceived"
   * cost of the operation being measured. Up to the point we do each addition with 1/20 ns,
   * well beyond what hardware can actually do.
   *
   * This happens because the loop is heavily unrolled/pipelined, and the operation
   * to be measured is hoisted from the loop. Morale: don't overuse loops, rely on JMH
   * to get the measurement right.
   *
   * You can run this test:
   *
   * a) Via the command line:
   *    $ mvn clean install
   *    $ java -jar target/benchmarks.jar JMHSample_11 -f 1
   *    (we requested single fork; there are also other options, see -h)
   *
   * b) Via the Java API:
   *    (see the JMH homepage for possible caveats when running from IDE:
   *      http://openjdk.java.net/projects/code-tools/jmh/)
   */

  public static void main(String[] args) throws RunnerException {
    Options opt = new OptionsBuilder()
      .include(JMHSample_11_Loops.class.getSimpleName())
      .forks(1)
      .build();

    new Runner(opt).run();
  }

}
