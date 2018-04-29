package de.sample.project;

/**
 * A foo.
 */
public class Foo {
    private final String name;
    private final int number;
    private final Adder adder;

    /**
     * Creates a {@code Foo} with a name, a number and an adder.
     *
     * @param name   the name
     * @param number the number
     * @param adder  specifies how to add numbers
     */
    public Foo(final String name, final int number, final Adder adder) {
        this.name = name;
        this.number = number;
        this.adder = adder;
    }

    /**
     * Creates a {@code Foo} with a name and a name and a number. The foo will use a {@link MyAdder} to add numbers.
     *
     * @param name   the name
     * @param number the number
     */
    public Foo(final String name, final int number) {
        this(name, number, new MyAdder());
    }

    public String getName() {
        return name;
    }

    public int getNumber() {
        return number;
    }

    public Foo add(final int delta) {
        return new Foo(name, adder.add(number, delta), adder);
    }

    @Override
    public String toString() {
        return name + " (" + number + ")";
    }

    public static void main(String[] args) {
        demo(new Foo("foo", 123));
        demo(new Foo("foo", 123, new MyOtherAdder()));
    }

    private static void demo(final Foo foo) {
        System.out.println(foo);
        System.out.println(foo.add(123));
        System.out.println(foo);
        System.out.println();
    }
}
