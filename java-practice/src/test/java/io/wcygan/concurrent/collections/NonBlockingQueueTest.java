package io.wcygan.concurrent.collections;

import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.generator.InRange;
import com.pholser.junit.quickcheck.generator.Size;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import io.wcygan.collections.queue.Queue;
import io.wcygan.concurrent.collections.misc.NonblockingRandom;
import io.wcygan.concurrent.collections.queue.NonblockingQueue;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.LongStream;

@RunWith(JUnitQuickcheck.class)
public class NonBlockingQueueTest {

    private static final Integer ONE_HUNDRED = 100;

    @Property(trials = 25)
    public void sequentialAddAndRemove(@Size(max = 50) List<Integer> expected) {
        Queue<Integer> queue = new NonblockingQueue<>();
        expected.forEach(queue::add);
        expected.forEach(element -> Assertions.assertEquals(element, queue.remove()));
    }

    @Property(trials = 25)
    public void concurrentAddAndRemove(@InRange(minInt = 2, maxInt = 100) Integer numThreads) {
        Queue<Long> queue = new NonblockingQueue<>();
        NonblockingRandom random = new NonblockingRandom(System.nanoTime());

        List<Thread> workers = new ArrayList<>();
        for (int i = 0; i < numThreads; i++) {
            Thread adder = new Thread(addTwo(queue, random));
            Thread remover = new Thread(removeOne(queue));
            adder.start();
            remover.start();
            workers.add(adder);
            workers.add(remover);
        }

        workers.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                Assertions.fail("Couldn't join" + t.getName());
            }
        });

        List<Long> queueContents = new ArrayList<>();
        Long next = queue.remove();
        while (next != null) {
            queueContents.add(next);
            next = queue.remove();
        }

        Assertions.assertEquals(numThreads, queueContents.size());
    }

    private Runnable addTwo(Queue<Long> queue, NonblockingRandom random) {
        return () -> {
            for (int i = 0; i < 2; i++) {
                queue.add(random.nextLong(System.nanoTime()));
            }
        };
    }

    private Runnable removeOne(Queue<Long> queue) {
        return () -> {
            while (queue.remove() == null) {}
        };
    }

    @Test
    public void testOneHundredThousandAdds() {
        Queue<Long> queue = new NonblockingQueue<>();

        int numThreads = ONE_HUNDRED;
        List<Thread> workers = new ArrayList<>();
        for (int i = 0; i < numThreads; i++) {
            Thread adder = new Thread(addOneThousandTimes(queue));
            adder.start();
            workers.add(adder);
        }

        workers.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                Assertions.fail("Couldn't join" + t.getName());
            }
        });

        List<Long> queueContents = new ArrayList<>();
        Long next = queue.remove();
        while (next != null) {
            queueContents.add(next);
            next = queue.remove();
        }

        Assertions.assertEquals(100000, queueContents.size());
    }

    private Runnable addOneThousandTimes(Queue<Long> queue) {
        return () -> LongStream.range(0, 1000).forEach(queue::add);
    }
}
