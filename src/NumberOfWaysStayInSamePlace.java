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
    long[][] dp;
    int Mod = 1000000007;

    public int numWays(int steps, int len) {
        dp = new long[steps + 2][steps + 2];
        for (int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], -1);
        }
        return (int) dos(0, len, steps);
    }

    private long dos(int i, int len, int steps) {
        if (i > steps) {
            return 0;
        }
        if (steps == 0) {
            if (i == 0) {
                return 1;
            } else {
                return 0;
            }
        }
        if (dp[i][steps] != -1) {
            return dp[i][steps];
        }
        long wayr = 0;
        if (i + 1 < len) {
            wayr = dos(i + 1, len, steps - 1);
        }
        long wayl = 0;
        if (i - 1 >= 0) {
            wayl = dos(i - 1, len, steps - 1);
        }
        long ways = dos(i, len, steps - 1);
        dp[i][steps] = wayl + wayr + ways;
        dp[i][steps] %= Mod;
        return dp[i][steps];
    }
}
