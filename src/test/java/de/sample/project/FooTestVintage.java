package de.sample.project;

import de.sample.project.Foo;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FooTestVintage {

    @Test
    public void testFoo1() {
        final Foo foo = new Foo("foo", 123);

        assertThat(foo.getName()).isEqualTo("foo");
        assertThat(foo.getNumber()).isEqualTo(123);
    }

    @Test
    public void testFoo2() {
        final Foo foo = new Foo("foo", 123).add(123);

        assertThat(foo.getName()).isEqualTo("foo");
        assertThat(foo.getNumber()).isEqualTo(246);
    }
}