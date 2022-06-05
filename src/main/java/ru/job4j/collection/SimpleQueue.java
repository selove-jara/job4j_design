package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();
    private int stackInput;
    private int stackOut;

    public boolean isEmpty() {
        if (stackInput == 0 && stackOut == 0) {
            throw new NoSuchElementException();
        }
        return stackOut == 0;
    }

    public T poll() {
        if (isEmpty()) {
            while (stackInput > 0) {
                out.push(in.pop());
                stackInput--;
                stackOut++;
            }
        }
        stackOut--;
        return out.pop();
    }

    public void push(T value) {
        in.push(value);
        stackInput++;
    }
}