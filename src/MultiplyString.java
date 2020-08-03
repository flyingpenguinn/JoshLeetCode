/*
LC#43
Given two non-negative integers num1 and num2 represented as strings, return the product of num1 and num2, also represented as a string.

Example 1:

Input: num1 = "2", num2 = "3"
Output: "6"
Example 2:

Input: num1 = "123", num2 = "456"
Output: "56088"
Note:

The length of both num1 and num2 is < 110.
Both num1 and num2 contain only digits 0-9.
Both num1 and num2 do not contain any leading zero, except the number 0 itself.
You must not use any built-in BigInteger library or convert the inputs to integer directly.
 */

import java.util.Arrays;

public class MultiplyString {

    // traps:
    // 1. += ! += ! +=
    // use carry in add
    // take care of starting 0s and resultant 0
    public String multiply(String num1, String num2) {
        int[] res = new int[num1.length() + num2.length()];
        for (int i = num1.length() - 1; i >= 0; i--) {
            for (int j = num2.length() - 1; j >= 0; j--) {
                int v1 = num1.charAt(i) - '0';
                int v2 = num2.charAt(j) - '0';
                int multi = v1 * v2;
                res[i + j + 1] += multi % 10;
                res[i + j] += multi / 10;
            }
        }
        int carry = 0;
        for (int i = res.length - 1; i >= 0; i--) {
            int sum = res[i] + carry;
            res[i] = sum % 10;
            carry = sum / 10;
        }
        int i = 0;
        while (i < res.length && res[i] == 0) {
            i++;
        }
        StringBuilder sb = new StringBuilder();
        while (i < res.length) {
            sb.append(res[i++]);
        }
        return sb.length() == 0 ? "0" : sb.toString();
    }


    public static void main(String[] args) {
        System.out.println(new MultiplyString().multiply("999", "999"));
    }
}
