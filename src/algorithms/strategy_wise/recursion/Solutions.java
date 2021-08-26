package algorithms.strategy_wise.recursion;

import utils.ListNode;

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
    }

    /**
     * LeetCode 203: 移除链表元素 【简单】
     * 给你一个链表的头节点 head 和一个整数 val ，请你删除链表中所有满足 Node.val == val 的节点，并返回 新的头节点 。
     * 输入：head = [1,2,6,3,4,5,6], val = 6
     * 输出：[1,2,3,4,5]
     * <p>
     * 解法：
     * 1、遍历链表：注意删除后指针不能移动，否则会跳过下一个节点
     * 2、递归遍历：注意递归返回结果怎么重新把链表串起来
     */

    // 递归解法
    public ListNode removeElements2(ListNode head, int val) {
        if (head == null) {
            return null;
        }
        // 注意这里head的含义不同：等号左边的head是当前函数的head
        head.next = removeElements2(head.next, val);
        // 这里的 head 是当前问题的子问题的head
        if (head.val == val) {
            return head.next;
        } else {
            return head;
        }
    }

    // 非递归解法
    public ListNode removeElements(ListNode head, int val) {
        ListNode dummyHead = new ListNode(-1, head);
        ListNode prev = dummyHead;
        while (prev.next != null) {
            if (prev.next.val == val) {
                prev.next = prev.next.next;
            } else {
                prev = prev.next;
            }
        }
        return dummyHead.next;
    }

    /**
     * LeetCode 22: 括号生成 【中等】
     * 数字 n代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
     * <p>
     * 示例 1：
     * 输入：n = 3
     * 输出：["((()))","(()())","(())()","()(())","()()()"]
     * <p>
     * 示例 2：
     * 输入：n = 1
     * 输出：["()"]
     * <p>
     * 解题思路：递归+确定好如何生成括号是合法的
     * 1、做括号的个数和有括号的个数都必须等于n
     * 2、当做括号和有括号用完后，返回结果
     * 3、如果做括号没用完，就可以一直添加左括号
     * 4、右括号不能先添加，只有做括号个数大于右括号个数时才能添加
     */
    // 这里需要一个全局变量，来装所有有效括号
    private List<String> res = new ArrayList<>();

    public List<String> generateParenthesis(int n) {
        parenthesis(0, 0, n, "");
        return res;
    }

    // 定义一个递归函数：left用来记录左括号使用个数，right用来记录右括号使用个数
    private void parenthesis(int left, int right, int n, String pare) {
        if (left == n && right == n) {
            res.add(pare);
            return;
        }
        // 只要左括号没用完就递归添加左括号
        if (left < n) {
            parenthesis(left + 1, right, n, pare + "(");
        }
        // 当左括号个数比右括号多的时候，添加右括号
        if (left > right) {
            parenthesis(left, right + 1, n, pare + ")");
        }
    }

    /**
     * LeetCode 257: 二叉树所有路径 【简单】
     * 给定一个二叉树，返回所有从根节点到叶子节点的路径。
     * 说明:叶子节点是指没有子节点的节点。
     *
     * 示例:
     * 输入:
     *
     *    1
     *  /   \
     * 2     3
     *  \
     *   5
     *
     * 输出: ["1->2->5", "1->3"]
     * 解释: 所有根节点到叶子节点的路径为: 1->2->5, 1->3
     *
     * 解题思路：递归，注意每次递归的返回值比较复杂
     *  1、终止条件：当节点为叶子节点时，返回叶子节点的值
     *  2、递归寻找左右子树的路径
     *
     * @param root
     * @return
     */
    public List<String> binaryTreePaths(TreeNode root) {
        // res保存每棵树的路径，而且，随着递归过程结束（从下往上），res中保存的路径由小变大
        // 这里 res 工作的过程是：每轮递归创建个空的，然后将本轮子树的路径拷贝过来，再补上本轮节点的值
        List<String> res = new ArrayList<>();
        if (root==null) return res;
        // 递归终止条件
        if (root.left == null && root.right == null){
            res.add(String.valueOf(root.val));
            // 这里要return一下，不然上一轮的递归拿不到下一轮递归的结果
            return res;
        }
        // 递归：触底反弹
        List<String> leftPath = binaryTreePaths(root.left);  // 对左子树递归
        // 遍历上次递归子树的结果，生成当前子树的路径（这里需要注意的是，leftPath存的是当前子树的子树的路径）
        for (int i = 0; i < leftPath.size(); i++) {  // 这里访问的节点跟上面递归访问的节点差一个代差
            res.add(String.valueOf(root.val) + "->" + leftPath.get(i));
        }

        // 同理，对右子树递归
        List<String> rightPath = binaryTreePaths(root.right);  // 对左子树递归
        for (int i = 0; i < rightPath.size(); i++) {
            res.add(String.valueOf(root.val) + "->" + rightPath.get(i));
        }

        // 当递归反弹到root节点后，相应左右子树生成的路径也add到了 res 中，最后 return res就保存了所有路径的字符串
        return res;
    }

    /**
     * LeetCode 113：路径总和II 【中等】
     * 给你二叉树的根节点 root 和一个整数目标和 targetSum ，找出所有 从根节点到叶子节点 路径总和等于给定目标和的路径。
     * 叶子节点 是指没有子节点的节点。
     *
     * 示例：
     * 输入：root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
     * 输出：[[5,4,11,2],[5,8,4,5]]
     *
     * 解题思路：
     *  1、
     *
     * @param root
     * @param targetSum
     * @return
     */
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> finalRes = new ArrayList<>();
        List<List<Integer>> allPaths = getPaths(root);
        for (List<Integer> list:allPaths){
            int sum = 0;
            for (int i = 0; i < list.size(); i++) {
                sum+=list.get(i);
            }
            if (sum == targetSum){
                finalRes.add(list);
            }
        }
        return finalRes;
    }

    private List<List<Integer>> getPaths(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        if (root.left == null && root.right == null){
            List<Integer> path = new ArrayList<>();
            path.add(root.val);
            res.add(path);
            return res;
        }
        List<List<Integer>> leftPath = getPaths(root.left);
        for (int i = 0; i < leftPath.size(); i++) {
            leftPath.get(i).add(0,root.val);
            res.add(leftPath.get(i));
        }
        List<List<Integer>> rightPaht = getPaths(root.right);
        for (int i = 0; i < rightPaht.size(); i++) {
            rightPaht.get(i).add(0,root.val);
            res.add(rightPaht.get(i));
        }
        return res;
    }

}
