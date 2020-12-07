package com.ibeetl.code.ch05;

import com.ibeetl.code.ch05.template.EmptyWriter;
import com.ibeetl.code.ch05.template.Template;
import com.ibeetl.code.ch05.template.Template2;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.concurrent.TimeUnit;

/**
 * 格式化例子，预编译能获得最好的性能
 * @author 公众号 java系统优化
 */
@BenchmarkMode(Mode.Throughput)
@Warmup(iterations = 10)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Threads(1)
@Fork(1)
@OutputTimeUnit(TimeUnit.SECONDS)
@State(Scope.Benchmark)
public class MessageFormatTest {

    private String PATTERN = "result a = {} ,b={}, c={} d={} !";
    private Object[] args = new Object[]{"a1", "b1", "abc", "e-f"};

    Template t = null;
    Template2 t2 = null;

    @Benchmark
    public String format() throws IOException {
        return msgFormat(newWriter(), PATTERN, args);
    }

    @Benchmark
    public String templateFormat() throws IOException {
        return t.render(newWriter(), args);
    }

    @Benchmark
    public String template2Format() throws IOException {

        return t2.render(newWriter(), args);

    }




    @Setup
    public void initTemplate(){
         t = Template.getTemplate(PATTERN);
         //再次优化的
         t2 = new Template2(t.getTokens());
    }

    protected Writer newWriter() {
//    return new EmptyWriter();
        return new StringWriter();
    }

    protected String msgFormat(Writer out, String pattern, Object... args) throws IOException {

        int index = -1;
        int start = 0;
        int varIndex = 0;


        while ((index = pattern.indexOf("{}", start)) != -1) {
            out.write(pattern.substring(start, index));
            start = index + 2;
            out.append(String.valueOf(args[varIndex++]));
        }

        if (start < pattern.length()) {
            out.append(pattern.substring(start));
        }
        return out.toString();
    }

    public static void main(String[] args) throws RunnerException,IOException {

//        MessageFormatTest test = new MessageFormatTest();
//        test.templateFormat();
        Options opt = new OptionsBuilder()
                .include(MessageFormatTest.class.getSimpleName())
                .forks(1)
                .build();
        new Runner(opt).run();
    }


}
