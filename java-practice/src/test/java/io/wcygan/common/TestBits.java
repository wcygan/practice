package io.wcygan.common;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestBits {

    private static final Integer ZERO = 0b0000;
    private static final Integer ONE = 0b0001;
    private static final Integer TWO = 0b0010;
    private static final Integer THREE = 0b0011;
    private static final Integer FOUR = 0b0100;

    @Test
    public void testUnion() {
        assertEquals(ZERO, Bits.union(ZERO, ZERO));
        assertEquals(ONE, Bits.union(ZERO, ONE));
        assertEquals(THREE, Bits.union(ONE, TWO));
    }

    @Test
    public void testIntersection() {
        assertEquals(ONE, Bits.intersection(THREE, ONE));
        assertEquals(ZERO, Bits.intersection(TWO, ONE));
        assertEquals(ZERO, Bits.intersection(FOUR, ZERO));
        assertEquals(64, Bits.intersection(127, 64));
        assertEquals(0x01, Bits.intersection(0xff, 0x01));
        assertEquals(0x00, Bits.intersection(0xaa, 0x44));
        assertEquals(0b000100, Bits.intersection(0b000100, 0b001100));
        assertEquals(0b010101, Bits.intersection(0b110101, 0b011101));
    }

    @Test
    public void testMultiplyByTwo() {
        assertEquals(ZERO, Bits.multiplyByTwo(ZERO));
        assertEquals(TWO, Bits.multiplyByTwo(ONE));
        assertEquals(FOUR, Bits.multiplyByTwo(TWO));
    }

    @Test
    public void testToggleKthBit() {
        assertEquals(ONE, Bits.toggleKthBit(ZERO, 0));
        assertEquals(THREE, Bits.toggleKthBit(ONE, 1));
        assertEquals(6, Bits.toggleKthBit(TWO, 2));
    }

    @Test
    public void testLeftShiftK() {
        assertEquals(8, Bits.leftShiftK(1, 3));
        assertEquals(64, Bits.leftShiftK(2, 5));
        assertEquals(96, Bits.leftShiftK(3, 5));
    }

    @Test
    public void testClearKthBit() {
        assertEquals(ZERO, Bits.clearKthBit(FOUR, TWO));
        assertEquals(ONE, Bits.clearKthBit(THREE, ONE));
        assertEquals(TWO, Bits.clearKthBit(TWO, ZERO));
    }
}
