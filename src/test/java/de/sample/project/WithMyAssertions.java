package de.sample.project;

public interface WithMyAssertions {

    default FooAssert assertingThat(final Foo foo) {
        return FooAssert.assertingThat(foo);
    }
}
