package io.wcygan.modern;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class OptionalUtilsTest {
    private static Stream<Arguments> optionalDataProvider() {
        return Stream.of(
                Arguments.of(Optional.of(1), Optional.of(1), PredicateUtils.IS_ODD),
                Arguments.of(Optional.of(2), Optional.of(2), PredicateUtils.IS_EVEN),
                Arguments.of(Optional.of(1), Optional.empty(), PredicateUtils.IS_EVEN));
    }

    @ParameterizedTest
    @MethodSource("optionalDataProvider")
    public void testFilter(Optional<Integer> given, Optional<Integer> expected, Predicate<Integer> predicate) {
        Optional<Integer> result = OptionalUtils.filter(given, predicate);
        Assertions.assertEquals(expected, result);
    }
}
