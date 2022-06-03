package ru.job4j.collection;

public class Node<E> {

    E value;
    Node<E> next;

    public Node(E value) {
    }


    public Node<E> getNext() {
        return next;
    }

    public void setNext(Node<E> next) {
        this.next = next;
    }

    public E getValue() {
        return value;
    }
}
