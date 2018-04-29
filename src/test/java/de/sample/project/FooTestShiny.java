package de.sample.project;

import info.solidsoft.mockito.java8.api.WithMockito;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class FooTestShiny implements WithAssertions, WithMyAssertions, WithMockito {

    @Mock
    private Adder m_adder;

    @Test
    void testFoo1() {
        final Foo foo = new Foo("foo", 123);

        assertThat(foo.getName()).isEqualTo("foo");
        assertThat(foo.getNumber()).isEqualTo(123);
    }

    @Test
    void testFoo2() {
        final Foo foo = new Foo("foo", 123).add(123);

        assertThat(foo.getName()).isEqualTo("foo");
        assertThat(foo.getNumber()).isEqualTo(246);
    }

    @Nested
    @DisplayName("some parameterized tests")
    class SomeParameterizedTests {

        @ParameterizedTest
        @ValueSource(ints = {1, 2, 4, 8, 16, 32, 12345})
        @DisplayName("using a value source")
        void testFoo3(final int number) {
            final Foo foo = new Foo("foo", number).add(number);

            assertThat(foo.getName()).isEqualTo("foo");
            assertThat(foo.getNumber()).isEqualTo(2 * number);
        }

        @ParameterizedTest
        @ValueSource(ints = {1, 2, 4, 8, 16, 32, 12345})
        @DisplayName("checking results with a custom assert")
        void testFoo4(final int number) {
            final Foo foo = new Foo("foo", number).add(number);

            assertingThat(foo).hasName("foo").hasNumber(2 * number);
        }
    }

    @Nested
    @DisplayName("some tests relying on an OtherAdder and on a mocked Adder")
    class SomeTestsWithNonDefaultAdders {

        @Test
        @DisplayName("testing MyOtherAdder")
        void testFoo5() {
            final Foo foo = new Foo("foo", 123, new MyOtherAdder()).add(123);

            assertingThat(foo).hasName("foo").hasNumber(123123);
        }

        @Test
        @DisplayName("testing a mocked Adder (relying on an explicit method call)")
        void testFoo6() {
            final Adder adder = mock(Adder.class);
            when(adder.add(anyInt(), anyInt())).thenReturn(42);

            final Foo foo = new Foo("foo", 123, adder).add(123);

            assertingThat(foo).hasName("foo").hasNumber(42);
        }

        @Test
        @DisplayName("testing a mocked Adder (using an annotation)")
        void testFoo7() {
            when(m_adder.add(anyInt(), anyInt())).thenReturn(42);

            final Foo foo = new Foo("foo", 123, m_adder).add(123);

            assertingThat(foo).hasName("foo").hasNumber(42);
        }
    }
}
