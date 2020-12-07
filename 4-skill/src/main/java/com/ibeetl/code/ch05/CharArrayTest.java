package com.ibeetl.code.ch05;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;
/**
 * local变量对性能提升，如果local提升不明显，有可能是CPU缓存导致的，在CPU缓存面前，堆变量，栈变量性能都是渣渣
 * @author xiandafu@126.com ,公众号 java系统优化
 */
@BenchmarkMode(Mode.Throughput)
@Warmup(iterations = 5)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Threads(1)
@Fork(1)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)
public class CharArrayTest {
	char[] array = new char[0];


    @Benchmark
    public int testGetField() {
        int count = 0;
        int len =  array.length;
        for (int i = 0; i < len; i++) {
            if(array[i]=='t'){
            	count++;
			}
        }
        return count;
    }




    @Benchmark
    public int testLocalVariable() {
        int count = 0;
        char[] locaArray = array;
        int len =  locaArray.length;
        for (int i = 0; i < len; i++) {
			if(locaArray[i]=='t'){
				count++;
			}
        }
        return count;

    }

    @Setup
    public void init(){
        String str = "hello,this is effective java code";
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        this.array = sb.toString().toCharArray();



    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(CharArrayTest.class.getSimpleName())
                .forks(1)
                .build();
        new Runner(opt).run();
    }
}
