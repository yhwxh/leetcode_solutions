package data_sturcture;

import utils.ListNode;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 二分搜索数：BST
 * 1、每个节点都大于左子树所有节点，都小于其右子树所有节点
 * 2、BST是保序的，只能存储可比较的元素
 *
 * @param <E>
 */
public class BinarySearchTree<E extends Comparable> {
    public Node root;
    private int size;

    public BinarySearchTree() {

    }

    public BinarySearchTree(E e) {
        root = new Node(e);
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void add(E e) {
        root = add(root, e);
    }

    private Node add(Node node, E e) {
        if (node == null) {
            size++;
            return new Node(e);  // 把插入的节点返回出去
        }
        if (node.val.compareTo(e) > 0) {
            node.left = add(node.left, e);
        } else if (node.val.compareTo(e) < 0) {
            node.right = add(node.right, e);
        }
        return node;  // 最后把当前子树的root节点返回
    }

    // add 的非递归写法
    public void add2(E e) {
        if (root == null) {
            root = new Node(e);
            size++;
            return;
        }
        Node cur = root;
        while (cur != null) {
            if (cur.val.compareTo(e) > 0) {
                if (cur.left == null) {
                    cur.left = new Node(e);
                    size++;
                    return;
                } else {
                    cur = cur.left;
                }
            } else if (cur.val.compareTo(e) < 0) {
                if (cur.right == null) {
                    cur.right = new Node(e);
                    size++;
                    return;
                } else {
                    cur = cur.right;
                }
            }
        }
    }

    public boolean contains(E e) {
        return contains(root, e);
    }

    private boolean contains(Node node, E e) {
        if (node == null) {
            return false;
        }
        if (node.val.compareTo(e) == 0) {
            return true;
        } else if (node.val.compareTo(e) > 0) {
            return contains(node.left, e);
        } else {
            return contains(node.right, e);
        }
    }

    // contains 非递归写法
    public boolean contains2(E e) {
        if (root == null) {
            return false;
        }
        Node cur = root;
        while (cur != null) {
            if (cur.val.compareTo(e) == 0) {
                return true;
            } else if (cur.val.compareTo(e) > 0) {
                cur = cur.left;
            } else {
                cur = cur.right;
            }
        }
        return false;
    }

    /**
     * BST 的遍历：以下的先、后、中序是相对于每棵子树的跟节点而言的
     *      1、中序遍历：先左 再根 后右，遍历结果是由小到大顺序排列的
     *      2、前序遍历：先根 再左 后右，常用的一种遍历方式，最自然的一种遍历方式
     *      3、后续遍历：先左 再右 后根，遍历结果是倒序排列的
     */
    public void preOrder(){
        preOrder(root);
    }
    private void preOrder(Node node){
        if (node == null) {
            return;
        }
        System.out.print(node.val + " ");
        preOrder(node.left);
        preOrder(node.right);
    }

    // 非递归遍历
    public void traverse(){
        if (root == null){
            return;
        }

        Queue<Node> elements = new LinkedList();
        elements.add(root);

        while (elements != null){
            Node cur = elements.poll();
            System.out.print(cur.val + " ");
            if (cur.left != null){
                elements.add(cur.left);
                continue;
            }
            if (cur.right !=null){
                elements.add(cur.right);
                continue;
            }
        }
    }

    private class Node {
        public E val;
        public Node left, right;

        public Node(E e) {
            this.val = e;
            left = null;
            right = null;
        }
    }


    public static void main(String[] args) {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        int[] arr = {30, 28, 56, 43, 18, 57, 32, 20, 60};
        for (int i : arr) {
            bst.add(i);
        }
        System.out.print("前序遍历：");
        bst.preOrder();
        System.out.println("");
        bst.traverse();
        System.out.printf("");

        System.out.println("contains 58: " + bst.contains2(58));
        System.out.println("contains 43: " + bst.contains2(43));
    }
}
