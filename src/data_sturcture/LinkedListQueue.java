package data_sturcture;

import data_sturcture.interfaces.Queue;

public class LinkedListQueue<E> implements Queue<E> {

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void enqueue(E e) {
        // 进队列是在队尾，适合在链表的尾部操作
        if (tail == null){
            tail = new Node(e);
            head = tail;  // 此时记得维护以下head
        } else {
            tail.next = new Node(e);
            tail = tail.next;
        }
            size++;
    }

    @Override
    public E dequeue() {
        // 出队列是在队首，适合在链表的头部处理
        if (head == null)
            throw new IllegalArgumentException("");
        Node delNode = head;
        head = head.next;
        delNode.next = null;
        if (head == null) {
            tail = null;
        }
        size--;
        return (E) delNode.e;
    }

    @Override
    public E front() {
        return (E) head.e;
    }

    private class Node<E>{
        public E e;
        public Node next;

        public Node(E e, Node next){
            this.e = e;
            this.next = next;
        }

        public Node(E e) {
            this(e, null);
        }

        public Node() {
            this(null, null);
        }
    }

    public Node head;
    public Node tail;
    public int size;

    public LinkedListQueue(){
        head = null;
        tail = null;
        size = 0;
    }


}
