import java.util.Arrays;

public class MaxPointsTouristsCanEarn {
    public int maxScore(int n, int k, int[][] ss, int[][] ts) {
        int res = 0;
        dp = new int[k][n];
        for (int i = 0; i < k; ++i) {
            Arrays.fill(dp[i], -1);
        }
        for (int i = 0; i < n; ++i) {
            int cur = solve(0, i, n, k, ss, ts);
            res = Math.max(res, cur);
        }
        return res;
    }

    private int[][] dp;

    private int solve(int day, int city, int n, int k, int[][] ss, int[][] ts) {
        if (day == k) {
            return 0;
        }
        if (dp[day][city] != -1) {
            return dp[day][city];
        }
        int res = ss[day][city] + solve(day + 1, city, n, k, ss, ts);
        for (int j = 0; j < n; ++j) {
            if (j == city) {
                continue;
            }
            int way2 = ts[city][j] + solve(day + 1, j, n, k, ss, ts);
            res = Math.max(res, way2);
        }
        dp[day][city] = res;
        return res;
    }
}
