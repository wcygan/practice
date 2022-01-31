package io.wcygan.modern;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.function.Predicate;
import java.util.stream.Stream;

public class PredicateUtilsTest {

    private static final Integer EVEN_INTEGER = 2;
    private static final Integer ODD_INTEGER = 1;

    private static Stream<Arguments> optionalDataProvider() {
        return Stream.of(
                Arguments.of(ODD_INTEGER, PredicateUtils.IS_ODD, true),
                Arguments.of(ODD_INTEGER, PredicateUtils.IS_EVEN, false),
                Arguments.of(EVEN_INTEGER, PredicateUtils.IS_ODD, false),
                Arguments.of(EVEN_INTEGER, PredicateUtils.IS_EVEN, true));
    }

    @ParameterizedTest
    @MethodSource("optionalDataProvider")
    public void testPredicate(Integer given, Predicate<Integer> predicate, Boolean shouldSucceed) {
        if (predicate.test(given) != shouldSucceed) {
            Assertions.fail();
        }
    }
}
