package data_sturcture;

public class LinkedListMap<K, V> {
    private class Node {
        public K key;
        public V value;
        public Node next;

        public Node(K key, V value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public Node(K key) {
            this(key, null, null);
        }

        public Node() {
            this(null, null, null);
        }

        @Override
        public String toString() {
            StringBuilder res = new StringBuilder();
            res.append(key.toString()).append(" : ").append(value.toString());
            return res.toString();
        }

    }

    private Node dummyHead;
    private int size;

    public LinkedListMap() {
        dummyHead = new Node();
        size = 0;
    }

    private Node getNode(K key) {
        Node cur = dummyHead.next;
        while (cur != null) {
            if (cur.key.equals(key)) {
                return cur;
            }
            cur = cur.next;
        }
        return null;
    }

    public void add(K key, V val) {
        Node node = getNode(key);
        // 不允许重复，所以在 key 不存在的时候才去 add
        if (node == null) {
            // 在头节点添加元素
            dummyHead.next = new Node(key, val, dummyHead.next);
            size++;
        } else {  // 当 key 存在，就覆盖原来的元素
            node.value = val;
        }
    }

    public V remove(K key) {
        Node prev = dummyHead;
        // 先找到待删除的节点
        while (prev.next != null){
            if (prev.next.key.equals(key)){
                break;
            }
            prev = prev.next;
        }

        // 这里还需要判断下 prev.next 是否为空，因为在当前链表为空时，会报错
        if (prev.next != null) {
            Node delNode = prev.next;
            prev.next = delNode.next;
            delNode.next = null;
            size--;
            return delNode.value;
        }
        return null;
    }

    public boolean contains(K key) {
        return getNode(key) != null;
    }

    public V get(K key) {
        Node node = getNode(key);
        return node == null ? null : node.value;
    }

    public void set(K key, V val) {
        Node node = getNode(key);
        if (node == null){
            throw new IllegalArgumentException(key + " doesn't exists");
        }
        node.value = val;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

}
