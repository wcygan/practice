package io.wcygan.algorithms.strings;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestStringToInteger {
    @Test
    public void itWorks() {
        String num = "1234";
        Integer expected = 1234;
        Assertions.assertEquals(expected, StringToInteger.parse(num));
    }

    @Test
    public void itWorksOnNegatives() {
        String num = "-1234";
        Integer expected = -1234;
        Assertions.assertEquals(expected, StringToInteger.parse(num));
    }

    @Test
    public void itWorksAgain() {
        String num = "124434";
        Integer expected = 124434;
        Assertions.assertEquals(expected, StringToInteger.parse(num));
    }

    @Test
    public void itWorksOnNegativesAgain() {
        String num = "-1123234";
        Integer expected = -1123234;
        Assertions.assertEquals(expected, StringToInteger.parse(num));
    }
}
