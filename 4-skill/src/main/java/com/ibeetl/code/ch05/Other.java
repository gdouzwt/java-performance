package com.ibeetl.code.ch05;

import java.util.Random;
/**
 * 非性能相关，验证随机式不随机
 * @author 公众号 java系统优化
 */
public class Other {
	public void test(){
		String name1 ="hi";
		String name2 = "world";
		info("hello {},{}",name1,name2);
	}
	public void info(String format,Object... args){

	}

	public static void  main(String[] args){
		bit();
	}

	public static void bit(){
		int  a = 111;
		System.out.println((a & 1)==1);

		System.out.println(a>>1);
		System.out.println(a<<1);
	}

	public static void random(){
		int seed = 100;
		int range = 50;
		Random random = new Random(System.currentTimeMillis());
		int a1 = random.nextInt(range);
		int a2 = random.nextInt(range);
		random = new Random(seed);
		int b1 = random.nextInt(range);
		int b2 = random.nextInt(range);
		random = new Random(seed);
		int c1 = random.nextInt(range);
		int c2 = random.nextInt(range);
		System.out.println("a "+a1+","+a2);
		System.out.println("b "+b1+","+b2);
		System.out.println("c "+c1+","+c2);
	}
}
