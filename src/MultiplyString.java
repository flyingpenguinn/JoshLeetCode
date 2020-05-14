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

public class MultiplyString {

    // put raw numbers from i and j to i+j+1 first then from n-1 to 0 do the carrying
    public String multiply(String a, String b) {
        int na = a.length();
        int nb = b.length();
        int nall = na + nb;
        int[] r = new int[nall];
        for (int i = na - 1; i >= 0; i--) {
            for (int j = nb - 1; j >= 0; j--) {
                int av = a.charAt(i) - '0';
                int bv = b.charAt(j) - '0';
                int raw = av * bv;
                // not i+j, i+j+1. for example 33*34, 2 is at position 2+2+1 =5
                r[i + j + 1] += raw % 10;
                r[i + j] += raw / 10;
            }
        }
        int carry = 0;
        for (int i = nall - 1; i >= 1; i--) {
            int raw = r[i] + carry;
            r[i] = raw % 10;
            r[i - 1] += raw / 10;
        }
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < nall && r[i] == 0) {
            i++;
        }
        while (i < nall) {
            sb.append(r[i++]);
        }
        return sb.length() == 0 ? "0" : sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(new MultiplyString().multiply("999", "999"));
    }
}
