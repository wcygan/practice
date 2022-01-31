package io.wcygan.gotchas;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class LazyOptionalTest {

    @Test
    public void theyAreDifferent() {
        LazyOptional test = new LazyOptional();
        Assertions.assertNotEquals(test.lazy(), test.eager());
    }
}
