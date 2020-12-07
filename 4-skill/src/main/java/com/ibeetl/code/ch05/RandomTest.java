package com.ibeetl.code.ch05;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * 使用随机数的正确姿势，Random虽然是线程安全，但不如ThreadLocalRandom
 * @author 公众号 java系统优化
 */

@BenchmarkMode(Mode.AverageTime)
@Warmup(iterations = 5)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Threads(10)
@Fork(1)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Benchmark)
public class RandomTest {

	Random random = new Random();



    @Benchmark
    public int random() {
		return random.nextInt(50);
    }

	@Benchmark
	public int localRandom() {
		ThreadLocalRandom random = ThreadLocalRandom.current();
		return random.nextInt(50);
	}





    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(RandomTest.class.getSimpleName())
                .forks(1)
                .build();
        new Runner(opt).run();
    }
}
