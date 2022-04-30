package io.wcygan.questions.leetcode.medium.q102LevelOrder;

import io.wcygan.questions.leetcode.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

// https://leetcode.com/problems/binary-tree-level-order-traversal/
class Solution {

    static class LevelNode {
        TreeNode node;
        int level;

        public LevelNode(TreeNode node, int level) {
            this.node = node;
            this.level = level;
        }
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> lists = new ArrayList<>();

        if (root == null) {
            return lists;
        }

        Queue<LevelNode> q = new ArrayDeque<>();
        q.add(new LevelNode(root, 0));

        while (!q.isEmpty()) {
            LevelNode curr = q.poll();

            if (curr.node.left != null) {
                q.add(new LevelNode(curr.node.left, curr.level + 1));
            }

            if (curr.node.right != null) {
                q.add(new LevelNode(curr.node.right, curr.level + 1));
            }

            if (lists.size() != curr.level + 1) {
                lists.add(new ArrayList<>());
            }

            lists.get(curr.level).add(curr.node.val);
        }

        return lists;
    }
}