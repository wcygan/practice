package io.wcygan.gotchas;

import java.util.Optional;
import java.util.function.Supplier;

/**
 * {@link java.util.Optional#orElse(Object)} is an eagerly-resolved method. {@link
 * java.util.Optional#orElseGet(Supplier)} is a lazily-resolved method.
 *
 * <p>To see this in action, we can compare the result of {@link LazyOptional#lazy()} with the
 * result of {@link LazyOptional#eager()} to verify that they differ
 *
 * <p>For more information, see https://www.baeldung.com/java-optional-or-else-vs-or-else-get
 */
public class LazyOptional {

    /* eagerly evaluate the "orElse" branch */
    public Integer eager() {
        Counter counter = new Counter();
        Optional.of(1).orElse(counter.countUp());
        return counter.count();
    }

    /* lazily evaluate the "orElseGet" branch */
    public Integer lazy() {
        Counter counter = new Counter();
        Optional.of(1).orElseGet(counter::countUp);
        return counter.count();
    }

    private static class Counter {
        private int ct = 0;

        public int countUp() {
            ct += 1;
            return ct;
        }

        public int count() {
            return ct;
        }
    }
}
