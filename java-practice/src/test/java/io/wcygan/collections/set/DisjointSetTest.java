package io.wcygan.collections.set;

import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.generator.Size;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@RunWith(JUnitQuickcheck.class)
public class DisjointSetTest {

    @Test
    public void happyPath() {
        var dj = new DisjointSet<Integer>();
        var one = 1;
        var two = 2;
        var three = 3;

        // add all
        assertEquals(one, dj.find(one));
        assertEquals(two, dj.find(two));
        assertEquals(three, dj.find(three));

        // first union
        dj.union(one, two);
        assertEquals(one, dj.find(two));
        assertEquals(one, dj.find(one));
        assertEquals(three, dj.find(three));

        // second union
        dj.union(two, three);
        assertEquals(one, dj.find(two));
        assertEquals(one, dj.find(one));
        assertEquals(one, dj.find(three));
    }

    @Property
    public void exerciseDisjointSet(@Size(min = 100, max = 1000) List<Integer> items) {
        var dj = new DisjointSet<Integer>();
        var first = items.get(0);

        for (var item : items) {
            assertEquals(item, dj.find(item));
            dj.union(first, item);
            assertEquals(first, dj.find(item));
            dj.union(item, first);
            assertEquals(first, dj.find(item));
        }
    }

    @Test
    public void joinSets() {
        var dj = new DisjointSet<Integer>();

        var s1 = List.of(1, 2, 3);
        var s2 = List.of(4, 5, 6);
        var s3 = List.of(7, 8, 9);

        List.of(s1, s2, s3).forEach(lst -> {
            var fst = lst.get(0);
            lst.forEach(item -> {
                dj.union(fst, item);
                assertEquals(fst, dj.find(item));
            });
        });

        List.of(s1, s2, s3).forEach(lst -> assertEquals(lst.get(0), dj.find(lst.get(2))));

        dj.union(s1.get(0), s2.get(0));
        assertEquals(dj.find(s1.get(0)), dj.find(s2.get(0)));
        assertNotEquals(dj.find(s1.get(0)), dj.find(s3.get(0)));

        dj.union(s1.get(0), s3.get(0));
        assertEquals(dj.find(s1.get(0)), dj.find(s3.get(0)));
    }
}
