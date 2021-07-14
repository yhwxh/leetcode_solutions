package algorithms.data_sturcture_wise.binary_search_tree;

import java.util.ArrayList;
import java.util.List;

public class Solutions {
    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }

        TreeNode(int[] arr) {
            if (arr == null || arr.length == 0) {
                throw new IllegalArgumentException("");
            }
            this.val = arr[0];
            TreeNode cur = this;
            for (int i = 1; i < arr.length; i++) {
                if (i < cur.val) {
                    cur.left = new TreeNode(i);
                } else if (i > cur.val) {
                    cur.right = new TreeNode(i);
                }
            }
        }
    }
    /**
     * LeetCode 94: 二叉树的中序遍历
     * 给定一个二叉树的根节点 root ，返回它的 中序 遍历。
     * <p>
     * 输入：root = [1,null,2,3]
     * 输出：[1,3,2]
     * <p>
     * 输入：root = []
     * 输出：[]
     * <p>
     * 输入：root = [1]
     * 输出：[1]
     * <p>
     * 输入：root = [1,2]
     * 输出：[2,1]
     * <p>
     * 输入：root = [1,null,2]
     * 输出：[1,2]
     *
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList();
        if (root == null) {
            return res;
        }
        res.addAll(inorderTraversal(root.left));
        res.add(root.val);
        res.addAll(inorderTraversal(root.right));
        return res;
    }

    /**
     * LeetCode 104: 二叉树的最大深度
     * 给定一个二叉树，找出其最大深度。
     * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
     * 说明: 叶子节点是指没有子节点的节点。
     * <p>
     * 示例：
     * 给定二叉树 [3,9,20,null,null,15,7]，
     *          3
     *         / \
     *        9  20
     *          /  \
     *         15   7
     * 返回其最大深度：3
     *
     * @param root
     * @return
     */
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 0;
    }

    /**
     * LeetCode 173: 二叉搜索树迭代器
     * 实现一个二叉搜索树迭代器类BSTIterator ，表示一个按中序遍历二叉搜索树（BST）的迭代器：
     * BSTIterator(TreeNode root) 初始化 BSTIterator 类的一个对象。
     * BST 的根节点 root 会作为构造函数的一部分给出。
     * 指针应初始化为一个不存在于 BST 中的数字，且该数字小于 BST 中的任何元素。
     * boolean hasNext() 如果向指针右侧遍历存在数字，则返回 true ；否则返回 false 。
     * int next()将指针向右移动，然后返回指针处的数字。
     * 注意，指针初始化为一个不存在于 BST 中的数字，所以对 next() 的首次调用将返回 BST 中的最小元素。
     * 你可以假设 next() 调用总是有效的，也就是说，当调用 next() 时，BST 的中序遍历中至少存在一个下一个数字。
     * <p>
     * 示例：
     *          7
     *         / \
     *        3  15
     *          /  \
     *          9   20
     * 输入
     * ["BSTIterator", "next", "next", "hasNext", "next", "hasNext", "next", "hasNext", "next", "hasNext"]
     * [[[7, 3, 15, null, null, 9, 20]], [], [], [], [], [], [], [], [], []]
     * 输出
     * [null, 3, 7, true, 9, true, 15, true, 20, false]
     * <p>
     * 解释
     * BSTIterator bSTIterator = new BSTIterator([7, 3, 15, null, null, 9, 20]);
     * bSTIterator.next();    // 返回 3
     * bSTIterator.next();    // 返回 7
     * bSTIterator.hasNext(); // 返回 True
     * bSTIterator.next();    // 返回 9
     * bSTIterator.hasNext(); // 返回 True
     * bSTIterator.next();    // 返回 15
     * bSTIterator.hasNext(); // 返回 True
     * bSTIterator.next();    // 返回 20
     * bSTIterator.hasNext(); // 返回 False
     *
     * @param
     * @return
     */
    private class BSTIterator {

        public BSTIterator(TreeNode root) {

        }

        public int next() {
            return 0;
        }

        public boolean hasNext() {
            return false;
        }
    }


    // 构造测试用例使用
    public TreeNode getTestInstance(int[] arr) {
        return new TreeNode(arr);
    }

    public static void main(String[] args) {
        Solutions s = new Solutions();
        TreeNode bst = s.getTestInstance(new int[]{1, 2, 3});

        System.out.println(s.inorderTraversal(bst));
    }
}
