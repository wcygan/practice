package io.wcygan.questions.leetcode.medium.q148SortList;

import io.wcygan.questions.leetcode.ListNode;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class SolutionTest {

    @Test
    public void test() {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);

        node4.next = node3;
        node3.next = node2;
        node2.next = node1;

        ListNode answer = new Solution().sortList(node4);
        Assertions.assertEquals(answer.val, 1);
        Assertions.assertEquals(answer.next.val, 2);
        Assertions.assertEquals(answer.next.next.val, 3);
        Assertions.assertEquals(answer.next.next.next.val, 4);
    }
}
