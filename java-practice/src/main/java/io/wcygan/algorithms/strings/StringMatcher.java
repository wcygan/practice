package io.wcygan.algorithms.strings;

public interface StringMatcher {

    default int firstIndex(String txt) {
        return firstIndex(txt.toCharArray());
    }

    int firstIndex(char[] txt);

    static StringMatcher kmp(String txt) {
        return new StringMatcherKMP(txt);
    }

    static StringMatcher bruteforce(String txt) {
        return new StringMatcherBruteForce(txt);
    }
}
