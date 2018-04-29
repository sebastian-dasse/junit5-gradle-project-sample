package de.sample.project;

public class MyAdder implements Adder {

    @Override
    public int add(final int a, final int b) {
        return a + b;
    }
}
