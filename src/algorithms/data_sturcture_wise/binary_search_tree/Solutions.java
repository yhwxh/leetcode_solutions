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
     * LeetCode 94: 二叉树的中序遍历 【简单】
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
     * LeetCode 104: 二叉树的最大深度，也就是从根节点到叶子节点最长路径的长度
     * 给定一个二叉树，找出其最大深度。
     * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
     * 说明:叶子节点是指没有子节点的节点。
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
     * 解题思路：递归
     *  1、判断当前正在遍历树的左右子树最大深度
     *  2、由于我们遍历的是当前节点左右子树的最大深度，所以当前节点对应树的最大深度要加1，即加上当前节点的一个深度
     *  3、本质上，该递归过程相当于一层一层的从下往上叠加（从最底层，不断往最大层上加1）
     *
     * @param root
     * @return
     */
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftMaxDepth = maxDepth(root.left);
        int rightMaxDepth = maxDepth(root.right);
        // 记得加上当前节点的最大深度
        return Math.max(leftMaxDepth, rightMaxDepth)+1;
    }

    /**
     * LeetCode 111: 二叉树的最小深度，即从跟节点到叶子节点到最小路径
     * 给定一个二叉树，找出其最小深度。
     * 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
     * 说明：叶子节点是指没有子节点的节点。
     *
     * 解题思路：不是简单的把最大路径的max换成min就可以了
     *  1、注意题意：叶子节点是指没有子节点的节点，所以，对于这种情况 root = [2,null,3,null,4,null,5,null,6]，如果简单的取min，就相当于把根节点当作了叶子节点
     *  2、正确思路是：
     *      当当前节点有左右子树时，跟最大深度逻辑一样，将max换成min就可以
     *      当当前节点有右子树，直接返回右子树的深度
     *      当当前节点有左子树，直接返回左子树的深度
     * @param root
     * @return
     */
    public int minDepth(TreeNode root) {
        if (root == null)return 0;
        if (root.left != null && root.right != null) {
            int leftMinDepth = minDepth(root.left);
            int rightMinDepth = minDepth(root.right);
            return Math.min(leftMinDepth, rightMinDepth) + 1;
        } else if (root.left != null && root.right == null){
            return minDepth(root.left) +1;
        } else {
            return minDepth(root.right) + 1;
        }
    }

    /**
     * LeetCode 226：反转二叉树
     * 反转一颗二叉树
     *
     * 示例：
     * 输入：
     *      4
     *    /   \
     *   2     7
     *  / \   / \
     * 1   3 6   9
     *
     * 输出：
     *      4
     *    /   \
     *   7     2
     *  / \   / \
     * 9   6 3   1
     *
     * 解题思路：
     *  1、递归反转当前遍历节点的左右子树
     *  2、反转完当前节点的左右子树后，交换左右子树的位置
     *
     * @param root
     * @return
     */
    public TreeNode invertTree(TreeNode root) {
        // 递归终止条件
        if(root == null) return null;
        // 对左子树反转
        invertTree(root.left);
        // 对右子树反转
        invertTree(root.right);
        // 反转完左右子树，再将左右子树调换位置（这里才是函数的核心操作，上面递归至少探底，并没有有意义的实际动作）
//        swap(root.left, root.right);
        TreeNode tmp = root.right;
        root.right = root.left;
        root.left = tmp;
        return root;
    }

    /**
     * LeetCode 100: 相同的树
     * 给你两棵二叉树的根节点 p 和 q ，编写一个函数来检验这两棵树是否相同。
     * 如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的。
     *
     * 示例 1：
     * 输入：p = [1,2,3], q = [1,2,3]
     * 输出：true
     *
     * 解题思路：递归
     *  1、递归判断左右子树是否相等
     *  2、判断左右子树的时候要分情况讨论
     *      2.1 左子树为空，右子树为空 => 此时两树相同，返回true
     *      2.2 当左子树为空，右子树不为空；左子树不为空，右子树为空 => 左右子树不同，返回false
     *      2.3 左右子树都不为空的时候，递归探底
     * @param p
     * @param q
     * @return
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p==null && q==null) return true;

        // 当左子树为空，右子树不为空；左子树不为空，右子树为空
        if (p ==null || q == null) return false;

        // 需要在当前节点相等，且都有左右子树的情况下，对左右子树进行递归探底。如果相等，会碰到终止条件，返回true
        if (p.val == q.val){
            // 左子树相同，右子树也相同才相同
            return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
        } else {
            return false;
        }
    }

    /**
     * LeetCode 101: 对称二叉树
     * 给定一个二叉树，检查它是否是镜像对称的。
     * 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。
     *
     *     1
     *    / \
     *   2   2
     *  / \ / \
     * 3  4 4  3
     *  
     * 但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:
     *
     *     1
     *    / \
     *   2   2
     *    \   \
     *    3    3
     *
     * 解题思路：递归（此题类似 leetcode 100）
     *  1、递归的对比两颗子树是否是对称的（左子树跟右子树比较）
     *  2、判断左右子树的时候要分情况讨论
     *     2.1 左子树为空，右子树为空 => 此时两树对称，返回true
     *     2.2 当左子树为空，右子树不为空；左子树不为空，右子树为空 => 左右子树不对称，返回false
     *     2.3 左右子树都不为空的时候，递归探底
     * @param root
     * @return
     */
    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return compareLeftRight(root.left, root.right);
    }
    private boolean compareLeftRight(TreeNode left, TreeNode right){
        if (left==null && right==null) return true;
        if (left == null || right == null) return false;
        if (left.val == right.val){
            return compareLeftRight(left.left, right.right) && compareLeftRight(left.right, right.left);
        } else {
            return false;
        }
    }

    /**
     * LeetCode 173: 二叉搜索树迭代器 【中等】
     * 实现一个二叉搜索树迭代器类BSTIterator ，表示一个按中序遍历二叉搜索树（BST）的迭代器：
     * BSTIterator(TreeNode root) 初始化 BSTIterator 类的一个对象。
     * BST 的根节点 root 会作为构造函数的一部分给出。
     * 指针应初始化为一个不存在于 BST 中的数字，且该数字小于 BST 中的任何元素。
     * boolean hasNext() 如果向指针右侧遍历存在数字，则返回 true ；否则返回 false 。
     * int next()将指针向右移动，然后返回指针处的数字。
     * 注意，指针初始化为一个不存在于 BST 中的数字，所以对 next() 的首次调用将返回 BST 中的最小元素。
     * 你可以假设next()调用总是有效的，也就是说，当调用 next()时，BST 的中序遍历中至少存在一个下一个数字。
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
     * 解题思路：主要是在构造函数中通过中序遍历记录下元素顺序
     *  1、声明一个 int 指针，指向当前访问元素所在的索引
     *  2、构造函数中调用中序遍历，记录二叉树中序遍历的元素顺序
     *  3、如果当前索引没有超出元素个数，则可以继续访问下一个元素
     *
     * @param
     * @return
     */
    private class BSTIterator {
        // 记录下一个要访问元素的索引
        private int index;
        private List<Integer> valRecord;
        public BSTIterator(TreeNode root) {
            index = 0;
            valRecord = new ArrayList<>();
            // 按中序遍历顺序记录下所有节点的元素
            inOrder(root);
        }

        public int next() {
            // 每次调用 next ，返回当前指针指向的元素，然后右移一位当前指针
            return valRecord.get(index++);
        }

        public boolean hasNext() {
            // 当指针指向列表尾部的时候返回false,最终指针指向索引等于size
            return index < valRecord.size();
        }
        private void inOrder(TreeNode root){
            if (root == null) return;
            inOrder(root.left);
            valRecord.add(root.val);
            inOrder(root.right);
        }
    }

    // contains 函数
    public boolean contains(TreeNode root, int target){
        if (root == null){
            return false;
        }
        if (root.val == target) return true;
        if (root.left!=null){
            return contains(root.left, target);
        }
        if (root.right != null){
            return contains(root.right, target);
        }
        return false;
    }



    public static void main(String[] args) {
        Solutions s = new Solutions();
        TreeNode bst = s.getTestInstance(new int[]{1, 2, 3});

        System.out.println(s.inorderTraversal(bst));
    }

    // 构造测试用例使用
    public TreeNode getTestInstance(int[] arr) {
        return new TreeNode(arr);
    }
}
