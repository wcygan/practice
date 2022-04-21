package io.wcygan.questions.leetcode.q148SortList;

import java.util.Comparator;
import java.util.PriorityQueue;

class Solution {
    public ListNode sortList(ListNode head) {
        PriorityQueue<ListNode> q = new PriorityQueue<>(Comparator.comparing(a -> a.val));
        while (head != null) {
            ListNode next = head.next;
            head.next = null;
            q.add(head);
            head = next;
        }

        ListNode dummy = new ListNode(-1);
        ListNode tail = dummy;

        while (!q.isEmpty()) {
            tail.next = q.remove();
            tail = tail.next;
        }

        return dummy.next;
    }

    public static class ListNode {
        int val;
        Solution.ListNode next;

        ListNode() {}

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, Solution.ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
