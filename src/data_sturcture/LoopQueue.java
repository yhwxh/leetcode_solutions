package data_sturcture;

import data_sturcture.interfaces.Queue;

public class LoopQueue<E> implements Queue<E> {
    /**
     * 循环队列的要点在于定义清楚什么时候队列为空，什么时候队列为满
     * 循环队列为空：front == tail
     * 循环队列为满：(tail + 1) % capacity == front (之所以是 tail + 1，是我们有意识的浪费了一个空间，如果不浪费，队列为空和满的条件就一样了)
     * front: 为指向队列第一个元素位置的指针
     * tail: 为指向队列最后一个元素的后面一位的指针
     * 所以，front 和 tail 维护的区间是左闭右开的 [front, tail)
     * 循环的实现主要是由 front 和 tail 两个指针控制，
     * 比如，tail的移动不是简单的 tail++，而是 tail=（tail + 1）% capacity
     * <p>
     * 注意：
     * 取模这种运算比较特别，他可以让指针在 [0,"除数") 这个范围里循环起来，比如 x%8，无论x取多少，结果都是在 [0,7) 这个范围循环
     */
    private E[] data;
    private int front, tail;
    private int size;

    public LoopQueue(int capacity) {
        data = (E[]) new Object[capacity + 1];  // 注意这里的+1，因为我们有意识的浪费了一个空间
        front = 0;
        tail = 0;
        size = 0;
    }

    public LoopQueue() {
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

    public int getCapacity() {
        // 开辟新空间的时候有意识的多开了一个空间，所以真实的 capacity 要减 1
        return data.length - 1;
    }

    @Override
    public void enqueue(E e) {
        // 如果队列满了就扩容
        if ((tail + 1) % data.length == front) {
            resize(getCapacity() * 2);
        }
        data[tail] = e;
        // 循环队列里尾部指针的维护不是简单的 tail++（相当于 tail++ 后再取模）
        tail = (tail + 1) % data.length;
        size++;
    }

    private void resize(int newCapacity) {
        E[] newData = (E[]) new Object[newCapacity + 1];
        // 扩容后的队列的形式就是铺平展开的样子
        for (int i = 0; i < size; i++) {
            // 这里与元数组索引的对应不是很容易想到
            // 不用去想具体这种形式是怎么想到的，其物理意义就是索引指针相对 front 偏移几个位移，然后再通过取模运算确定具体索引是多少
            // 是不是觉得学 C/C++ 在理解程序运行过程上很有帮助
            newData[i] = data[(i + front) % data.length];
        }
        data = newData;
        front = 0;
        tail = size;
    }

    @Override
    public E dequeue() {
        if (isEmpty()) {
            throw new IllegalArgumentException("");
        }
        E res = data[front];
        data[front] = null;
        // 维护一下 front 指针，跟维护 tail 一样，本质就是让 front 指针偏移一个位移
        front = (front + 1) % data.length;
        size--;
        if (size == getCapacity() / 4 && getCapacity() / 2 != 0) {
            resize(getCapacity() / 2);
        }
        return res;
    }

    @Override
    public E front() {
        if (isEmpty()) {
            throw new IllegalArgumentException("");
        }
        return data[front];
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("LoopQueue:[");
        // 这里的遍历方式和 enqueue 中复制数组内容的遍历等价
        for (int i = front; i < tail; i = (i + 1) % data.length) {
            res.append(data[i]);
            if ((i + 1) % data.length != tail) {
                res.append(", ");
            }
        }
        res.append("] out");
        return res.toString();
    }
}
