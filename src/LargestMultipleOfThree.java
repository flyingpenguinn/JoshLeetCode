import java.util.Arrays;

/*
LC#1363
Given an integer array of digits, return the largest multiple of three that can be formed by concatenating some of the given digits in any order.

Since the answer may not fit in an integer data type, return the answer as a string.

If there is no answer return an empty string.



Example 1:

Input: digits = [8,1,9]
Output: "981"
Example 2:

Input: digits = [8,6,7,1,0]
Output: "8760"
Example 3:

Input: digits = [1]
Output: ""
Example 4:

Input: digits = [0,0,0,0,0,0]
Output: "0"


Constraints:

1 <= digits.length <= 10^4
0 <= digits[i] <= 9
The returning answer must not contain unnecessary leading zeros.
 */
public class LargestMultipleOfThree {
    // only get rid of one number if possible. otherwise get rid of 2. no more than 2
    // similar to 1262 same reasoning
    public String largestMultipleOfThree(int[] digits) {
        Arrays.sort(digits);
        int n = digits.length;
        if (digits[n - 1] == 0) {
            return "0";
        }
        int sum = 0;
        boolean[] takeout = new boolean[n];
        for (int i = 0; i < n; i++) {
            sum += digits[i];
        }
        if (sum % 3 == 0) {
            return tos(digits, takeout);
        } else {
            if (sum % 3 == 1) {
                for (int i = 0; i < n; i++) {
                    if (digits[i] % 3 == 1) {
                        takeout[i] = true;
                        return tos(digits, takeout);
                    }
                }
                int twos = 0;
                for (int i = 0; i < n; i++) {
                    if (digits[i] % 3 == 2) {
                        takeout[i] = true;
                        twos++;
                        if (twos == 2) {
                            return tos(digits, takeout);
                        }
                    }
                }
            } else {
                for (int i = 0; i < n; i++) {
                    if (digits[i] % 3 == 2) {
                        takeout[i] = true;
                        return tos(digits, takeout);
                    }
                }
                int ones = 0;
                for (int i = 0; i < n; i++) {
                    if (digits[i] % 3 == 1) {
                        takeout[i] = true;
                        ones++;
                        if (ones == 2) {
                            return tos(digits, takeout);
                        }
                    }
                }
            }
        }
        return "";
    }

    private String tos(int[] digits, boolean[] takeout) {
        StringBuilder sb = new StringBuilder();
        for (int i = digits.length - 1; i >= 0; i--) {
            if (!takeout[i]) {
                sb.append(digits[i]);
            }
        }
        return sb.toString();
    }
}
