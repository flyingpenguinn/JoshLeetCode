public class LongestPalindromicSubsequenceII {
    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        // i...j, ending at char k on the two ends
        int[][][] dp = new int[n][n][26];
        for (int len = 2; len <= n; len++) {
            for (int i = 0; i + len - 1 < n; i++) {
                int j = i + len - 1;
                if (s.charAt(i) == s.charAt(j)) {
                    char c = s.charAt(i);
                    int maxnonc = 0;
                    for (int k = 0; k < 26; k++) {
                        char kc = (char) (k + 'a');
                        if (kc != c) {
                            maxnonc = Math.max(maxnonc, dp[i + 1][j - 1][k]);
                            dp[i][j][k] = dp[i + 1][j - 1][k];
                        }
                    }
                    dp[i][j][c - 'a'] = maxnonc + 2;
                } else {
                    for (int k = 0; k < 26; k++) {
                        dp[i][j][k] = Math.max(dp[i + 1][j][k], dp[i][j - 1][k]);
                    }
                }
            }
        }
        int max = 0;
        for (int k = 0; k < 26; k++) {
            max = Math.max(max, dp[0][n - 1][k]);
        }
        return max;

    }
}
