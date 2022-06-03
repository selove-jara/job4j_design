package ru.job4j.collection;

import java.util.Iterator;
import java.util.Objects;

public class SimpleLinkedList<E> implements LinkedList<E> {

    int size = 0;
    Node<E> first;
    Node<E> last;

    @Override
    public void add(E value) {
        Node<E> node = new Node<>(value);
        node.value = value;
        if (first == null) {
            first = node;
        } else {
            last.next = node;
        }
        last = node;
        size++;
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        Node<E> f = first;
        for (int i = 0; i < index; i++) {
            f = f.next;
        }
        return f.getValue();
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {

            Node<E> element = first;

            @Override
            public boolean hasNext() {
                return element != null;
            }

            @Override
            public E next() {
                E rsl = element.getValue();
                element = element.getNext();
                return rsl;
            }

        };
    }
}