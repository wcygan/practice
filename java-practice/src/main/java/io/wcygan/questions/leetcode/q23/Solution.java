package io.wcygan.questions.leetcode.q23;

import java.util.Comparator;
import java.util.PriorityQueue;

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

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {}

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}