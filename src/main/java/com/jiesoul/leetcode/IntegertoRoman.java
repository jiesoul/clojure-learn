package com.jiesoul.leetcode;

public class IntegertoRoman {

    public static String intToRoman(int num) {
        int[] a = new int[]{1000, 100, 10, 1};
        String[] b = new String[]{"M", "C", "X", "I"};
        String[] c = new String[]{"", "D", "L", "V"};
        StringBuilder roman = new StringBuilder();

        for (int i = 0; i < a.length; i++) {
            int q = num / a[i];
            if (q == 9) {
                roman.append(b[i]).append(b[i-1]);
            } else if (q >= 5) {
                roman.append(c[i]);
                while (q > 5) {
                    roman.append(b[i]);
                    q--;
                }
            } else if (q == 4) {
                roman.append(b[i]).append(c[i]);
            } else {
                while (q > 0) {
                    roman.append(b[i]);
                    q--;
                }
            }

            num = num % a[i];
        }

        return roman.toString();
    }

    public static void main(String[] args) {
        System.out.println(intToRoman(1994));
        System.out.println(intToRoman(58));
        System.out.println(intToRoman(9));
        System.out.println(intToRoman(4));
        System.out.println(intToRoman(3));
    }
}
