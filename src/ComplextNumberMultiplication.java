/*
LC#537
Given two strings representing two complex numbers.

You need to return a string representing their multiplication. Note i2 = -1 according to the definition.

Example 1:
Input: "1+1i", "1+1i"
Output: "0+2i"
Explanation: (1 + i) * (1 + i) = 1 + i2 + 2 * i = 2i, and you need convert it to the form of 0+2i.
Example 2:
Input: "1+-1i", "1+-1i"
Output: "0+-2i"
Explanation: (1 - i) * (1 - i) = 1 + i2 - 2 * i = -2i, and you need convert it to the form of 0+-2i.
Note:

The input strings will not have extra blank.
The input strings will be given in the form of a+bi, where the integer a and b will both belong to the range of [-100, 100]. And the output should be also in this form.
 */
public class ComplextNumberMultiplication {

    public String complexNumberMultiply(String x, String y) {
        String[] sa = x.split("\\+");
        String[] sb = y.split("\\+");
        int a = Integer.valueOf(sa[0]);
        int b = Integer.valueOf(sa[1].substring(0, sa[1].length() - 1));
        int c = Integer.valueOf(sb[0]);
        int d = Integer.valueOf(sb[1].substring(0, sb[1].length() - 1));
        int ra = a * c - b * d;
        int rb = a * d + b * c;
        return ra + "+" + rb + "i";
    }
}
