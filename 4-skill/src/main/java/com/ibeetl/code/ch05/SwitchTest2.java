package com.ibeetl.code.ch05;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

/**
 * if和switch较多分支，需要比较13次，性能测试
 * @author 公众号 java系统优化
 */
@BenchmarkMode(Mode.Throughput)
@Warmup(iterations = 5)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Threads(1)
@Fork(1)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)
public class SwitchTest2 {
	int a = 17;

	public static void main(String[] args) throws RunnerException {
		Options opt = new OptionsBuilder().include(SwitchTest2.class.getSimpleName()).forks(1).build();
		new Runner(opt).run();
	}

	@Benchmark
	public int testTableSwitch() {
		int c = 0;
		switch (a) {
			case 1:
				c = a;
				break;
			case 2:
				c = a;
				break;
			case 3:
				c = a;
				break;
			case 5:
				c = a;
				break;
			case 9:
				c = a;
				break;
			case 10:
				c = a;
				break;
			case 11:
				c = a;
				break;
			case 12:
				c = a;
				break;
			case 13:
				c = a;
				break;
			case 14:
				c = a;
				break;
			case 15:
				c = a;
				break;
			case 16:
				c = a;
				break;
			case 17:
				c = a;
				break;
			case 18:
				c = a;
				break;
			case 19:
				c = a;
				break;
			case 20:
				c = a;
				break;
			case 21:
				c = a;
				break;
			case 22:
				c = a;
				break;


		}
		return c;

	}

	@Benchmark
	public int testLookupSwitch() {
		int c = 0;
		switch (a) {
			case 1:
				c = a;
				break;
			case 2:
				c = a;
				break;
			case 3:
				c = a;
				break;
			case 5:
				c = a;
				break;
			case 9:
				c = a;
				break;
			case 10:
				c = a;
				break;
			case 11:
				c = a;
				break;
			case 12:
				c = a;
				break;
			case 13:
				c = a;
				break;
			case 14:
				c = a;
				break;
			case 15:
				c = a;
				break;
			case 16:
				c = a;
				break;
			case 17:
				c = a;
				break;
			case 119:
				c = a;
				break;

		}
		return c;
	}

	@Benchmark
	public int testIf() {
		int c = 0;
		if (a == 1) {
			c = a;
		} else if (a == 2) {
			c = a;
		} else if (a == 3) {
			c = a;
		} else if (a == 5) {
			c = a;
		} else if (a == 6) {
			c = a;
		}else if (a == 7) {
			c = a;
		}else if (a == 8) {
			c = a;
		}else if (a == 9) {
			c = a;
		}else if (a == 10) {
			c = a;
		}else if (a ==11) {
			c = a;
		}else if (a ==12) {
			c = a;
		}else if (a == 13) {
			c = a;
		}else if (a == 17) {
			c = a;
		}else if (a == 19) {
			c = a;
		}

		return c;

	}
}
