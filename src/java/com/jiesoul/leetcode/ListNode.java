package com.jiesoul.leetcode;

public class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(val);
        ListNode nextNode = this.next;
        while (nextNode != null) {
            s.append("->").append(nextNode.val);
            nextNode = nextNode.next;
        }
        return s.toString();
    }

    public static void main(String[] args) {
        ListNode listNode = new ListNode(0);
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        listNode.next = listNode1;
        listNode1.next = listNode2;

        System.out.println(listNode);

    }
}
