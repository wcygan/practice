package io.wcygan.questions.leetcode.hard.q23MergeKSortedLists;

import io.wcygan.questions.leetcode.ListNode;

import java.util.Comparator;
import java.util.PriorityQueue;

// https://leetcode.com/problems/merge-k-sorted-lists/
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null) {
            return null;
        }

        // add all nodes to the heap
        PriorityQueue<ListNode> heap = new PriorityQueue<>(Comparator.comparing(a -> a.val));
        for (ListNode listNode : lists) {
            if (listNode != null) {
                heap.offer(listNode);
            }
        }

        // continuously merge the smallest list node
        ListNode head = null;
        ListNode tail = null;
        while (!heap.isEmpty()) {
            ListNode current = heap.remove();

            if (head == null) {
                // list is empty
                head = new ListNode(current.val);
                tail = head;
            } else {
                // list is non-empty
                tail.next = new ListNode(current.val);
                tail = tail.next;
            }

            if (current.next != null) {
                heap.add(current.next);
            }
        }

        return head;
    }
}