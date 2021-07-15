/*
LC#629
Given two integers n and k, find how many different arrays consist of numbers from 1 to n such that there are exactly k inverse pairs.

We define an inverse pair as following: For ith and jth element in the array, if i < j and a[i] > a[j] then it's an inverse pair; Otherwise, it's not.

Since the answer may be very large, the answer should be modulo 109 + 7.

Example 1:

Input: n = 3, k = 0
Output: 1
Explanation:
Only the array [1,2,3] which consists of numbers from 1 to 3 has exactly 0 inverse pair.


Example 2:

Input: n = 3, k = 1
Output: 2
Explanation:
The array [1,3,2] and [2,1,3] have exactly 1 inverse pair.


Note:

The integer n is in the range [1, 1000] and k is in the range [0, 1000].
 */
public class KinversePairsArray {
    // given 1....n-1, we have dp[n-1][k] ways
    // dp[n][k] = dp[n-1][k] -> put n at the last, n
    // dp[n-1][k-1]-> put n at the n-1...thus forming k
    // dp[n-1][k-2] -> put n at the n-2 th
    // dp[n-1][k-(n-1)] -> put n at the first. note if(k-n-1)<=0 then it would be starting from 0
    private long Mod = 1000000007;

    public int kInversePairs(int n, int k) {
        long[][] dp = new long[n + 1][k + 1];
        long[] psum = new long[k + 1];
        for (int i = 1; i <= n; i++) {
            long[] csum = new long[k + 1];
            for (int j = 0; j <= k; j++) {
                if (j == 0) {
                    dp[i][j] = 1;
                } else {
                    dp[i][j] = psum[j] - (j < i ? 0 : psum[j - i]);
                }
                dp[i][j] %= Mod;
                if (dp[i][j] < 0) {
                    dp[i][j] += Mod;
                }
                csum[j] = (j == 0 ? 0 : csum[j - 1]) + dp[i][j];
                csum[j] %= Mod;
            }
            psum = csum;
        }
        return (int) (dp[n][k]);
    }
}
