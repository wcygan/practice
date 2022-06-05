package io.wcygan.algorithms.strings;

import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class TestStringMatcherKMP {
    @Test
    public void itWorks() {
        String text = "Hello, World!";
        String pattern = "World!";
        StringMatcher matcher = StringMatcher.kmp(text);
        int index = matcher.search(pattern);

        assertThat(index).isPositive();
        assertThat(index).isEqualTo(text.indexOf("World!"));
    }
}
