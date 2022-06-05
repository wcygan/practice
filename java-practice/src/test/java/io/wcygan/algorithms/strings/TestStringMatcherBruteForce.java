package io.wcygan.algorithms.strings;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TestStringMatcherBruteForce {

    @Test
    public void happyPath() {
        String pattern = "ello";
        char[] a = "Hello".toCharArray();
        StringMatcher bruteforce = StringMatcher.bruteforce(pattern);
        int idx = bruteforce.firstIndex(a);
        assertThat(idx).isPositive();
    }
}
