package com.jiesoul.leetcode;

public class MergekSortedLists {

    public ListNode mergeKLists1(ListNode[] lists) {
        if (lists.length == 0) {
            return null;
        }

        int index = 0;
        for (int i = 1; i < lists.length; i++) {
            ListNode cur = lists[i];
            if (cur == null) {
                continue;
            }
            if (lists[index] == null || lists[index].val > cur.val) {
                index = i;
            }
        }

        if (lists[index] == null) {
            return null;
        }

        ListNode listNode = new ListNode(lists[index].val);
        lists[index] = lists[index].next;
        listNode.next = mergeKLists1(lists);
        return listNode;

    }

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0) {
            return null;
        }
        ListNode list = lists[0];
        for (int i = 1; i < lists.length; i++) {
            list = twoMerge(list, lists[i]);
        }
        return list;
    }

    private ListNode twoMerge(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        ListNode list;
        if (l1.val < l2.val) {
            list = new ListNode(l1.val);
            l1 = l1.next;
        } else {
            list = new ListNode(l2.val);
            l2 = l2.next;
        }
        list.next = twoMerge(l1, l2);
        return list;
    }
}
