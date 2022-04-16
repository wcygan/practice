package io.wcygan.algorithms.gotchas;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;

@State(Scope.Benchmark)
public class CacheLinesBenchmark {

    /**
     * <pre>
     *
     * (Run on 2021 M1 Max Macbook Pro)
     * Benchmark                                          Mode  Cnt  Score   Error  Units
     * i.w.a.gotchas.CacheLinesBenchmark.touchEveryItem     ss  100  0.171 ± 0.004  ms/op
     * i.w.a.gotchas.CacheLinesBenchmark.touchEveryLine     ss  100  0.143 ± 0.006  ms/op
     *
     * We see that these two benchmarks are relatively similar in runtime. This is due to
     * the time taken by the memory bus in transferring the contents of the array {@link this#testData}
     * from main memory into the CPU cache
     *
     * </pre>
     */
    private final int ARR_SIZE = 2 * 1024 * 1024;

    private final int[] testData = new int[ARR_SIZE];

    @Benchmark
    public void touchEveryItem(Blackhole bh) {
        for (int i = 0; i < testData.length; i++) {
            testData[i]++;
        }
        bh.consume(testData[testData.length - 1]);
    }

    @Benchmark
    public void touchEveryLine(Blackhole bh) {
        for (int i = 0; i < testData.length; i += 16) {
            testData[i]++;
        }
        bh.consume(testData[testData.length - 1]);
    }
}
