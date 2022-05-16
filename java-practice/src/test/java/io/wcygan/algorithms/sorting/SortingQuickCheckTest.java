package io.wcygan.algorithms.sorting;

import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.generator.Size;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;

import java.util.Collections;
import java.util.List;

@RunWith(JUnitQuickcheck.class)
public class SortingQuickCheckTest {

    private static final List<SortingAlgorithm<Integer>> ALGORITHMS =
            List.of(new QuickSort<>(), new MergeSort<>(), new ParallelMergeSort<>(), new HeapSort<>());

    @Property(trials = 50)
    public void testSortingAlgorithms(@Size(max = 100) List<Integer> actual) {
        List<Integer> expected = actual.stream().sorted().toList();
        ALGORITHMS.forEach(algorithm -> verifySort(algorithm, expected, actual));
    }

    private void verifySort(SortingAlgorithm<Integer> algorithm, List<Integer> expected, List<Integer> actual) {
        Collections.shuffle(actual);
        algorithm.sort(actual);
        Assertions.assertEquals(
                expected, actual, "Testing " + algorithm.getClass().getName());
    }
}
