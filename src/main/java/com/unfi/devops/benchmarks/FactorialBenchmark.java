/*
 * Copyright (c) 2014, Oracle America, Inc.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *  * Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 *
 *  * Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 *
 *  * Neither the name of Oracle nor the names of its contributors may be used
 *    to endorse or promote products derived from this software without
 *    specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF
 * THE POSSIBILITY OF SUCH DAMAGE.
 */

package com.unfi.devops.benchmarks;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.ThreadLocalRandom;
// import java.lang.Math;
import java.util.Random;


public class FactorialBenchmark {

    @State(Scope.Thread)
    public static class MyState {
        public int a = 1;
        public int b = 2;
        public long n = 536871066;
        // n = (long)(Math.random() * range) + minimum;
        // private Random rand;
        // rand = new Random();
        // randomNum = minimum + rand.nextInt((maximum - minimum) + 1);
        // n=randomNum;

    }

    // @Benchmark
    // @BenchmarkMode(Mode.Throughput)
    // @OutputTimeUnit(TimeUnit.MILLISECONDS)
    // @Fork(value = 1)
    // // @State(Scope.Benchmark)
    // // @OutputTimeUnit(TimeUnit.MICROSECONDS)
    // @Warmup(iterations = 5, time = 20, timeUnit = TimeUnit.MILLISECONDS)
    // @Measurement(iterations = 20, time = 200, timeUnit = TimeUnit.MILLISECONDS)
    // public void testMethod(MyState mystate, Blackhole blackhole) {
    //     // This is a demo/sample template for building your JMH benchmarks. Edit as needed.
    //     // Put your benchmark code here.

    //     int s,minimum=1000,maximum=2000;
    //     Random rand = new Random();
    //     int randomNum = minimum + rand.nextInt((maximum - minimum) + 1);
    //     s=mystate.a+mystate.b;
    //     blackhole.consume(s);
    //     blackhole.consume(randomNum);
    // }

    @Benchmark
    @BenchmarkMode(Mode.SingleShotTime)
    @OutputTimeUnit(TimeUnit.SECONDS)
    @Fork(value = 3)
    // @State(Scope.Benchmark)
    // @OutputTimeUnit(TimeUnit.MICROSECONDS)
    @Warmup(iterations = 5, time = 100, timeUnit = TimeUnit.MILLISECONDS)
    @Measurement(iterations = 5, time = 500, timeUnit = TimeUnit.MILLISECONDS)
    public void cpu_factorial(MyState mystate, Blackhole blackhole) {
        // This is a demo/sample template for building your JMH benchmarks. Edit as needed.
        // Put your benchmark code here.

        long p=1;
        long minimum=mystate.n -1000;
        long maximum=mystate.n;
        Random rand = new Random();
        long randomNum = minimum + rand.nextLong((maximum - minimum) + 1);
        for (int i=1;i<=randomNum;i++)
        {
            p = p*i;
        }
        blackhole.consume(p);
    }

}
