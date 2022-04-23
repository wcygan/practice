package io.wcygan.concurrent.collections.misc;

import javax.annotation.concurrent.ThreadSafe;
import java.util.concurrent.atomic.AtomicLong;

@ThreadSafe
public class NonblockingRandom {
    AtomicLong seed;

    public NonblockingRandom(long seed) {
        this.seed = new AtomicLong(seed);
    }

    static long xorShift(long x) {
        x ^= (x << 21);
        x ^= (x >>> 35);
        x ^= (x << 4);
        return x;
    }

    public long nextLong(long n) {
        while (true) {
            long currentSeed = seed.get();
            long nextSeed = xorShift(currentSeed);
            if (seed.compareAndSet(currentSeed, nextSeed)) {
                long remainder = currentSeed % nextSeed;
                return (remainder > 0) ? remainder : remainder + n;
            }
        }
    }
}
