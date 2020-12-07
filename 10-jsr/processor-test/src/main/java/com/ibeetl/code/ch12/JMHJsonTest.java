package com.ibeetl.code.ch12;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@Warmup(iterations = 10)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Threads(1)
@Fork(1)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Benchmark)
public class JMHJsonTest {

	ObjectMapper jacksonMapper = new ObjectMapper();
	UserJsonMapper jsonMapper = MapperTools.getMapper(UserJsonMapper.class);
	User user = new User("张三", "大厅123");

	@Benchmark
	public String jsr269(){
		String value =  jsonMapper.write(user);
		return value;
	}



	@Benchmark
	public String jackson() throws Exception{
		String value = jacksonMapper.writeValueAsString(user);
		return value;
	}


	public static void main(String[] args) throws RunnerException {
		Options opt = new OptionsBuilder()
		  .include(JMHJsonTest.class.getSimpleName())
		  .forks(1)
		  .build();
		new Runner(opt).run();
	}
}

