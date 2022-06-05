package io.wcygan.algorithms.strings;

public interface StringMatcher {
    default int search(String txt) {
        return search(txt.toCharArray());
    }

    int search(char[] txt);

    static StringMatcher kmp(String txt) {
        return new StringMatcherKMP(txt);
    }
}
