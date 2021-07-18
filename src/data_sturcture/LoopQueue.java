package data_sturcture;

import data_sturcture.interfaces.Queue;

public class LoopQueue<E> implements Queue<E> {
    /**
     * 循环队列的要点在于定义清楚什么时候队列为空，什么时候队列为满
     * 循环队列为空：front == tail
     * 循环队列为满：(tail + 1) % capacity == front (之所以是 tail + 1，是我们有意识的浪费了一个空间)
     * front: 为指向队列第一个元素的位置
     * tail: 为执行队列最后一个元素后一位的指针
     * 循环的实现主要是由 front 和 tail 两个指针控制，
     * 比如，tail的移动不是简单的 tail++，而是 tail=（tail + 1）% capacity
     */
    private E[] data;
    private int front, tail;
    private int size;

    public LoopQueue(int capacity) {
        data = (E[])new Object[capacity + 1];  // 注意这里的+1，因为我们有意识的浪费了一个空间
        front = 0;
        tail = 0;
        size = 0;
    }
    public LoopQueue(){
        this(10);
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        // 判断空队列的条件
        return front == tail;
    }

    public int getCapacity(){
        return data.length - 1;
    }

    @Override
    public void enqueue(E e) {
        // 如果队列满了就扩容
        if ((tail + 1) % data.length == front){
            resize(getCapacity() * 2);
        }
        data[tail] = e;
        // 循环队列里尾部指针的维护不是简单的 tail++
        tail = (tail + 1) % data.length;
        size++;
    }
    private void resize(int newCapacity){
        E[] newData = (E[])new Object[newCapacity + 1];
        for (int i = 0; i < size; i++) {
            // 这里与元数组索引的对应不是很容易想到
            newData[i] = data[(i + front) % data.length];
        }
        data = newData;
        front = 0;
        tail = size;
    }

    @Override
    public E dequeue() {
        if (isEmpty()){
            throw new IllegalArgumentException("");
        }
        E res = data[front];
        data[front] = null;
        size--;
        if (size == getCapacity() / 4 && getCapacity()/2 != 0){
            resize(getCapacity()/2);
        }
        return res;
    }

    @Override
    public E front() {
        if (isEmpty()){
            throw new IllegalArgumentException("");
        }
        return data[front];
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append("LoopQueue:[");
        // 这里的遍历方式和 enqueue 中复制数组内容的遍历等价
        for (int i = front; i < tail; i=(i+1) % data.length) {
            res.append(data[i]);
            if ((i+1)%data.length != tail){
                res.append(", ");
            }
        }
        res.append("] out");
        return res.toString();
    }
}
