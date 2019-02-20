package com.jiesoul.leetcode;

public class ReverseNodesinkGroup {

    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null) return null;
        ListNode[] nodes = new ListNode[k];
        int cnt = 0;
        while (cnt < k && head != null) {
            ListNode temp = head;
            head = head.next;
            temp.next = null;
            nodes[cnt] = temp;
            cnt++;
        }
        ListNode result = null;
        if (cnt == k) {
            result = nodes[0];
            result.next = reverseKGroup(head, k);
            for (int i = 1; i < k; i++) {
                nodes[i].next = result;
                result = nodes[i];
            }
        } else {
            result = nodes[cnt-1];
            for (int i = cnt - 2; i >= 0; i--) {
                nodes[i].next = result;
                result = nodes[i];
            }
        }


        return result;
    }

    public ListNode reverse(ListNode head) {

        ListNode last = null;
        while (head != null) {
            ListNode temp = head;
            head = head.next;
            temp.next = last;
            last = temp;

        }
        return last;
    }

    public static void main(String[] args) {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(4);
        ListNode n5 = new ListNode(5);

        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;

        ListNode listNode = new ReverseNodesinkGroup().reverseKGroup(n1, 2);
        System.out.println(listNode);
    }
}
