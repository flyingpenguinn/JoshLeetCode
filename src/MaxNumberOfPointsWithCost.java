import base.ArrayUtils;

public class MaxNumberOfPointsWithCost {
    // on each row as we go to the right or go to the left, we "enable" more values that can compete the max value
    private int Max = 1000000000;
    private int Min = -Max;

    public long maxPoints(int[][] a) {
        int m = a.length;
        int n = a[0].length;
        long[][] dp = new long[m][n];
        for (int j = 0; j < n; j++) {
            dp[0][j] = a[0][j];
        }
        for (int i = 1; i < m; i++) {
            long maxleft = Min;
            // dp[i][p]-j+p if p<=j
            for (int j = 0; j < n; j++) {
                maxleft = Math.max(maxleft, dp[i - 1][j] + j);
                dp[i][j] = Math.max(dp[i][j], maxleft - j + a[i][j]);
            }
            long maxright = Min;
            for (int j = n - 1; j >= 0; j--) {
                maxright = Math.max(maxright, dp[i - 1][j] - j);
                dp[i][j] = Math.max(dp[i][j], maxright + j + a[i][j]);
            }
        }
        long rt = Min;
        for (int j = 0; j < n; j++) {
            rt = Math.max(rt, dp[m - 1][j]);
        }
        return rt;
    }

    public static void main(String[] args) {
        System.out.println(new MaxNumberOfPointsWithCost().maxPoints(ArrayUtils.read("[[1,2,3],[1,5,1],[3,1,1]]")));
    }
}
