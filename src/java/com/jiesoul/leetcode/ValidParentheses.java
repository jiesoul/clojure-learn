package com.jiesoul.leetcode;

import java.util.Stack;

public class ValidParentheses {

    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '{' || c == '[' || c == '(') {
                stack.push(c);
            }

            if (c == '}' && (stack.isEmpty() || stack.pop() != '{')) {
                return false;
            }
            if (c == ']' && (stack.isEmpty() || stack.pop() != '[')) {
                return false;
            }
            if (c == ')' && (stack.isEmpty() || stack.pop() != '(')) {
                return false;
            }
        }

        return stack.isEmpty();
    }
}
