package io.wcygan.questions.leetcode.medium.q148SortList;

import io.wcygan.questions.leetcode.ListNode;

import java.util.Comparator;
import java.util.PriorityQueue;

// https://leetcode.com/problems/sort-list/
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
}
