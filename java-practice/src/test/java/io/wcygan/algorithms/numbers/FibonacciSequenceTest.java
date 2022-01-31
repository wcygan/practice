package io.wcygan.algorithms.numbers;

import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.generator.InRange;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;

import java.math.BigInteger;

@RunWith(JUnitQuickcheck.class)
public class FibonacciSequenceTest {

    @Property(trials = 25)
    public void testFibonacciSequence(@InRange(minInt = 1, maxInt = 20) int n) {
        BigInteger a = new FibonacciSequence().getFibonacciNumberCached(n);
        BigInteger b = new FibonacciSequence().getFibonacciNumber(n);
        Assertions.assertEquals(a, b);
    }
}
