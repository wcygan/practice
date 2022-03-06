package io.wcygan.gotchas;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestStringPool {
    /**
     * "one" and "two" are both in the string pool
     */
    @Test
    public void stringAreEqual() {
        var one = "Hello";
        var two = "Hello";
        Assertions.assertSame(one, two);
        Assertions.assertEquals(one, two);
    }

    /**
     * "one" is located in the string pool
     * "two" is located in the heap, but not the string pool
     */
    @Test
    public void stringAreNotEqual() {
        var one = "Hello";
        var two = new String("Hello");
        Assertions.assertNotSame(one, two);
        Assertions.assertEquals(one, two);
    }
}
