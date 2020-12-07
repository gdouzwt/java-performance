package com.ibeetl.code.ch03;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch使用例子
 * @author 公众号 java系统优化
 */
public class Server {
	enum Status{NOMARL,ERROR}
	ConcurrentHashMap<String,Status> status = new ConcurrentHashMap<>();
	CountDownLatch latch;
	public Server(){

	}

	public void start() throws Exception{

		String[] urls = new String[]{"192.168.1.13","192.168.1.14"};
		check(urls);

	}
	public void check(String[] urls) throws Exception{
		latch = new CountDownLatch(urls.length);
		for(String url:urls){
			Thread t = new Thread(new ServiceCheck(this,url),"check-"+url);
			t.start();
		}
		latch.await();
		System.out.println(status);

	}

	public static void main(String[] args) throws Exception{
		Server server = new Server();
		server.start();

	}

	class ServiceCheck implements  Runnable{

		String url = null;
		Server server;
		public ServiceCheck(Server server,String url){
			this.server = server;
			this.url = url;
		}

		@Override
		public void run() {
			check();
			server.latch.countDown();
		}
		private void check(){
			try{
				//模拟检测
				Thread.sleep(100);
				server.status.put(url,Status.NOMARL);
				System.out.println("check "+url);
			}catch(Exception ex){
				ex.printStackTrace();
			}

		}
	}

}
