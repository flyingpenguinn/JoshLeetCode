import java.util.Arrays;

public class StringCompressionII {
    // 2d dp, while we run past at most j DIFFERENT chars from i. there is some greedy in it as if we keep i, we better remove OTHER characters
    private Integer[][] dp;

    public int getLengthOfOptimalCompression(String s, int k) {
        int n = s.length();
        dp = new Integer[n][k + 1];
        return solve(s, k, 0);
    }

    private int getclen(int llen) {
        if (llen == 1) {
            return 1;
        } else if (llen < 10) {
            return 2;
        } else if (llen < 100) {
            return 3;
        }
        return 4;
    }

    private int solve(String s, int k, int i) {
        int n = s.length();
        if (k < 0) {
            return Integer.MAX_VALUE;
        }
        if (i == n) {
            return 0;
        }
        if (dp[i][k] != null) {
            return dp[i][k];
        }

        int res = solve(s, k - 1, i + 1); // delete
        int count = 0, sind = s.charAt(i) - 'a', removed = 0;
        for (int j = i; j < n; ++j) {
            int jind = s.charAt(j) - 'a';
            if (jind == sind) {
                ++count;
            } else {
                ++removed;
            }
            if (removed > k) {
                break;
            }
            int cur = getclen(count) + solve(s, k - removed, j + 1);
            res = Math.min(res, cur);
        }
        dp[i][k] = res;
        return res;
    }
}