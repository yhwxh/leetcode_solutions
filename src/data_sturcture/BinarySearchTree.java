package data_sturcture;

import java.util.*;
import java.util.LinkedList;

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
     * BST 的 深度优先（DFS） 遍历：以下的先、后、中序是相对于每棵子树的跟节点而言的
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
//        System.out.println(node.val + " ");  // 中序遍历
        preOrder(node.right);
//        System.out.println(node.val + " ");  // 后序遍历

    }

    // 非递归遍历: 前序遍历（中序和后续实际应用不多）
    public void traverse(){
        /**
         * 非递归遍历的思路就是模拟系统栈调用的动作
         * 注意压栈出栈的顺序：先右后左，因为栈是先进后出，而我们是要先访问左孩子节点
         * 栈的作用在这里来记录我们要访问的节点及其顺序（也就是下一步我们依次要访问哪些节点）
         */
        if (root == null){
            return;
        }

        // 初始化的时候先将根节点压栈
        Stack<Node> elements = new Stack<>();
        elements.add(root);

        while (!elements.isEmpty()){
            Node cur = elements.pop();
            System.out.print(cur.val + " ");
            // 对当前节点的孩子节点压栈的顺序是先右后左
            if (cur.right != null){
                elements.add(cur.right);
            }
            if (cur.left !=null){
                elements.add(cur.left);
            }
        }
    }

    /**
     * BST 的 广度优先（BFS） 遍历(层序遍历)：此处我们把跟节点叫做深度为 0
     *      这种方式一般是非递归的，一般我们借助 Queue 这种数据结构来实现
     * BFS 的作用在于更快的找到某个元素
     */
    public void levelOrder(){
        /**
         * BFS 和 DFS 的前序遍历区别在于使用的数据结构和访问顺序的不同
         */
        if (root == null){
            return;
        }

        // 初始化，先将跟节点入队
//        Queue<Node> queue = new ArrayDeque<>();
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            System.out.println(cur.val);
            if (cur.left != null) {
                queue.add(cur.left);
            }
            if (cur.right != null) {
                queue.add(cur.right);
            }
        }
    }

    /**
     * BST 的删除动作
     *      最小值：从跟节点一直向左走，直到走不动（相当于操作链表，找尾节点）
     *      最大值：从跟节点一直向右走，直到走不动（相当于操作链表，找尾节点）
     */
    public E removeMinNR(){
        /**
         * 非递归实现
         */
        if (root == null){
            return null;
        }
        Node cur = root;
        while (cur != null) {
            if (cur.left == null) {
                return cur.left.val;
            } else {
                cur = cur.left;
            }
        }
        return null;
    }
    public E removeMin(){
        E minE = minimum(root).val;
        removeMin(root);
        return minE;
    }
    private Node removeMin(Node node){
        /**
         * 目的是删除掉以 node 为根节点的二分搜索树的最小节点
         * 并返回删除节点后的新二分搜索树
         */
        // 当做节点为空的时候，记录一下该节点的右子树，后面用来将其返回给该节点的左子树
        if (node.left == null) {
            Node rightChildren = node.right;
            node.right = null;  // 断开右子树
            size--;
            return rightChildren;
        }
        node.left = removeMin(node.left);  // 递归，并将右子树接在左子树的位置
        return node;  // 返回当前新树的根
    }
    private Node minimum(Node node){
        if (node.left == null) {
            return node;
        }
        return minimum(node.left);
    }
    public E removeMax() {
        E maxE = maximum(root).val;
        removeMax(root);
        return maxE;
    }

    private Node removeMax(Node node) {
        if (node.right == null) {
            Node leftChildren = node.left;
            node.left = null;
            size--;
            return leftChildren;
        }
        node.right = removeMax(node.right);
        return node;
    }
    private Node maximum(Node node){
        if (node.right == null) {
            return node;
        }
        return maximum(node.right);
    }
    public void remove(E e){
        /**
         * 删除的动作，难点在于要删除的节点是既有左子树又有右子树的节点
         * 此时我们需要重新考虑各个节点间序的关系(需要左右子树的融合)
         *      方法是，找到删除节点的后继节点（或者前驱节点）：Hibbard deletion
         *          后继节点：比当前节点大，且距离当前节点最近的节点（右子树的最小值）
         *          前驱节点：比当前节点小，且距离当前节点最近的节点（左子树的最大值）
         *
         *      而带删除的节点是该节点右子树的最小值
         */
        root = remove(root, e);
    }
    // 递归函数
    private Node remove(Node node, E e){
        /**
         * 删除以 node 为跟节点的 二分搜索树 的最小节点
         * 然后返回删除后的新二分搜索树的根
         */
        if (node == null) {
            return null;
        }

        if (e.compareTo(node.val) < 0){
            node.left = remove(node.left, e);
            return node;
        } else if (e.compareTo(node.val) > 0) {
            node.right = remove(node.right, e);
            return node;
        } else{
            if (node.left == null){  // 只有右子树的时候，根删除最小值一样
                Node rightChildren = node.right;
                node.right = null;
                size--;
                return rightChildren;
            }
            if (node.right == null){  // 只有左子树的时候，根删除最大值一样
                Node leftChildren = node.left;
                node.left = null;
                size--;
                return leftChildren;
            }

            // 待删除节点左右子树均不为空时：找到比待删除节点大（小）的最小值（最大值），即待删除节点右（左）子树的最小值（最大值）
            // 然后用这个节点代替待删除节点，并删除最小值
            Node successor = minimum(node.right);
            // 重新构建左右子树
            successor.right = removeMin(node.right);  // 将待删除节点右子树删除最小值后的新树返回给删除节点的右子树
            successor.left = node.left;

            // 删除待删除节点的指向(这里才是对待删除节点真正的删除动作)
            node.left = node.right = null;
            return successor;
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
        System.out.println("=================================TEST TRAVERSE==============================");
        System.out.print("前序遍历：");
        bst.preOrder();
        System.out.print("\n非递归前序遍历： ");
        bst.traverse();
        System.out.println("");
        
        System.out.println("=================================TEST CONTAINS==============================");
        System.out.println("contains 58: " + bst.contains2(58));
        System.out.println("contains 43: " + bst.contains2(43));

        System.out.println("=================================TEST REMOVE==============================");
        List<Integer> removeOrder = new ArrayList<>();
        while (!bst.isEmpty()){
            removeOrder.add(bst.removeMin());
        }
        System.out.println(removeOrder.toString());
        for (int i = 1; i < removeOrder.size(); i++) {
            if (removeOrder.get(i-1) > removeOrder.get(i)){
                throw new IllegalArgumentException("ERROR");
            }
        }
        System.out.println("SUCCESS");
    }
}
