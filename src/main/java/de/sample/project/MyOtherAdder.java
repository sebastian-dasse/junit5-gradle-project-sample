package de.sample.project;

public class MyOtherAdder implements Adder {

    @Override
    public int add(final int a, final int b) {
        try {
            return Integer.parseInt(a + "" + b);
        } catch (final NumberFormatException e) {
            return -1;
        }
    }
}
