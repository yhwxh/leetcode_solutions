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
        // 有 虚拟头节点的话就不用判断头节点了
//        if (head == null) {
//            return null;
//        }
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

    /**
     * LeetCode 2: 两数相加
     * 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
     * 请你将两个数相加，并以相同形式返回一个表示和的链表。
     * 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
     *
     * 输入：l1 = [2,4,3], l2 = [5,6,4]
     * 输出：[7,0,8]
     * 解释：342 + 465 = 807.
     * 示例 2：
     *
     * 输入：l1 = [0], l2 = [0]
     * 输出：[0]
     * 示例 3：
     *
     * 输入：l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
     * 输出：[8,9,9,9,0,0,0,1]
     *
     * 输入：l1：2->4->9->null ，  l2：5->6->4->9->null
     * 输出：7->0->4->0->1->null
     * @param l1
     * @param l2
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode();
        ListNode cur = dummyHead;
        while (l1 != null || l2 != null) {
            int addRes = 0;
            int additionVal = cur.next != null? 1 : 0;

            if (l1 == null) {
                addRes = l2.val + additionVal;
                cur.next = new ListNode(addRes % 10);
                if (addRes >= 10) {
                    cur.next.next = new ListNode(1);
                }
                l2 = l2.next;
            } else if (l2 == null) {
                addRes = l1.val + additionVal;
                cur.next = new ListNode(addRes % 10);
                if (addRes >= 10) {
                    cur.next.next = new ListNode(1);
                }
                l1 = l1.next;
            } else {
                addRes = l1.val + l2.val + additionVal;
                cur.next = new ListNode(addRes % 10);
                if (addRes >= 10) {
                    cur.next.next = new ListNode(1);
                }
                l1 = l1.next;
                l2 = l2.next;
            }
            cur = cur.next;
        }
        return dummyHead.next;
    }


    public static void main(String[] args) {
        // test 203
        int[] arr = new int[]{1, 2, 6, 3, 4, 5, 6};
        ListNode data = new ListNode(arr);
        Solutions slt = new Solutions();
        System.out.println(slt.removeElements(data, 6));
    }
}
