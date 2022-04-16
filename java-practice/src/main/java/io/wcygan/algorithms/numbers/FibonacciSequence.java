package io.wcygan.algorithms.numbers;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.math.BigInteger;

public class FibonacciSequence {
    public BigInteger getFibonacciNumberCached(int n) {
        if (n == 0) {
            return BigInteger.ZERO;
        } else if (n == 1) {
            return BigInteger.ONE;
        } else {
            return memo.getUnchecked(n - 1).add(memo.getUnchecked(n - 2));
        }
    }

    public BigInteger getFibonacciNumber(int n) {
        if (n == 0) {
            return BigInteger.ZERO;
        } else if (n == 1) {
            return BigInteger.ONE;
        } else {
            return getFibonacciNumber(n - 1).add(getFibonacciNumber(n - 2));
        }
    }

    private LoadingCache<Integer, BigInteger> memo =
            CacheBuilder.newBuilder().maximumSize(1000).build(CacheLoader.from(this::getFibonacciNumberCached));
}
