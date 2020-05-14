/*
LC#306
Additive number is a string whose digits can form additive sequence.

A valid additive sequence should contain at least three numbers. Except for the first two numbers, each subsequent number in the sequence must be the sum of the preceding two.

Given a string containing only digits '0'-'9', write a function to determine if it's an additive number.

Note: Numbers in the additive sequence cannot have leading zeros, so sequence 1, 2, 03 or 1, 02, 3 is invalid.



Example 1:

Input: "112358"
Output: true
Explanation: The digits can form an additive sequence: 1, 1, 2, 3, 5, 8.
             1 + 1 = 2, 1 + 2 = 3, 2 + 3 = 5, 3 + 5 = 8
Example 2:

Input: "199100199"
Output: true
Explanation: The additive sequence is: 1, 99, 100, 199.
             1 + 99 = 100, 99 + 100 = 199


Constraints:

num consists only of digits '0'-'9'.
1 <= num.length <= 35
Follow up:
How would you handle overflow for very large input integers?
 */
public class AdditiveNumber {
    public boolean isAdditiveNumber(String a) {
        int n = a.length();
        long n1 = 0;
        for (int i = 0; i < n; i++) {
            if (a.charAt(0) == '0' && i + 1 > 1) {
                break;
            }
            n1 = n1 * 10 + (a.charAt(i) - '0');
            if (n1 >= (1L << 41)) {
                break;
            }
            long n2 = 0;
            for (int j = i + 1; j < n; j++) {
                if (a.charAt(i + 1) == '0' && j - i > 1) {
                    break;
                }
                n2 = n2 * 10 + (a.charAt(j) - '0');

                if (n2 >= (1L << 41)) {
                    break;
                }
                long tn1 = n1;
                long tn2 = n2;
                long t = tn1 + tn2;
                String st = String.valueOf(t);
                int k = j + 1;
                int nums = 2;
                while (k < n) {

                    if (a.startsWith(st, k)) {
                        k += st.length();
                        nums++;
                        tn1 = tn2;
                        tn2 = t;
                        t = tn1 + tn2;
                        st = String.valueOf(t);
                    } else {
                        break;
                    }
                }
                if (k == n && nums >= 3) {
                    return true;
                }
            }
        }
        return false;
    }
}
