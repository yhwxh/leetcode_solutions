package algorithms.strategy_wise.recursion;

import utils.ListNode;

import java.util.ArrayList;
import java.util.Arrays;
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
     *  1、先找到所有到达叶子节点的路径
     *  2、然后判断所有路径是否是满足条件的
     *
     * @param root
     * @param targetSum
     * @return
     */
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> finalRes = new ArrayList<>();
        // 所有到达叶子节点的路径
        List<List<Integer>> allPaths = getPaths(root);
        // 遍历所有路径，留下满足条件的
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
        /**
         * 该函数用来找到多有从根节点到叶子节点的路径
         */
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


    /**
     * 递归具有天然的回溯特点
     * 以下为递归在回溯的应用
     */


    /**
     * LeetCode 46: 全排列 【中等】
     * 给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
     * 示例 1：
     * 输入：nums = [1,2,3]
     * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]

     * 示例 2：
     * 输入：nums = [0,1]
     * 输出：[[0,1],[1,0]]
     *
     * 解题思路：回溯，根据生成了几个数来判断是否得道一个完整的排列
     *  1、遍历数组中每个元素，如果这个元素被使用过，就跳过，否则就加入容器，进行下个元素判断
     *  2、在每个元素加入后，加入的个数也变了，这是需要往下个"元素个数" 的基础上递归
     *      递归终止条件就是记录每个排列顺序的容器中加入元素个数为数组大小时
     *  3、递归结束后要将当前正在处理的元素回溯回去，也就是要返回原来状态
     */
    // 这里创建一个全局变量来保存最终结果，函数每次生成一个排列加入到该变量中
    private List<List<Integer>> rest = new ArrayList<>();
    // 记录每个元素在每一轮的使用状态（它是动态变化的）
    boolean[] used;
    // LeetCode 46：全排列
    public List<List<Integer>> permute(int[] nums) {
        // 回溯算法
        if (nums==null || nums.length==0){
            return rest;
        }
        // 初始化used为nums的大小
        used = new boolean[nums.length];
        Arrays.fill(used, false);
        // 初始化将要生成的某个全排列
        List<Integer> p = new ArrayList<>();
        // 调用回溯函数
        generatePermutation(nums,0, p);
        return rest;
    }
    // 回溯算法中的递归
    private void generatePermutation(int[] nums, int index, List<Integer> p){
        /** 回溯函数：
         * 递归得道原数组nums中去掉index的全排列
         * index:现在正在处理第几个元素
         * p:处理到index时得道的有index元素的排列
         */
        // 当index到达数组最后一个元素时，此时生成了一个完整的排列
        if (index==nums.length){
            // 这里p是个全局饮用，不能直接add，要用p的复制版本，否则p会被后面动作改变
            rest.add(new ArrayList<>(p));
        }
        // 不是最后一个元素的时候，都要从新遍历一遍数组，把之前用过的元素跳过，没用过的加入到p中
        for (int i = 0; i < nums.length; i++) {
            // 如果要知道是否使用过某些元素，需要遍历一遍数组中元素是否在p中，但是这里用了一个额外遍历来记录了每个元素的使用状态
            if (!used[i]){  // 没被使用过的元素才会加入列表
                p.add(nums[i]);
                used[i] = true;  // 记得修改使用的状态
                // 递归：下探后面的元素的排列
                generatePermutation(nums, index+1, p);
                // 使用完需要将当前元素状态回溯，以便下次使用(这两步才是回溯的本质:回到原状)
                p.remove(p.size()-1);
                used[i] = false;
            }
        }
    }

    /** 组合问题
     * LeetCode 77: 组合 【中等】
     * 给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合。
     * 你可以按 任何顺序 返回答案。
     *
     * 示例 1：
     * 输入：n = 4, k = 2
     * 输出：
     * [
     *   [2,4],
     *   [3,4],
     *   [2,3],
     *   [1,2],
     *   [1,3],
     *   [1,4],
     * ]
     *
     * @param n
     * @param k
     * @return
     */
    List<List<Integer>> resC = new ArrayList<>();
    public List<List<Integer>> combine(int n, int k) {
        if (n <= 0|| k<=0||k>n){
            return resC;
        }
        List<Integer> c = new ArrayList<>();
        generateCombinations(n, k, 1, c); // start 不是索引是元素值，这里元素值从1开始
        return resC;
    }
    private void generateCombinations(int n, int k, int start, List<Integer> c){
        /**该函数用于递归求解 C(n,k)
         * start: 从start开始搜索新的元素，因为start之前的元已经被找到的组合（注意这里是不考虑顺序的，[1,2]和[2,1]是一样的，所以如果[1,2]被找到，就不能用[2,1]了）
         * c: 当前已经找到的元素
         */
        if (c.size() == k){
            resC.add(new ArrayList<>(c));
        }
        // 仅对剩余元素遍历，之前使用过的
        for (int i = start; i <= n ; i++) {
            c.add(i);
            // 这里start的变化是随着i变动的，不是直接在start上加1，这样start相当于固定的了
            generateCombinations(n, k, i+1, c);
            // 状态回溯
            c.remove(c.size()-1);
        }
    }

    /** 回溯
     * LeetCode 78:子集 【中等】
     * @param nums
     * @return
     */
    List<List<Integer>> result = new ArrayList<>();
    public List<List<Integer>> subsets(int[] nums) {
        //TODO
        if (nums==null || nums.length==0) return result;

        result.add(new ArrayList<>());
        List<Integer> sub = new ArrayList<>();
        for (int i = 1; i <= nums.length; i++) {
            generateSubset(nums, 0, i, 0, sub);
        }
        return result;
    }
    private void generateSubset(int[] nums,int startIndex, int k, int count, List<Integer> sub){
        /**
         * 递归生成所有子集
         * startIndex: 记录从第几个索引开始找，之前找到的就不能在加入了
         * k: 表示本次需要几个元素
         * count:记录生成了几个元素
         * sub:记录当前子集已确定下来的元素
         */
        if (count == k){
            result.add(new ArrayList<>(sub));
        }
        for (int i = startIndex; i < nums.length; i++) {
            sub.add(nums[startIndex]);
            generateSubset(nums, i+1, k, count+1, sub);
            sub.remove(sub.size()-1);
        }
    }


}
