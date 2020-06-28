import java.util.Arrays;

/*
LC#279
Given a positive integer n, find the least number of perfect square numbers (for example, 1, 4, 9, 16, ...) which sum to n.

Example 1:

Input: n = 12
Output: 3
Explanation: 12 = 4 + 4 + 4.
Example 2:

Input: n = 13
Output: 2
Explanation: 13 = 4 + 9.
 */
public class PerfectSquares {
    //memoization... can also use iterative ways. On*sqrt(n)
    int[] dp;

    public int numSquares(int n) {
        dp = new int[n + 1];
        return dp(n);
    }

    private int dp(int n) {
        if (n == 0) {
            return 0;
        }
        if (dp[n] != 0) {
            return dp[n];
        }
        int min = n;
        for (int i = 1; i * i <= n; i++) {
            int sq = i * i;
            int later = dp(n - sq);
            min = Math.min(min, later + 1);
        }
        dp[n] = min;
        return min;
    }

    public static void main(String[] args) {
        System.out.println(new PerfectSquares().numSquares(135));
    }
}
