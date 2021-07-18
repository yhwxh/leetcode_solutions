package data_sturcture;

import data_sturcture.interfaces.Stack;

public class LinkedListStack<E> implements Stack<E> {
    private LinkedList data;

    public LinkedListStack(){
        data = new LinkedList();
    }

    @Override
    public int getSize() {
        return data.getSize();
    }

    @Override
    public boolean isEmpty() {
        return data.isEmpty();
    }

    @Override
    public void put(E e) {
        data.addFirst(e);
    }

    @Override
    public E pop() {
        return (E) data.removeFirst();
    }

    @Override
    public E peek() {
        return (E) data.getFirst();
    }
}
