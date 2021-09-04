package io.wcygan;

import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import org.junit.runner.RunWith;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(JUnitQuickcheck.class)
public class QuickCheckTest {
    @Property
    public void concatenationLength(String s1, String s2) {
        assertEquals(s1.length() + s2.length(), (s1 + s2).length());
    }

    @Property(trials = 10)
    public void testConcat(String s1, String s2) {
        String joined = App.concat(s1, s2);
        assertEquals(s1.length() + s2.length(), joined.length());
    }
}
