public class MinWhiteTilesAfterCoveringWithCarpet {
    private Integer[][] dp;

    public int minimumWhiteTiles(String floor, int nc, int cl) {
        int n = floor.length();
        dp = new Integer[n][nc + 1];
        return solve(floor.toCharArray(), 0, nc, cl);
    }

    // ith tile, have j carpets left
    private int solve(char[] a, int i, int j, int cl) {
        int n = a.length;
        if (i >= n) {
            return 0;
        }
        if (dp[i][j] != null) {
            return dp[i][j];
        }
        int cur = a[i] == '1' ? 1 : 0;
        int way1 = (int) 1e9;
        if (j > 0) {
            way1 = solve(a, i + cl, j - 1, cl);
        }
        int way2 = cur + solve(a, i + 1, j, cl);
        int rt = Math.min(way1, way2);
        dp[i][j] = rt;
        return rt;
    }
}
