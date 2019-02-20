package com.jiesoul.leetcode;

import java.util.Arrays;

public class ThreeSumClosest {

    public int threeSumClosest(int[] nums, int target) {
        int len = nums.length;
        if (len < 3) {
            throw  new IllegalArgumentException("error args");
        }
        Arrays.sort(nums);

        int result = nums[0] + nums[1] + nums[2];
        for (int i = 0; i < nums.length-2; i++) {
            if (i > 0 && nums[i] == nums[i-1]) {
                continue;
            }
            int twoResult = twoSumClosest(nums, target, nums[i], i+1);
            if (Math.abs(twoResult - target) < Math.abs(result - target)) {
                result = twoResult;
            }
        }
        return result;
    }

    private int twoSumClosest(int[] nums, int target, int n, int index) {
        int l = index, r = nums.length - 1;
        int result = nums[l] + nums[r] + n;
        while (l < r) {
            int temp = (nums[l] + nums[r] + n);
            if (Math.abs(temp-target) < Math.abs(result - target)) {
                result = temp;
            }
            if (temp - target == 0) {
                break;
            } else if (temp - target > 0) {
                r--;
            } else {
                l++;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        ThreeSumClosest tsc = new ThreeSumClosest();
        int[] a = new int[]{-1, 2, 1, -4};
        int target = 1;
        System.out.println(tsc.threeSumClosest(a, target));

        int[] b = new int[]{0,2,1,-3};
        System.out.println(tsc.threeSumClosest(b, target));

        int[] c = new int[]{1,1,-1,-1,3};
        target = 3;
        System.out.println(tsc.threeSumClosest(c, target));
    }

}
