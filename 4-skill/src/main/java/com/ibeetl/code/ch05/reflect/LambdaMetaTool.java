package com.ibeetl.code.ch05.reflect;

import java.lang.invoke.*;
import java.util.function.Function;
/**
 * jdk8的办法，性能很好
 * @author 公众号 java系统优化
 */
public class LambdaMetaTool implements ReflectTool {

  private final Function getterFunction;
  public LambdaMetaTool(){

    try{
      MethodHandles.Lookup lookup = MethodHandles.lookup();
      CallSite site = LambdaMetafactory.metafactory(lookup,
        "apply",
        MethodType.methodType(Function.class),
        MethodType.methodType(Object.class, Object.class),
        lookup.findVirtual(User.class, "getName", MethodType.methodType(String.class)),
        MethodType.methodType(String.class, User.class));
      getterFunction = (Function) ((CallSite) site).getTarget().invokeExact();
    }catch(Throwable ex){
      throw new IllegalArgumentException(ex);
    }


  }

  @Override
  public Object getValue(Object target, String attr) {
    return getterFunction.apply(target);
  }


  public static void  main(String[] args){
    LambdaMetaTool tool = new LambdaMetaTool();
    User user = new User();
    user.setName("abc");
    String value = (String)tool.getValue(user,"name");
    System.out.println(value);
  }
}
