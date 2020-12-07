package com.ibeetl.code.ch03;

import com.ibeetl.code.ch03.pool.PoolManager;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
/**
 * 一个简单使用实例，必须注意，线程池是宝贵资源，必须统一管理（或者像spring那样统一配置）
 * @author 公众号 java系统优化
 */
public class FutureTaskTest2 {
	public static void main(String[] args) throws Exception {
		int a = 10;
		int b = 22;
		int c = 3;
		int d = 9;
		//求和a+b+c+d,可以分成俩个FutureTask，分别通过来个线程计算a+b和c+d,执行完毕后再求和
		Future<Integer>[] futures = new Future[2];

		ThreadPoolExecutor poolExecutor = PoolManager.instance().getQueryPool().getCustomThreadPoolExecutor();
		futures[0] = poolExecutor.submit(new Sum(a, b));
		futures[1] = poolExecutor.submit(new Sum(c, d));

		int total = 0;
		for (Future<Integer> task : futures) {
			total = total + task.get();
		}

		System.out.println(total);


	}

	static class Sum implements Callable<Integer> {
		private int x;
		private int y;

		public Sum(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public Integer call() throws Exception {
			return x + y;
		}

	}
}
