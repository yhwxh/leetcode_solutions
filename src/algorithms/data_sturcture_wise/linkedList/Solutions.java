package algorithms.data_sturcture_wise.linkedList;

import utils.ListNode;

public class Solutions {
    /**
     * LeetCode 203: 移除链表元素 【简单】
     * 给你一个链表的头节点 head 和一个整数 val ，请你删除链表中所有满足 Node.val == val 的节点，并返回 新的头节点 。
     * 输入：head = [1,2,6,3,4,5,6], val = 6
     * 输出：[1,2,3,4,5]
     * <p>
     * 解法：
     */
    public ListNode removeElements(ListNode head, int val) {
        if (head == null) {
            return null;
        }
        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;
        ListNode pre = dummyHead;
        while (pre.next != null) {
            if (pre.val == val) {
                pre.next = pre.next.next;
                // 如果无重复元素，就在这里停止
//                return head;
            } else {
                pre = pre.next;
            }
        }
        return dummyHead.next;
    }

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

    // 不使用头节点
    public ListNode removeElements3(ListNode head, int val) {
        // 将所有满足条件的头节点删除
        while (head !=null && head.val == val){
            head = head.next;
        }
        if (head == null) {
            return null;
        }
        ListNode prev = head;
        while (prev.next != null) {
            if (prev.next.val == val){
                prev.next = prev.next.next;
            } else {
                prev = prev.next;
            }
        }
        return head;
    }


    public static void main(String[] args) {
        // test 203
        int[] arr = new int[]{1, 2, 6, 3, 4, 5, 6};
        ListNode data = new ListNode(arr);
        Solutions slt = new Solutions();
        System.out.println(slt.removeElements(data, 6));
    }
}
