package com.jiesoul.leetcode;

import java.util.Arrays;

public class LongestCommonPrefix {

    public static String longestCommonPrefix(String[] strs) {
        int len = strs.length;
        if (len == 0) {
            return "";
        }
        String prefix = "";
        Arrays.stream(strs).findFirst();
        int k = 0;
        boolean bool = true;
        while (bool) {
            String s = strs[0];
            if (k > s.length() - 1) {
                break;
            }
            char c = s.charAt(k);
            for (int i = 1; i < strs.length; i++) {
                String ss = strs[i];
                if (k > ss.length()-1) {
                    bool = false;
                    break;
                }
                char cc = ss.charAt(k);
                if (cc != c) {
                    bool = false;
                    break;
                }
            }
            if (bool) {
                prefix += c;
            }
            k++;
        }

        return prefix;
    }

    public static String longestCommonPrefix1(String[] strs) {
        if (strs.length == 0) {
            return "";
        }
        String perfix = strs[0];
        for (int i = 1; i < strs.length; i++) {
            while (strs[i].indexOf(perfix) != 0) {
                perfix = perfix.substring(0, perfix.length()-1);
                if (perfix.isEmpty()) {
                    return "";
                }
            }
        }
        return perfix;
    }


    public static void main(String[] args) {
        System.out.println(longestCommonPrefix1(new String[]{"dog","racecar","car"}));
        System.out.println(longestCommonPrefix1(new String[]{"flower","flow","flight"}));
    }
}
