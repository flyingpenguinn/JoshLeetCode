import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/*
LC#1012
Given a positive integer N, return the number of positive integers less than or equal to N that have at least 1 repeated digit.



Example 1:

Input: 20
Output: 1
Explanation: The only positive number (<= 20) with at least 1 repeated digit is 11.
Example 2:

Input: 100
Output: 10
Explanation: The positive numbers (<= 100) with atleast 1 repeated digit are 11, 22, 33, 44, 55, 66, 77, 88, 99, and 100.
Example 3:

Input: 1000
Output: 262


Note:

1 <= N <= 10^9
 */
public class NumbersWithRepeatedDigits {
    // digital dp on numbers with all unique numbers
    int[][][] dp;

    public int numDupDigitsAtMostN(int num) {
        String s = String.valueOf(num);
        int n = s.length();
        dp = new int[n][2][1 << 10];
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[i].length; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }
        return num - don(0, s, 1, 0) + 1;
    }

    // all unique numbers+ empty. so subtract this in the above code
    private int don(int i, String s, int eq, int st) {
        int n = s.length();
        if (i == n) {
            return 1;
        }
        if (dp[i][eq][st] != -1) {
            return dp[i][eq][st];
        }
        // this is the technique to get numbers whose length < s.length()
        int cur = st == 0 ? don(i + 1, s, 0, st) : 0;
        int sd = s.charAt(i) - '0';
        int start = st == 0 ? 1 : 0;
        for (int j = start; j <= 9; j++) {
            if (eq == 1 && j > sd) {
                break;
            }
            int neq = eq == 1 && j == sd ? 1 : 0;
            if (((st >> j) & 1) == 0) {
                int nst = (st | (1 << j));
                cur += don(i + 1, s, neq, nst);
            }
        }
        dp[i][eq][st] = cur;
        return cur;
    }

    public static void main(String[] args) {
        System.out.println(new NumbersWithRepeatedDigits().numDupDigitsAtMostN(20));
    }
}
