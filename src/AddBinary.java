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
    public String addBinary(String a, String b) {
        StringBuilder sb = new StringBuilder();
        int na = a.length();
        int nb = b.length();
        int i = na - 1;
        int j = nb - 1;
        int carry = 0;
        while (i >= 0 || j >= 0) {
            int ia = i == -1 ? 0 : (a.charAt(i--) - '0');
            int jb = j == -1 ? 0 : (b.charAt(j--) - '0');
            int raw = ia + jb + carry;
            sb.append(raw % 2);
            carry = raw / 2;
        }
        if (carry == 1) {
            sb.append(carry);
        }
        return sb.reverse().toString();

    }
}
