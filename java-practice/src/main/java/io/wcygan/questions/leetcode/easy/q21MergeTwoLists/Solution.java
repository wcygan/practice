package io.wcygan.questions.leetcode.easy.q21MergeTwoLists;

import io.wcygan.questions.leetcode.ListNode;

// https://leetcode.com/problems/merge-two-sorted-lists/
class Solution {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        // make sure there are lists to merge
        if (list1 == null) return list2;
        if (list2 == null) return list1;

        // setup some pointers
        ListNode dummy = new ListNode(-1); // for elegance
        ListNode curr = dummy;

        // merge the lists
        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                curr.next = list1;
                list1 = list1.next;
            } else {
                curr.next = list2;
                list2 = list2.next;
            }

            curr = curr.next;
        }

        curr.next = list1 == null ? list2 : list1;

        // return the head of the list
        return dummy.next;
    }
}
