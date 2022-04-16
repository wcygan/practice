package io.wcygan.collections.queue;

import com.pholser.junit.quickcheck.From;
import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import io.wcygan.common.Utilities;
import io.wcygan.testutils.IntegerListGenerator;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;

import java.time.Instant;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(JUnitQuickcheck.class)
public class PriorityQueueTest {
    Comparator<Event> compareByInstant = Comparator.comparing(e -> e.date);

    private List<Queue<Integer>> intPriorityQueueProvider() {
        return List.of(new DWayHeap<>(Integer::compare), new BinaryHeap<>(Integer::compare));
    }

    @Test
    public void priorityByInstant() {
        List.of(new BinaryHeap<>(compareByInstant), new DWayHeap<>(compareByInstant))
                .forEach(q -> {
                    var now = Instant.now();
                    var e1 = new Event(Date.from(now.minusSeconds(100)), 1);
                    var e2 = new Event(Date.from(now.minusSeconds(200)), 2);
                    var e3 = new Event(Date.from(now.minusSeconds(300)), 3);
                    var e4 = new Event(Date.from(now.minusSeconds(50)), 4);
                    var e5 = new Event(Date.from(now.minusSeconds(5000)), 5);
                    List.of(e3, e1, e2).forEach(q::add);
                    assertEquals(e3, q.remove());
                    assertEquals(e2, q.remove());
                    q.add(e4);
                    q.add(e5);
                    assertEquals(e5, q.remove());
                    assertEquals(e1, q.remove());
                    assertEquals(e4, q.remove());
                });
    }

    @Property(trials = 25)
    public void addAllRemoveAllThreeTimes(@From(IntegerListGenerator.class) List<Integer> items) {
        for (var q : intPriorityQueueProvider()) {
            var uniques = Utilities.uniques(items);
            for (int i = 0; i < 3; i++) {
                uniques.forEach(q::add);
                uniques.stream().sorted(Integer::compare).forEach(x -> {
                    assertEquals(x, q.remove());
                });
            }
        }
    }

    @Property(trials = 25)
    public void halfAndQuarter(@From(IntegerListGenerator.class) List<Integer> items) {
        int size = items.size();
        for (var q : intPriorityQueueProvider()) {
            var uniques = Utilities.uniques(items);
            var sorted = uniques.stream().sorted().toList();
            uniques.forEach(q::add);

            // remove half
            int half = size / 2;
            for (int i = half; i < size; i++) {
                var want = sorted.get(i - half);
                var got = q.remove();
                assertEquals(want, got);
            }

            // add a quarter
            int quarter = half / 2;
            for (int i = 0; i < quarter; i++) {
                q.add(sorted.get(i));
            }

            // remove the quarter
            for (int i = 0; i < quarter; i++) {
                assertEquals(sorted.get(i), q.remove());
            }
        }
    }

    public static class Event {
        Date date;
        int descriptor;

        Event(Date date, int descriptor) {
            this.date = date;
            this.descriptor = descriptor;
        }
    }
}
