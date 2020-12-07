package com.ibeetl.code.ch03;

/**
 * 线程死锁,运行后观察线程状态，运行后，通过jvisualvm观察
 * @author xiandafu ,公众号 java系统优化
 */
public class ThreadDeadTest {

	static Object lock1 = new Object();
	static Object lock2 = new Object();

	public static void main(String[] args) {

		TestDeadLock td1 = new TestDeadLock("task-1",lock1,lock2);
		TestDeadLock td2 = new TestDeadLock("task-2",lock2,lock1);
		td1.start();;
		td2.start();
	}


}


class TestDeadLock extends Thread {
	private Object lockA;
	private Object lockB;

	public TestDeadLock(String name,Object lockA,Object lockB) {
		super(name);
		this.lockA = lockA;
		this.lockB = lockB;
	}

	public void run() {
		synchronized (lockA) {
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
				e.printStackTrace();
			}
			synchronized (lockB) {
				System.out.println("1");
			}
		}
	}

}




