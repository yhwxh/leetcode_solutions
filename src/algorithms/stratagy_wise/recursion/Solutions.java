package algorithms.stratagy_wise.recursion;

import utils.ListNode;

public class Solutions {
    /**
     * LeetCode 203: 移除链表元素 【简单】
     * 给你一个链表的头节点 head 和一个整数 val ，请你删除链表中所有满足 Node.val == val 的节点，并返回 新的头节点 。
     * 输入：head = [1,2,6,3,4,5,6], val = 6
     * 输出：[1,2,3,4,5]
     * <p>
     * 解法：
     *  1、遍历链表：注意删除后指针不能移动，否则会跳过下一个节点
     *  2、递归遍历：注意递归返回结果怎么重新把链表串起来
     */

    // 递归解法
    public ListNode removeElements2(ListNode head, int val) {
        if (head == null) {
            return null;
        }
        head.next = removeElements2(head.next, val);
        if (head.val == val){
            return head.next;
        } else {
            return head;
        }
    }

    public static void main(String[] args) {

    }
}
