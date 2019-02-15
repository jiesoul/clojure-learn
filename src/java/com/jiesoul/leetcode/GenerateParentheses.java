package com.jiesoul.leetcode;

import java.util.ArrayList;
import java.util.List;

public class GenerateParentheses {

    // when n==4 , (())(()) not
    public List<String> generateParenthesis1(int n) {

        if (n == 0) {
            return null;
        } else if (n == 1) {
            List<String> list = new ArrayList<>();
            list.add("()");
            return list;
        } else {
            List<String> temp = generateParenthesis1(n-1);
            List<String> list = new ArrayList<>();
            for (String s : temp) {
                list.add("(" + s + ")");
            }
            for (String s : temp) {
                list.add(s + "()");

                if (!(s + "()").equals("()" + s)) {
                    list.add("()" + s);
                }
            }
            return list;

        }
    }

    public List<String> generateParenthesis2(int n) {
        List<String> list = new ArrayList<>();
        if (n == 0) {
            list.add("");
        } else {
            for (int i = 0; i < n; ++i) {
                for (String left : generateParenthesis2(i)) {
                    for (String right : generateParenthesis2(n-1-i)) {
                        list.add("(" + left + ")" + right);
                    }
                }
            }
        }

        return list;
    }

    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();
        backtrack(ans, "", 0, 0, n);
        return ans;
    }

    public void backtrack(List<String> ans, String cur, int open, int close, int max) {
        if (cur.length() == max * 2) {
            ans.add(cur);
            return;
        }
        if (open < max) {
            backtrack(ans, cur + "(", open+1, close, max);
        }
        if (close < open) {
            backtrack(ans, cur + ")", open, close+1, max);
        }
    }

    public static void main(String[] args) {
        GenerateParentheses parentheses = new GenerateParentheses();
        List<String> list = parentheses.generateParenthesis(4);
        for (String s : list) {
            System.out.println(s);
        }
    }
}
