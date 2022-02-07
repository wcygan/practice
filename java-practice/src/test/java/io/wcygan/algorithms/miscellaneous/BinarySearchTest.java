package io.wcygan.algorithms.miscellaneous;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

public class BinarySearchTest {
    private static Stream<Arguments> binarySearchData() {
        return Stream.of(
                Arguments.of(List.of(1, 2, 3), 1, 0),
                Arguments.of(List.of(1, 2, 3), 2, 1),
                Arguments.of(List.of(1, 2, 3), 3, 2),
                Arguments.of(List.of(-10, -9, -8, 1, 2, 3, 4, 5, 6), -9, 1));
    }

    @ParameterizedTest
    @MethodSource("binarySearchData")
    public void itWorks(List<Integer> data, Integer toFind, Integer expectedIndex) {
        Integer actualIndex = BinarySearch.search(data, toFind);
        Assertions.assertEquals(actualIndex, expectedIndex);
    }
}
