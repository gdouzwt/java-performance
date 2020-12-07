package com.ibeetl.code.ch05.zip;

public class Order {
	/**
	 * 0位表示是否测试，1-4位节表示用户状态，5-8位表示订单状态
	 */
	int status;

	public boolean isTest(){
		//取出第1位的值
		return (status&0b1)==1;
	}

	public int getUserStatus(){
		// 右移1，取出1-4位的值
		return (status>>1&0b1111);
	}

	public int getOrderStatus(){
		//右移5，取出5-8位的值
		return (status>>5&0b1111);
	}


	public boolean isLargeProudct(){
		return (status>>9&0b1)==1;
	}

	public static void  main(String[] args){
		Order order = new Order();
		order.status = 0b1_0100_0110_1;
		System.out.println(order.status );

		System.out.println(order.isTest());
		System.out.println(order.getUserStatus());
		System.out.println(order.getOrderStatus());
		System.out.println(order.isLargeProudct());
	}
}
