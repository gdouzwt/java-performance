/**
 * 
 */
package org.beetl.core.om.asm;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.beetl.core.misc.ByteClassLoader;
import org.beetl.core.om.AttributeAccess;

/**
 * @author laozhaishaozuo@foxmail.com
 *
 */
public class ASMBeanFactory {

    private final Map<Class<?>, AttributeAccess> beanMap = new ConcurrentHashMap<>();
    private final Map<ClassLoader, ByteClassLoader> classLoaders = new ConcurrentHashMap<>();

    ClassLoader classLoader = Thread.currentThread().getContextClassLoader() != null
            ? Thread.currentThread().getContextClassLoader()
            : ASMBeanFactory.class.getClassLoader();

    boolean usePropertyDescriptor;

    public Object value(Object bean, String attrName) {
        Class<?> beanClass = bean.getClass();
        AttributeAccess generatedBean = generateBean(beanClass);
        return generatedBean == null ? null : generatedBean.value(bean, attrName);
    }

    public boolean isUsePropertyDescriptor() {
        return usePropertyDescriptor;
    }

    public void setUsePropertyDescriptor(boolean usePropertyDescriptor) {
        this.usePropertyDescriptor = usePropertyDescriptor;
    }

    public synchronized AttributeAccess generateBean(Class<?> beanClass) {
        if (beanMap.containsKey(beanClass)) {
            return beanMap.get(beanClass);
        }
        try {
            ClassLoader beanClassLoader = beanClass.getClassLoader();
            ByteClassLoader byteLoader = classLoaders.get(beanClassLoader);
            if (byteLoader == null) {
                byteLoader = new ByteClassLoader(beanClassLoader);
                classLoaders.putIfAbsent(beanClassLoader, byteLoader);
            }
            byte[] code = EnhanceClassGenerator.generate(beanClass, this.usePropertyDescriptor);
            String generatedBeanName = EnhanceClassGenerator.createGeneratedClassName(beanClass);
            Class<?> enhanceClass = byteLoader.findClassByName(generatedBeanName);
            if (enhanceClass == null) {
                enhanceClass = byteLoader.defineClass(generatedBeanName, code);
                writeClassToFile(code, beanClass, generatedBeanName);
            }
            Object obj = enhanceClass.getDeclaredConstructor().newInstance();
            beanMap.put(beanClass, (AttributeAccess) obj);
            return beanMap.get(beanClass);

        } catch (Exception e) {
            throw new IllegalStateException(e);
        }

    }

    private static void writeClassToFile(final byte[] code, final Class<?> beanClass,
            final String generatedBeanName) {
        try {
            String classPath = beanClass.getResource("").getPath();
            // 将二进制流写到本地磁盘上
            File file = new File(classPath,
                    BeanEnhanceUtils.getSimpleClassName(generatedBeanName) + ".class");
            System.out.println(file.getAbsolutePath());
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(code);
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
