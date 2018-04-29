package de.sample.project;

import org.assertj.core.api.AbstractAssert;

import java.util.Objects;

public class FooAssert extends AbstractAssert<FooAssert, Foo> {

    private FooAssert(final Foo foo) {
        super(foo, FooAssert.class);
    }

    public static FooAssert assertingThat(final Foo foo) {
        return new FooAssert(foo);
    }

    public FooAssert hasName(final String name) {
        isNotNull();
        if (!Objects.equals(actual.getName(), name)) {
            failWithMessage("Expected foo's name to be <%s> but was <%s>", name, actual.getName());
        }
        return this;
    }

    public FooAssert hasNumber(final int number) {
        isNotNull();
        if (actual.getNumber() != number) {
            failWithMessage("Expected foo's number to be <%s> but was <%s>", number, actual.getNumber());
        }
        return this;
    }
}
