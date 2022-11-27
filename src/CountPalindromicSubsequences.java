public class CountPalindromicSubsequences {
    private Long[][][][] dp;
    private long mod = (long) (1e9 + 7);

    public int countPalindromes(String s) {
        char[] sc = s.toCharArray();
        int n = s.length();
        dp = new Long[n][5][10][10];
        return (int) solve(sc, 0, 0, 0, 0);
    }

    // ith pos, j selected, k and l are the left parts
    private long solve(char[] s, int i, int j, int k, int l) {
        int n = s.length;
        if (j == 5) {
            return 1;
        }
        if (i == n) {
            return 0;
        }
        if (dp[i][j][k][l] != null) {
            return dp[i][j][k][l];
        }
        int dig = s[i] - '0';
        long way1 = solve(s, i + 1, j, k, l);
        long way2 = 0;
        if (j == 0) {
            way2 = solve(s, i + 1, 1, dig, l);
        } else if (j == 1) {
            way2 = solve(s, i + 1, 2, k, dig);
        } else if (j == 2) {
            way2 = solve(s, i + 1, 3, k, l);
        } else if (j == 3 && dig == l) {
            way2 = solve(s, i + 1, 4, k, l);
        } else if (j == 4 && dig == k) {
            way2 = solve(s, i + 1, 5, k, l);
        }
        long res = way1 + way2;
        res %= mod;
        dp[i][j][k][l] = res;
        return res;
    }
}
