package io.wcygan.algorithms.dynamic_programming;

/**
 * Given a rod of length n inches and a table of prices that includes prices of all pieces of size
 * smaller than n. Determine the maximum value obtainable by cutting up the rod and selling the
 * pieces.
 */
public class RodCutting {
    public static int cutRodSlow(int[] prices, int n) {
        if (n <= 0) return 0;
        int maxValue = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            maxValue = Math.max(maxValue, prices[i] + cutRodSlow(prices, n - i - 1));
        }
        return maxValue;
    }

    public static int cutRodFast(int[] prices, int n) {
        int[] dp = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            int q = Integer.MIN_VALUE;
            for (int j = 0; j < i; j++) {
                q = Math.max(q, prices[j] + dp[i - j - 1]);
            }
            dp[i] = q;
        }

        return dp[n];
    }
}
