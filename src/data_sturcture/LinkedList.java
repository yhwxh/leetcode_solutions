package data_sturcture;

public class LinkedList<E> {
    /**
     * 在处理链表问题时，遍历链表的指针是指向当前节点还是前继节点都可以的
     * 如果指针指向前继节点，那么我们要处理的目标节点就是 pre.next 指向的节点
     * 如果指向的是当前节点，那么我们处理的目标节点就是 cur 指向的节点
     * 二者区别就是遍历停止的条件，pre 最终指向停留在目标节点前一个节点，cur 停留在在目标节点上
     *
     * 一般情况下：
     * 1）如果涉及到对链表的断开操作，使用前继节点 pre
     * 2）如果不涉及断开操作，直接操作节点，使用当前节点 cur
     * 3) 使用前继节点处理的情况多一些，能用cur处理的都可以用pre来处理
     * @param <E>
     */

    private class Node<E> {
        public E data;
        public Node next;

        public Node(E e, Node next) {
            data = e;
            this.next = next;
        }

        public Node(E e) {
            this(e, null);
        }

        public Node() {
            this(null, null);
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (Node cur = this; cur != null; cur = cur.next) {
                sb.append(cur.data + "->");
            }
            sb.append("null");
            return sb.toString();
        }
    }

    // 要想访问链表，必须把链表的头(也就是链表中第一个节点)存起来
    private Node dummyHead;
    private int size;

    public LinkedList() {
        dummyHead = new Node(null, null);
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    //  在链表头添加节点
    public void addFirst(E e) {
//        Node node = new Node(e);
//        node.next = head;
//        head = node;
        // 以上三句可以简写为
//        head = new Node(e, head);
//        size++;
        addMid(0, e);
    }

    /**
     * 注意，链表中的操作顺序很重要
     * 在链表中间加入节点
     * 加入节点必须找到加入位置前的那个节点
     */
    public void addMid(int index, E e) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("");
        }
        // 由于判断条件判断不到头节点，要单独判断
//        if (index == 0){
//            addFirst(e);
//            size ++;
//        }else {

        // 注意这里的遍历截止条件，表示的是移动次数，不是移动到的索引
        Node pre = dummyHead;  // 初始 pre 为head
        for (int i = 0; i < index; i++) {
            pre = pre.next;
        }
        // 注意这个简写方法，它的过程是：先创建一个节点，让该节点的next指向 pre.next ，然后更新 pre.next 为新创建的 node
        // 这就是插入的顺序，必须先把要插入的节点先挂在链表的合适位置，再断开重新链接
        pre.next = new Node(e, pre.next);
        size++;
//        }
    }

    public void addLast(E e) {
        addMid(size, e);
    }

    public void set(int index, E e) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("");
        }

        Node cur = dummyHead.next;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        cur.data = e;
    }

    public boolean contains(E e) {
        Node cur = dummyHead.next;
        while (cur != null) {
            if (cur.data.equals(e)) {
                return true;
            }
            cur = cur.next;
        }
        return false;
    }

    public E remove(int index) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("");
        }
        Node pre = dummyHead;
        for (int i = 0; i < index; i++) {
            pre = pre.next;
        }
        Node delNode = pre.next;
        pre.next = delNode.next;
        delNode.next = null;
        size--;
        return (E) delNode.data;
    }

    public E removeFirst() {
        return remove(0);
    }

    public E removeLast() {
        return remove(size - 1);
    }

    public E get(int index) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("");
        }
        Node cur = dummyHead.next;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        return (E) cur.data;
    }

    public E getFirst() {
        return get(0);
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
//        Node cur = dummyHead.next;
//        for (int i =0 ; i<size; i++) {
//            res.append(cur + "->");
//            cur = cur.next;
//        }
//        while (cur != null) {
//            res.append(cur + "->");
//            cur = cur.next;
//        }
        for (Node cur = dummyHead.next; cur != null; cur = cur.next) {
            res.append(cur + "->");
        }
        res.append("NULL");
        return res.toString();
    }
}
