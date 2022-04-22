package io.wcygan.reflect;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class InspectorTest {

    @Test
    public void classHierarchyA() {
        var hierarchy = Inspector.classHierarchyOf(A.class);
        assertThat(hierarchy).containsValue(Object.class);
    }

    @Test
    public void classHierarchyB() {
        var hierarchy = Inspector.classHierarchyOf(B.class);
        assertThat(hierarchy).containsValue(A.class);
    }

    @Test
    public void classHierarchyC() {
        var hierarchy = Inspector.classHierarchyOf(C.class);
        assertThat(hierarchy).containsValue(A.class);
    }

    @Test
    public void classHierarchyD() {
        var hierarchy = Inspector.classHierarchyOf(D.class);
        assertThat(hierarchy).containsValue(A.class);
        assertThat(hierarchy).containsValue(C.class);
    }

    @Test
    public void interfacesOfB() {
        var interfaces = Inspector.interfacesOf(B.class);
        assertThat(interfaces).contains(Foo.class);
        assertThat(interfaces).contains(Bar.class);
        assertThat(interfaces).contains(Baz.class);
        assertThat(interfaces).contains(Apple.class);
        assertThat(interfaces).contains(Banana.class);
    }

    @Test
    public void interfacesOfD() {
        var interfaces = Inspector.interfacesOf(D.class);
        assertThat(interfaces).contains(Bar.class);
        assertThat(interfaces).contains(Baz.class);
        assertThat(interfaces).contains(Apple.class);
        assertThat(interfaces).contains(Banana.class);
    }


    private static class A {}

    private static class B extends A implements Foo {}

    private static class C extends A {}

    private static class D extends C implements Bar {}

    private interface Apple {}
    private interface Banana {}

    private interface Foo extends Bar {}
    private interface Bar extends Baz, Apple, Banana {}
    private interface Baz {}
}
