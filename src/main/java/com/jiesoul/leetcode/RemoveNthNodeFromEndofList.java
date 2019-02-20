package com.jiesoul.leetcode;

public class RemoveNthNodeFromEndofList {

    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (n <= 0) {
            throw new RuntimeException("Invalid value of k");
        }
        if (head == null) {
            return head;
        }
        ListNode reverse = reverseListNode(head);
        if (n == 1) {
            reverse = reverse.next;
            return reverseListNode(reverse);
        }
        ListNode temp = reverse;
        for (int i = 2; i < n; i++) {
            temp = temp.next;
            if (temp == null) {
                break;
            }
        }

        if (temp.next == null) {
            return reverseListNode(reverse);
        }

        temp.next = temp.next.next;

        return reverseListNode(reverse);

    }

    private ListNode reverseListNode(ListNode listNode) {
        if (listNode == null || listNode.next == null) {
            return listNode;
        }
        ListNode first = listNode;
        ListNode reverse = null;
        while (first != null) {
            ListNode second = first.next;
            first.next = reverse;
            reverse = first;
            first = second;
        }

        return reverse;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(3);
        ListNode l4 = new ListNode(4);
        ListNode l5 = new ListNode(5);
        l1.next = l2;
        l2.next = l3;
        l3.next = l4;
        l4.next = l5;

        ListNode listNode = new RemoveNthNodeFromEndofList().removeNthFromEnd(l1, 2);
        System.out.println(listNode);
    }
}
