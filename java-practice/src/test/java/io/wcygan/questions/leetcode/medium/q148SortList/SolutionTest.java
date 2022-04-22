package io.wcygan.questions.leetcode.medium.q148SortList;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class SolutionTest {

    @Test
    public void test() {
        Solution.ListNode node1 = new Solution.ListNode(1);
        Solution.ListNode node2 = new Solution.ListNode(2);
        Solution.ListNode node3 = new Solution.ListNode(3);
        Solution.ListNode node4 = new Solution.ListNode(4);

        node4.next = node3;
        node3.next = node2;
        node2.next = node1;

        Solution.ListNode answer = new Solution().sortList(node4);
        Assertions.assertEquals(answer.val, 1);
        Assertions.assertEquals(answer.next.val, 2);
        Assertions.assertEquals(answer.next.next.val, 3);
        Assertions.assertEquals(answer.next.next.next.val, 4);
    }
}
