package org.beetl.core.om.asm;

class FieldDescription {
	String name;
	/**
	 * The field's descriptor (see {@link org.objectweb.asm.Type}).
	 */
	String desc;
	/**
	 * getXXX
	 */
	String readMethodName;
	/**
	 * 类似()I,(I)Ljava/lang/Integer;
	 * @see {@link org.objectweb.asm.Type}
	 */
	String readMethodDesc;

	public FieldDescription() {
	}

	public FieldDescription(String name, String desc, String readMethodName, String readMethodDesc) {
		super();
		this.name = name;
		this.desc = desc;
		this.readMethodName = readMethodName;
		this.readMethodDesc = readMethodDesc;
	}


}
