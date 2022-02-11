package io.wcygan.testutils;

import com.pholser.junit.quickcheck.generator.ComponentizedGenerator;
import com.pholser.junit.quickcheck.generator.GenerationStatus;
import com.pholser.junit.quickcheck.random.SourceOfRandomness;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class IntegerListGenerator extends ComponentizedGenerator<List> {
    boolean generatedEmptyList = false;

    public IntegerListGenerator() {
        super(List.class);
    }

    @Override
    public List<Integer> generate(SourceOfRandomness sourceOfRandomness, GenerationStatus generationStatus) {
        if (!generatedEmptyList) {
            generatedEmptyList = true;
            return new ArrayList<>();
        }
        int rng = sourceOfRandomness.nextInt(0, 20);
        int listSize = 0;
        if (rng >= 0 && rng <= 16) {
            listSize = sourceOfRandomness.nextInt(0, 100);
        } else if (rng >= 17 && rng <= 19) {
            listSize = sourceOfRandomness.nextInt(100, 1000);
        } else if (rng == 20) {
            listSize = sourceOfRandomness.nextInt(1000, 10000);
        }

        return IntStream.range(0, listSize)
                .map(i -> sourceOfRandomness.nextInt(Integer.MIN_VALUE, Integer.MAX_VALUE))
                .distinct()
                .boxed()
                .toList();
    }

    @Override
    public int numberOfNeededComponents() {
        return 1;
    }
}