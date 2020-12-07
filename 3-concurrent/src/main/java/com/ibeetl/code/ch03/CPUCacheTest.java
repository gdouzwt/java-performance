package com.ibeetl.code.ch03;

/**
 * 一个经典的多线程编程陷阱，由于stop可能从cpu缓存里去读，主线程通过设置stop并不能停止线程B
 * 可以使用volatile保证可见性，也可以试试14行，15行的代码,也能起到"神奇"效果
 * @author 公众号 java系统优化
 */
public class CPUCacheTest {
	private static /* volatile */ boolean stop = false;
	public static void main(String[] args){
		Thread a = new Thread("B"){
		  public void run(){
			  while (!stop) {
				  //		  	pause(1);
				 // System.out.println(1);
				  int a = 1;
			}
			System.out.println("exit");
		  }
		};
    	a.start();
		pause(100);
		stop = true;

  }
  public static void pause(int time){
    try {
      Thread.sleep(time);
    }catch(Exception ex){
    }

  }

}
