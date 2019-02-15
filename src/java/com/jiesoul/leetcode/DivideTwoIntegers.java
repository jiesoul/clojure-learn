package com.jiesoul.leetcode;

public class DivideTwoIntegers {

    public static int divide(int dividend, int divisor) {
        boolean flag = false;
        if ((dividend > 0 && divisor > 0) || (dividend < 0 && divisor < 0)) {
            flag = true;
        }

        if (divisor == 0) {
            throw new RuntimeException("divisor not is zero");
        }

        int r = 0;
        int d = dividend == Integer.MIN_VALUE ? Integer.MAX_VALUE : Math.abs(dividend);
        int v = divisor == Integer.MIN_VALUE ? Integer.MAX_VALUE : Math.abs(divisor);
        while (v <= d) {
            r++;
            d = d - v;
        }

        if (r == Integer.MIN_VALUE) {
            return Integer.MAX_VALUE;
        } else {
            return flag ? r : -r;
        }

    }

    public static void main(String[] args) {
        System.out.println(DivideTwoIntegers.divide(10, 3));
        System.out.println(DivideTwoIntegers.divide(7, -3));
        System.out.println(DivideTwoIntegers.divide(-7, -3));
        System.out.println(DivideTwoIntegers.divide(-2147483648, -1));
    }
}
