package com.jiesoul.leetcode.zigzag;

import java.util.ArrayList;
import java.util.List;

public class Solution {

    public String convert(String s, int numRows) {
        boolean isContinue = true;
        String[] result = new String[numRows];
        for (int i = 0; i < numRows; i++) {
            result[i] = "";
        }
        int n = 0;
        for (int i = 0; i < s.length(); i++) {
            if (n == 0) {
                isContinue = false;
            }
            if (n == numRows -1) {
                isContinue = true;
            }
            if (isContinue) {
                result[n % numRows] += s.charAt(i);
                n--;
            } else {
                result[n] += s.charAt(i);
                n++;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (String ss : result) {
            sb.append(ss);
        }

        return sb.toString();
    }

    public String convert1(String s, int numRows) {
        int len = s.length();
        if (numRows == 1) {
            return s;
        }
        List<StringBuilder> rows = new ArrayList<>();
        for (int i = 0; i < Math.min(len, numRows); i++) {
            rows.add(new StringBuilder());
        }

        int curRow = 0;
        boolean isContinue = false;
        for (int i = 0; i < len; i++) {
            rows.get(curRow).append(s.charAt(i));
            if (curRow == 0 || curRow + 1 == numRows) {
                isContinue = !isContinue;
            }
            curRow += isContinue ? 1 : -1;
        }

        StringBuilder str = new StringBuilder();
        for (StringBuilder ss : rows) {
            str.append(ss);
        }

        return str.toString();
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        long now = System.currentTimeMillis();
        System.out.println(solution.convert1("PAYPALISHIRING", 3));
        System.out.println("PAHNAPLSIIGYIR");
        System.out.println(solution.convert1("PAYPALISHIRING", 4));
        System.out.println("PINALSIGYAHRPI");
        System.out.println("time: " + (System.currentTimeMillis() - now));
    }
}
