import base.ArrayUtils;

public class SellingPiecesOfWood {

    public long sellingWood(int m, int n, int[][] prices) {
        long[][] dp = new long[m + 1][n + 1];
        for (int[] p : prices) {
            dp[p[0]][p[1]] = p[2];
        }
        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                for (int k = 1; k <= i / 2; ++k) {
                    dp[i][j] = Math.max(dp[i][j], dp[k][j] + dp[i - k][j]);
                }
                for (int k = 1; k <= j / 2; ++k) {
                    dp[i][j] = Math.max(dp[i][j], dp[i][k] + dp[i][j - k]);
                }
            }
        }
        return dp[m][n];
    }


    public static void main(String[] args) {
        System.out.println(new SellingPiecesOfWood().sellingWood(10, 11, ArrayUtils.read("[[1,10,1],[8,1,3],[7,2,9]]")));
    }
}

