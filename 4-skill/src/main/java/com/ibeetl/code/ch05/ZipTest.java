package com.ibeetl.code.ch05;

import com.ibeetl.code.ch05.zip.Content;
import com.ibeetl.code.ch05.zip.ZipUtil;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * 压缩耗时测试。需要注意，压缩性能很好，但耗CPU。
 *
 * apache compress 提供更多的压缩，但性能上大部分差不多。
 *
 * @author 公众号 java系统优化
 */
@BenchmarkMode(Mode.AverageTime)
@Warmup(iterations = 10)
@Measurement(iterations = 10, time = 1, timeUnit = TimeUnit.SECONDS)
@Threads(1)
@Fork(1)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)
public class ZipTest {
	byte[] k5 = null;
	byte[] k20 = null;
	byte[] k100 = null;
	//默认，最快，最好压缩
	@Param({"-1", "1", "9"})
	int level;

	public static void main(String[] args) throws RunnerException {
		Options opt = new OptionsBuilder().include(ZipTest.class.getSimpleName()).forks(1).build();
		new Runner(opt).run();
	}

	@Benchmark
	public byte[] k5() throws IOException {
		return ZipUtil.compress(k5, level);
	}

	@Benchmark
	public byte[] k20() throws IOException {
		return ZipUtil.compress(k20, level);
	}

	@Benchmark
	public byte[] k100() throws IOException {
		return ZipUtil.compress(k100, level);
	}

	@Setup
	public void init() {
		Content content = new Content();
		this.k5 = content.genContentBySize(1000 * 5);
		this.k20 = content.genContentBySize(1000 * 20);
		this.k100 = content.genContentBySize(1000 * 100);
	}
}
