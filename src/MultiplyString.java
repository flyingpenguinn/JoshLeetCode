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

    // put raw numbers from i and j to i+j+1 and i+j first then from n-1 to 0 do the carrying
    public String multiply(String a, String b) {

        int[] prod = new int[a.length() + b.length()];
        for (int i = a.length() - 1; i >= 0; i--) {
            for (int j = b.length() - 1; j >= 0; j--) {
                int va = a.charAt(i) - '0';
                int vb = b.charAt(j) - '0';
                int multi = va * vb;
                prod[i + j + 1] += multi % 10;  // must be +=
                prod[i + j] += multi / 10;
            }
        }
        int carry = 0;
        for (int i = prod.length - 1; i >= 0; i--) {
            int cur = prod[i] + carry;
            carry = cur / 10;
            prod[i] = cur % 10;
        }
        int i = 0;
        while (i < prod.length && prod[i] == 0) {
            i++;
        }
        if (i == prod.length) {
            return "0";
        }
        StringBuilder sb = new StringBuilder();
        while (i < prod.length) {
            sb.append(prod[i++]);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(new MultiplyString().multiply("999", "999"));
    }
}
