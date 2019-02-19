package com.jiesoul.leetcode;

import java.util.Arrays;
import java.util.Collection;

/**
 * Created with IntelliJ IDEA. Description:
 *
 * @Author: JIESOUL
 * @Date: 2019-02-19
 * @Time: 9:11
 */
public class TwoSum {


  public int[] twosum(int[] nums, int target) {
    int[] numsre = new int[2];
    for (int i = 0; i < nums.length; i++) {
      for (int j = i + 1; j < nums.length; j++) {
        if (nums[i] + nums[j] == target) {
          numsre[0] = i;
          numsre[1] = j;
          return numsre;
        }
      }
    }
    return numsre;
  }


  public int[] twosum1(int[] nums, int target) {
    int[] numsre = new int[2];
    for (int i = 0; i < nums.length; i++) {
      if (nums[i] > target) {
        return numsre;
      }
      for (int j = i + 1; j < nums.length; j++) {
        if (nums[i] + nums[j] >= target) {
          numsre[0] = i;
          numsre[1] = j;
          return numsre;
        }
      }
    }
    return numsre;
  }
}
