package io.wcygan.collections.list;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

public class LinkedListTest {
    private static final Random rnd = new Random();
    private static LinkedList<String> list;

    @BeforeEach
    public void setUp() {
        list = new LinkedList<>();
    }

    @Test
    public void isEmpty() {
        assertTrue(list.isEmpty());
        list.add("any");
        assertFalse(list.isEmpty());
        list.remove("any");
        assertTrue(list.isEmpty());
        list.add("any");
        assertFalse(list.isEmpty());
        list.removeTail();
        assertTrue(list.isEmpty());
    }

    @Test
    public void size() throws Exception {
        assertEquals(0, list.size());
        list.add("any");
        int howMany = 3 + rnd.nextInt(7);
        IntStream.range(0, howMany).forEach(i -> {
            list.add("" + i);
        });
        list.add("any");

        assertEquals(2 + howMany, list.size());

        assertFalse(list.remove("any").isEmpty());
        assertEquals(1 + howMany, list.size());
        assertFalse(list.remove("any").isEmpty());
        assertEquals(howMany, list.size());
        assertFalse(list.remove("any").hasValue());
        assertEquals(howMany, list.size(), "Shouldn' t decrement size when remove fails");
        assertFalse(list.removeTail().isEmpty());
        assertEquals(howMany - 1, list.size());
    }

    @Test
    public void add() throws Exception {
        LinkedList<Integer> list = new LinkedList<>();
        int maxVal = 10 + rnd.nextInt(10);
        IntStream.range(0, maxVal)
                .forEach(i -> assertEquals(i, (int) list.add(i).getValue()));
        IntStream.range(0, maxVal).forEach(i -> assertTrue(list.contains(i)));
    }

    @Test
    public void remove() throws Exception {
        LinkedList<Integer> list = new LinkedList<>();
        int maxVal = 10 + rnd.nextInt(10);
        java.util.List<Integer> values = IntStream.range(0, maxVal).boxed().collect(Collectors.toList());
        assertTrue(list.addAll(values));

        assertEquals(maxVal, list.size());
        Collections.shuffle(values);

        values.forEach(i -> assertSame(list.remove(i).getValue(), i));
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
    }

    @Test
    public void removeTail() throws Exception {
        LinkedList<Integer> list = new LinkedList<>();
        int maxVal = 10 + rnd.nextInt(10);
        java.util.List<Integer> values = IntStream.range(0, maxVal).boxed().collect(Collectors.toList());
        assertTrue(list.addAll(values));

        assertEquals(maxVal, list.size());

        int i = 0;
        while (!list.isEmpty()) {
            assertEquals(i++, list.removeTail().getValue());
        }
        assertEquals(i, maxVal);
    }

//    @Test
//    public void testMultiThreading() throws Exception {
//        int maxWait = 5;
//
//        LinkedList<String> list = new LinkedList<>();
//
//        ExecutorService executor = Executors.newFixedThreadPool(10);
//
//        java.util.List<String> englishWords = new ArrayList<>(Arrays.asList(
//                "this", "is", "just", "to", "test", "concurrent", "access", "for", "synchronized", "cache"));
//
//        java.util.List<String> italianWords = new ArrayList<>(Arrays.asList(
//                "prova",
//                "sul",
//                "funzionamento",
//                "di",
//                "una",
//                "cache+",
//                "condivisa",
//                "in",
//                "ambiente",
//                "multi-threaded"));
//
//        Function<java.util.List<String>, Runnable> heapFillerGen = (words) -> () -> words.forEach(w -> {
//            try {
//                list.add(w);
//                Thread.sleep(1 + rnd.nextInt(maxWait / 2));
//            } catch (InterruptedException e) {
//                throw new IllegalStateException(e);
//            }
//        });
//
//        Runnable englishWordsSetter = heapFillerGen.apply(englishWords);
//        Runnable italianWordsSetter = heapFillerGen.apply(italianWords);
//
//        Function<Integer, Runnable> heapGetterGen = (runs) -> () -> {
//            try {
//                Thread.sleep(1 + rnd.nextInt(maxWait));
//                IntStream.range(0, runs).forEach(j -> {
//                    list.removeTail();
//                });
//            } catch (InterruptedException e) {
//                throw new IllegalStateException(e);
//            }
//        };
//
//        int numRemoves = 5;
//        Runnable wordsGetter = heapGetterGen.apply(numRemoves);
//
//        executor.execute(englishWordsSetter);
//        executor.execute(italianWordsSetter);
//        // Make sure the first few words have been added;
//        Thread.sleep(2 * maxWait);
//
//        executor.execute(wordsGetter);
//
//        // Wait till we are sure all threads are done
//        try {
//            executor.awaitTermination(50 * maxWait, TimeUnit.MILLISECONDS);
//        } catch (InterruptedException e) {
//            throw new AssertionError("Computation was stuck");
//        }
//
//        // Check that all elements have been added to the list
//        assertEquals(
//                englishWords.size() + italianWords.size() - numRemoves,
//                list.size(),
//                "All elements should have been added");
//    }
}