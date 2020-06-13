/*
LC#67
Given two binary strings, return their sum (also a binary string).

The input strings are both non-empty and contains only characters 1 or 0.

Example 1:

Input: a = "11", b = "1"
Output: "100"
Example 2:

Input: a = "1010", b = "1011"
Output: "10101"
 */
public class AddBinary {
    int base = 2;

    public String addBinary(String a, String b) {
        int i = a.length() - 1;
        int j = b.length() - 1;
        int carry = 0;
        StringBuilder sb = new StringBuilder();
        while (i >= 0 || j >= 0 || carry > 0) {
            int va = i < 0 ? 0 : a.charAt(i--) - '0';
            int vb = j < 0 ? 0 : b.charAt(j--) - '0';
            int sum = va + vb + carry;
            sb.append(sum % base);
            carry = sum / base;
        }
        return sb.reverse().toString();
    }
}
