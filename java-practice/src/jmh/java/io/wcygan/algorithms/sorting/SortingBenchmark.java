package io.wcygan.algorithms.sorting;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Threads;
import org.openjdk.jmh.infra.Blackhole;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

@State(Scope.Benchmark)
public class SortingBenchmark {

    private static final List<Integer> integers;

    static {
        integers = new ArrayList<>();
        IntStream.range(0, 1000).boxed().forEach(integers::add);
        Collections.shuffle(integers);
    }

    @Benchmark
    public void benchmarkQuickSort(Blackhole bh) {
        var lst = new ArrayList<>(integers);
        SortingAlgorithm<Integer> sorter = new QuickSort<>();
        sorter.sort(lst);
        bh.consume(lst.size());
    }

    @Benchmark
    public void benchmarkMergeSort(Blackhole bh) {
        var lst = new ArrayList<>(integers);
        SortingAlgorithm<Integer> sorter = new MergeSort<>();
        sorter.sort(lst);
        bh.consume(lst.size());
    }

    @Threads(Threads.MAX)
    @Benchmark
    public void benchmarkParallelMergeSort(Blackhole bh) {
        var lst = new ArrayList<>(integers);
        SortingAlgorithm<Integer> sorter = new ParallelMergeSort<>();
        sorter.sort(lst);
        bh.consume(lst.size());
    }

    @Benchmark
    public void benchmarkHeapSort(Blackhole bh) {
        var lst = new ArrayList<>(integers);
        SortingAlgorithm<Integer> sorter = new HeapSort<>();
        sorter.sort(lst);
        bh.consume(lst.size());
    }
}
