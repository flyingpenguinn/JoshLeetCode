public class KthSmallestInstructions {
    // TLE if we search all given the large k
    // so in order to get lexi kth we first see what should be on the first digit, then second...etc
    // to know this we need to know how many ways we have to place H on the first digit. if it's too small, we pick V and subtract H values
    private Integer[][] dp;

    public String kthSmallestPath(int[] dst, int k) {
        int t = dst[0];
        int l = dst[1];
        dp = new Integer[t + 1][l + 1];
        dfs(0, 0, t, l);
        StringBuilder sb = new StringBuilder();
        int i = 0;
        int j = 0;
        while (i < t || j < l) {
            if (j + 1 <= l && dp[i][j + 1] >= k) {
                sb.append("H");
                j++;
            } else if (j + 1 > l) {
                sb.append("V");
                i++;
            } else {
                sb.append("V");
                k -= dp[i][j + 1];
                i++;
            }
        }
        return sb.toString();

    }

    private int dfs(int i, int j, int t, int l) {
        if (i == t && j == l) {
            dp[i][j] = 1;
            return 1;
        }
        if (dp[i][j] != null) {
            return dp[i][j];
        }
        int res = 0;
        if (j + 1 <= l) {
            res += dfs(i, j + 1, t, l);
        }
        if (i + 1 <= t) {
            res += dfs(i + 1, j, t, l);
        }
        dp[i][j] = res;
        return res;
    }
}
