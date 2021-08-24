package data_sturcture;

import data_sturcture.interfaces.Queue;

public class ArrayQueue<E> implements Queue<E> {
    /**
     * 基于动态数组实现的队列
     */
    DynamicArray<E> array;

    public ArrayQueue(int capacity) {
        array = new DynamicArray<>(capacity);
    }
    public ArrayQueue(){
        array = new DynamicArray<>();
    }

    @Override
    public int getSize() {
        return array.getSize();
    }

    @Override
    public boolean isEmpty() {
        return array.isEmpty();
    }

    @Override
    public void enqueue(E e) {
        array.addLast(e);
    }

    @Override
    public E dequeue() {
        return array.removeFirst();
    }

    @Override
    public E front() {
        return array.getFirst();
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append("Queue: \nFirst [");
        for (int i = 0; i < array.getSize(); i++) {
            res.append(array.get(i));
            if (i == array.getSize()-1){
                res.append(", ");
            }
            res.append("] end");
        }
        return res.toString();
    }
}
