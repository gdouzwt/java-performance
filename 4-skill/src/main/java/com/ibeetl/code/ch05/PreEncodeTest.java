package com.ibeetl.code.ch05;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * 预先编码提升性能，微服务里，这种调用还是非常耗时
 * @author 公众号 java系统优化
 */
@BenchmarkMode(Mode.AverageTime)
@Warmup(iterations = 10)
@Measurement(iterations = 10, time = 1, timeUnit = TimeUnit.SECONDS)
@Threads(1)
@Fork(1)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Benchmark)
public class PreEncodeTest {
	String name= "XDF0001";


    @Benchmark
    public byte[] getByte() throws Exception {
       StringBuilder sb = new StringBuilder();
       sb.append("<product>").append(name).append("</product>");
       return sb.toString().getBytes("UTF-8");
    }

	@Benchmark
	public byte[] getByteByPreEncode() throws Exception {
		return ProudctXML.getBytes(name);
	}

	static class ProudctXML{
    	static byte[] productStart = getBytes("<product>");
		static byte[] productEnd = getBytes("</product>");

		static byte[]  getBytes(String content){
    		try{
				return content.getBytes("UTF-8");
			}catch (Exception e){
    			throw new IllegalStateException(e);
			}

		}

		public static byte[] getXML(String productName) throws IOException {
			ByteArrayOutputStream os = new  ByteArrayOutputStream();
			os.write(productStart);
			os.write(productName.getBytes("UTF-8"));
			os.write(productEnd);
			return os.toByteArray();
		}



	}




    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(PreEncodeTest.class.getSimpleName())
                .forks(1)
                .build();
        new Runner(opt).run();
    }
}
