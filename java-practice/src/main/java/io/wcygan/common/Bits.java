package io.wcygan.common;

public class Bits {
    public static int multiplyByTwo(int n) {
        return n << 1;
    }

    public static int leftShiftK(int n, int k) {
        return n << k;
    }

    public static int toggleKthBit(int n, int k) {
        n ^= (1 << k);
        return n;
    }

    public static int clearKthBit(int n, int k) {
        return n & ~(1 << k);
    }

    public static int intersection(int left, int right) {
        return left & right;
    }

    public static int union(int left, int right) {
        return left | right;
    }
}
