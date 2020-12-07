package com.ibeetl.code.ch01.com.ibeetl.code.ch01.jmh;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

/**
 * baseline空方法能有效验证写的jmh是否正确
 * @author 公众号 java系统优化
 */
@BenchmarkMode(Mode.AverageTime)
@Warmup(iterations = 3)
@Measurement(iterations = 3, time = 1, timeUnit = TimeUnit.SECONDS)
@Threads(1)
@Fork(1)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Benchmark)
public class DeadCodeTest {

	int a = 10;

	public static void main(String[] args) throws RunnerException {
		Options opt = new OptionsBuilder().include(DeadCodeTest.class.getSimpleName()).forks(1).build();

		new Runner(opt).run();
	}

	@Benchmark
	public void baseline() {
		// do nothing, this is a baseline
	}

	@Benchmark
	public void calc(Blackhole hole) {
		hole.consume (call());
	}

	private double call() {
		int c = a * 30;
		int d = c+2*c;
		double e = d/0.9;
		return e;
	}

}
