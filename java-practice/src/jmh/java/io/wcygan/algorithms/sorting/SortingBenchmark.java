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

    /**
     * {@code
     * Benchmark                                            Mode  Cnt   Score   Error  Units
     * i.w.a.sorting.SortingBenchmark.classicSort             ss  100  12.142 ± 0.081  ms/op
     * i.w.a.sorting.SortingBenchmark.heapsort                ss  100  13.768 ± 0.073  ms/op
     * i.w.a.sorting.SortingBenchmark.mergesort               ss  100  16.790 ± 0.112  ms/op
     * i.w.a.sorting.SortingBenchmark.parallelMergesort       ss  100  17.958 ± 0.835  ms/op
     * i.w.a.sorting.SortingBenchmark.parallelStandardSort    ss  100   2.338 ± 0.091  ms/op
     * i.w.a.sorting.SortingBenchmark.quicksort               ss  100  12.859 ± 0.088  ms/op
     * i.w.a.sorting.SortingBenchmark.standardSort            ss  100  13.733 ± 0.061  ms/op
     * }
     */
    private static final List<Integer> integers;

    static {
        integers = new ArrayList<>();
        IntStream.range(0, 100_000).boxed().forEach(integers::add);
        Collections.shuffle(integers);
    }

    @Benchmark
    public List<Integer> quicksort(Blackhole bh) {
        var lst = new ArrayList<>(integers);
        SortingAlgorithm<Integer> sorter = new QuickSort<>();
        sorter.sort(lst);
        return lst;
    }

    @Benchmark
    public List<Integer> mergesort(Blackhole bh) {
        var lst = new ArrayList<>(integers);
        SortingAlgorithm<Integer> sorter = new MergeSort<>();
        sorter.sort(lst);
        return lst;
    }

    @Threads(Threads.MAX)
    @Benchmark
    public List<Integer> parallelMergesort(Blackhole bh) {
        var lst = new ArrayList<>(integers);
        SortingAlgorithm<Integer> sorter = new ParallelMergeSort<>();
        sorter.sort(lst);
        return lst;
    }

    @Benchmark
    public List<Integer> heapsort(Blackhole bh) {
        var lst = new ArrayList<>(integers);
        SortingAlgorithm<Integer> sorter = new HeapSort<>();
        sorter.sort(lst);
        return lst;
    }

    @Benchmark
    public List<Integer> classicSort(Blackhole bh) {
        var lst = new ArrayList<>(integers);
        Collections.sort(lst);
        return lst;
    }

    @Benchmark
    public List<Integer> standardSort(Blackhole bh) {
        return integers.stream().sorted().toList();
    }

    @Benchmark
    public List<Integer> parallelStandardSort(Blackhole bh) {
        return integers.parallelStream().sorted().toList();
    }
}
