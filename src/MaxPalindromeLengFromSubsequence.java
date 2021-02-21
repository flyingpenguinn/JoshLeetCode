public class MaxPalindromeLengFromSubsequence {
    private Integer[][][] dp;

    public int longestPalindrome(String w1, String w2) {
        dp = new Integer[w1.length()][w2.length()][2];
        int[][] dp2 = new int[w2.length()][w2.length()];
        int[][] dp1 = new int[w1.length()][w1.length()];
        calc(w1, dp1);
        calc(w2, dp2);
        int rt = dol(w1, w2, 0, w2.length() - 1, 0, dp1, dp2);
        return rt <= 1 ? 0 : rt;
    }

    private void calc(String w, int[][] dp) {
        for (int len = 1; len <= w.length(); len++) {
            for (int i = 0; i + len - 1 < w.length(); i++) {
                int j = i + len - 1;
                if (len == 1) {
                    dp[i][j] = 1;
                } else if (len == 2) {
                    dp[i][j] = w.charAt(i) == w.charAt(j) ? 2 : 1; // not 0, but 1 !
                } else {
                    if (w.charAt(i) == w.charAt(j)) {
                        dp[i][j] = 2 + dp[i + 1][j - 1];
                    } else {
                        dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                    }
                }
            }
        }
    }

    private int dol(String w1, String w2, int i, int j, int k, int[][] dp1, int[][] dp2) {
        if (i == w1.length() && j == -1) {
            return 0;
        } else if (i == w1.length()) {
            return k == 0 ? Integer.MIN_VALUE : dp2[0][j];  // what we can make out of w2
        } else if (j == -1) {
            return k == 0 ? Integer.MIN_VALUE : dp1[i][w1.length() - 1];
        }
        if (dp[i][j][k] != null) {
            return dp[i][j][k];
        }
        if (w1.charAt(i) == w2.charAt(j)) {
            dp[i][j][k] = 2 + dol(w1, w2, i + 1, j - 1, 1, dp1, dp2);
        } else {
            int way1 = dol(w1, w2, i + 1, j, k, dp1, dp2);
            int way2 = dol(w1, w2, i, j - 1, k, dp1, dp2);
            dp[i][j][k] = Math.max(way1, way2);
        }
        return dp[i][j][k];
    }
}
