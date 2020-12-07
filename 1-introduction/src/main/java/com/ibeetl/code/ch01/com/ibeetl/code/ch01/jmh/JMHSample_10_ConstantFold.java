package com.ibeetl.code.ch01.com.ibeetl.code.ch01.jmh;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;
/**
 * 这是官网提供的常量折叠演示，我把关键英语翻译了一下
 * @author 公众号 java系统优化
 * @see  http://hg.openjdk.java.net/code-tools/jmh/file/tip/jmh-samples/src/main/java/org/openjdk/jmh/samples/
 */
@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class JMHSample_10_ConstantFold {

  /*
   * The flip side of dead-code elimination is constant-folding.
   *
   * If JVM realizes the result of the computation is the same no matter what,
   * it can cleverly optimize it. In our case, that means we can move the
   * computation outside of the internal JMH loop.
   *
   * This can be prevented by always reading the inputs from non-final
   * instance fields of @State objects, computing the result based on those
   * values, and follow the rules to prevent DCE.
   */

  // IDEs will say "Oh, you can convert this field to local variable". Don't. Trust. Them.
  // (While this is normally fine advice, it does not work in the context of measuring correctly.)
  private double x = Math.PI;

  // IDEs will probably also say "Look, it could be final". Don't. Trust. Them. Either.
  // (While this is normally fine advice, it does not work in the context of measuring correctly.)
  private final double wrongX = Math.PI;

  @Benchmark
  public double baseline() {
    // 基准测试，如果其他jmh方法性能跟这个一样，那么就说明那些jmh犯法写错了
    return Math.PI;
  }

  @Benchmark
  public double measureWrong_1() {
    // 错误的测试，jvm不需要计算，直接返回
    return Math.log(Math.PI);
  }

  @Benchmark
  public double measureWrong_2() {
    // 还是错误的测试，wrongX是常量，可以预测结果
    return Math.log(wrongX);
  }

  @Benchmark
  public double measureRight() {
    // 正确测试.
    return Math.log(x);
  }

  /*
   * ============================== HOW TO RUN THIS TEST: ====================================
   *
   * You can see the unrealistically fast calculation in with measureWrong_*(),
   * while realistic measurement with measureRight().
   *
   * You can run this test:
   *
   * a) Via the command line:
   *    $ mvn clean install
   *    $ java -jar target/benchmarks.jar JMHSample_10 -i 5 -f 1
   *    (we requested single fork; there are also other options, see -h)
   *
   * b) Via the Java API:
   *    (see the JMH homepage for possible caveats when running from IDE:
   *      http://openjdk.java.net/projects/code-tools/jmh/)
   */

  public static void main(String[] args) throws RunnerException {
    Options opt = new OptionsBuilder()
      .include(JMHSample_10_ConstantFold.class.getSimpleName())
      .forks(1)
      .build();

    new Runner(opt).run();
  }

}
