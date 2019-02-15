package com.jiesoul.leetcode;

public class DivideTwoIntegers {

    public static int divide(int dividend, int divisor) {
        boolean flag = false;
        if ((dividend > 0 && divisor > 0) || (dividend < 0 && divisor < 0)) {
            flag = true;
        }

        if (dividend == 0) {
            throw new RuntimeException("divisor not is zero");
        }

        int r = 0;
        int d = Math.abs(dividend);
        int v = Math.abs(divisor);
        while (d < v) {
            r++;
            d = d - v;
        }

        return flag ? r : -r;

    }

    public static void main(String[] args) {
        System.out.println(DivideTwoIntegers.divide(10, 3));
        System.out.println(DivideTwoIntegers.divide(7, -3));
        System.out.println(DivideTwoIntegers.divide(-2147483648, -1));
    }
}
