public class FindMaxFruitsCollected {
    // trap: can't pass the diagonal
    public int maxCollectedFruits(int[][] a) {
        int n = a.length;
        int res = 0;
        int c1 = 0;
        for (int i = 0; i < n; ++i) {
            // child 1 can only visit diagnal
            c1 += a[i][i];
        }
        dp = new Integer[n][n];
        int c2 = solve(a, 0, n - 1, 1); // step 0, col is n-1, row is i
        dp = new Integer[n][n];
        int c3 = solve(a, 0, n - 1, 2);// step 0, row is n-1, col is i
        // System.out.println(c1+" "+c2+" "+c3);
        return c1 + c2 + c3;
    }

    private Integer[][] dp;

    private int solve(int[][] a, int i, int j, int type) {
        int n = a.length;
        if (j < 0 || j >= n) {
            return Integer.MIN_VALUE;
        }
        if (i > n - 1) {
            return Integer.MIN_VALUE;
        }

        if (i == n - 1) {
            if (j != n - 1) {
                return Integer.MIN_VALUE;
            } else {
                return 0;
            }
        }

        if (dp[i][j] != null) {
            return dp[i][j];
        }
        if (i >= j) {
            return 0;
        }
        int cc = type == 1 ? a[i][j] : a[j][i];
        int way1 = cc + solve(a, i + 1, j - 1, type);
        int way2 = cc + solve(a, i + 1, j, type);
        int way3 = cc + solve(a, i + 1, j + 1, type);
        int res = Math.max(way1, Math.max(way2, way3));
        dp[i][j] = res;
        return res;
    }
}
