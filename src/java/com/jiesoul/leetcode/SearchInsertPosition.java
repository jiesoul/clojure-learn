package com.jiesoul.leetcode;

public class SearchInsertPosition {

    public int searchInsert(int[] nums, int target) {
        if (target < nums[0]) {
            return 0;
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] >= target) {
                return i;
            }
        }
        return nums.length;
    }

    public static void main(String[] args) {
        SearchInsertPosition searchInsertPosition = new SearchInsertPosition();
        int[] a = {1,3,5,6};

        System.out.println(searchInsertPosition.searchInsert(a, 5));
        System.out.println(searchInsertPosition.searchInsert(a, 2));
        System.out.println(searchInsertPosition.searchInsert(a, 7));
        System.out.println(searchInsertPosition.searchInsert(a, 0));
    }
}
