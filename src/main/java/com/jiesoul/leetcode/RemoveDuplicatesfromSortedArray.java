package com.jiesoul.leetcode;

public class RemoveDuplicatesfromSortedArray {

    public int removeDuplicates(int[] nums) {
        int len = nums.length;
        int idx = 0;
        for (int i = 1; i < len; i++) {
            if (nums[i] != nums[i-1]) {
                idx++;
                nums[idx] = nums[i];
            }
        }
        return idx + 1;
    }
}
