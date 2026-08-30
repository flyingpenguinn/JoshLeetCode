import java.util.Arrays;

public class MinOperationsToFormSubsetSumI {
    private int Max = (int) (1e9);
    private int timelimit = 15;
    private int[][][][] dp;

    public int minOperations(int[] a, int sum) {
        int n = a.length;
        dp = new int[n][3][timelimit][sum + 1];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < 3; ++j) {
                for (int k = 0; k < timelimit; ++k) {
                    Arrays.fill(dp[i][j][k], -1);
                }
            }
        }
        int rt = solve(a, 0, 0, 0, 0, sum);
        return rt>=Max? -1:rt;
    }

    private int solve(int[] a, int i, int type, int times, int csum, int sum) {
        int n = a.length;
        if (csum > sum) {
            return Max;
        }
        if (i == n) {
            return csum == sum ? 0 : Max;
        }
        if (dp[i][type][times][csum] != -1) {
            return dp[i][type][times][csum];
        }
        int way0 = solve(a, i + 1, 0, 0, csum, sum);

        int way1 = Max;
         if (times + 1 < timelimit && type != 2 && (a[i] * (1 << (times+1))<=sum) ) {
            way1 = 1 + solve(a, i, 1, times + 1, csum, sum);
        }
        int way2 = Max;
        if (times + 1 < timelimit && type != 1 && a[i]>=(1<<(times+1))) {
            way2 = 1 + solve(a, i, 2, times + 1, csum, sum);
        }

        int way3 = Max;
        if (type == 1) {
            int added = a[i] * (1 << times);
            way3 = solve(a, i + 1, 0, 0, csum + added, sum);
        } else if (type == 2) {
            int added = a[i];
            while (times > 0) {
                added /= 2;
                --times;
            }
            way3 = solve(a, i + 1, 0, 0, csum + added, sum);
        } else {
            way3 = solve(a, i + 1, 0, 0, csum + a[i], sum);
        }
        int res = Math.min(way1, Math.min(way2, Math.min(way3, way0)));
        dp[i][type][times][csum] = res;
        return res;
    }
}
