package com.ibeetl.com.ch09;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.beetl.core.om.AttributeAccess;
import org.beetl.core.om.asm.ASMBeanFactory;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
@Warmup(iterations = 10,time = 1)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Fork(1)
@State(Scope.Benchmark) // 每个测试线程一个实例
public class BeanValueBenchmark {

    private User user = new User();
    private static String[] testNames = null;
    private static Set<String> ignoreFieldNames = new HashSet<>();
    private static Set<String> testignoreFieldNames = new HashSet<>();


	AttributeAccess asmUserAccess = null;


	ReflectAttributeAcces reflectAttributeAcces = null;

    static {
    	//用于测试所有字段
        ignoreFieldNames.add("serialVersionUID");
        Field[] fields = User.class.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            if (!ignoreFieldNames.contains(fields[i].getName())) {
                testignoreFieldNames.add(fields[i].getName());
            }
        }
        testNames = testignoreFieldNames.toArray(new String[1]);
    }

    @Setup
    public void setup()  throws Exception{
		//准备好数据
		genASM();
		genReflect();
		user();

    }

    private void genASM(){

		ASMBeanFactory asmBeanFactory = new ASMBeanFactory();
		asmBeanFactory.setUsePropertyDescriptor(true);
		//动态生成一个类
		asmUserAccess =  asmBeanFactory.generateBean(User.class);
	}

	private void genReflect() throws Exception{
		reflectAttributeAcces = new ReflectAttributeAcces(User.class);
	}

	private void user(){
		user.setName("shaozuo");
		user.setAddress("北京");
		user.setNumbers(15);
		user.setBirthDate(new Date());
		user.setAge((short) 12);
		user.setDistance(44L);
		user.setFlag((byte) 1);
		user.setManager(false);
		user.setHeight(1.73F);
		user.setGender('M');
	}



    @Benchmark
    public Object  byAsm() {
		return asmUserAccess.value(user, "name");

    }

	@Benchmark
	public Object  direct() {
		return user.getName();
	}


	@Benchmark
    public Object byRelectBeans() {
       return  reflectAttributeAcces.value(user, "name");
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder().include(BeanValueBenchmark.class.getSimpleName()).build();
        new Runner(opt).run();
    }
}
