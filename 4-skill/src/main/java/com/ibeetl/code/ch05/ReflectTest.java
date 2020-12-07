package com.ibeetl.code.ch05;

import com.ibeetl.code.ch05.reflect.*;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;


/**
 * 反射调用性能对比，很多java框架都会用到反射，反射性能高低影响了框架性能
 * @author 公众号 java系统优化
 */
@BenchmarkMode(Mode.AverageTime)
@Warmup(iterations = 10)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Threads(1)
@Fork(1)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Benchmark)
public class ReflectTest {
	ReflectTool direct = new UserDirectAccessTool();
	ReflectTool reflect = new CachedJavaRelectTool();
	ReflectTool methodHandle = new MethodHandleTool();
	LambdaMetaTool lambda = new LambdaMetaTool();
	User user = null;
	String attr= "name";

    @Benchmark
    public String direct() {
      return (String)direct.getValue(user,attr);
    }

	@Benchmark
	public String reflect() {
		return (String)reflect.getValue(user,attr);
	}

	@Benchmark
	public String methodHandle() {
		return (String)methodHandle.getValue(user,attr);
	}
	@Benchmark
	public String lambda() {
		return (String)lambda.getValue(user,attr);
	}




    @Setup
    public void init(){
        user = new User();
        user.setName("age");


    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(ReflectTest.class.getSimpleName())
                .forks(1)
                .build();
        new Runner(opt).run();
    }
}
