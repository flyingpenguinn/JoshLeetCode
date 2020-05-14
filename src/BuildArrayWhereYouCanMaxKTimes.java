import java.util.Arrays;

/*
LC#1420
Given three integers n, m and k. Consider the following algorithm to find the maximum element of an array of positive integers:


You should build the array arr which has the following properties:

arr has exactly n integers.
1 <= arr[i] <= m where (0 <= i < n).
After applying the mentioned algorithm to arr, the value search_cost is equal to k.
Return the number of ways to build the array arr under the mentioned conditions. As the answer may grow large, the answer must be computed modulo 10^9 + 7.



Example 1:

Input: n = 2, m = 3, k = 1
Output: 6
Explanation: The possible arrays are [1, 1], [2, 1], [2, 2], [3, 1], [3, 2] [3, 3]
Example 2:

Input: n = 5, m = 2, k = 3
Output: 0
Explanation: There are no possible arrays that satisify the mentioned conditions.
Example 3:

Input: n = 9, m = 1, k = 1
Output: 1
Explanation: The only possible array is [1, 1, 1, 1, 1, 1, 1, 1, 1]
Example 4:

Input: n = 50, m = 100, k = 25
Output: 34549172
Explanation: Don't forget to compute the answer modulo 1000000007
Example 5:

Input: n = 37, m = 17, k = 7
Output: 418930126


Constraints:

1 <= n <= 50
1 <= m <= 100
0 <= k <= n
 */
public class BuildArrayWhereYouCanMaxKTimes {
    // at i, previous max i premax, we have remk chances of maxing the previous one
    long[][][] dp;

    public int numOfArrays(int n, int m, int k) {
        dp = new long[n][m + 1][k + 1];
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }
        return (int) don(0, 0, k, n, m);
    }

    long Mod = 1000000007;

    private long don(int i, int premax, int remk, int n, int m) {
        if (remk < 0) {
            return 0;
        }
        if (i == n) {
            return remk == 0 ? 1 : 0;
        }
        if (dp[i][premax][remk] != -1) {
            return dp[i][premax][remk];
        }
        long here = 0;
        for (int cur = 1; cur <= m; cur++) {
            long next = 0;
            if (cur > premax) {
                next = don(i + 1, cur, remk - 1, n, m);
            } else {
                next = don(i + 1, premax, remk, n, m);
            }
            here += next;
            here %= Mod;
        }
        dp[i][premax][remk] = here;
        return here;
    }
}
