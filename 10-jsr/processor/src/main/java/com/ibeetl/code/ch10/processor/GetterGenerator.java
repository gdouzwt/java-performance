package com.ibeetl.code.ch10.processor;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;

import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldInsnNode;
import org.objectweb.asm.tree.FieldNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.VarInsnNode;

/**
 * jdk1.8可以运行，jdk11报错
 * 
 * @author zhangshimin
 *
 */
public class GetterGenerator implements Opcodes {

	private ProcessingEnvironment processingEnv;
	private ClassLoader classLoader = getClass().getClassLoader();
	protected final HashSet<String> _createdFiles;
	private final Messager messager;

	public GetterGenerator(ProcessingEnvironment processingEnv) {
		this.processingEnv = processingEnv;
		messager = processingEnv.getMessager();
		_createdFiles = new HashSet<>();

	}

	public void generate(TypeElement typeElement) throws IOException {
		String clazzQualifiedName = typeElement.getQualifiedName().toString();
		String clazzInternalName = ProcessorUtils.getInternalName(clazzQualifiedName);
		if (_createdFiles.contains(clazzQualifiedName)) {
			return;
		}
		_createdFiles.add(clazzQualifiedName);
		messager.printMessage(Diagnostic.Kind.NOTE, clazzQualifiedName);
		ClassReader reader = new ClassReader(classLoader.getResourceAsStream(clazzInternalName + ".class"));
		ClassNode cn = new ClassNode();
		reader.accept(cn, 0);
		addMethod(clazzInternalName, cn);
		ClassWriter cw = new ClassWriter(Opcodes.ASM5);
		cn.accept(cw);

		JavaFileObject builderFile = processingEnv.getFiler().createClassFile(clazzQualifiedName, typeElement);
		OutputStream fout = builderFile.openOutputStream();
		fout.write(cw.toByteArray());
		fout.close();

	}

	private void writeBy(String clazzInternalName, ClassWriter cw) throws IOException {
		Files.createDirectories(Paths.get("com\\ibeetl\\com\\ch12\\"));
		File file = new File(clazzInternalName + ".class");
		FileOutputStream fout = new FileOutputStream(file);
		try {
			fout.write(cw.toByteArray());
			fout.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public void addMethod(final String clazzInternalName, ClassNode cn) {
		List<FieldNode> fields = cn.fields;
		if (fields != null && !fields.isEmpty()) {
			int fieldSize = fields.size();
			FieldNode fieldNode = null;
			for (int i = 0; i < fieldSize; i++) {
				fieldNode = fields.get(i);
				MethodNode mn = new MethodNode(ACC_PUBLIC, ProcessorUtils.getGetterMethodName(fieldNode),
						"()" + fieldNode.desc, null, null);
				InsnList il = mn.instructions;
				il.add(new VarInsnNode(ILOAD, 0));
				il.add(new FieldInsnNode(GETFIELD, clazzInternalName, fieldNode.name, fieldNode.desc));
				il.add(new InsnNode(ARETURN));
				mn.maxStack = 1;
				mn.maxLocals = 1;
				cn.methods.add(mn);
			}
		} else {
			messager.printMessage(Diagnostic.Kind.WARNING, "fields is null");
		}
	}


}
