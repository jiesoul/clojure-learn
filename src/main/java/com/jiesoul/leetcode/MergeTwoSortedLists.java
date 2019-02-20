package com.jiesoul.leetcode;

public class MergeTwoSortedLists {

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {

        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        int v1 = l1.val;
        int v2 = l2.val;
        if (v1 < v2) {
            ListNode listNode = new ListNode(v1);
            listNode.next = mergeTwoLists(l1.next, l2);
            return listNode;
        } else {
            ListNode listNode = new ListNode(v2);
            listNode.next = mergeTwoLists(l1, l2.next);
            return listNode;
        }
    }
}
