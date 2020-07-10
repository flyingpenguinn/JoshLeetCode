import java.util.Arrays;

/*
LC#1269
5274. Number of Ways to Stay in the Same Place After Some Steps
User Accepted:210
User Tried:305
Total Accepted:210
Total Submissions:470
Difficulty:Hard
You have a pointer at index 0 in an array of size arrLen. At each step, you can move 1 position to the left, 1 position to the right in the array or stay in the same place  (The pointer should not be placed outside the array at any time).

Given two integers steps and arrLen, return the number of ways such that your pointer still at index 0 after exactly steps steps.

Since the answer may be too large, return it modulo 10^9 + 7.



Example 1:

Input: steps = 3, arrLen = 2
Output: 4
Explanation: There are 4 differents ways to stay at index 0 after 3 steps.
Right, Left, Stay
Stay, Right, Left
Right, Stay, Left
Stay, Stay, Stay
Example 2:

Input: steps = 2, arrLen = 4
Output: 2
Explanation: There are 2 differents ways to stay at index 0 after 2 steps
Right, Left
Stay, Stay
Example 3:

Input: steps = 4, arrLen = 2
Output: 8


Constraints:

1 <= steps <= 500
1 <= arrLen <= 10^6
 */
public class NumberOfWaysStayInSamePlace {
    private int[][] dp;

    public int numWays(int steps, int n) {
        dp = new int[steps + 1][steps + 1];
        for (int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], -1);
        }
        return solve(0, 0, steps, n);
    }

    private int Mod = 1000000007;

    private int solve(int step, int pos, int steps, int n) {
        if (pos < 0 || pos >= n) {
            return 0;
        }
        if (step == steps) {
            return pos == 0 ? 1 : 0;
        }
        if (dp[step][pos] != -1) {
            return dp[step][pos];
        }
        int remSteps = steps - step;
        if (pos > remSteps) {
            return 0;
        }
        int left = solve(step + 1, pos - 1, steps, n);
        int right = solve(step + 1, pos + 1, steps, n);
        int stay = solve(step + 1, pos, steps, n);
        long rawRes = (0L + left + right + stay);
        int res = (int) (rawRes % Mod);
        dp[step][pos] = res;
        return res;
    }
}
