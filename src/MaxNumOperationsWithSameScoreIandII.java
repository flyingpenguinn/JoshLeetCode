public class MaxNumOperationsWithSameScoreIandII {
    public int maxOperations(int[] a) {
        int n = a.length;
        dp = new Integer[n][n];
        int way1 = a[0] + a[1];
        int way2 = a[0] + a[n - 1];
        int way3 = a[n - 2] + a[n - 1];
        int r1 = solve(a, 0, n - 1, way1);

        dp = new Integer[n][n];
        int r2 = solve(a, 0, n - 1, way2);

        dp = new Integer[n][n];
        int r3 = solve(a, 0, n - 1, way3);
        return Math.max(r1, Math.max(r2, r3));
    }

    private Integer[][] dp;

    private int solve(int[] a, int i, int j, int t) {
        if (i >= j) {
            return 0;
        }
        if (dp[i][j] != null) {
            return dp[i][j];
        }
        int r1 = 0;
        int r2 = 0;
        int r3 = 0;
        if (a[i] + a[i + 1] == t) {
            r1 = 1 + solve(a, i + 2, j, t);
        }
        if (a[i] + a[j] == t) {
            r2 = 1 + solve(a, i + 1, j - 1, t);
        }
        if (a[j - 1] + a[j] == t) {
            r3 = 1 + solve(a, i, j - 2, t);
        }
        //  System.out.println(i+","+j+" r1="+r1+" r2="+r2+" r3="+r3);
        int res = Math.max(r1, Math.max(r2, r3));
        dp[i][j] = res;
        return res;
    }
}
