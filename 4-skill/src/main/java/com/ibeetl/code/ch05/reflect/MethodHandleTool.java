package com.ibeetl.code.ch05.reflect;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

/** jdk7的办法，貌似不行啊
 * @author 公众号 java系统优化
 */
public class MethodHandleTool implements  ReflectTool {

  MethodHandle handle = null;
  public MethodHandleTool(){
    //模拟缓存
    handle = getMethodHandler(User.class,"name");
  }

  @Override
  public Object getValue(Object target, String attr) {
//    MethodHandle handle = getMethodHandler(target.getClass(),attr);
    try{
      Object value = handle.invokeExact(target);
      return value;
    }catch(Throwable ex){
      throw new IllegalArgumentException(ex);
    }

  }


  public  MethodHandle getMethodHandler(Class target,String attr) {
    //定义方法的返回值和入参
    MethodType mt = MethodType.methodType(String.class);
    String methodName = buildGetterName(attr);
    MethodHandle mh = null;
    try {
      //查找方法句柄
      MethodHandle orginalMh = MethodHandles.lookup().findVirtual(target, methodName, mt);
      //适配，mh调用的输出输出都是Object
      mh =  orginalMh.asType(MethodType.methodType(Object.class, Object.class));
    } catch (NoSuchMethodException | IllegalAccessException e) {
      throw new IllegalArgumentException(e);
    }

    return mh;
  }

  private String buildGetterName(String attr){
    //根据属性名，得到getter方法
    return "get"+Character.toUpperCase(attr.charAt(0))+attr.substring(1);
  }

  public static void  main(String[] args){
    MethodHandleTool tool = new MethodHandleTool();
    User user = new User();
    user.setName("abc");
    String value = (String)tool.getValue(user,"name");
    System.out.println(value);
  }

}
