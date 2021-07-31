package utils;

import java.util.List;

/**
 * 放弃范性，默认全是 int 型
 */
public class ListNode {
    public int val;
    public ListNode next;

    public ListNode(){ }
    public ListNode(int e){
        this.val = e;
        next = null;
    }
    public ListNode(int e, ListNode next){
        this.val = e;
        this.next = next;
    }

    public ListNode(int[] data){
        if (data == null || data.length == 0) {
            throw new IllegalArgumentException("Illegal argument");
        }
        this.val = data[0];
        ListNode  cur = this;
        for (int i = 1; i<data.length; i++){
            cur.next = new ListNode(data[i]);
            cur = cur.next;
        }
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        ListNode cur = this;
        while (cur != null){
            res.append(cur.val + "->");
            cur = cur.next;
        }
        res.append("null");
        return res.toString();
    }

}
