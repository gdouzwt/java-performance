package com.ibeetl.code.ch05.messagePack;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.msgpack.MessagePack;
import org.msgpack.core.MessageBufferPacker;
import org.msgpack.core.MessagePacker;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * MessagePack vs Jackson
 * @author 公众号 java系统优化
 */
@BenchmarkMode(Mode.AverageTime)
@Warmup(iterations = 10)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Threads(1)
@Fork(1)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Benchmark)
public class PackTest {


	Product p = null;
	MessagePack pack = new MessagePack();
	ObjectMapper mapper = new ObjectMapper();


	@Benchmark
	public byte[] packObject() throws IOException {
		return pack.write(p);
	}

	@Benchmark
	public byte[] corePackProudct() throws IOException {
		return corePackProudct(p);
	}
	@Benchmark
	public byte[] jackson() throws Exception {
		String json =  mapper.writeValueAsString(p);
		return json.getBytes("UTF-8");
	}

	private byte[] corePackProudct(Product p) throws IOException{

		MessageBufferPacker packer = org.msgpack.core.MessagePack.newDefaultBufferPacker();
		if(p.getDel()!=null){
			packer.packBoolean(p.getDel());
		}else{
			packer.packNil();
		}

		if(p.getCreateTime()!=null){
			packer.packLong(p.getCreateTime().getTime());
		}else{
			packer.packNil();
		}

		if(p.getDescription()!=null){
			packer.packString(p.getDescription());
		}else{
			packer.packNil();
		}

		if(p.getId()!=null){
			packer.packInt(p.getId());
		}else{
			packer.packNil();
		}

		if(p.getName()!=null){
			packer.packString(p.getName());
		}else{
			packer.packNil();
		}

		if(p.getProductStatus()!=null){
			packer.packBoolean(p.getProductStatus());
		}else{
			packer.packNil();
		}

		if(p.getUpdateTime()!=null){
			packer.packLong(p.getUpdateTime().getTime());
		}else{
			packer.packNil();
		}

		packer.close();
		return packer.toByteArray();
	}

	@Setup
	public void init() throws Exception {
		p = new Product();
		p.setDel(true);
		p.setCreateTime(new Date());
		p.setDescription("product");
		p.setId(1);
		p.setName("雨伞");
		p.setPrice(32.1);
		p.setProductStatus(true);
		p.setUpdateTime(null);

	}
	public static void main(String[] args) throws RunnerException {
		Options opt = new OptionsBuilder().include(PackTest.class.getSimpleName()).forks(1).build();
		new Runner(opt).run();
	}
}

