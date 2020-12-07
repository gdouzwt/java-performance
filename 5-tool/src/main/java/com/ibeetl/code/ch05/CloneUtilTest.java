package com.ibeetl.code.ch05;

import com.ibeetl.code.ch05.model.Department;
import com.ibeetl.code.ch05.model.User;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/** 各种克隆方法，从jvm缓存中取出的对象需要克隆才能任意使用
 * 那种克隆方法快，维护简单？
 * @author 公众号 java系统优化
 */
@BenchmarkMode(Mode.AverageTime)
@Warmup(iterations = 5)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Threads(1)
@Fork(1)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Benchmark)
public class CloneUtilTest {

    Department department;
    CloneUtil util = new CloneUtil();

    @Benchmark
    public Department testCloneByHardCode() {
        return util.cloneDepartment(department);

    }

    @Benchmark
    public Department testCloneByObjectWriter() {
        return util.cloneByObjectWriter(department);
    }


    @Benchmark
    public Department testCloneBySelma() {

        return util.cloneBySelma(department);
    }

    @Benchmark
    public Department testCloneByJson() {

        return util.cloneByJson(department);
    }


    @Setup
    public void init() {
        department = new Department();
        department.setId(1);
        department.setName("HR");
        User user = new User();
        user.setId(1);
        user.setName("lijz");
        user.setPassword("123456");
        List<User> list = new ArrayList<>();
        list.add(user);
        department.setUsers(list);

    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(CloneUtilTest.class.getSimpleName())
                .forks(1)
                .build();
        new Runner(opt).run();
    }
}
