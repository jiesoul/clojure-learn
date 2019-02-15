package com.jiesoul.leetcode;

public class ImplementstrStr {

    public int strStr(String haystack, String needle) {
        if (needle.length() == 0) {
            return 0;
        }
        int i = 0;
        int j = 0;
        while (i < haystack.length() && j < needle.length()) {
            if (haystack.charAt(i) == needle.charAt(j)) {
                i++;
                j++;
            } else {
                i = i - j + 1;
                j = 0;
            }
        }
        if ((j == 0 && i != 0) || j < needle.length()-1) {
            return -1;
        }
        return i-needle.length();
    }

    public static void main(String[] args) {
        System.out.println(new ImplementstrStr().strStr("mississippi", "issipi"));
        System.out.println(new ImplementstrStr().strStr("mississippi", "issip"));
        System.out.println(new ImplementstrStr().strStr("hello", "ll"));
        System.out.println(new ImplementstrStr().strStr("aaaaa", "bba"));
    }
}
