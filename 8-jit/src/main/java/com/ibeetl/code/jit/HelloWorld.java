package com.ibeetl.code.jit;

/**
 * 加入如下虚拟机启动参数，可以看到编译过程以及igetMesssage 方法和sayHello内联，
 * -server -XX:+PrintCodeCache -XX:+PrintFlagsFinal -XX:+UnlockDiagnosticVMOptions -XX:+PrintCompilation -XX:+LogCompilation -XX:+PrintInlining
 *
 * @author  公众号 java系统优化
 */
public class HelloWorld {

  public static void main(String[] args) throws Exception{
    HelloWorld say = new HelloWorld();
    for(int i=0;i<1000000;i++){
      say.sayHello();

    }

  }
  public void sayHello(){
    String msg = getMessage();
    String output = "hello"+msg;
  }
  public synchronized  String getMessage() {
    return "world";
  }
}
