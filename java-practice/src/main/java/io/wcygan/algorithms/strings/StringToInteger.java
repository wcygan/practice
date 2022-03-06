package io.wcygan.algorithms.strings;

public class StringToInteger {

    private static final Integer ZERO_DECIMAL_ASCII_VALUE = 48;

    public static Integer parse(String s) {
        int sum = 0;
        int[] chars = s.chars().toArray();
        int len = chars.length;

        int start = 0;
        boolean negative = false;
        if (chars[0] == '-') {
            negative = true;
            start++;
        }

        for (int i = start; i < len; i++) {
            var multiplier = Math.pow(10, len - i - 1);
            var intValue = (chars[i] - ZERO_DECIMAL_ASCII_VALUE);
            sum += multiplier * intValue;
        }

        return negative ? (-1 * sum) : sum;
    }
}
