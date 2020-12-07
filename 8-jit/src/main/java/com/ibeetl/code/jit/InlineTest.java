package com.ibeetl.code.jit;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 内联和不内联的区别
 * @author  公众号 java系统优化
 */
@BenchmarkMode(Mode.AverageTime)
@Warmup(iterations = 10)
@Measurement(iterations = 10, time = 1, timeUnit = TimeUnit.SECONDS)
@Threads(1)
@Fork(1)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Benchmark)
public class InlineTest {

	int x=0,y=0;
    @Benchmark
	@CompilerControl(CompilerControl.Mode.DONT_INLINE)
    public  int   add(){
        return dataAdd(x,y);
    }

    @Benchmark
	@CompilerControl(CompilerControl.Mode.PRINT)
    public  int  addInline(){
		return dataAdd(x,y);
    }

    private int  dataAdd(int x,int y){
    	return x+y;
	}







    @Setup
	public void init(){
		x =1;
		y= 2;
	}

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(InlineTest.class.getSimpleName())
                .build();
        new Runner(opt).run();
    }
}
