package io.wcygan.questions.leetcode.hard.q432AllOne;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AllOneTest {

    @Test
    public void allOneEmpty() {
        AllOne allOne = new AllOne();
        assertThat(allOne.getMaxKey()).isEqualTo(AllOne.EMPTY);
        assertThat(allOne.getMinKey()).isEqualTo(AllOne.EMPTY);
    }

    @Test
    public void allOneA() {
        String key = "Hello";
        AllOne allOne = new AllOne();

        allOne.inc(key);
        assertThat(allOne.getMaxKey()).isEqualTo(key);

        allOne.dec(key);
        assertThat(allOne.getMaxKey()).isEqualTo(AllOne.EMPTY);

        allOne.inc(key);
        assertThat(allOne.getMaxKey()).isEqualTo(key);
    }

    @Test
    public void allOneB() {
        String hello = "Hello";
        String world = "World";
        AllOne allOne = new AllOne();

        allOne.inc(hello);
        allOne.inc(hello);
        assertThat(allOne.getMaxKey()).isEqualTo(hello);
        assertThat(allOne.getMinKey()).isEqualTo(hello);

        allOne.inc(world);
        assertThat(allOne.getMinKey()).isEqualTo(world);

        allOne.dec(hello);
        allOne.dec(hello);
        assertThat(allOne.getMaxKey()).isEqualTo(world);
        assertThat(allOne.getMinKey()).isEqualTo(world);

        allOne.dec(world);
        assertThat(allOne.getMaxKey()).isEqualTo(AllOne.EMPTY);
    }

    @Test
    public void allOneC() {
        String a = "a";
        String b = "b";
        AllOne allOne = new AllOne();

        allOne.inc(a);

        allOne.inc(b);
        allOne.inc(b);
        allOne.inc(b);
        allOne.inc(b);
        allOne.inc(b);

        allOne.dec(b);
        allOne.dec(b);

        assertThat(allOne.getMaxKey()).isEqualTo(b);
        assertThat(allOne.getMinKey()).isEqualTo(a);
    }
}
