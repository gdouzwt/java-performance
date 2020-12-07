package com.ibeetl.code.ch02;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * 封箱和装箱测试,stream 操作不一定快啊，不过容易阅读和编写是优势
 * @author 公众号 java系统优化
 */
@BenchmarkMode(Mode.AverageTime)
@Warmup(iterations = 10)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Threads(1)
@Fork(1)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Benchmark)
public class IntTest {



	public static void main(String[] args) throws RunnerException {

		Options opt = new OptionsBuilder().include(IntTest.class.getSimpleName()).forks(1).build();
		new Runner(opt).run();
	}

	@Benchmark
	public int add() {
		IntStream intStream = IntStream.of(10, 20, 30, 40, 350);
		return  intStream.sum();

	}

	@Benchmark
	public int addInteger() {
		List<Integer> list = Arrays.asList(10, 20, 30, 40, 350);
		int total = 0;
		for(Integer i:list){
			total+=i;
		}
		return total;
	}
}

