/**
 * 
 */
package com.ibeetl.com.ch09;

import org.beetl.core.om.asm.ASMBeanFactory;

/**
 *
 */
public class GetValueByAsm {
	private GetValueByAsm() {

	}

	private static final ASMBeanFactory asmBeanFactory;
	static {
		asmBeanFactory = new ASMBeanFactory();
		asmBeanFactory.setUsePropertyDescriptor(true);
	}

	public static Object value(Object bean, String attrName) {
		return asmBeanFactory.value(bean, attrName);
	}

}
