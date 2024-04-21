public class MinOperationsToSatisfyCondition {
    // each column must be of the same number
    private Integer[][] dp;

    public int minimumOperations(int[][] a) {
        int m = a.length;
        int n = a[0].length;


        dp = new Integer[n][11];
        return solve(a, 0, 10);
    }

    private int solve(int[][] a, int col, int pre) {

        int m = a.length;
        int n = a[0].length;
        if (col == n) {
            return 0;
        }
        if (dp[col][pre] != null) {
            return dp[col][pre];
        }
        int res = (int) 1e9;
        for (int k = 0; k <= 9; ++k) {
            if (k == pre) {
                continue;
            }
            int cres = 0;
            for (int i = 0; i < m; ++i) {
                if (a[i][col] != k) {
                    ++cres;
                }
            }
            int cur = cres + solve(a, col + 1, k);
            res = Math.min(res, cur);
        }
        dp[col][pre] = res;
        return res;
    }
}
