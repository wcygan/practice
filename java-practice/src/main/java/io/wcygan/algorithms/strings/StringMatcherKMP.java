package io.wcygan.algorithms.strings;

// Described in page 1002 in Cormen Algorithms
// Implementation from https://algs4.cs.princeton.edu/53substring/KMP.java.html
public class StringMatcherKMP implements StringMatcher {

    private final int r;
    private final int m;
    private final int[][] dfa;

    public StringMatcherKMP(String pattern) {
        this(pattern.toCharArray(), 256);
    }

    public StringMatcherKMP(char[] pattern, int radix) {
        this.r = radix;
        this.m = pattern.length;
        this.dfa = new int[this.r][this.m];

        dfa[pattern[0]][0] = 1;

        for (int x = 0, j = 1; j < m; j++) {
            for (int c = 0; c < this.r; c++) {
                dfa[c][j] = dfa[c][x];
            }

            dfa[pattern[j]][j] = j + 1;
            x = dfa[pattern[j]][x];
        }
    }

    @Override
    public int firstIndex(char[] txt) {
        int n = txt.length;

        int i, j;
        for (i = 0, j = 0; i < n && j < this.m; i++) {
            j = dfa[txt[i]][j];
        }

        if (j == this.m) {
            return i - this.m;
        }

        return -1;
    }
}
