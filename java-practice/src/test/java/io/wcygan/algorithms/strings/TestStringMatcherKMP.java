package io.wcygan.algorithms.strings;

import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class TestStringMatcherKMP {
    @Test
    public void testFindFirstIndex_Exists() {
        String text = "Hello, World!";
        String pattern = "World!";
        StringMatcher matcher = StringMatcher.kmp(pattern);
        int index = matcher.firstIndex(text);

        assertThat(index).isPositive();
        assertThat(index).isEqualTo(text.indexOf("World!"));
    }

    @Test
    public void testFindFirstIndex_doesNotExist() {
        String text = "Hello, World!";
        String pattern = "World!!!!!";
        StringMatcher matcher = StringMatcher.kmp(pattern);
        int index = matcher.firstIndex(text);

        assertThat(index).isNegative();
    }
}
