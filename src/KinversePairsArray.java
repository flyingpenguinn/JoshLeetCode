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
    int Mod = 1000000007;

    //@SILU always find ways to cache formulas like dp[i][j]+dp[i][j-1]+...dp[i][j-k+1]
    // usually by diff between j and j-1
    public int kInversePairs(int n, int k) {
        long[][] dp = new long[n + 1][k + 1];

        long[] pre = new long[k + 1];
        long[] cur = new long[k + 1];

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= k; j++) {
                int allpairs = i * (i - 1) / 2;
                if (allpairs < j) {
                    dp[i][j] = 0;
                } else if (allpairs == j) {
                    dp[i][j] = 1;
                } else if (j == 0) {
                    dp[i][j] = 1;
                } else {
                    // dp[i][j]= dp[i-1][j]+dp[i-1][j-1]+dp[i-1]j-2]...dp[i-1][j-i+1]
                    // dp[i][j+1] = dp[i-1][j+1]+dp[i-1][j]+....+dp[i-1][j+1-i+1]

                    // dp[i][j+1] = dp[i][j]+dp[i-1][j+1]-dp[i-1][j-i+1]
                    long minus = (j - i >= 0) ? dp[i - 1][j - i] : 0;
                    dp[i][j] = dp[i][j - 1] + dp[i - 1][j] - minus;
                    dp[i][j] = (dp[i][j] + Mod) % Mod;
                }


            }
        }
        return (int) dp[n][k];
    }
}


class KinversePairsMine {
    // or another way: use prefix sum
    long Mod = 1000000007;

    public int kInversePairs(int n, int k) {
        long[][] dp = new long[n + 1][k + 1];
        // 1 number, 0 pair is the only possibility

        // dp[i][k] = dp[i-1][k]+ dp[i-1][k-1]+dp[i-1][k-2]+.....dp[i-1][k-i+1];
        long[] psum = new long[k + 1];
        long[] csum = new long[k + 1];
        for (int i = 1; i <= n; i++) {
            for (int t = 0; t <= k; t++) {
                if (t > i * (i - 1) / 2) {
                    dp[i][t] = 0;
                } else if (t == 0) {
                    dp[i][t] = 1;
                } else {
                    // if t-i <0 then we have nothing to chop from, just the whole prefix array
                    long before = t - i < 0 ? 0 : psum[t - i];
                    dp[i][t] = (psum[t] - before + Mod) % Mod;
                }
                csum[t] = (t == 0 ? 0 : csum[t - 1]) + dp[i][t];
                csum[t] %= Mod;
            }
            psum = csum;
            csum = new long[k + 1];
        }
        return (int) (dp[n][k] % Mod);
    }
}
