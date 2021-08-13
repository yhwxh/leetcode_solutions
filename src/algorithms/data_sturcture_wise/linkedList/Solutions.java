package algorithms.data_sturcture_wise.linkedList;

import utils.ListNode;

import java.util.List;

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
        if (head.val == val) {
            return head.next;
        } else {
            return head;
        }
    }

    // 不使用头节点
    public ListNode removeElements3(ListNode head, int val) {
        // 将所有满足条件的头节点删除
        while (head != null && head.val == val) {
            head = head.next;
        }
        if (head == null) {
            return null;
        }
        ListNode prev = head;
        while (prev.next != null) {
            if (prev.next.val == val) {
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
     * <p>
     * 输入：l1 = [2,4,3], l2 = [5,6,4]
     * 输出：[7,0,8]
     * 解释：342 + 465 = 807.
     * 示例 2：
     * <p>
     * 输入：l1 = [0], l2 = [0]
     * 输出：[0]
     * 示例 3：
     * <p>
     * 输入：l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
     * 输出：[8,9,9,9,0,0,0,1]
     * <p>
     * 输入：l1：2->4->9->null ，  l2：5->6->4->9->null
     * 输出：7->0->4->0->1->null
     *
     * @param l1
     * @param l2
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode();
        ListNode prev = dummyHead;
        while (l1 != null || l2 != null) {
            int addRes = 0;
            int additionVal = prev.next != null ? 1 : 0;

            if (l1 == null) {
                addRes = l2.val + additionVal;
                prev.next = new ListNode(addRes % 10);
                if (addRes >= 10) {
                    prev.next.next = new ListNode(1);
                }
                l2 = l2.next;
            } else if (l2 == null) {
                addRes = l1.val + additionVal;
                prev.next = new ListNode(addRes % 10);
                if (addRes >= 10) {
                    prev.next.next = new ListNode(1);
                }
                l1 = l1.next;
            } else {
                addRes = l1.val + l2.val + additionVal;
                prev.next = new ListNode(addRes % 10);
                if (addRes >= 10) {
                    prev.next.next = new ListNode(1);
                }
                l1 = l1.next;
                l2 = l2.next;
            }
            prev = prev.next;
        }
        return dummyHead.next;
    }

    /**
     * LeetCode 206：反转链表 【简单】
     * <p>
     * 解题思路：
     * 1、界定清楚当前要改变指向的节点的前继节点后继节点就可以了
     *
     * @param head
     */
    public ListNode reverseList(ListNode head) {
        if (head == null) return null;
        ListNode prev = null;
        ListNode cur = head;

        while (cur != null) {
            ListNode suc = cur.next;
            cur.next = prev;
            prev = cur;
            cur = suc;
        }
        return prev;
    }

    /**
     * LeetCode 92: 反转链表II 【中等】
     * 给你单链表的头指针 head 和两个整数 left 和 right ，其中 left <= right 。请你反转从位置 left 到位置 right 的链表节点，返回 反转后的链表 。
     * <p>
     * 输入：head = [1,2,3,4,5], left = 2, right = 4
     * 输出：[1,4,3,2,5]
     * <p>
     * 解题思路：
     * 1、定义好新区间的头节点、头节点的前继节点、尾节点、尾节点的后继节点
     * 2、保存好区间的前继节点
     * 3、改变记录下四个节点中的指向（不需要区间里的每个节点都操作，只需要改变头尾就行）
     *
     * @param head
     * @param left
     * @param right
     * @return
     */
    public ListNode reverseBetween(ListNode head, int left, int right) {
        // 设置一个虚拟头节点
        ListNode dummyHead = new ListNode();
        dummyHead.next = head;
        ListNode prev = dummyHead;
        // 定义遍历的节点，初始为头节点
        ListNode cur = head;
        // 区间链表的前继节点
        ListNode subHeadPrev = null;
        // 区间链表的头节点
        ListNode subHead = null;
        // 区间链表的尾节点
        ListNode subTail = null;
        // 区间链表的后继节点
        ListNode subTailSuc = null;
        // 遍历链表，寻找区间的头节点、头节点的前继节点、尾节点、尾节点的后继节点
        for (int i = 1; i <= right; i++) {
            ListNode suc = cur.next;
            if (i == left) {
                subHeadPrev = prev;
                subHead = cur;
            }
            if (i == right) {
                subTail = cur;
                subTailSuc = cur.next;
            }
            if (i > left) {
                cur.next = prev;
            }
            prev = cur;
            cur = suc;
        }
        // 改变指向
        subHeadPrev.next = subTail;
        subHead.next = subTailSuc;
        return dummyHead.next;
    }

    /**
     * LeetCode 24: 两两交换链表中的节点
     * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
     * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
     * <p>
     * 输入：head = [1,2,3,4]
     * 输出：[2,1,4,3]
     * <p>
     * 解题思路：必须知道 前继节点、当前节点、后继节点
     *
     * @param head
     * @return
     */
    public ListNode swapPairs(ListNode head) {
        ListNode dummyHead = new ListNode();
        dummyHead.next = head;
        ListNode prev = dummyHead;
        while (prev.next != null && prev.next.next != null) {
            // 记录下三个节点，不然交换完后不能正确移动指针
            ListNode cur = prev.next;
            ListNode suc = cur.next;
            ListNode sucSuc = suc.next;
            // 改变三个节点的指向：改变指向要保证改变的先后顺序，不然容易出错
            prev.next = suc;
            cur.next = sucSuc;
            suc.next = cur;
            // 移动指针
            prev = cur;
        }
        return dummyHead.next;
    }

    /**
     * LeetCode 61：旋转链表
     * 给你一个链表的头节点 head ，旋转链表，将链表每个节点向右移动 k 个位置。
     * <p>
     * 输入：head = [1,2,3,4,5], k = 2
     * 输出：[4,5,1,2,3]
     * <p>
     * 解题思路：
     * 1、先遍历链表，让尾节点指向头节点
     * 3、从当前头节点遍历，找到下一个（k+1）节点作为新链表的头节点，并在第k个位置断开
     * 4、注意特殊情况：当移动此时大于链表长度时，本质上就是移动 k mode n(k%n) 次
     *
     * @param head
     * @param k
     * @return
     */
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null /*|| k==0*/) return head;
        ListNode dummyHead = new ListNode();
        dummyHead.next = head;
        ListNode prev = dummyHead;
        int count = 0;
        while (prev.next != null) {
            prev = prev.next;
            count++;
        }
        // 尾节点指向头节点
        prev.next = head;
        // 由于有环,移动次数也会循环，移动真实的移动次数变为 k %= count;
        k %= count;
        // 重新判断 k 是否为 0；
//        if (k == 0) return head;
        // 后面的步骤可以覆盖k==0的情况，所以不用单独判断 k==0
        for (int i = 0; i < count - k; i++) {
            // 初始时，pre指向链表最后一个元素，形成闭环后就相当于原链表头节点的前继节点
            prev = prev.next;
        }
        ListNode newHead = prev.next;
        prev.next = null;
        return newHead;
    }


    /**
     * LeetCode 141: 环形链表（判断链表是否有环）
     * 给定一个链表，判断链表中是否有环。
     * 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。
     * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。
     * 如果 pos 是 -1，则在该链表中没有环。注意：pos 不作为参数进行传递，仅仅是为了标识链表的实际情况。
     * 如果链表中存在环，则返回 true 。 否则，返回 false 。
     * <p>
     * 解题思路：
     * 思路一：使用Set，如果某个节点在Set中出现过，就有环，否则没有
     * 思路二：快慢指针：如果快慢指针相遇，则有环
     *
     * @param head
     * @return
     */
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) return false;
        ListNode p = head;
        ListNode q = head.next;

        // 当两个指针没相遇就一直循环
        while (p != q) {
            // 如果快指针指向空，或者快指针当下个节点指向空，就表示没环
            if (q == null || q.next == null)
                return false;
            p = p.next;
            q = q.next.next;
        }
        return true;
    }

    /**
     * LeetCode 83: 删除排序链表中的重复元素 【简单】
     * 存在一个按升序排列的链表，给你这个链表的头节点 head ，请你删除所有重复的元素，使每个元素 只出现一次 。
     * 返回同样按升序排列的结果链表。
     * <p>
     * 解题思路：
     * 1、不需要虚拟头节点，这个会带来删除头节点的危险，而头节点是不会被删除的
     * 2、永远返回头节点就行
     *
     * @param head
     * @return
     */
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) return head;
        ListNode prev = head;

        while (prev.next != null) {
            ListNode cur = prev.next;
            if (cur.val == prev.val) {
                prev.next = cur.next;
            } else {
                prev = cur;
            }
        }
        return head;
    }

    /**
     * LeetCode 19: 删除链表的倒数第N个元素
     * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
     * 进阶：你能尝试使用一趟扫描实现吗？
     * <p>
     * 解题思路：
     * 思路一：遍历两次链表
     * 1、第一次，记录链表元素个数
     * 2、重新遍历（只需从1遍历到n-k），找到要删除节点的前继节点
     * 思路二：使用双指针维护一个要删除节点的前继节点和Null的区间，同时移动区间，找到要删除节点的前继节点
     * 1、先从虚拟头节点遍历链表N+1次，找到区间的右侧边界
     * 2、再同时移动两个指针，知道右侧指针到达Null，此时左侧指针指向的节点就是要删除节点的前继节点
     *
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummyHead = new ListNode();
        dummyHead.next = head;

        // 我们要让p和q两个指针维护一个跨度为 n+1 的区间, p 指向要删除节点的前继节点，q指向空，这两个指针正好间隔n个节点
        ListNode p = dummyHead;
        ListNode q = dummyHead;

        // 初始化这个区间
        for (int i = 0; i < n + 1; i++) {
            q = q.next;
        }
        // 此时 p 到 q 正好是一个间隔 n 个节点的区间，同时移动两个指针，知道q指针指向 null，那么p指针就是要删除节点的前继节点
        while (q != null) {
            q = q.next;
            p = p.next;
        }

        ListNode delNode = p.next;
        p.next = delNode.next;
        return dummyHead.next;
    }

    public ListNode removeNthFromEnd2(ListNode head, int n) {
        if (head == null) return head;
        int count = 0;
        ListNode cur = head;
        while (cur != null) {
            count++;
            cur = cur.next;
        }
        if (n > count) return head;
        ListNode dummyHead = new ListNode();
        dummyHead.next = head;
        ListNode prev = dummyHead;
        for (int i = 1; i <= count - n; i++) {
            prev = prev.next;
        }
        ListNode delNode = prev.next;
        prev.next = delNode.next;

        return dummyHead.next;
    }

    /**
     * LeetCode 142：环形链表II（判断链表是否有环，返回链表入环处位置）【中等】
     * 给定一个链表，返回链表开始入环的第一个节点。如果链表无环，则返回null。
     * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。
     * 如果 pos 是 -1，则在该链表中没有环。注意，pos 仅仅是用于标识环的情况，并不会作为参数传递到函数中。
     * 说明：不允许修改给定的链表。
     * <p>
     * 进阶：
     * 你是否可以使用 O(1) 空间解决此题？
     * <p>
     * 输入：head = [3,2,0,-4], pos = 1
     * 输出：返回索引为 1 的链表节点
     * 解释：链表中有一个环，其尾部连接到第二个节点。
     * <p>
     * 解题思路：
     * 思路一：借用Set，每遍历一个节点查看Set中是否存在，若存在，就表明有环，而且就是入环节点，否则加入Set继续遍历
     * 思路二：需要利用一个结论性的性质：如果链表有环，当快慢指针相遇当时候，一个指向head的指针和原来的快指针同时移动k次，两个指针必定在入环扣的节点相遇
     * k是head节点到入环节点的间隔节点数
     * 1、设定快慢指针，快指针每次移动两个节点，慢指针每次移动一个节点
     * 2、移动快慢指针，直到两个指针相遇
     * 3、设定一个指向head的新指针，同时移动slow指针和新指针，两个指针相遇的节点就是入环节点
     *
     * @param head
     * @return
     */
    public ListNode detectCycle(ListNode head) {
        if (head == null) return null;
        // 先找到快慢指针相遇的位置
        ListNode slow = head;
        ListNode fast = head.next;
        // 注意，这里快慢指针的初始不是指向的同一个节点，这导致相遇的时候指向的节点也会不一样（这里这种初始话会比指向同一个节点提前一个节点相遇）
        while (fast != slow) {
            if (fast == null || fast.next == null) {
                return null;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        // 经过上面操作后，两个指针相遇。再同时移动快指针和新的初始指向head的慢指针，再次相遇就是链表入口节点
        ListNode dummyHead = new ListNode();
        dummyHead.next = head;
        // 由于上面快慢指针初始化的方式，导致我们要循环的次数多一个，从head开始算第一次移动
        ListNode newSlow = dummyHead;
        while (newSlow != slow) {
            newSlow = newSlow.next;
            slow = slow.next;
        }
        return slow;
    }


    public static void main(String[] args) {
        // test 203
        int[] arr = new int[]{1, 2, 6, 3, 4, 5, 6};
        int[] test = {1, 1, 1, 2, 3, 4};
        ListNode data = new ListNode(new int[]{0, 0, 0, 0, 0});
        ListNode testLinkedList = new ListNode(test);
        Solutions slt = new Solutions();
//        System.out.println(slt.removeElements(data, 6));
//        System.out.println(testLinkedList);
////        System.out.println(slt.reverseBetween(testLinkedList, 2, 4));
//
//        System.out.println(slt.rotateRight(testLinkedList, 0));
//        System.out.println(slt.swapPairs(testLinkedList));
        System.out.println(slt.deleteDuplicates(data));
    }
}
