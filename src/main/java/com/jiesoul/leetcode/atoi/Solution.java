package com.jiesoul.leetcode.atoi;

public class Solution {

    public int myAtoi1(String str) {
        String s = str.trim();
        if (s.length() == 0) {
            return 0;
        }
        boolean neg = false;
        if (s.startsWith("-") || s.startsWith("+")) {
            if (s.startsWith("-")) {
                neg = true;
            }
            s = s.substring(1);
        }
        char c = s.charAt(0);
        if (c < 48 || c > 57) {
            return 0;
        }

        java.util.regex.Pattern pattern = java.util.regex.Pattern.compile("^\\d+");
        java.util.regex.Matcher matcher = pattern.matcher(s);
        while (matcher.find()) {
            s = matcher.group(0);
        }
        int ret = 0;
        try {
            ret = Integer.parseInt(s);
        } catch (Exception e) {
            if (neg) {
                ret = Integer.MIN_VALUE;
            } else {
                ret = Integer.MAX_VALUE;
            }
        }
        return ret;
        
    }

    public int myAtoi(String str) {
        String s = str.trim();
        boolean neg = false;
        if (s.startsWith("-")) {
            neg = true;
            s = s.substring(1);
        } else if (s.startsWith("+")) {
            s = s.substring(1);
        }
        long ret = 0;
        for (int i = 0; i < s.length(); i++) {
            int c = s.charAt(i) - 48;
            if (c > 10 || c < 0) {
                break;
            }
            ret = ret * 10 + c;
            if (!neg && ret > Integer.MAX_VALUE) {
                ret = Integer.MAX_VALUE;
                break;
            } else if (neg && ret < Integer.MIN_VALUE) {
                ret = Integer.MIN_VALUE;
                break;
            }
        }
        if (neg) {
            ret = -ret;
        }
        return (int)ret;
    }

    public static void main(String[] args) {
        String str = "-+419";
        java.util.regex.Pattern pattern = java.util.regex.Pattern.compile("^(\\-|\\+)\\d+");
        java.util.regex.Matcher matcher = pattern.matcher(str);
        while (matcher.find()) {
            System.out.println(matcher.group());
        }

        Solution solution = new Solution();
        System.out.println(solution.myAtoi1("   -419 with words"));
        System.out.println(solution.myAtoi1("    -4193"));
        System.out.println(solution.myAtoi1("4193"));
        System.out.println(solution.myAtoi1("words and 987"));
        System.out.println(solution.myAtoi1("-91283472332"));
        System.out.println(solution.myAtoi1("20000000000000000000"));
        System.out.println(solution.myAtoi1("-9223372036854775808"));

    }
}
