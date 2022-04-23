package io.wcygan.concurrent.collections;

import io.wcygan.concurrent.collections.misc.NonblockingRandom;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class NonblockingRandomTest {

    private static final int LIMIT = 10000;

    @Test
    public void testRandom() {
        NonblockingRandom random = new NonblockingRandom(System.nanoTime());
        long curr = random.nextLong(System.nanoTime());
        for (int i = 0; i < LIMIT; i++) {
            long next = random.nextLong(curr);
            Assertions.assertNotEquals(curr, next);
            curr = next;
        }
    }
}
