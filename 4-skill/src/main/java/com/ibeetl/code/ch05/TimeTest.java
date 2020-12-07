package com.ibeetl.code.ch05;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

/**
 * Benchmark                              Mode  Samples  Score  Score error   Units
 * c.i.c.c.TimeTest.currentTimeMillis    thrpt        5  0.075        0.001  ops/ns
 * c.i.c.c.TimeTest.nanoTime             thrpt        5  0.025        0.002  ops/ns
 * @author 公众号 java系统优化
 */
@BenchmarkMode(Mode.Throughput)
@Warmup(iterations = 5)
@Measurement(iterations = 5, time = 3, timeUnit = TimeUnit.SECONDS)
@Threads(200)
@Fork(1)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Benchmark)
public class TimeTest {


    @Benchmark
    public long currentTimeMillis() {
       return System.currentTimeMillis();
    }

	@Benchmark
	public long nanoTime() {
		return System.nanoTime();
	}





    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(TimeTest.class.getSimpleName())
                .forks(1)
                .build();
        new Runner(opt).run();
    }
}
