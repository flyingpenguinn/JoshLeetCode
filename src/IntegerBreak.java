/*
LC#343
Given a positive integer n, break it into the sum of at least two positive integers and maximize the product of those integers. Return the maximum product you can get.

Example 1:

Input: 2
Output: 1
Explanation: 2 = 1 + 1, 1 × 1 = 1.
Example 2:

Input: 10
Output: 36
Explanation: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36.
Note: You may assume that n is not less than 2 and not larger than 58.
 */
public class IntegerBreak {
    int[] dp;

    public int integerBreak(int n) {
        dp = new int[n + 1];
        return doi(n, n);
    }

    int doi(int n, int lim) {
        if (dp[n] != 0) {
            return dp[n];
        }
        int r = 0;
        if (n != lim) {
            r = n;
        }
        for (int i = 1; i < n; i++) {
            int cur = i * doi(n - i, lim);
            r = Math.max(r, cur);
        }
        dp[n] = r;
        return r;
    }
}

// anything >=4 should be broken so we only do 2 and 3
class IntegerBreakMath {
    public int integerBreak(int n) {
        if (n == 3) {
            return 2;
        }
        if (n <= 2) {
            return 1;
        }
        int r = 1;
        // keep taking 3 out till we <=4
        while (n > 4) {
            n -= 3;
            r *= 3;
        }
        // can just return r*n: if >4 we must have taken numbers out so it's ok to just *n. if n==4 it happens to be 4 too
        return r * n;
    }
}
