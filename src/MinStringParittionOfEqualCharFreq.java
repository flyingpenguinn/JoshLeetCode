import java.util.Arrays;

public class MinStringParittionOfEqualCharFreq {
    public int minimumSubstringsInPartition(String s) {
        int n = s.length();
        dp = new int[n];
        Arrays.fill(dp, -1);
        return solve(s, 0);
    }

    private int[] dp;

    private int solve(String s, int i) {
        int n = s.length();
        if (i == n) {
            return 0;
        }
        if (dp[i] != -1) {
            return dp[i];
        }
        int res = n;
        int[] m = new int[26];
        for (int j = i; j < n; ++j) {
            char c = s.charAt(j);
            int cind = c - 'a';
            ++m[cind];
            if (balanced(m)) {
                int later = solve(s, j + 1);
                int cur = later + 1;
                res = Math.min(res, cur);
            }
        }
        dp[i] = res;
        return res;
    }

    private boolean balanced(int[] m) {
        int v = -1;
        for (int i = 0; i < 26; ++i) {
            if (m[i] == 0) {
                continue;
            }
            if (v == -1) {
                v = m[i];
            } else if (v != m[i]) {
                return false;
            }
        }
        return true;
    }
}
