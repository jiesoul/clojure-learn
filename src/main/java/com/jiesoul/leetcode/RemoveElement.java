package com.jiesoul.leetcode;

public class RemoveElement {

    public int removeElement(int[] nums, int val) {
        int len = nums.length;
        int l = 0;
        for (int i = 0; i < len; i ++) {
            if (nums[i] == val) {
                for (int j = i + 1; j < len ; j++) {
                    if (nums[j] != val) {
                        int temp = nums[j];
                        nums[j] = nums[i];
                        nums[i] = temp;
                        l++;
                        break;
                    }
                }
            } else {
                l++;
            }
        }
        return l;
    }

    public static void main(String[] args) {
        int[] a = {0,1,2,2,3,0,4,2};
        RemoveElement e = new RemoveElement();
        System.out.println(e.removeElement(a, 2));

        int[] b = {1,1};
        System.out.println(e.removeElement(b, 1));
    }
}
