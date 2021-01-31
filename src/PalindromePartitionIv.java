public class PalindromePartitionIv {
    private Boolean[][] dp;

    public boolean checkPartitioning(String s) {
        int n = s.length();
        char[] sc = s.toCharArray();
        dp = new Boolean[n][4];
        boolean[][] palin = new boolean[n][n];
        for (int len = 1; len <= n; len++) {
            for (int i = 0; i + len - 1 < n; i++) {
                int j = i + len - 1;
                if (len == 1) {
                    palin[i][j] = true;
                } else if (len == 2) {
                    if (sc[i] == sc[j]) {
                        palin[i][j] = true;
                    }
                } else {
                    if (sc[i] == sc[j] && palin[i + 1][j - 1]) {
                        palin[i][j] = true;
                    }
                }
            }
        }
        return cando(s, 0, 3, palin);
    }


    private boolean cando(String s, int i, int k, boolean[][] palin) {
        int n = s.length();
        if (i == n) {
            return k == 0;
        }
        if (k == 0) {
            return false;
        }
        if (dp[i][k] != null) {
            return dp[i][k];
        }
        for (int j = i; j < s.length(); j++) {
            if (palin[i][j] && cando(s, j + 1, k - 1, palin)) {
                dp[i][k] = true;
                return true;
            }
        }
        dp[i][k] = false;
        return false;
    }
}
