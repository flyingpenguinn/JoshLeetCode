import java.util.Arrays;

public class FindMaxLenOfValidSubseqI {
    private int[][][] dp;

    public int maximumLength(int[] a) {
        int n = a.length;
        dp = new int[n][3][2];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < 3; ++j) {
                Arrays.fill(dp[i][j], -1);
            }
        }
        return Math.max(solve(a, 0, 2, 0), solve(a, 0, 2, 1));
    }

    private int Min = (int) -1e9;

    private int solve(int[] a, int i, int j, int mod) {
        int n = a.length;
        if (i == n) {
            return j == 2 ? Min : 0;
        }
        if (dp[i][j][mod] != -1) {
            return dp[i][j][mod];
        }
        int way1 = solve(a, i + 1, j, mod);
        int way2 = 0;
        if (j == 2) {
            way2 = 1 + solve(a, i + 1, a[i] % 2, mod);
        } else {
            if ((j + a[i]) % 2 == mod) {
                way2 = 1 + solve(a, i + 1, a[i] % 2, mod);
            }
        }
        int res = Math.max(way1, way2);
        dp[i][j][mod] = res;
        return res;
    }
}
