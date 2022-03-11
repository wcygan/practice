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
     * <pre>
     * Benchmark                                            Mode  Cnt   Score   Error  Units
     * i.w.a.sorting.SortingBenchmark.classicSort             ss  100  11.862 ± 0.078  ms/op
     * i.w.a.sorting.SortingBenchmark.heapsort                ss  100  13.471 ± 0.053  ms/op
     * i.w.a.sorting.SortingBenchmark.mergesort               ss  100  16.587 ± 0.115  ms/op
     * i.w.a.sorting.SortingBenchmark.parallelStandardSort    ss  100   2.506 ± 0.086  ms/op
     * i.w.a.sorting.SortingBenchmark.quicksort               ss  100  12.454 ± 0.184  ms/op
     * i.w.a.sorting.SortingBenchmark.standardSort            ss  100  13.430 ± 0.053  ms/op
     *
     * parallelMergeSort usually runs out of memory :(
     * </pre>
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
