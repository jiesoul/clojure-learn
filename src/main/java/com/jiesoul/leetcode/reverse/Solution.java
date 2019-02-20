package com.jiesoul.leetcode.reverse;

public class Solution {

    public int reverse(int x) {
        if (x < 10 && x > -10) {
            return x;
        }
        boolean neg = false;
        if (x < 0) {
            neg = true;
            x = -x;
        }
        int num = x / 10;
        long ret = x % 10;
        while (num / 10 != 0) {
            ret = ret * 10 + (num % 10);
            num = num / 10;
        }
        ret = ret * 10 + num;
        if (ret > Integer.MAX_VALUE || ret < Integer.MIN_VALUE) {
            ret = 0;
        }
        if (neg) {
            ret = -ret;
        }
        return (int) ret;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        System.out.println(solution.reverse(321));
        System.out.println(solution.reverse(-321));
    }
}
