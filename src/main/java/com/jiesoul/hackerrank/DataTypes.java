package com.jiesoul.hackerrank;

import java.util.Scanner;

/**
 * Created with IntelliJ IDEA. Description:
 *
 * @Author: JIESOUL
 * @Date: 2019-01-30
 * @Time: 11:13
 */
public class DataTypes {

  public static void main(String[] args) {
    int i = 4;
    double d = 4.0;
    String s = "HackerRank ";

    Scanner scan = new Scanner(System.in);
    int ii = scan.nextInt();
    double dd = scan.nextDouble();
    String ss = scan.nextLine();

    System.out.println(i + ii);
    System.out.println(d + dd);
    System.out.println(s + ss);

    scan.close();
  }
}
