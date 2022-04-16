package io.wcygan.concurrent.executors;

import com.linkedin.parseq.Task;
import io.wcygan.algorithms.numbers.PrimeFactorization;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Stream;

public class ParSeqExecutorTest extends ParSeqTestBase {

    @Test
    public void itWorks() {
        Task<List<Integer>> a = Task.callable(() -> PrimeFactorization.primeFactors(10));
        Assertions.assertEquals(
                Stream.of(2, 5).sorted().toList(),
                runAndWait(a).stream().sorted().toList());
    }

    @Test
    public void testPar() {
        Task<?> par = Task.par(
                Task.callable(() -> PrimeFactorization.primeFactors(920)),
                Task.callable(() -> PrimeFactorization.primeFactors(242406)),
                Task.callable(() -> PrimeFactorization.primeFactors(992922)),
                Task.callable(() -> PrimeFactorization.primeFactors(4567822)));
        Assertions.assertNotNull(runAndWait(par));
    }
}
