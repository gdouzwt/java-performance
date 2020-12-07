package com.ibeetl.code.jit;

/**
 * 虚方法调用观察，可以使用{@code HellWorld} 例子的虚拟机参数
 * @author  公众号 java系统优化
 */
public class VTableTest {
  public static void main(String[] args) throws Exception{

  	Foo foo =  getFoo();
  	int total = 0;
  	for(int i=0;i<10000;i++){
		total+=foo.say();
	}

  }

  public static Foo getFoo(){
  	return new Bar();
  }


}
class Foo {
	int say() {
		return 1;
	}
}
class Bar extends Foo{
	int say(){
		return 2;
	}
	void bar() {
		System.out.println("bar");
	}
}
