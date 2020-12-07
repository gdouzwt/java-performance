package com.ibeetl.code.ch05.zip;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;
import java.util.zip.ZipOutputStream;

import static java.util.zip.Deflater.DEFAULT_COMPRESSION;
import static java.util.zip.Deflater.DEFLATED;

public class ZipUtil {

	//避免每次都分配一个缓冲
	static ThreadLocal<byte[]> local = new ThreadLocal<byte[]>(){
		protected byte[] initialValue(){
			return  new byte[1024];
		}
	};

	public static byte[] zip(byte[] bs) throws IOException {
		return compress(bs,DEFAULT_COMPRESSION);
	}

	public static byte[] compress(byte[] input, int compressionLevel
			) throws IOException {
		Deflater compressor = new Deflater(compressionLevel, false);
		compressor.setInput(input);
		compressor.finish();
		ByteArrayOutputStream bao = new ByteArrayOutputStream();
		byte[] readBuffer = local.get();
		int readCount = 0;
		while (!compressor.finished()) {
			readCount = compressor.deflate(readBuffer);
			if (readCount > 0) {
				bao.write(readBuffer, 0, readCount);
			}
		}

		compressor.end();
		return bao.toByteArray();
	}

	public static byte[] decompress(byte[] input)
			throws IOException, DataFormatException {
		Inflater decompressor = new Inflater(false);
		decompressor.setInput(input);
		ByteArrayOutputStream bao = new ByteArrayOutputStream();
		byte[] readBuffer = local.get();;
		int readCount = 0;

		while (!decompressor.finished()) {
			readCount = decompressor.inflate(readBuffer);
			if (readCount > 0) {
				bao.write(readBuffer, 0, readCount);
			}
		}
		decompressor.end();
		return bao.toByteArray();
	}


	public static void main(String[] args)throws IOException{
		Content content = new Content();
		byte[] bs = content.genContentBySize(1000*5);
		byte[] zip = ZipUtil.zip(bs);
		System.out.println(zip.length);
		bs = content.genContentBySize(1000*20);
		zip = ZipUtil.zip(bs);
		System.out.println(zip.length);

		bs = content.genContentBySize(1000*100);
		zip = ZipUtil.compress(bs,-1);
		System.out.println(zip.length);

		zip = ZipUtil.compress(bs,1);
		System.out.println(zip.length);

		zip = ZipUtil.compress(bs,9);
		System.out.println(zip.length);
	}
}
