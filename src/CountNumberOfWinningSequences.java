import java.util.Arrays;

public class CountNumberOfWinningSequences {
    private String ones = "WFE";
    private long[][][] dp;
    private int shift = 1001;
    private long mod = (long) (1e9 + 7);

    public int countWinningSequences(String s) {
        int n = s.length();
        dp = new long[n][4][2005];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < 4; ++j) {
                Arrays.fill(dp[i][j], -1);
            }
        }
        long rt = solve(s.toCharArray(), 0, 3, 0);
        return (int) rt;
    }

    private long solve(char[] s, int i, int j, int k) {
        int n = s.length;
        if (i == n) {
            return k > 0 ? 1 : 0;
        }
        int kshift = k + shift;
        if (dp[i][j][kshift] != -1) {
            return dp[i][j][kshift];
        }
        int aindex = ones.indexOf(s[i]);
        long res = 0;
        for (int p = 0; p < ones.length(); ++p) {
            if (j == p) {
                continue;
            }
            long cur = 0;
            if (p == (aindex + 1) % 3) {
                cur = solve(s, i + 1, p, k - 1);
            } else if (p == aindex) {
                cur = solve(s, i + 1, p, k);
            } else {
                cur = solve(s, i + 1, p, k + 1);
            }
            res += cur;
            res %= mod;
        }
        dp[i][j][kshift] = res;
        return res;
    }
}
