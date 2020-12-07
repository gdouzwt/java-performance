package com.ibeetl.code.ch03;

/**
 * 模拟百万分之一情况下出现的重排序可能
 * @author 公众号 java系统优化
 */
public class ResortTest {

   int x=0,y=0;
   int a=0,b=0;

  int index = 0;
  public ResortTest(int index){
    this.index = index;
  }
  public void run() throws Exception{
      Thread threadA = new Thread(){
        public void run(){
          a=1;
          x=b;
        }
      };
      Thread threadB = new Thread(){
        public void run(){
          b=1;
          y=a;
        }
      };
      threadA.start();
      threadB.start();
      threadA.join();
      threadB.join();
      if(x==0&&y==0){
      	//重排序会出现这种情况
        System.out.println(index+"="+x+""+y);
      }

  }

  public static void main(String[] args) throws Exception{
    for(int i=0;i<1000000;i++){
      new ResortTest(i).run();
    }


  }

}
