package com.ibeetl.code.jit.generated;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.Collection;
import java.util.Collections;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import javax.annotation.Generated;
import org.openjdk.jmh.annotations.CompilerControl;
import org.openjdk.jmh.runner.InfraControl;
import org.openjdk.jmh.infra.ThreadParams;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.results.Result;
import org.openjdk.jmh.results.ThroughputResult;
import org.openjdk.jmh.results.AverageTimeResult;
import org.openjdk.jmh.results.SampleTimeResult;
import org.openjdk.jmh.results.SingleShotResult;
import org.openjdk.jmh.util.SampleBuffer;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Threads;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.results.RawResults;
import org.openjdk.jmh.results.ResultRole;
import java.lang.reflect.Field;
import org.openjdk.jmh.infra.BenchmarkParams;
import org.openjdk.jmh.infra.IterationParams;

@Generated("org.openjdk.jmh.generators.core.BenchmarkGenerator")
public final class ClientTest_machinecode {

    private boolean p0, p0_1, p0_2, p0_3, p0_4, p0_5, p0_6, p0_7, p0_8, p0_9, p0_10, p0_11, p0_12, p0_13, p0_14, p0_15;
    private boolean p1, p1_1, p1_2, p1_3, p1_4, p1_5, p1_6, p1_7, p1_8, p1_9, p1_10, p1_11, p1_12, p1_13, p1_14, p1_15;
    private boolean p2, p2_1, p2_2, p2_3, p2_4, p2_5, p2_6, p2_7, p2_8, p2_9, p2_10, p2_11, p2_12, p2_13, p2_14, p2_15;
    private boolean p3, p3_1, p3_2, p3_3, p3_4, p3_5, p3_6, p3_7, p3_8, p3_9, p3_10, p3_11, p3_12, p3_13, p3_14, p3_15;
    private boolean p4, p4_1, p4_2, p4_3, p4_4, p4_5, p4_6, p4_7, p4_8, p4_9, p4_10, p4_11, p4_12, p4_13, p4_14, p4_15;
    private boolean p5, p5_1, p5_2, p5_3, p5_4, p5_5, p5_6, p5_7, p5_8, p5_9, p5_10, p5_11, p5_12, p5_13, p5_14, p5_15;
    private boolean p6, p6_1, p6_2, p6_3, p6_4, p6_5, p6_6, p6_7, p6_8, p6_9, p6_10, p6_11, p6_12, p6_13, p6_14, p6_15;
    private boolean p7, p7_1, p7_2, p7_3, p7_4, p7_5, p7_6, p7_7, p7_8, p7_9, p7_10, p7_11, p7_12, p7_13, p7_14, p7_15;
    private boolean p8, p8_1, p8_2, p8_3, p8_4, p8_5, p8_6, p8_7, p8_8, p8_9, p8_10, p8_11, p8_12, p8_13, p8_14, p8_15;
    private boolean p9, p9_1, p9_2, p9_3, p9_4, p9_5, p9_6, p9_7, p9_8, p9_9, p9_10, p9_11, p9_12, p9_13, p9_14, p9_15;
    private boolean p10, p10_1, p10_2, p10_3, p10_4, p10_5, p10_6, p10_7, p10_8, p10_9, p10_10, p10_11, p10_12, p10_13, p10_14, p10_15;
    private boolean p11, p11_1, p11_2, p11_3, p11_4, p11_5, p11_6, p11_7, p11_8, p11_9, p11_10, p11_11, p11_12, p11_13, p11_14, p11_15;
    private boolean p12, p12_1, p12_2, p12_3, p12_4, p12_5, p12_6, p12_7, p12_8, p12_9, p12_10, p12_11, p12_12, p12_13, p12_14, p12_15;
    private boolean p13, p13_1, p13_2, p13_3, p13_4, p13_5, p13_6, p13_7, p13_8, p13_9, p13_10, p13_11, p13_12, p13_13, p13_14, p13_15;
    private boolean p14, p14_1, p14_2, p14_3, p14_4, p14_5, p14_6, p14_7, p14_8, p14_9, p14_10, p14_11, p14_12, p14_13, p14_14, p14_15;
    private boolean p15, p15_1, p15_2, p15_3, p15_4, p15_5, p15_6, p15_7, p15_8, p15_9, p15_10, p15_11, p15_12, p15_13, p15_14, p15_15;
    int startRndMask;

    public Collection<? extends Result> machinecode_Throughput(InfraControl control, ThreadParams threadParams) throws Throwable {
        if (threadParams.getSubgroupIndex() == 0) {
            Blackhole_1_jmh l_blackhole1_0 = _jmh_tryInit_f_blackhole1_0(control, threadParams);
            ClientTest_1_jmh l_clienttest0_G = _jmh_tryInit_f_clienttest0_G(control, threadParams);

            control.preSetup();
            if (!l_blackhole1_0.readyIteration) {
                l_blackhole1_0.clearSinks();
                l_blackhole1_0.readyIteration = true;
            }

            control.announceWarmupReady();
            while (control.warmupShouldWait) {
                l_blackhole1_0.consume(l_clienttest0_G.machinecode());
            }

            RawResults res = new RawResults(control.benchmarkParams.getOpsPerInvocation());
            machinecode_thrpt_jmhLoop(control, res, l_clienttest0_G, l_blackhole1_0);
            res.operations /= control.iterationParams.getBatchSize();
            control.announceWarmdownReady();
            try {
                while (control.warmdownShouldWait) {
                    l_blackhole1_0.consume(l_clienttest0_G.machinecode());
                }
                control.preTearDown();
            } catch (InterruptedException ie) {
                control.preTearDownForce();
            }
            if (l_blackhole1_0.readyIteration) {
                l_blackhole1_0.readyIteration = false;
            }

            if (control.isLastIteration()) {
                while(!ClientTest_1_jmh.tearTrialMutexUpdater.compareAndSet(l_clienttest0_G, 0, 1)) {
                    if (Thread.interrupted()) throw new InterruptedException();
                }
                try {
                    if (l_clienttest0_G.readyTrial) {
                        l_clienttest0_G.readyTrial = false;
                    }
                } finally {
                    ClientTest_1_jmh.tearTrialMutexUpdater.set(l_clienttest0_G, 0);
                }
                synchronized(this.getClass()) {
                    f_clienttest0_G = null;
                }
                f_blackhole1_0 = null;
            }
            Collection<Result> results = new ArrayList<Result>();
            results.add(new ThroughputResult(ResultRole.PRIMARY, "machinecode", res.getOperations(), res.getTime(), control.benchmarkParams.getTimeUnit()));
            return results;
        } else
            throw new IllegalStateException("Harness failed to distribute threads among groups properly");
    }

    public void machinecode_thrpt_jmhLoop(InfraControl control, RawResults result, ClientTest_1_jmh l_clienttest0_G, Blackhole_1_jmh l_blackhole1_0) throws Throwable {
        long operations = 0;
        long realTime = 0;
        result.startTime = System.nanoTime();
        do {
            l_blackhole1_0.consume(l_clienttest0_G.machinecode());
            operations++;
        } while(!control.isDone);
        result.stopTime = System.nanoTime();
        result.realTime = realTime;
        result.operations = operations;
    }


    public Collection<? extends Result> machinecode_AverageTime(InfraControl control, ThreadParams threadParams) throws Throwable {
        if (threadParams.getSubgroupIndex() == 0) {
            Blackhole_1_jmh l_blackhole1_0 = _jmh_tryInit_f_blackhole1_0(control, threadParams);
            ClientTest_1_jmh l_clienttest0_G = _jmh_tryInit_f_clienttest0_G(control, threadParams);

            control.preSetup();
            if (!l_blackhole1_0.readyIteration) {
                l_blackhole1_0.clearSinks();
                l_blackhole1_0.readyIteration = true;
            }

            control.announceWarmupReady();
            while (control.warmupShouldWait) {
                l_blackhole1_0.consume(l_clienttest0_G.machinecode());
            }

            RawResults res = new RawResults(control.benchmarkParams.getOpsPerInvocation());
            machinecode_avgt_jmhLoop(control, res, l_clienttest0_G, l_blackhole1_0);
            res.operations /= control.iterationParams.getBatchSize();
            control.announceWarmdownReady();
            try {
                while (control.warmdownShouldWait) {
                    l_blackhole1_0.consume(l_clienttest0_G.machinecode());
                }
                control.preTearDown();
            } catch (InterruptedException ie) {
                control.preTearDownForce();
            }
            if (l_blackhole1_0.readyIteration) {
                l_blackhole1_0.readyIteration = false;
            }

            if (control.isLastIteration()) {
                while(!ClientTest_1_jmh.tearTrialMutexUpdater.compareAndSet(l_clienttest0_G, 0, 1)) {
                    if (Thread.interrupted()) throw new InterruptedException();
                }
                try {
                    if (l_clienttest0_G.readyTrial) {
                        l_clienttest0_G.readyTrial = false;
                    }
                } finally {
                    ClientTest_1_jmh.tearTrialMutexUpdater.set(l_clienttest0_G, 0);
                }
                synchronized(this.getClass()) {
                    f_clienttest0_G = null;
                }
                f_blackhole1_0 = null;
            }
            Collection<Result> results = new ArrayList<Result>();
            results.add(new AverageTimeResult(ResultRole.PRIMARY, "machinecode", res.getOperations(), res.getTime(), control.benchmarkParams.getTimeUnit()));
            return results;
        } else
            throw new IllegalStateException("Harness failed to distribute threads among groups properly");
    }

    public void machinecode_avgt_jmhLoop(InfraControl control, RawResults result, ClientTest_1_jmh l_clienttest0_G, Blackhole_1_jmh l_blackhole1_0) throws Throwable {
        long operations = 0;
        long realTime = 0;
        result.startTime = System.nanoTime();
        do {
            l_blackhole1_0.consume(l_clienttest0_G.machinecode());
            operations++;
        } while(!control.isDone);
        result.stopTime = System.nanoTime();
        result.realTime = realTime;
        result.operations = operations;
    }


    public Collection<? extends Result> machinecode_SampleTime(InfraControl control, ThreadParams threadParams) throws Throwable {
        if (threadParams.getSubgroupIndex() == 0) {
            Blackhole_1_jmh l_blackhole1_0 = _jmh_tryInit_f_blackhole1_0(control, threadParams);
            ClientTest_1_jmh l_clienttest0_G = _jmh_tryInit_f_clienttest0_G(control, threadParams);

            control.preSetup();
            if (!l_blackhole1_0.readyIteration) {
                l_blackhole1_0.clearSinks();
                l_blackhole1_0.readyIteration = true;
            }

            control.announceWarmupReady();
            while (control.warmupShouldWait) {
                l_blackhole1_0.consume(l_clienttest0_G.machinecode());
            }

            int targetSamples = (int) (control.getDuration(TimeUnit.MILLISECONDS) * 20); // at max, 20 timestamps per millisecond
        int batchSize = control.iterationParams.getBatchSize();
            SampleBuffer buffer = new SampleBuffer();
            machinecode_sample_jmhLoop(control, buffer, targetSamples, control.benchmarkParams.getOpsPerInvocation(), batchSize, l_clienttest0_G, l_blackhole1_0);
            control.announceWarmdownReady();
            try {
                while (control.warmdownShouldWait) {
                    l_blackhole1_0.consume(l_clienttest0_G.machinecode());
                }
                control.preTearDown();
            } catch (InterruptedException ie) {
                control.preTearDownForce();
            }
            if (l_blackhole1_0.readyIteration) {
                l_blackhole1_0.readyIteration = false;
            }

            if (control.isLastIteration()) {
                while(!ClientTest_1_jmh.tearTrialMutexUpdater.compareAndSet(l_clienttest0_G, 0, 1)) {
                    if (Thread.interrupted()) throw new InterruptedException();
                }
                try {
                    if (l_clienttest0_G.readyTrial) {
                        l_clienttest0_G.readyTrial = false;
                    }
                } finally {
                    ClientTest_1_jmh.tearTrialMutexUpdater.set(l_clienttest0_G, 0);
                }
                synchronized(this.getClass()) {
                    f_clienttest0_G = null;
                }
                f_blackhole1_0 = null;
            }
            Collection<Result> results = new ArrayList<Result>();
            results.add(new SampleTimeResult(ResultRole.PRIMARY, "machinecode", buffer, control.benchmarkParams.getTimeUnit()));
            return results;
        } else
            throw new IllegalStateException("Harness failed to distribute threads among groups properly");
    }

    public void machinecode_sample_jmhLoop(InfraControl control, SampleBuffer buffer, int targetSamples, long opsPerInv, int batchSize, ClientTest_1_jmh l_clienttest0_G, Blackhole_1_jmh l_blackhole1_0) throws Throwable {
        long realTime = 0;
        int rnd = (int)System.nanoTime();
        int rndMask = startRndMask;
        long time = 0;
        int currentStride = 0;
        do {
            rnd = (rnd * 1664525 + 1013904223);
            boolean sample = (rnd & rndMask) == 0;
            if (sample) {
                time = System.nanoTime();
            }
            for (int b = 0; b < batchSize; b++) {
                if (control.volatileSpoiler) return;
                l_blackhole1_0.consume(l_clienttest0_G.machinecode());
            }
            if (sample) {
                buffer.add((System.nanoTime() - time) / opsPerInv);
                if (currentStride++ > targetSamples) {
                    buffer.half();
                    currentStride = 0;
                    rndMask = (rndMask << 1) + 1;
                }
            }
        } while(!control.isDone);
        startRndMask = Math.max(startRndMask, rndMask);
    }


    public Collection<? extends Result> machinecode_SingleShotTime(InfraControl control, ThreadParams threadParams) throws Throwable {
        if (threadParams.getSubgroupIndex() == 0) {
            Blackhole_1_jmh l_blackhole1_0 = _jmh_tryInit_f_blackhole1_0(control, threadParams);
            ClientTest_1_jmh l_clienttest0_G = _jmh_tryInit_f_clienttest0_G(control, threadParams);

            control.preSetup();
            if (!l_blackhole1_0.readyIteration) {
                l_blackhole1_0.clearSinks();
                l_blackhole1_0.readyIteration = true;
            }

            RawResults res = new RawResults(control.benchmarkParams.getOpsPerInvocation());
        int batchSize = control.iterationParams.getBatchSize();
            machinecode_ss_jmhStub(control, batchSize, res, l_clienttest0_G, l_blackhole1_0);
                control.preTearDown();
            if (l_blackhole1_0.readyIteration) {
                l_blackhole1_0.readyIteration = false;
            }

            if (control.isLastIteration()) {
                while(!ClientTest_1_jmh.tearTrialMutexUpdater.compareAndSet(l_clienttest0_G, 0, 1)) {
                    if (Thread.interrupted()) throw new InterruptedException();
                }
                try {
                    if (l_clienttest0_G.readyTrial) {
                        l_clienttest0_G.readyTrial = false;
                    }
                } finally {
                    ClientTest_1_jmh.tearTrialMutexUpdater.set(l_clienttest0_G, 0);
                }
                synchronized(this.getClass()) {
                    f_clienttest0_G = null;
                }
                f_blackhole1_0 = null;
            }
            Collection<Result> results = new ArrayList<Result>();
            results.add(new SingleShotResult(ResultRole.PRIMARY, "machinecode", res.getTime(), control.benchmarkParams.getTimeUnit()));
            return results;
        } else
            throw new IllegalStateException("Harness failed to distribute threads among groups properly");
    }

    public void machinecode_ss_jmhStub(InfraControl control, int batchSize, RawResults result, ClientTest_1_jmh l_clienttest0_G, Blackhole_1_jmh l_blackhole1_0) throws Throwable {
        long realTime = 0;
        result.startTime = System.nanoTime();
        for (int b = 0; b < batchSize; b++) {
            if (control.volatileSpoiler) return;
            l_blackhole1_0.consume(l_clienttest0_G.machinecode());
        }
        result.stopTime = System.nanoTime();
        result.realTime = realTime;
    }

    
    static volatile ClientTest_1_jmh f_clienttest0_G;
    
    ClientTest_1_jmh _jmh_tryInit_f_clienttest0_G(InfraControl control, ThreadParams threadParams) throws Throwable {
        synchronized(this.getClass()) {
            if (f_clienttest0_G == null) {
                f_clienttest0_G = new ClientTest_1_jmh();
            }
            if (!f_clienttest0_G.readyTrial) {
                f_clienttest0_G.init();
                f_clienttest0_G.readyTrial = true;
            }
        }
        return f_clienttest0_G;
    }
    
    Blackhole_1_jmh f_blackhole1_0;
    
    Blackhole_1_jmh _jmh_tryInit_f_blackhole1_0(InfraControl control, ThreadParams threadParams) throws Throwable {
        if (f_blackhole1_0 == null) {
            Blackhole_1_jmh val = new Blackhole_1_jmh();
            val.readyTrial = true;
            f_blackhole1_0 = val;
        }
        return f_blackhole1_0;
    }


    static class Blackhole_1_jmh_B1 extends org.openjdk.jmh.infra.Blackhole {
        private boolean pb1_0, pb1_0_1, pb1_0_2, pb1_0_3, pb1_0_4, pb1_0_5, pb1_0_6, pb1_0_7, pb1_0_8, pb1_0_9, pb1_0_10, pb1_0_11, pb1_0_12, pb1_0_13, pb1_0_14, pb1_0_15;
        private boolean pb1_1, pb1_1_1, pb1_1_2, pb1_1_3, pb1_1_4, pb1_1_5, pb1_1_6, pb1_1_7, pb1_1_8, pb1_1_9, pb1_1_10, pb1_1_11, pb1_1_12, pb1_1_13, pb1_1_14, pb1_1_15;
        private boolean pb1_2, pb1_2_1, pb1_2_2, pb1_2_3, pb1_2_4, pb1_2_5, pb1_2_6, pb1_2_7, pb1_2_8, pb1_2_9, pb1_2_10, pb1_2_11, pb1_2_12, pb1_2_13, pb1_2_14, pb1_2_15;
        private boolean pb1_3, pb1_3_1, pb1_3_2, pb1_3_3, pb1_3_4, pb1_3_5, pb1_3_6, pb1_3_7, pb1_3_8, pb1_3_9, pb1_3_10, pb1_3_11, pb1_3_12, pb1_3_13, pb1_3_14, pb1_3_15;
        private boolean pb1_4, pb1_4_1, pb1_4_2, pb1_4_3, pb1_4_4, pb1_4_5, pb1_4_6, pb1_4_7, pb1_4_8, pb1_4_9, pb1_4_10, pb1_4_11, pb1_4_12, pb1_4_13, pb1_4_14, pb1_4_15;
        private boolean pb1_5, pb1_5_1, pb1_5_2, pb1_5_3, pb1_5_4, pb1_5_5, pb1_5_6, pb1_5_7, pb1_5_8, pb1_5_9, pb1_5_10, pb1_5_11, pb1_5_12, pb1_5_13, pb1_5_14, pb1_5_15;
        private boolean pb1_6, pb1_6_1, pb1_6_2, pb1_6_3, pb1_6_4, pb1_6_5, pb1_6_6, pb1_6_7, pb1_6_8, pb1_6_9, pb1_6_10, pb1_6_11, pb1_6_12, pb1_6_13, pb1_6_14, pb1_6_15;
        private boolean pb1_7, pb1_7_1, pb1_7_2, pb1_7_3, pb1_7_4, pb1_7_5, pb1_7_6, pb1_7_7, pb1_7_8, pb1_7_9, pb1_7_10, pb1_7_11, pb1_7_12, pb1_7_13, pb1_7_14, pb1_7_15;
        private boolean pb1_8, pb1_8_1, pb1_8_2, pb1_8_3, pb1_8_4, pb1_8_5, pb1_8_6, pb1_8_7, pb1_8_8, pb1_8_9, pb1_8_10, pb1_8_11, pb1_8_12, pb1_8_13, pb1_8_14, pb1_8_15;
        private boolean pb1_9, pb1_9_1, pb1_9_2, pb1_9_3, pb1_9_4, pb1_9_5, pb1_9_6, pb1_9_7, pb1_9_8, pb1_9_9, pb1_9_10, pb1_9_11, pb1_9_12, pb1_9_13, pb1_9_14, pb1_9_15;
        private boolean pb1_10, pb1_10_1, pb1_10_2, pb1_10_3, pb1_10_4, pb1_10_5, pb1_10_6, pb1_10_7, pb1_10_8, pb1_10_9, pb1_10_10, pb1_10_11, pb1_10_12, pb1_10_13, pb1_10_14, pb1_10_15;
        private boolean pb1_11, pb1_11_1, pb1_11_2, pb1_11_3, pb1_11_4, pb1_11_5, pb1_11_6, pb1_11_7, pb1_11_8, pb1_11_9, pb1_11_10, pb1_11_11, pb1_11_12, pb1_11_13, pb1_11_14, pb1_11_15;
        private boolean pb1_12, pb1_12_1, pb1_12_2, pb1_12_3, pb1_12_4, pb1_12_5, pb1_12_6, pb1_12_7, pb1_12_8, pb1_12_9, pb1_12_10, pb1_12_11, pb1_12_12, pb1_12_13, pb1_12_14, pb1_12_15;
        private boolean pb1_13, pb1_13_1, pb1_13_2, pb1_13_3, pb1_13_4, pb1_13_5, pb1_13_6, pb1_13_7, pb1_13_8, pb1_13_9, pb1_13_10, pb1_13_11, pb1_13_12, pb1_13_13, pb1_13_14, pb1_13_15;
        private boolean pb1_14, pb1_14_1, pb1_14_2, pb1_14_3, pb1_14_4, pb1_14_5, pb1_14_6, pb1_14_7, pb1_14_8, pb1_14_9, pb1_14_10, pb1_14_11, pb1_14_12, pb1_14_13, pb1_14_14, pb1_14_15;
        private boolean pb1_15, pb1_15_1, pb1_15_2, pb1_15_3, pb1_15_4, pb1_15_5, pb1_15_6, pb1_15_7, pb1_15_8, pb1_15_9, pb1_15_10, pb1_15_11, pb1_15_12, pb1_15_13, pb1_15_14, pb1_15_15;
    }
    
    static class Blackhole_1_jmh_B2 extends Blackhole_1_jmh_B1 {
        public volatile int setupTrialMutex;
        public volatile int tearTrialMutex;
        public final static AtomicIntegerFieldUpdater setupTrialMutexUpdater = AtomicIntegerFieldUpdater.newUpdater(Blackhole_1_jmh_B2.class, "setupTrialMutex");
        public final static AtomicIntegerFieldUpdater tearTrialMutexUpdater = AtomicIntegerFieldUpdater.newUpdater(Blackhole_1_jmh_B2.class, "tearTrialMutex");
    
        public volatile int setupIterationMutex;
        public volatile int tearIterationMutex;
        public final static AtomicIntegerFieldUpdater setupIterationMutexUpdater = AtomicIntegerFieldUpdater.newUpdater(Blackhole_1_jmh_B2.class, "setupIterationMutex");
        public final static AtomicIntegerFieldUpdater tearIterationMutexUpdater = AtomicIntegerFieldUpdater.newUpdater(Blackhole_1_jmh_B2.class, "tearIterationMutex");
    
        public volatile int setupInvocationMutex;
        public volatile int tearInvocationMutex;
        public final static AtomicIntegerFieldUpdater setupInvocationMutexUpdater = AtomicIntegerFieldUpdater.newUpdater(Blackhole_1_jmh_B2.class, "setupInvocationMutex");
        public final static AtomicIntegerFieldUpdater tearInvocationMutexUpdater = AtomicIntegerFieldUpdater.newUpdater(Blackhole_1_jmh_B2.class, "tearInvocationMutex");
    
        public boolean readyTrial;
        public boolean readyIteration;
        public boolean readyInvocation;
    }
    
    static class Blackhole_1_jmh_B3 extends Blackhole_1_jmh_B2 {
        private boolean pb3_0, pb3_0_1, pb3_0_2, pb3_0_3, pb3_0_4, pb3_0_5, pb3_0_6, pb3_0_7, pb3_0_8, pb3_0_9, pb3_0_10, pb3_0_11, pb3_0_12, pb3_0_13, pb3_0_14, pb3_0_15;
        private boolean pb3_1, pb3_1_1, pb3_1_2, pb3_1_3, pb3_1_4, pb3_1_5, pb3_1_6, pb3_1_7, pb3_1_8, pb3_1_9, pb3_1_10, pb3_1_11, pb3_1_12, pb3_1_13, pb3_1_14, pb3_1_15;
        private boolean pb3_2, pb3_2_1, pb3_2_2, pb3_2_3, pb3_2_4, pb3_2_5, pb3_2_6, pb3_2_7, pb3_2_8, pb3_2_9, pb3_2_10, pb3_2_11, pb3_2_12, pb3_2_13, pb3_2_14, pb3_2_15;
        private boolean pb3_3, pb3_3_1, pb3_3_2, pb3_3_3, pb3_3_4, pb3_3_5, pb3_3_6, pb3_3_7, pb3_3_8, pb3_3_9, pb3_3_10, pb3_3_11, pb3_3_12, pb3_3_13, pb3_3_14, pb3_3_15;
        private boolean pb3_4, pb3_4_1, pb3_4_2, pb3_4_3, pb3_4_4, pb3_4_5, pb3_4_6, pb3_4_7, pb3_4_8, pb3_4_9, pb3_4_10, pb3_4_11, pb3_4_12, pb3_4_13, pb3_4_14, pb3_4_15;
        private boolean pb3_5, pb3_5_1, pb3_5_2, pb3_5_3, pb3_5_4, pb3_5_5, pb3_5_6, pb3_5_7, pb3_5_8, pb3_5_9, pb3_5_10, pb3_5_11, pb3_5_12, pb3_5_13, pb3_5_14, pb3_5_15;
        private boolean pb3_6, pb3_6_1, pb3_6_2, pb3_6_3, pb3_6_4, pb3_6_5, pb3_6_6, pb3_6_7, pb3_6_8, pb3_6_9, pb3_6_10, pb3_6_11, pb3_6_12, pb3_6_13, pb3_6_14, pb3_6_15;
        private boolean pb3_7, pb3_7_1, pb3_7_2, pb3_7_3, pb3_7_4, pb3_7_5, pb3_7_6, pb3_7_7, pb3_7_8, pb3_7_9, pb3_7_10, pb3_7_11, pb3_7_12, pb3_7_13, pb3_7_14, pb3_7_15;
        private boolean pb3_8, pb3_8_1, pb3_8_2, pb3_8_3, pb3_8_4, pb3_8_5, pb3_8_6, pb3_8_7, pb3_8_8, pb3_8_9, pb3_8_10, pb3_8_11, pb3_8_12, pb3_8_13, pb3_8_14, pb3_8_15;
        private boolean pb3_9, pb3_9_1, pb3_9_2, pb3_9_3, pb3_9_4, pb3_9_5, pb3_9_6, pb3_9_7, pb3_9_8, pb3_9_9, pb3_9_10, pb3_9_11, pb3_9_12, pb3_9_13, pb3_9_14, pb3_9_15;
        private boolean pb3_10, pb3_10_1, pb3_10_2, pb3_10_3, pb3_10_4, pb3_10_5, pb3_10_6, pb3_10_7, pb3_10_8, pb3_10_9, pb3_10_10, pb3_10_11, pb3_10_12, pb3_10_13, pb3_10_14, pb3_10_15;
        private boolean pb3_11, pb3_11_1, pb3_11_2, pb3_11_3, pb3_11_4, pb3_11_5, pb3_11_6, pb3_11_7, pb3_11_8, pb3_11_9, pb3_11_10, pb3_11_11, pb3_11_12, pb3_11_13, pb3_11_14, pb3_11_15;
        private boolean pb3_12, pb3_12_1, pb3_12_2, pb3_12_3, pb3_12_4, pb3_12_5, pb3_12_6, pb3_12_7, pb3_12_8, pb3_12_9, pb3_12_10, pb3_12_11, pb3_12_12, pb3_12_13, pb3_12_14, pb3_12_15;
        private boolean pb3_13, pb3_13_1, pb3_13_2, pb3_13_3, pb3_13_4, pb3_13_5, pb3_13_6, pb3_13_7, pb3_13_8, pb3_13_9, pb3_13_10, pb3_13_11, pb3_13_12, pb3_13_13, pb3_13_14, pb3_13_15;
        private boolean pb3_14, pb3_14_1, pb3_14_2, pb3_14_3, pb3_14_4, pb3_14_5, pb3_14_6, pb3_14_7, pb3_14_8, pb3_14_9, pb3_14_10, pb3_14_11, pb3_14_12, pb3_14_13, pb3_14_14, pb3_14_15;
        private boolean pb3_15, pb3_15_1, pb3_15_2, pb3_15_3, pb3_15_4, pb3_15_5, pb3_15_6, pb3_15_7, pb3_15_8, pb3_15_9, pb3_15_10, pb3_15_11, pb3_15_12, pb3_15_13, pb3_15_14, pb3_15_15;
    }
    
    static final class Blackhole_1_jmh extends Blackhole_1_jmh_B3 {
    }
    
    static class ClientTest_1_jmh_B1 extends com.ibeetl.code.jit.ClientTest {
        private boolean pb1_0, pb1_0_1, pb1_0_2, pb1_0_3, pb1_0_4, pb1_0_5, pb1_0_6, pb1_0_7, pb1_0_8, pb1_0_9, pb1_0_10, pb1_0_11, pb1_0_12, pb1_0_13, pb1_0_14, pb1_0_15;
        private boolean pb1_1, pb1_1_1, pb1_1_2, pb1_1_3, pb1_1_4, pb1_1_5, pb1_1_6, pb1_1_7, pb1_1_8, pb1_1_9, pb1_1_10, pb1_1_11, pb1_1_12, pb1_1_13, pb1_1_14, pb1_1_15;
        private boolean pb1_2, pb1_2_1, pb1_2_2, pb1_2_3, pb1_2_4, pb1_2_5, pb1_2_6, pb1_2_7, pb1_2_8, pb1_2_9, pb1_2_10, pb1_2_11, pb1_2_12, pb1_2_13, pb1_2_14, pb1_2_15;
        private boolean pb1_3, pb1_3_1, pb1_3_2, pb1_3_3, pb1_3_4, pb1_3_5, pb1_3_6, pb1_3_7, pb1_3_8, pb1_3_9, pb1_3_10, pb1_3_11, pb1_3_12, pb1_3_13, pb1_3_14, pb1_3_15;
        private boolean pb1_4, pb1_4_1, pb1_4_2, pb1_4_3, pb1_4_4, pb1_4_5, pb1_4_6, pb1_4_7, pb1_4_8, pb1_4_9, pb1_4_10, pb1_4_11, pb1_4_12, pb1_4_13, pb1_4_14, pb1_4_15;
        private boolean pb1_5, pb1_5_1, pb1_5_2, pb1_5_3, pb1_5_4, pb1_5_5, pb1_5_6, pb1_5_7, pb1_5_8, pb1_5_9, pb1_5_10, pb1_5_11, pb1_5_12, pb1_5_13, pb1_5_14, pb1_5_15;
        private boolean pb1_6, pb1_6_1, pb1_6_2, pb1_6_3, pb1_6_4, pb1_6_5, pb1_6_6, pb1_6_7, pb1_6_8, pb1_6_9, pb1_6_10, pb1_6_11, pb1_6_12, pb1_6_13, pb1_6_14, pb1_6_15;
        private boolean pb1_7, pb1_7_1, pb1_7_2, pb1_7_3, pb1_7_4, pb1_7_5, pb1_7_6, pb1_7_7, pb1_7_8, pb1_7_9, pb1_7_10, pb1_7_11, pb1_7_12, pb1_7_13, pb1_7_14, pb1_7_15;
        private boolean pb1_8, pb1_8_1, pb1_8_2, pb1_8_3, pb1_8_4, pb1_8_5, pb1_8_6, pb1_8_7, pb1_8_8, pb1_8_9, pb1_8_10, pb1_8_11, pb1_8_12, pb1_8_13, pb1_8_14, pb1_8_15;
        private boolean pb1_9, pb1_9_1, pb1_9_2, pb1_9_3, pb1_9_4, pb1_9_5, pb1_9_6, pb1_9_7, pb1_9_8, pb1_9_9, pb1_9_10, pb1_9_11, pb1_9_12, pb1_9_13, pb1_9_14, pb1_9_15;
        private boolean pb1_10, pb1_10_1, pb1_10_2, pb1_10_3, pb1_10_4, pb1_10_5, pb1_10_6, pb1_10_7, pb1_10_8, pb1_10_9, pb1_10_10, pb1_10_11, pb1_10_12, pb1_10_13, pb1_10_14, pb1_10_15;
        private boolean pb1_11, pb1_11_1, pb1_11_2, pb1_11_3, pb1_11_4, pb1_11_5, pb1_11_6, pb1_11_7, pb1_11_8, pb1_11_9, pb1_11_10, pb1_11_11, pb1_11_12, pb1_11_13, pb1_11_14, pb1_11_15;
        private boolean pb1_12, pb1_12_1, pb1_12_2, pb1_12_3, pb1_12_4, pb1_12_5, pb1_12_6, pb1_12_7, pb1_12_8, pb1_12_9, pb1_12_10, pb1_12_11, pb1_12_12, pb1_12_13, pb1_12_14, pb1_12_15;
        private boolean pb1_13, pb1_13_1, pb1_13_2, pb1_13_3, pb1_13_4, pb1_13_5, pb1_13_6, pb1_13_7, pb1_13_8, pb1_13_9, pb1_13_10, pb1_13_11, pb1_13_12, pb1_13_13, pb1_13_14, pb1_13_15;
        private boolean pb1_14, pb1_14_1, pb1_14_2, pb1_14_3, pb1_14_4, pb1_14_5, pb1_14_6, pb1_14_7, pb1_14_8, pb1_14_9, pb1_14_10, pb1_14_11, pb1_14_12, pb1_14_13, pb1_14_14, pb1_14_15;
        private boolean pb1_15, pb1_15_1, pb1_15_2, pb1_15_3, pb1_15_4, pb1_15_5, pb1_15_6, pb1_15_7, pb1_15_8, pb1_15_9, pb1_15_10, pb1_15_11, pb1_15_12, pb1_15_13, pb1_15_14, pb1_15_15;
    }
    
    static class ClientTest_1_jmh_B2 extends ClientTest_1_jmh_B1 {
        public volatile int setupTrialMutex;
        public volatile int tearTrialMutex;
        public final static AtomicIntegerFieldUpdater setupTrialMutexUpdater = AtomicIntegerFieldUpdater.newUpdater(ClientTest_1_jmh_B2.class, "setupTrialMutex");
        public final static AtomicIntegerFieldUpdater tearTrialMutexUpdater = AtomicIntegerFieldUpdater.newUpdater(ClientTest_1_jmh_B2.class, "tearTrialMutex");
    
        public volatile int setupIterationMutex;
        public volatile int tearIterationMutex;
        public final static AtomicIntegerFieldUpdater setupIterationMutexUpdater = AtomicIntegerFieldUpdater.newUpdater(ClientTest_1_jmh_B2.class, "setupIterationMutex");
        public final static AtomicIntegerFieldUpdater tearIterationMutexUpdater = AtomicIntegerFieldUpdater.newUpdater(ClientTest_1_jmh_B2.class, "tearIterationMutex");
    
        public volatile int setupInvocationMutex;
        public volatile int tearInvocationMutex;
        public final static AtomicIntegerFieldUpdater setupInvocationMutexUpdater = AtomicIntegerFieldUpdater.newUpdater(ClientTest_1_jmh_B2.class, "setupInvocationMutex");
        public final static AtomicIntegerFieldUpdater tearInvocationMutexUpdater = AtomicIntegerFieldUpdater.newUpdater(ClientTest_1_jmh_B2.class, "tearInvocationMutex");
    
        public volatile boolean readyTrial;
        public volatile boolean readyIteration;
        public volatile boolean readyInvocation;
    }
    
    static class ClientTest_1_jmh_B3 extends ClientTest_1_jmh_B2 {
        private boolean pb3_0, pb3_0_1, pb3_0_2, pb3_0_3, pb3_0_4, pb3_0_5, pb3_0_6, pb3_0_7, pb3_0_8, pb3_0_9, pb3_0_10, pb3_0_11, pb3_0_12, pb3_0_13, pb3_0_14, pb3_0_15;
        private boolean pb3_1, pb3_1_1, pb3_1_2, pb3_1_3, pb3_1_4, pb3_1_5, pb3_1_6, pb3_1_7, pb3_1_8, pb3_1_9, pb3_1_10, pb3_1_11, pb3_1_12, pb3_1_13, pb3_1_14, pb3_1_15;
        private boolean pb3_2, pb3_2_1, pb3_2_2, pb3_2_3, pb3_2_4, pb3_2_5, pb3_2_6, pb3_2_7, pb3_2_8, pb3_2_9, pb3_2_10, pb3_2_11, pb3_2_12, pb3_2_13, pb3_2_14, pb3_2_15;
        private boolean pb3_3, pb3_3_1, pb3_3_2, pb3_3_3, pb3_3_4, pb3_3_5, pb3_3_6, pb3_3_7, pb3_3_8, pb3_3_9, pb3_3_10, pb3_3_11, pb3_3_12, pb3_3_13, pb3_3_14, pb3_3_15;
        private boolean pb3_4, pb3_4_1, pb3_4_2, pb3_4_3, pb3_4_4, pb3_4_5, pb3_4_6, pb3_4_7, pb3_4_8, pb3_4_9, pb3_4_10, pb3_4_11, pb3_4_12, pb3_4_13, pb3_4_14, pb3_4_15;
        private boolean pb3_5, pb3_5_1, pb3_5_2, pb3_5_3, pb3_5_4, pb3_5_5, pb3_5_6, pb3_5_7, pb3_5_8, pb3_5_9, pb3_5_10, pb3_5_11, pb3_5_12, pb3_5_13, pb3_5_14, pb3_5_15;
        private boolean pb3_6, pb3_6_1, pb3_6_2, pb3_6_3, pb3_6_4, pb3_6_5, pb3_6_6, pb3_6_7, pb3_6_8, pb3_6_9, pb3_6_10, pb3_6_11, pb3_6_12, pb3_6_13, pb3_6_14, pb3_6_15;
        private boolean pb3_7, pb3_7_1, pb3_7_2, pb3_7_3, pb3_7_4, pb3_7_5, pb3_7_6, pb3_7_7, pb3_7_8, pb3_7_9, pb3_7_10, pb3_7_11, pb3_7_12, pb3_7_13, pb3_7_14, pb3_7_15;
        private boolean pb3_8, pb3_8_1, pb3_8_2, pb3_8_3, pb3_8_4, pb3_8_5, pb3_8_6, pb3_8_7, pb3_8_8, pb3_8_9, pb3_8_10, pb3_8_11, pb3_8_12, pb3_8_13, pb3_8_14, pb3_8_15;
        private boolean pb3_9, pb3_9_1, pb3_9_2, pb3_9_3, pb3_9_4, pb3_9_5, pb3_9_6, pb3_9_7, pb3_9_8, pb3_9_9, pb3_9_10, pb3_9_11, pb3_9_12, pb3_9_13, pb3_9_14, pb3_9_15;
        private boolean pb3_10, pb3_10_1, pb3_10_2, pb3_10_3, pb3_10_4, pb3_10_5, pb3_10_6, pb3_10_7, pb3_10_8, pb3_10_9, pb3_10_10, pb3_10_11, pb3_10_12, pb3_10_13, pb3_10_14, pb3_10_15;
        private boolean pb3_11, pb3_11_1, pb3_11_2, pb3_11_3, pb3_11_4, pb3_11_5, pb3_11_6, pb3_11_7, pb3_11_8, pb3_11_9, pb3_11_10, pb3_11_11, pb3_11_12, pb3_11_13, pb3_11_14, pb3_11_15;
        private boolean pb3_12, pb3_12_1, pb3_12_2, pb3_12_3, pb3_12_4, pb3_12_5, pb3_12_6, pb3_12_7, pb3_12_8, pb3_12_9, pb3_12_10, pb3_12_11, pb3_12_12, pb3_12_13, pb3_12_14, pb3_12_15;
        private boolean pb3_13, pb3_13_1, pb3_13_2, pb3_13_3, pb3_13_4, pb3_13_5, pb3_13_6, pb3_13_7, pb3_13_8, pb3_13_9, pb3_13_10, pb3_13_11, pb3_13_12, pb3_13_13, pb3_13_14, pb3_13_15;
        private boolean pb3_14, pb3_14_1, pb3_14_2, pb3_14_3, pb3_14_4, pb3_14_5, pb3_14_6, pb3_14_7, pb3_14_8, pb3_14_9, pb3_14_10, pb3_14_11, pb3_14_12, pb3_14_13, pb3_14_14, pb3_14_15;
        private boolean pb3_15, pb3_15_1, pb3_15_2, pb3_15_3, pb3_15_4, pb3_15_5, pb3_15_6, pb3_15_7, pb3_15_8, pb3_15_9, pb3_15_10, pb3_15_11, pb3_15_12, pb3_15_13, pb3_15_14, pb3_15_15;
    }
    
    static final class ClientTest_1_jmh extends ClientTest_1_jmh_B3 {
    }
    

}

