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
        int ca = 0;
        StringBuilder sb = new StringBuilder();
        while (i >= 0 || j >= 0) {
            int av = i == -1 ? 0 : a.charAt(i--) - '0';
            int bv = j == -1 ? 0 : b.charAt(j--) - '0';
            int raw = av + bv + ca;
            int res = raw % 10;
            sb.append(res);
            ca = raw / 10;

        }
        if (ca != 0) {
            sb.append(ca);
        }
        return sb.reverse().toString();
    }
}
