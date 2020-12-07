package com.ibeetl.code.ch01.test;

import com.ibeetl.code.ch01.sample.Area;
import com.ibeetl.code.ch01.sample.AreaService;
import com.ibeetl.code.ch01.sample.PreferAreaService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class PerformaceAreaTest {
    static AreaService areaService = new AreaService();
    static PreferAreaService perferAreaService = new PreferAreaService();

    public static void main(String[] args){
        int max = 100000;
        List<Area> data = buildData(20);
        Long start = System.nanoTime();
        testStringKey(max,data);
        Long end = System.nanoTime();
        testObjectKey(max,data);
        Long end1 = System.nanoTime();
        print(start,end,end1);

    }


    public static void  testStringKey(int max, List<Area> data){
        for(int i=0;i<max;i++){
            areaService.buildArea(data);
        }
    }
    public static void  testObjectKey(int max, List<Area> data){
        for(int i=0;i<max;i++){
            perferAreaService.buildArea(data);
        }
    }


    static void print(long start,long end,long end1){
        long  elapsedTime = TimeUnit.NANOSECONDS.toMillis(end - start);
        long  perferElapsedTime = TimeUnit.NANOSECONDS.toMillis(end1 - end);
        System.out.println("elapsedTime="+elapsedTime+",perferElapsedTime="+perferElapsedTime);
    }

    static List<Area> buildData(int count){
        List<Area>  list = new ArrayList<>(count);
        for(int i=0;i<count;i++){
            Area area = new Area(i,i*10);
            list.add(area);
        }
        return list;
    }
}
