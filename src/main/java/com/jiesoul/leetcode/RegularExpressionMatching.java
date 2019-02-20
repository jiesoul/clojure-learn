package com.jiesoul.leetcode;

public class RegularExpressionMatching {
    public boolean isMatch(String s, String p) {
        int sl = s.length();
        int pl = p.length();
        if (pl == 0) {
            return sl == 0;
        }

        if (pl == 1 || p.charAt(1) != '*') {
            if (sl == 0 || (p.charAt(0) != '.' && p.charAt(0) != s.charAt(0))) {
                return false;
            }
            return isMatch(s.substring(1), p.substring(1));
        }
        int i = -1;
        while (i < sl && (i < 0 || p.charAt(0) == '.' || p.charAt(0) == s.charAt(i))) {
            if (isMatch(s.substring(i+1), p.substring(2))) {
                return true;
            }
            i++;
        }
        return false;

    }

    public boolean isMatch1(String s, String p) {
        if (p.isEmpty()) {
            return s.isEmpty();
        }
        boolean firstMatch = (!s.isEmpty() && (p.charAt(0) == s.charAt(0) || p.charAt(0) == '.'));
        if (p.length() >= 2 && p.charAt(1) == '*') {
            return isMatch1(s, p.substring(2)) || (firstMatch && isMatch1(s.substring(1), p));
        } else {
            return firstMatch && isMatch1(s.substring(1), p.substring(1));
        }
    }

    public static void main(String[] args) {
        RegularExpressionMatching matching = new RegularExpressionMatching();
        System.out.println(matching.isMatch("mississippi", "mis*is*ip*."));
        System.out.println(matching.isMatch("aaa", "a*a"));
        System.out.println(matching.isMatch("aab", "c*a*b"));
        System.out.println(matching.isMatch("ab", ".*c"));
        System.out.println(matching.isMatch("aaa", "aaaa"));
        System.out.println(matching.isMatch("mississippi", "mis*is*p*."));
        System.out.println(matching.isMatch("aa", "a*"));
        System.out.println(matching.isMatch("aa", "a"));
        System.out.println(matching.isMatch("ab", ".*"));
    }
}
