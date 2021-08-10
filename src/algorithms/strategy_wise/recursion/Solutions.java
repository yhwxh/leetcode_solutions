package algorithms.strategy_wise.recursion;

import utils.ListNode;

import java.util.ArrayList;
import java.util.List;

public class Solutions {
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
     *
     * 示例 1：
     * 输入：n = 3
     * 输出：["((()))","(()())","(())()","()(())","()()()"]
     *
     * 示例 2：
     * 输入：n = 1
     * 输出：["()"]
     *
     * 解题思路：递归+确定好如何生成括号是合法的
     *  1、做括号的个数和有括号的个数都必须等于n
     *  2、当做括号和有括号用完后，返回结果
     *  3、如果做括号没用完，就可以一直添加左括号
     *  4、右括号不能先添加，只有做括号个数大于右括号个数时才能添加
     */
    // 这里需要一个全局变量，来装所有有效括号
    private List<String> res = new ArrayList<>();
    public List<String> generateParenthesis(int n) {
        parenthesis(0, 0, n, "");
        return res;
    }
    // 定义一个递归函数：left用来记录左括号使用个数，right用来记录右括号使用个数
    private void parenthesis(int left, int right, int n, String pare){
        if (left == n && right == n){
            res.add(pare);
            return;
        }
        // 只要左括号没用完就递归添加左括号
        if (left < n){
            parenthesis(left + 1, right, n, pare+"(");
        }
        // 当左括号个数比右括号多的时候，添加右括号
        if (left > right){
            parenthesis(left, right+1, n, pare+")");
        }
    }

}
