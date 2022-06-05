package io.wcygan.algorithms.strings;

import com.google.common.collect.ImmutableList;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;
import static org.assertj.core.api.Assertions.assertThat;


public class TestStringMatchers {

    private static final String PATTERN = "Hello";

    private static final List<StringMatcher> MATCHERS = ImmutableList.of(
            StringMatcher.bruteforce(PATTERN),
            StringMatcher.kmp(PATTERN)
    );

    private static Stream<Arguments> firstIndexDataProvider() {
        return Stream.of(
                Arguments.of("Hello, World!", 0),
                Arguments.of("Hello, Hello!", 0),
                Arguments.of("ello, Hello!", 6),
                Arguments.of("ellolodjaskdbahjsjdashello", -1),
                Arguments.of("ellolodjaskdbahjsjdasHello", 21),
                Arguments.of("1".repeat(1000) + "Hello", 1000),
                Arguments.of("1".repeat(5234) + "Hello", 5234)
        );
    }

    @ParameterizedTest
    @MethodSource("firstIndexDataProvider")
    public void checkFirstIndex(String txt, int expected) {
        MATCHERS.forEach(m -> {
            int idx = m.firstIndex(txt);
            assertThat(idx).isEqualTo(expected);
        });
    }
}
