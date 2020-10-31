import java.util.Arrays;

/*
LC#673
Given an unsorted array of integers, find the number of longest increasing subsequence.

Example 1:
Input: [1,3,5,4,7]
Output: 2
Explanation: The two longest increasing subsequence are [1, 3, 4, 7] and [1, 3, 5, 7].
Example 2:
Input: [2,2,2,2,2]
Output: 5
Explanation: The length of longest continuous increasing subsequence is 1, and there are 5 subsequences' length is 1, so output 5.
Note: Length of the given array will be not exceed 2000 and the answer is guaranteed to be fit in 32-bit signed int.
 */
public class NumberOfLongestIncreasingSubseq {
    // record the length and count for each index, then find max
    public int findNumberOfLIS(int[] a) {
        int n = a.length;
        if (n == 0) {
            return 0;
        }
        int[][] dp = new int[n][2];// len and count
        int max = -1;
        for (int i = 0; i < n; i++) {
            int maxj0 = -1;
            for (int j = i - 1; j >= 0; j--) {
                if (a[j] < a[i]) {
                    maxj0 = Math.max(maxj0, dp[j][0]);
                }
            }
            dp[i][0] = maxj0 + 1;
            for (int j = i - 1; j >= 0; j--) {
                if (a[j] < a[i] && dp[j][0] == maxj0) {
                    dp[i][1] += dp[j][1];
                }
            }
            if (dp[i][1] == 0) {
                dp[i][0] = 1;
                dp[i][1] = 1;
            }
            max = Math.max(max, dp[i][0]);
        }
        int res = 0;
        for (int i = 0; i < n; i++) {
            if (dp[i][0] == max) {
                res += dp[i][1];
            }
        }
        return res;
    }
}
