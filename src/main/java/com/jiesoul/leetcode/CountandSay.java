package com.jiesoul.leetcode;

public class CountandSay {
    public String countAndSay(int n) {
        if (n == 1) {
            return "1";
        } else {
            return say(countAndSay(n-1));
        }

    }

    private String say(String str) {
        StringBuilder sb = new StringBuilder();
        int k = 1;
        char c = str.charAt(0);
        for (int i = 1; i < str.length(); i++) {
            if (c == str.charAt(i)) {
                k++;
            } else {
                sb.append(k).append(c);
                k = 1;
                c = str.charAt(i);
            }
        }
        sb.append(k).append(c);
        return sb.toString();
    }

    public static void main(String[] args) {
        CountandSay countandSay = new CountandSay();
        System.out.println(countandSay.countAndSay(6));
        System.out.println(countandSay.countAndSay(5));
        System.out.println(countandSay.countAndSay(4));
        System.out.println(countandSay.countAndSay(2));
        System.out.println(countandSay.countAndSay(1));
    }
}
