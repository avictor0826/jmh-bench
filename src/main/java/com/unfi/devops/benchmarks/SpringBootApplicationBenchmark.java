//from https://github.com/stsypanov/spring-boot-benchmark/blob/master/src/main/java/com/tsypanov/sbb/SpringBootApplicationBenchmark.java
package com.unfi.devops.benchmarks;

import java.util.concurrent.TimeUnit;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.TearDown;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.unfi.devops.benchmarks.jdkbench.JdkbenchSpringApplication;

@State(Scope.Thread)
// @BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
// @Warmup(iterations = 0, time = 20, timeUnit = TimeUnit.MILLISECONDS)
// @Measurement(iterations = 1, time = 200, timeUnit = TimeUnit.MILLISECONDS)
// @Fork(value=3,jvmArgsAppend = {"-Xms2g", "-Xmx2g"})
public class SpringBootApplicationBenchmark {

    private ConfigurableApplicationContext context;

    @Benchmark
    @BenchmarkMode(Mode.SingleShotTime)
    @Warmup(iterations = 0, time=1, timeUnit = TimeUnit.MILLISECONDS)
    @Measurement(iterations = 1, timeUnit = TimeUnit.MILLISECONDS)
    @Fork(value=3,jvmArgsAppend = {"-Xms2g", "-Xmx2g"})
    public void cold_startUp(Blackhole blackhole) {
        context = SpringApplication.run(JdkbenchSpringApplication.class);
        blackhole.consume(context);
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @Warmup(iterations = 1, time=200, timeUnit = TimeUnit.MILLISECONDS)
    @Measurement(iterations = 3, time=1000, timeUnit = TimeUnit.MILLISECONDS)
    @Fork(value=1, jvmArgsAppend = {"-Xms2g", "-Xmx2g"})
    public void restart_springapp(Blackhole blackhole) {
        context = SpringApplication.run(JdkbenchSpringApplication.class);
        blackhole.consume(context);

    }

    @TearDown(Level.Invocation)
    public void close() {
        context.close();
    }
}