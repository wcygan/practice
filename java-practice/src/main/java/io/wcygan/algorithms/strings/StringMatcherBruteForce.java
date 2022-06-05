package io.wcygan.algorithms.strings;

import java.util.Arrays;

public class StringMatcherBruteForce implements StringMatcher {

    char[] pattern;
    int patternLength;

    public StringMatcherBruteForce(String pattern) {
        this(pattern.toCharArray());
    }

    public StringMatcherBruteForce(char[] txt) {
        this.pattern = txt;
        this.patternLength = txt.length;
    }

    @Override
    public int firstIndex(char[] txt) {
        int n = txt.length;

        for (int i = 0; i <= n - patternLength; i++) {
            int v =  Arrays.compare(pattern, 0, patternLength, txt, i, i + patternLength);
            if (0 == v) {
                return i;
            }
        }

        return -1;
    }
}
