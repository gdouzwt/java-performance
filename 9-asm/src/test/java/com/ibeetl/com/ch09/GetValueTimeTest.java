package com.ibeetl.com.ch09;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.time.StopWatch;
import org.junit.Before;
import org.junit.Test;

public class GetValueTimeTest {

    private static final int TEST_COUNT = 10000;
    private User user = new User();
    private static String[] testNames = null;
    private static Set<String> ignoreFieldNames = new HashSet<>();
    private static Set<String> testignoreFieldNames = new HashSet<>();

    static {
        ignoreFieldNames.add("serialVersionUID");
        Field[] fields = User.class.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            if (!ignoreFieldNames.contains(fields[i].getName())) {
                testignoreFieldNames.add(fields[i].getName());
            }
        }
        testNames = testignoreFieldNames.toArray(new String[1]);
    }

    @Before
    public void setup() {
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

    @Test
    public void testByReflect() {
        StopWatch watch = new StopWatch();
        watch.start();
        for (int j = 0; j < TEST_COUNT; j++) {

            for (int i = 0; i < testNames.length; i++) {
                GetValueByReflect.value(user, testNames[i]);
            }
        }
        watch.stop();
        System.out.println("reflect:" + watch.getNanoTime());
    }

    @Test
    public void testByAsm() {
        StopWatch watch;

        watch = new StopWatch();
        watch.start();
        GetValueByAsm.value(user, "name");
        watch.stop();
        System.out.println("generate asm : " + watch.getNanoTime());

        watch = new StopWatch();
        watch.start();
        for (int j = 0; j < TEST_COUNT; j++) {
            for (int i = 0; i < testNames.length; i++) {
                GetValueByAsm.value(user, testNames[i]);
            }
        }
        watch.stop();
        System.out.println("asm : " + watch.getNanoTime());

    }

    @Test
    public void testByAsm1() {
        StopWatch watch = new StopWatch();
        watch.start();
        for (int j = 0; j < TEST_COUNT; j++) {
            for (int i = 0; i < testNames.length; i++) {
                GetValueByAsm.value(user, testNames[i]);
            }
        }
        watch.stop();
        System.out.println("asm1 : " + watch.getNanoTime());

    }

    @Test
    public void testByBeans() {
        StopWatch watch = new StopWatch();
        watch.start();
        for (int j = 0; j < TEST_COUNT; j++) {
            for (int i = 0; i < testNames.length; i++) {
                GeValueByBeans.value(user, testNames[i]);
            }
        }
        watch.stop();
        System.out.println("by beans : " + watch.getNanoTime());

    }
}