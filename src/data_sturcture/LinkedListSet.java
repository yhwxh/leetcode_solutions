package data_sturcture;

import java.util.LinkedList;

/**
 * 基于链表的 Set 时间复杂度分析：
 *
 * @param <E>
 */

public class LinkedListSet<E extends Comparable> {
    private LinkedList<E> data;
    private int size;

    public void add(E e) {
        /**
         * O(n) 时间复杂度
         */
        if (!data.contains(e))
            data.addFirst(e);
    }

    public void remove(E e) {
        /**
         * O(n) 时间复杂度
         */
        data.remove(e);
    }

    boolean contains(E e) {
        /**
         * O(n) 时间复杂度
         */
        return data.contains(e);
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }
}
