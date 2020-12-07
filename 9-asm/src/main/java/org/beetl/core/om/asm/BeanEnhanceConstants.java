package org.beetl.core.om.asm;

import org.beetl.core.om.AttributeAccess;

/**
 * @author laozhaishaozuo@foxmail.com
 *
 */
final class BeanEnhanceConstants {

	private BeanEnhanceConstants() {

	}

	static final String METHOD_VALUE_OF = "valueOf";

	static final String OBJECT_INTERNAL_NAME = "java/lang/Object";

	static final String STRING_INTERNAL_NAME = "java/lang/String";

	static final String BEETL_EXCEPTION_INTERNAL_NAME = "org/beetl/core/exception/BeetlException";

	static final String STRING_BUILDER_INTERNAL_NAME = "java/lang/StringBuilder";

	static final String TO_STRING_METHOD_NAME = "toString";

	static final String TO_STRING_METHOD_DESCRIPTOR = "()Ljava/lang/String;";

	static final String METHOD_TO_GENERATE = "value";

	static final String METHOD_SIGNATURE = "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;";

	static final String CLASS_NAME = AttributeAccess.class.getName();

	static final String SUPER_CLASS_NAME = CLASS_NAME.replace('.', '/');

	static final String GENETRATED_CLASS_SUFFIX = AttributeAccess.class.getSimpleName();

	static final String GET_METHOD_NAME = "get";

	static final String GET_METHOD_DESC = "(Ljava/lang/Object;)Ljava/lang/Object;";

	static final String GET_BY_STRING_METHOD_DESC = "(Ljava/lang/String;)Ljava/lang/Object;";

	/**
	 * 表示方法：Object get(Object)
	 */
	static final String GET_METHOD_NAME_DESC = GET_METHOD_NAME + GET_METHOD_DESC;

	/**
	 * 表示方法：Object get(String)
	 */
	static final String GET_BY_STRING_METHOD_NAME_DESC = GET_METHOD_NAME + GET_BY_STRING_METHOD_DESC;


}
