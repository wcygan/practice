package io.wcygan.reflect;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class HierarchyTest {

    @Test
    public void testFindA() {
        var hierarchy = Hierarchy.find(A.class);
        assertThat(hierarchy).containsValue(Object.class);
    }

    @Test
    public void testFindB() {
        var hierarchy = Hierarchy.find(B.class);
        assertThat(hierarchy).containsValue(A.class);
    }

    @Test
    public void testFindC() {
        var hierarchy = Hierarchy.find(C.class);
        assertThat(hierarchy).containsValue(A.class);
    }

    @Test
    public void testFindD() {
        var hierarchy = Hierarchy.find(D.class);
        assertThat(hierarchy).containsValue(A.class);
        assertThat(hierarchy).containsValue(C.class);
    }

    private static class A {}

    private static class B extends A {}

    private static class C extends A {}

    private static class D extends C {}
}
