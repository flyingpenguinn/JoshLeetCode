import java.util.Arrays;

public class CoinChange {
    // "shua biao fa"
    int Max = 10000000;

    public int coinChange(int[] a, int t) {
        int n = a.length;
        int[] dp = new int[t + 1];
        Arrays.fill(dp, Max);
        dp[0] = 0;
        // dp[0]==0
        for (int i = 0; i < n; i++) {
            for (int j = 0; j + a[i] <= t; j++) {
                dp[j + a[i]] = Math.min(dp[j + a[i]], dp[j] + 1);
            }
        }
        return dp[t] >= Max ? -1 : dp[t];
    }
}

class CoinChange2DBottomUp {
    int Max = 10000000;

    public int coinChange(int[] a, int t) {
        int n = a.length;
        int[][] dp = new int[n + 1][t + 1];
        for (int j = 1; j <= t; j++) {
            dp[n][j] = Max;
        }
        // dp[j][0]==0
        // we can reverse i and j here no harm
        for (int j = 1; j <= t; j++) {
            for (int i = n - 1; i >= 0; i--) {
                dp[i][j] = dp[i + 1][j];
                if (j - a[i] >= 0) {
                    int wt = 1 + dp[i][j - a[i]];
                    dp[i][j] = Math.min(dp[i][j], wt);
                }
            }
        }
        return dp[0][t] >= Max ? -1 : dp[0][t];
    }
}

class CoinChangeMemoization {
    int[][] dp = null;

    // get amount, by using i to n-1 coins only
    private int doCoinChange(int[] coins, int amount, int i) {
        if (i == coins.length && amount > 0) {
            return -1;
        }
        if (amount == 0) {
            return 0;
        }
        if (dp[i][amount] != -2) {
            return dp[i][amount];
        }
        int minValue = doCoinChange(coins, amount, i + 1);// without
        if (amount - coins[i] >= 0) {
            int with = doCoinChange(coins, amount - coins[i], i);
            if (with >= 0) {
                with++;// add cur coin
                minValue = minValue < 0 ? with : Math.min(minValue, with);
            }
        }
        dp[i][amount] = minValue;
        return minValue;
    }

    public int coinChange(int[] coins, int amount) {
        // -2 to distinguish not done, and no result
        dp = new int[coins.length][amount + 1];
        for (int[] row : dp) {
            Arrays.fill(row, -2);
        }
        return doCoinChange(coins, amount, 0);
    }
}