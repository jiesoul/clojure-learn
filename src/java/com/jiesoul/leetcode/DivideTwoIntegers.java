package com.jiesoul.leetcode;


import clojure.lang.Numbers;

import java.util.List;

public class DivideTwoIntegers {

    public static int divide1(int dividend, int divisor) {
        boolean dividendNeg = dividend < 0 ? true : false;
        boolean divisorNeg = divisor < 0 ? true : false;
        int r = 0;
        int d = dividendNeg ? dividend : (0 - dividend);
        int v = divisorNeg ? divisor : (0 - divisor);
        if (v == -1) {
            r = -d;
        } else {
            while (v >= d) {
                r++;
                d = d - v;
            }
        }

        if (dividendNeg != divisorNeg) {
            return -r;

        } else {
            if (r <= Integer.MIN_VALUE) {
                r = Integer.MAX_VALUE;
            }
            return r;
        }

    }

    public static int divide(int dividend, int divisor) {
        boolean dividendNeg = dividend < 0 ? true : false;
        boolean divisorNeg = divisor < 0 ? true : false;
        int r = 0;
        int d = dividendNeg ? dividend : (0 - dividend);
        long v = divisorNeg ? divisor : (0 - divisor);
        int j = 0;
        if (v == -1) {
            r = -d;
            if (dividendNeg != divisorNeg) {
                return -r;
            } else {
                if (r <= Integer.MIN_VALUE) {
                    r = Integer.MAX_VALUE;
                }
                return r;
            }
        } else {
            long t = v;
            int tt = 0;
            while (t >= d) {
                t = (v << r);
                r += 1;
                tt += r;
            }

            if (dividendNeg != divisorNeg) {
                return -r;
            } else {
                return r;
            }
        }

    }

    public static void main(String[] args) {
//        int a = Numbers.divide() 10 / 2;

        System.out.println(DivideTwoIntegers.divide(10, 3));
        System.out.println(DivideTwoIntegers.divide(5, 3));
        System.out.println(DivideTwoIntegers.divide(100, 3));
        System.out.println(DivideTwoIntegers.divide(70, -3));
        System.out.println(DivideTwoIntegers.divide(-7, -3));
        System.out.println(DivideTwoIntegers.divide(-2147483648, -1));
        System.out.println(DivideTwoIntegers.divide(-2147483648, 1));
        System.out.println(DivideTwoIntegers.divide(-2147483648, 2));
    }
}
