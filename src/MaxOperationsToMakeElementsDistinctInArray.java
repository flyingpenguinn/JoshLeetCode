import java.util.*;

public class MaxOperationsToMakeElementsDistinctInArray {
    public int minLength(String s, int numOps) {
        char[] c = s.toCharArray();
        int n = c.length;
        int[] a = new int[n];
        for (int i = 0; i < n; ++i) {
            a[i] = c[i] - '0';
        }
        int l = 1;
        int u = n;
        while (l <= u) {
            int mid = l + (u - l) / 2;
            dp = new int[n][3][n + 1];
            for (int i = 0; i < n; ++i) {
                for (int j = 0; j < 3; ++j) {
                    Arrays.fill(dp[i][j], -1);
                }
            }
            if (solve(a, mid, 0, 2, 0) > numOps) {
                l = mid + 1;
            } else {
                u = mid - 1;
            }
        }
        return l;
    }

    private int[][][] dp;

    private int solve(int[] a, int len, int i, int pre, int clen) {
        int n = a.length;
        if (clen > len) {
            return (int) 1e9;
        }
        if (i == n) {
            return 0;
        }
        if (dp[i][pre][clen] != -1) {
            return dp[i][pre][clen];
        }
        int v = a[i];
        int fv = a[i] ^ 1;
        int way1 = 0;
        if (v == pre) {
            way1 = solve(a, len, i + 1, pre, clen + 1);
        } else {
            way1 = solve(a, len, i + 1, a[i], 1);
        }
        int way2 = 0;
        if (v == pre) {
            way2 = 1 + solve(a, len, i + 1, fv, 1);
        } else {
            way2 = 1 + solve(a, len, i + 1, fv, clen + 1);
        }
        int res = Math.min(way1, way2);
        dp[i][pre][clen] = res;
        return res;
    }
}
