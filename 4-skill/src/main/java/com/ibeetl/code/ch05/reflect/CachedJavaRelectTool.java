package com.ibeetl.code.ch05.reflect;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 同{@code JavaRelectTool}，缓存了Method
 * @author 公众号 java系统优化
 */
public class CachedJavaRelectTool implements ReflectTool {

	Object[] EMPTY_PARA = new Object[]{};
	Class[] EMPTY_CLASS = new Class[0];
	Map<Class, Map<String, Method>> caches = new ConcurrentHashMap<>();

	public static void main(String[] args) {
		CachedJavaRelectTool tool = new CachedJavaRelectTool();
		User user = new User();
		user.setName("abc");
		String value = (String) tool.getValue(user, "name");
		System.out.println(value);
	}

	@Override
	public Object getValue(Object target, String attr) {
		Method method = getCachedMethod(target.getClass(), attr);
		try {
			Object value = method.invoke(target, EMPTY_PARA);
			return value;
		} catch (Exception ex) {
			throw new IllegalArgumentException(ex);
		}

	}

	private Method getCachedMethod(Class target, String name) {
		Map<String, Method> calssDefMap = caches.get(target);
		if (calssDefMap == null) {
			calssDefMap = new ConcurrentHashMap<String, Method>();
			Map<String, Method> old = caches.putIfAbsent(target, calssDefMap);

		}
		Method method = getMethod(target, name, calssDefMap);
		return method;

	}

	private Method getMethod(Class targetClass, String attr, Map<String, Method> calssDefMap) {
		Method m = calssDefMap.get(attr);
		if (m != null) {
			return m;
		}

		String methodName = buildGetterName(attr);
		try {
			Method method = targetClass.getMethod(methodName, EMPTY_CLASS);
			calssDefMap.putIfAbsent(attr, method);
			return method;
		} catch (Exception ex) {
			throw new IllegalArgumentException(ex);
		}

	}

	private String buildGetterName(String attr) {
		//根据属性名，得到getter方法
		return "get" + Character.toUpperCase(attr.charAt(0)) + attr.substring(1);
	}
}
