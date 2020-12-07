package com.ibeetl.code.ch01.test;
import com.ibeetl.code.ch01.sample.Area;
import com.ibeetl.code.ch01.sample.AreaService;
import com.ibeetl.code.ch01.sample.PreferAreaService;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.Throughput)
@Warmup(iterations = 3)
@Measurement(iterations = 3, time = 5, timeUnit = TimeUnit.SECONDS)
@Threads(3)
@Fork(1)
@OutputTimeUnit(TimeUnit.SECONDS)
@State(Scope.Benchmark)
public class MyBenchmarkStateSimple {

    AreaService areaService = new AreaService();
    PreferAreaService perferAreaService = new PreferAreaService();
    List<Area> data = buildData(20);

    private  List<Area> buildData(int count){
        List<Area>  list = new ArrayList<>(count);
        for(int i=0;i<count;i++){
            Area area = new Area(i,i*10);
            list.add(area);
        }
        return list;
    }


    @Benchmark
    public  void  testStringKey(){
        areaService.buildArea(data);
    }
    @Benchmark
    public  void  testObjectKey(){
        perferAreaService.buildArea(data);
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(MyBenchmarkStateSimple.class.getSimpleName())
                .build();
        new Runner(opt).run();
    }
}
