package io.wcygan.questions.leetcode.easy.q226InvertBinaryTree;

import io.wcygan.questions.leetcode.TreeNode;

// https://leetcode.com/problems/invert-binary-tree/
public class Solution {
    public TreeNode invertTree(TreeNode root) {
        if (root == null) return null;

        // swap nodes
        TreeNode x = root.left;
        root.left = root.right;
        root.right = x;

        // invert children
        invertTree(root.left);
        invertTree(root.right);

        return root;
    }
}
