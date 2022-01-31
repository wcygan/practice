package io.wcygan.collections.queue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class SimpleUnboundedQueueTest {
    @Test
    public void addAndRemove() {
        List<Integer> expected = List.of(1, 2, 3);
        Queue<Integer> queue = new SimpleUnboundedQueue<>();
        expected.forEach(queue::add);
        expected.forEach(element -> Assertions.assertEquals(element, queue.remove()));
    }
}
