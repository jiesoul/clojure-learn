package com.jiesoul.leetcode;

import java.util.HashMap;
import java.util.Map;

public class RomantoInteger {

    public static int romanToInt(String s) {
        Map<Character, Integer> roman = new HashMap<>();
        roman.put('M', 1000);
        roman.put('D', 500);
        roman.put('C', 100);
        roman.put('L', 50);
        roman.put('X', 10);
        roman.put('V', 5);
        roman.put('I', 1);
        int result = 0;
        for (int i = 0, len = s.length(); i < len; i++) {
            char c = s.charAt(i);
            int temp = roman.get(c);
            if (i < len-1) {
                int n = roman.get(s.charAt(i+1));
                if (n > temp) {
                    temp = n - temp;
                    i++;
                }
            }
            result += temp;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(romanToInt("MCMXCIV"));
        System.out.println(romanToInt("LVIII"));
        System.out.println(romanToInt("IV"));
        System.out.println(romanToInt("IX"));
        System.out.println(romanToInt("III"));
    }
}
