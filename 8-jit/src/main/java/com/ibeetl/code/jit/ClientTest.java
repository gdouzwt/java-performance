package com.ibeetl.code.jit;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

/**
 * 解释执行和编译执行的区别
 * @author  公众号 java系统优化
 */
@BenchmarkMode(Mode.AverageTime)
@Warmup(iterations = 10)
@Measurement(iterations = 10, time = 1, timeUnit = TimeUnit.SECONDS)
@Threads(1)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Benchmark)
public class ClientTest {

	int x=0,y=0;

	/**
	 * 解释执行
	 * @return
	 */
    @Benchmark
	@Fork(value=1,jvmArgsAppend="-Xint")
	@CompilerControl(CompilerControl.Mode.DONT_INLINE)
    public  int  bytecode(){
		return x+y;
    }


	/**
	 * 没有-Xint,都是编译执行
	 * @return
	 */
	@Benchmark
	@Fork(value=1)
	public  int  machinecode(){
		return x+y;
	}

    private int add(int x,int y){
    	return x+y;
	}

    @Setup
	public void init(){
		x =1;
		y= 2;
	}

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(ClientTest.class.getSimpleName())
                .build();
        new Runner(opt).run();
    }
}
