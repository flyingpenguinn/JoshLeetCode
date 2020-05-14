import java.util.Arrays;

/*
LC#518
You are given coins of different denominations and a total amount of money. Write a function to compute the number of combinations that make up that amount. You may assume that you have infinite number of each kind of coin.



Example 1:

Input: amount = 5, coins = [1, 2, 5]
Output: 4
Explanation: there are four ways to make up the amount:
5=5
5=2+2+1
5=2+1+1+1
5=1+1+1+1+1
Example 2:

Input: amount = 3, coins = [2]
Output: 0
Explanation: the amount of 3 cannot be made up just with coins of 2.
Example 3:

Input: amount = 10, coins = [10]
Output: 1


Note:

You can assume that

0 <= amount <= 5000
1 <= coin <= 5000
the number of coins is less than 500
the answer is guaranteed to fit into signed 32-bit integer
 */
public class CoinChange2 {
    // just need one array dp amount because we only depend on i+1 or i-1's amount.rolling array
    // 2d rolling array. the dimension that we have array on must be in the inner most loop.
    public int change(int t, int[] a) {
        int n = a.length;
        int[] dp = new int[t + 1];
        dp[0] = 1;
        for (int i = n - 1; i >= 0; i--) {
          for (int j = 0; j <= t; j++) {

                int nopick = dp[j];
                int pick = 0;
                if (j >= a[i]) {
                    pick = dp[j - a[i]];
                }
                dp[j] = nopick + pick;
            }
        }
        return dp[t];
    }

    public static void main(String[] args) {
        CoinChange2 cc2 = new CoinChange2();
        int[] coins = {1, 2};
        System.out.println(cc2.change(3, coins));
    }
}


class CoinChange2DpMemoization {
    // easiest way is to do 2d memoization
    int[][] dp;

    public int change(int t, int[] a) {
        int n = a.length;
        dp = new int[n][t + 1];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], -1);
        }
        return doc(0, t, a);
    }

    int doc(int i, int t, int[] a) {
        int n = a.length;
        if (i == n) {
            return t == 0 ? 1 : 0;
        }
        if (dp[i][t] != -1) {
            return dp[i][t];
        }
        int wo = doc(i + 1, t, a);
        int w = 0;
        if (t >= a[i]) {
            // note we dont move i to give a[i] another chance
            w = doc(i, t - a[i], a);
        }
        dp[i][t] = wo + w;
        return dp[i][t];
    }
}

class CoinChange2DpIterative2D {
    // almost a direction conversion from memoization. can further reduce to 1d array because we only need j+1
    public int change(int t, int[] a) {
        int n = a.length;
        int[][] dp = new int[n + 1][t + 1];
        dp[n][0] = 1;
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j <= t; j++) {
                int nopick = dp[i + 1][j];
                int pick = 0;
                if (j >= a[i]) {
                    pick = dp[i][j - a[i]];
                }
                dp[i][j] = nopick + pick;
            }
        }
        return dp[0][t];
    }
}