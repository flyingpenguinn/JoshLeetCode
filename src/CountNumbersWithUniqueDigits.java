import java.util.Arrays;

/*
LC#357
Given a non-negative integer n, count all numbers with unique digits, x, where 0 ≤ x < 10n.

Example:

Input: 2
Output: 91
Explanation: The answer should be the total numbers in the range of 0 ≤ x < 100,
             excluding 11,22,33,44,55,66,77,88,99
 */
public class CountNumbersWithUniqueDigits {
    // dp: 10^3*2^10 == 1m complexity
    int[][] dp;

    public int countNumbersWithUniqueDigits(int n) {
        int maxn = Math.min(n, 10);
        int r = 0;
        for (int t = 0; t <= maxn; t++) {
            dp = new int[t + 1][1 << 10];
            for (int i = 0; i < dp.length; i++) {
                Arrays.fill(dp[i], -1);
            }
            r += doc(0, 0, t);
        }
        return r;
    }

    int doc(int i, int status, int n) {
        if (i == n) {
            return 1;
        }
        if (dp[i][status] != -1) {
            return dp[i][status];
        }
        int start = i == 0 ? 1 : 0;
        int r = 0;
        for (int j = start; j <= 9; j++) {
            if (((status >> j) & 1) == 1) {
                continue;
            }
            int nstatus = status | (1 << j);
            int later = doc(i + 1, nstatus, n);
            r += later;
        }
        dp[i][status] = r;
        return r;
    }
}

class CountNumberWithUniqueDigitsMath {
    // math, O(dic^2)
    public int countNumbersWithUniqueDigits(int n) {
        int maxn = Math.min(n, 10);
        int r = 0;
        for (int t = 0; t <= maxn; t++) {
            r += count(t);
        }
        return r;
    }

    private int count(int t) {
        if (t == 0) {
            return 1;
        } else if (t == 1) {
            return 9;
        } else {
            int r = 9;
            int base = 9;
            while (t > 1) {
                r *= base;
                base--;
                t--;
            }
            return r;
        }
    }
}