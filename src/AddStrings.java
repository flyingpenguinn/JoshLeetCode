/*
LC#415
Given two non-negative integers num1 and num2 represented as string, return the sum of num1 and num2.

Note:

The length of both num1 and num2 is < 5100.
Both num1 and num2 contains only digits 0-9.
Both num1 and num2 does not contain any leading zero.
You must not use any built-in BigInteger library or convert the inputs to integer directly.
 */
public class AddStrings {
    public String addStrings(String a, String b) {
        int i = a.length() - 1;
        int j = b.length() - 1;
        StringBuilder sb = new StringBuilder();
        int carry = 0;
        while (i >= 0 || j >= 0 || carry > 0) {
            int va = i < 0 ? 0 : a.charAt(i--) - '0';
            int vb = j < 0 ? 0 : b.charAt(j--) - '0';
            int sum = va + vb + carry;
            sb.append(sum % 10);
            carry = sum / 10;
        }
        return sb.reverse().toString();
    }
}
