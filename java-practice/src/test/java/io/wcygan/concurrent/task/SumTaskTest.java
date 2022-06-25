package io.wcygan.concurrent.task;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class SumTaskTest {

    public static Integer LEFT_BOUND = 0;
    public static Integer RIGHT_BOUND = 1000;

    @Test
    public void itWorks() {
        ForkJoinPool pool = new ForkJoinPool(10);
        List<Integer> lst = IntStream.range(LEFT_BOUND, RIGHT_BOUND).boxed().toList();
        Long expectedValue = LongStream.range(LEFT_BOUND, RIGHT_BOUND).sum();
        Assertions.assertEquals(expectedValue, pool.invoke(new SumTask(lst, 0, lst.size())));
    }
}
