package com.jiesoul.leetcode;

import java.util.*;

public class LetterCombinationsofaPhoneNumber {

    public List<String> letterCombinations(String digits) {
        Map<Character, String[]> map = new HashMap<>();
        map.put('2', new String[]{"a", "b", "c"});
        map.put('3', new String[]{"d", "e", "f"});
        map.put('4', new String[]{"g", "h", "i"});
        map.put('5', new String[]{"j", "k", "l"});
        map.put('6', new String[]{"m", "n", "o"});
        map.put('7', new String[]{"p", "q", "r", "s"});
        map.put('8', new String[]{"t", "u", "v"});
        map.put('9', new String[]{"w", "x", "y", "z"});


        int len = digits.length();
        if (len == 0) {
            return new ArrayList<>();
        }
        if (len == 1) {
            List<String> list = new ArrayList<>();
            String[] st = map.get(digits.charAt(0));
            list.addAll(Arrays.asList(st));
            return list;
        } else {
            String[] ss = map.get(digits.charAt(0));
            List<String> list = new ArrayList<>();
            for (String s : ss) {
                List<String> l = letterCombinations(digits.substring(1));
                for (String sss : l) {
                    list.add(s + sss);
                }
            }
            return list;
        }
    }

    public static void main(String[] args) {
        LetterCombinationsofaPhoneNumber letter = new LetterCombinationsofaPhoneNumber();
        List<String> ss = letter.letterCombinations("234");
        for (String s: ss) {
            System.out.println(s);
        }
    }

}
