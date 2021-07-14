package data_sturcture;

public class BSTMap<K extends Comparable<K>, V> {
    private class Node {
        public K key;
        public V value;
        public Node left, right;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
        }

        @Override
        public String toString() {
            StringBuilder res = new StringBuilder();
            res.append(key.toString()).append(" : ").append(value.toString());
            return res.toString();
        }

    }

    private Node root;
    private int size;

    public BSTMap() {
        root = null;
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private Node getNode(Node node, K key) {
        if (node == null) {
            return null;
        }
        if (key.compareTo(node.key) == 0) {
            return node;
        } else if (key.compareTo(node.key) < 0) {
            return getNode(node.left, key);
        } else {
            return getNode(node.right, key);
        }
    }

    public void add(K key, V val) {
        root = add(root, key, val);
    }

    private Node add(Node node, K key, V val) {
        if (node == null) {
            return new Node(key, val);
        }

        if (key.compareTo(node.key) < 0) {
            node.left = add(node.left, key, val);
        } else if (key.compareTo(node.key) > 0) {
            node.right = add(node.right, key, val);
        } else {
            node.value = val;
        }
        return node;
    }

    public boolean contains(K key){
        return getNode(root, key) != null;
    }

    public V get(K key){
        Node node = getNode(root, key);
        if (node != null) {
            return node.value;
        }
        return null;
    }

    public void set(K key, V val){
        Node node = getNode(root, key);
        if (node == null){
            throw new IllegalArgumentException("");
        }
        node.value = val;
    }

    public Node remove(K key){
        //TODO
        return null;
    }
    private Node minimum(Node node){
        if (node.left == null) {
            return node;
        }
        return minimum(node.left);
    }

    private Node removeMinimum(Node node, K key) {
        if (node == null){
            return null;
        }
        Node min = minimum(node);
        //TODO
        return null;
    }
}
