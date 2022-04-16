package io.wcygan.algorithms.dynamic_programming;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class RodCuttingTest {

    private static Stream<Arguments> rodCuttingData() {
        return Stream.of(
                Arguments.of(new int[] {1, 5, 8, 9, 10, 17, 17, 20}, 22),
                Arguments.of(new int[] {1, 5, 8, 9, 10, 17, 17, 20}, 22));
    }

    @ParameterizedTest
    @MethodSource("rodCuttingData")
    public void testSolution(int[] prices, int expectedValue) {
        int n = prices.length;
        Assertions.assertEquals(expectedValue, RodCutting.cutRodSlow(prices, n));
        Assertions.assertEquals(expectedValue, RodCutting.cutRodFast(prices, n));
    }
}
