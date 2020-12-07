package org.beetl.core.om.asm;

import java.beans.PropertyDescriptor;
import java.util.List;
import java.util.Map;

/**
 * asm生成的类描述
 * @author laozhaishaozuo@foxmail.com
 *
 */
class ClassDescription {

	Class<?> target = null;

	List<PropertyDescriptor> propertyDescriptors;

	// 0 ,1 get(Object),2 get(String)
	int generalGetType = 0;

	Map<Integer, List<FieldDescription>> fieldDescMap;

	boolean hasField = true;
}
