import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MaxScoreAfterNOperations {
    // subset enumerations
    public int maxScore(int[] a) {
        int n = a.length;
        int[][] dp = new int[n][1 << n];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
        return solve(a, 0, 0, dp);
    }

    private int solve(int[] a, int k, int st, int[][] dp) {
        int n = a.length;
        if (k == n) {
            return 0;
        }
        if (dp[k][st] != -1) {
            return dp[k][st];
        }
        int res = 0;
        for (int i = 0; i < n; ++i) {
            if (((st >> i) & 1) == 1) {
                continue;
            }
            for (int j = i + 1; j < n; ++j) {
                if (((st >> j) & 1) == 1) {
                    continue;
                }
                int gv = gcd(a[i], a[j]);
                int cur = gv * (k + 1);
                int nst = st;
                nst |= (1 << i);
                nst |= (1 << j);
                int cres = cur + solve(a, k + 1, nst, dp);
                res = Math.max(res, cres);
            }
        }
        dp[k][st] = res;
        return res;
    }

    private int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }
}
