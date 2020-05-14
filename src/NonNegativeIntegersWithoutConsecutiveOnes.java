import java.util.Arrays;

/*
LC#600
Given a positive integer n, find the number of non-negative integers less than or equal to n, whose binary representations do NOT contain consecutive ones.

Example 1:
Input: 5
Output: 5
Explanation:
Here are the non-negative integers <= 5 with their corresponding binary representations:
0 : 0
1 : 1
2 : 10
3 : 11
4 : 100
5 : 101
Among them, only integer 3 disobeys the rule (two consecutive ones) and the other 5 satisfy the rule.
Note: 1 <= n <= 109
 */
public class NonNegativeIntegersWithoutConsecutiveOnes {
    // at offset i , previous digit = pre is 0 or 1, and if the number is equal indicated by eq
    int[][][] dp;

    public int findIntegers(int a) {
        String sa = Integer.toBinaryString(a);
        int[] ba = new int[sa.length()];
        for (int i = 0; i < sa.length(); i++) {
            ba[i] = sa.charAt(i) - '0';
        }
        dp = new int[ba.length][2][2];
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < 2; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }
        return dof(0, 0, 1, ba);
    }

    private int dof(int i, int pre, int eq, int[] ba) {
        int n = ba.length;
        if (i == n) {
            return 1;
        }
        if (dp[i][pre][eq] != -1) {
            return dp[i][pre][eq];
        }
        int neq0 = eq;
        if (eq == 1 && ba[i] == 1) {
            neq0 = 0;
        }
        int pick0 = dof(i + 1, 0, neq0, ba);
        if (eq == 1 && ba[i] == 0) {
            dp[i][pre][eq] = pick0;
            return pick0;
        }
        if (pre == 1) {
            dp[i][pre][eq] = pick0;
            return pick0;
        }
        // if eq was 0, keep 0. otherwise keep 1
        int pick1 = dof(i + 1, 1, eq, ba);
        dp[i][pre][eq] = pick0 + pick1;
        return dp[i][pre][eq];
    }
}
