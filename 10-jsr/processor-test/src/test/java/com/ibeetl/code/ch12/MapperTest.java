package com.ibeetl.code.ch12;

import org.apache.commons.lang3.time.StopWatch;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MapperTest {

    private ObjectMapper jacksonMapper = new ObjectMapper();
    UserJsonMapper jsonMapper = MapperTools.getMapper(UserJsonMapper.class);

    @Test
    public void test() {
        StopWatch watch = new StopWatch();
        watch.start();
        System.out.println(jsonMapper.write(new User("张三", "大厅")));
//        System.out.println(jsonMapper.write(new User("张三", null)));
        watch.stop();
        System.out.println("test " + watch.getTime());
    }

    @Test
    public void testJackson() throws JsonProcessingException {
        StopWatch watch = new StopWatch();
        watch.start();
        System.out.println(jacksonMapper.writeValueAsString(new User("张三", "大厅")));
//        System.out.println(jacksonMapper.writeValueAsString(new User("张三", null)));
        watch.stop();
        System.out.println("testJackson " + watch.getTime());
    }
}
