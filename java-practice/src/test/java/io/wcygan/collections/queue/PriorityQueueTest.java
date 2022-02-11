package io.wcygan.collections.queue;

import com.pholser.junit.quickcheck.From;
import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import io.wcygan.common.Utilities;
import io.wcygan.testutils.IntegerListGenerator;
import org.junit.runner.RunWith;

import java.time.Instant;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@RunWith(JUnitQuickcheck.class)
public class PriorityQueueTest {
    public final Comparator<Event> orderByDate = Comparator.comparing(o -> o.date);

    @Property(trials = 25)
    public void exercisePriorityQueue(@From(IntegerListGenerator.class) List<Integer> items) {
        var q = new PriorityQueue<>(orderByDate);
        var uniques = Utilities.uniques(items);

        var now = Instant.now();
        var events = uniques.stream().map(x ->
                new Event(Date.from(now.minusSeconds(x)), x)
        ).toList();

        events.forEach(q::add);

        uniques.stream().sorted().forEach(x -> {
            var top = q.remove();
            assertEquals(x, top.descriptor);
        });
    }

    private static class Event {
        Date date;
        int descriptor;

        Event(Date date, int descriptor) {
            this.date = date;
            this.descriptor = descriptor;
        }

    }
}
