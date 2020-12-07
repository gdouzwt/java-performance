package com.ibeetl.com.ch09;

import org.beetl.core.om.AttributeAccess;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class ReflectAttributeAcces extends AttributeAccess {
  Map<String, Method> cache = new HashMap<>();
  public ReflectAttributeAcces(Class c) throws Exception{
    BeanInfo beanInfo = Introspector.getBeanInfo(c);
    PropertyDescriptor[] propDescriptors = beanInfo.getPropertyDescriptors();

    for (PropertyDescriptor propertyDescriptor: propDescriptors) {
      cache.put(propertyDescriptor.getName(),propertyDescriptor.getReadMethod());
    }
  }


  @Override
  public Object value(Object o, Object name) {
    try{
      Method m = cache.get(name);
      return m.invoke(o);
    }catch(Exception ex){
      throw new IllegalArgumentException(name.toString(),ex);
    }
  }
}
