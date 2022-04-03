import java.util.Arrays;

public class NumberOfWaysToSelectBuildings {
    private long[][][] dp;

    public long numberOfWays(String s) {
        int n = s.length();
        dp = new long[n][4][3];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < 4; ++j) {
                Arrays.fill(dp[i][j], -1);
            }
        }
        int[] ss = new int[n];
        for (int i = 0; i < n; ++i) {
            ss[i] = s.charAt(i) - '0';
        }
        return solve(ss, 0, 3, 2);
    }

    private long solve(int[] s, int i, int j, int k) {
        int n = s.length;
        if (i == n) {
            return j == 0 ? 1L : 0L;
        }
        if (dp[i][j][k] != -1) {
            return dp[i][j][k];
        }

        long way1 = solve(s, i + 1, j, k);
        long way2 = 0;
        if (s[i] != k && j > 0) {
            way2 = solve(s, i + 1, j - 1, s[i]);
        }
        long rt = way1 + way2;
        dp[i][j][k] = rt;
        return rt;
    }
}

class NumberOfWaysToSelectBuildingsAlternativeSolution {
    public long numberOfWays(String s) {
        long[][] dp = new long[4][2];
        dp[0][0] = dp[0][1] = 1;
        for (int i = 0; i < s.length(); ++i) {
            int v = s.charAt(i) - '0';
            for (int j = 1; j <= 3; ++j) {
                dp[j][v] += dp[j - 1][v ^ 1];
            }
        }
        return dp[3][0] + dp[3][1];
    }
}
