package com.jiesoul.leetcode;

public class SwapNodesinPairs {

    public ListNode swapPairs(ListNode head) {
        if (head != null && head.next != null) {
            ListNode l1 = head;
            ListNode l2 = l1.next;
            l1.next = null;
            head = l2.next;
            l2.next = null;
            l2.next = l1;
            l1.next = swapPairs(head);
            return l2;
        }
        return head;
    }

    public ListNode swapPairs1(ListNode head) {
        ListNode cur = head;
        while (cur != null && cur.next != null) {
            int v1 = cur.val;
            int v2 = cur.next.val;
            cur.val = v2;
            cur.next.val = v1;
            cur = cur.next.next;

        }
        return head;
    }
}
