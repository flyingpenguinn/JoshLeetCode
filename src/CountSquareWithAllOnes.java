public class CountSquareWithAllOnes {
    // almost identical to #221 maximal squares: the max side length == how many squares use i,j as right bottom
    public int countSquares(int[][] a) {
        int m = a.length;
        int n = a[0].length;
        int[][] dp = new int[m][n];
        int res = 0;
        // how many square ones use i j as right bottom
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (a[i][j] == 1) {
                    if (i == 0 || j == 0) {
                        dp[i][j] = 1;  // note it's square, so count =1
                    } else {
                        dp[i][j] = Math.min(dp[i - 1][j], Math.min(dp[i][j - 1], dp[i - 1][j - 1])) + 1;
                    }
                }
                res += dp[i][j];
            }
        }
        return res;
    }
}
