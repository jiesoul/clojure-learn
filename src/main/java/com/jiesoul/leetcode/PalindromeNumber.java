package com.jiesoul.leetcode;

public class PalindromeNumber {
    public boolean isPalindrome(int x) {
        if (x < 0 ) {
            return false;
        }
        if (x < 10) {
            return true;
        }
        int r = x / 10;
        int num = x % 10;
        while (r > 0) {
            num = num * 10 + r % 10;
            r = r / 10;
        }
        return x - num == 0;
    }

    public static void main(String[] args) {
        PalindromeNumber number = new PalindromeNumber();

        System.out.println(number.isPalindrome(121));
        System.out.println(number.isPalindrome(12));
        System.out.println(number.isPalindrome(-121));
    }
}
