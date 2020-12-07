package com.ibeetl.code.ch05.reflect;

import java.lang.reflect.Method;
/**
 * 常规方法，反射，但随时虚拟机的优化，反射性能越来越好
 * @author 公众号 java系统优化
 */
public class JavaRelectTool implements  ReflectTool {

  Object[] EMPTY_PARA = new Object[]{};
  Class[] EMPTY_CLASS = new Class[0];
  @Override
  public Object getValue(Object target, String attr) {
    String methodName = buildGetterName(attr);
    try{
      Class targetClass = target.getClass();
      Method method = targetClass.getMethod(methodName,EMPTY_CLASS);
      Object value = method.invoke(target,EMPTY_PARA);
      return value;
    }catch(Exception ex){
      throw new IllegalArgumentException(ex);
    }

  }

  private String buildGetterName(String attr){
    //根据属性名，得到getter方法
    return "get"+Character.toUpperCase(attr.charAt(0))+attr.substring(1);
  }

  public static void  main(String[] args){
    JavaRelectTool tool = new JavaRelectTool();
    User user = new User();
    user.setName("abc");
    String value = (String)tool.getValue(user,"name");
    System.out.println(value);
  }
}
