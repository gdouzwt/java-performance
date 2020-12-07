package com.ibeetl.code.ch03;

public class SynchronizedExample {

   Object lock = new Object();

  public static synchronized void getStatus(){

  }

  public   void updateStatus(){
    synchronized(lock){

    }
  }


  public synchronized void queryStatus(){

  }

}
