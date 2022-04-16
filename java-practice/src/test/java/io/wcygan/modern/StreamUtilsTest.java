package io.wcygan.modern;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class StreamUtilsTest {

    private static Stream<Arguments> listDataProvider() {
        return Stream.of(
                Arguments.of(List.of(1, 2, 3), List.of(2), PredicateUtils.IS_EVEN),
                Arguments.of(List.of(1, 2, 3), List.of(1, 3), PredicateUtils.IS_ODD));
    }

    @ParameterizedTest
    @MethodSource("listDataProvider")
    public void testFilter(List<Integer> given, List<Integer> expected, Predicate<Integer> predicate) {
        List<Integer> result = StreamUtils.filter(given, predicate);
        Assertions.assertEquals(expected, result);
    }
}
