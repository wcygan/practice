package io.wcygan.concurrent;

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
        Stream.of(2, 5).sorted().toList(), runAndWait(a).stream().sorted().toList());
  }
}
