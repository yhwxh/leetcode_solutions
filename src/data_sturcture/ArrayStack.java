package data_sturcture;//import java.util.Stack;

import data_sturcture.interfaces.Stack;

public class ArrayStack<E> implements Stack<E> {
    // 基于动态数组的栈实现
    DynamicArray<E> data;

    public ArrayStack(int capacity) {
        data = new DynamicArray<>(capacity);
    }
    public ArrayStack() {
        data = new DynamicArray<>();
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
        data.addLast(e);
    }

    @Override
    public E pop() {
        return data.removeLast();
    }

    @Override
    public E peek() {
        return data.getLast();
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append("Stack: [");

        for (int i = 0; i < data.getSize(); i++){
            res.append(i);
            if (i != data.getSize() -1){
                res.append(", ");
            }
        }
        res.append("] top");
        return res.toString();
    }
}