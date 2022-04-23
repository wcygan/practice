package io.wcygan.questions.leetcode.easy.q53MaximumSubarray;

// https://leetcode.com/problems/maximum-subarray/
class Solution {
    public int maxSubArray(int[] nums) {
        // divide by two to avoid problems with overflow
        int max = Integer.MIN_VALUE / 2;
        int curr = Integer.MIN_VALUE / 2;

        for (int num : nums) {
            curr = Math.max(curr + num, num);
            max = Math.max(max, curr);
        }

        return max;
    }
}
