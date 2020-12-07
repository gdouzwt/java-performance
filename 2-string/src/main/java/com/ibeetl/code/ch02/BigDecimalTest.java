package com.ibeetl.code.ch02;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

/**
 * BigDecimal 是电商，金融等行业必备数据类型，避免了double得不精确性，
 * Long在一定程度上可以代替BigDecimal运算，本例演示了long和bigdecimal的性能
 * @author  公众号 java系统优化
 */
@BenchmarkMode(Mode.AverageTime)
@Warmup(iterations = 10)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Threads(1)
@Fork(1)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Benchmark)
public class BigDecimalTest {


	BigDecimal a = new BigDecimal("0.05");
	BigDecimal b = new BigDecimal("0.01");
	long c = 5;
	long d = 1;

	public static void main(String[] args) throws RunnerException {

		Options opt = new OptionsBuilder().include(BigDecimalTest.class.getSimpleName()).forks(1).build();
		new Runner(opt).run();


	}

	@Benchmark
	@CompilerControl(CompilerControl.Mode.DONT_INLINE)
	public long addByLong() {
		return (c + d);
	}
	@Benchmark
	@CompilerControl(CompilerControl.Mode.DONT_INLINE)
	public BigDecimal addByBigDecimal() {
		return a.add(b);
	}
}
