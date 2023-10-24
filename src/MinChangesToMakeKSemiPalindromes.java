import java.util.Arrays;

public class MinChangesToMakeKSemiPalindromes {
    private int MAX = (int) 1e9;
    private int[][] v;
    private Integer[][] dp;

    public int minimumChanges(String s, int k) {
        int n = s.length();
        v = new int[n][n + 1];
        for (int i = 0; i < n; ++i) {
            Arrays.fill(v[i], MAX);
        }
        dp = new Integer[n][k + 1];
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                int len = j - i + 1;
                for (int p = 1; p * p <= len; ++p) {
                    if (len % p != 0) {
                        continue;
                    }
                    int cur = cost(s.toCharArray(), i, j, p);
                    v[i][j] = Math.min(v[i][j], cur);
                    int other = len / p;
                    if (other > p && other < len && len % other == 0) {
                        cur = cost(s.toCharArray(), i, j, other);
                        v[i][j] = Math.min(v[i][j], cur);
                    }
                }
            }
        }
        return solve(s.toCharArray(), k, 0);
    }

    private int cost(char[] s, int start, int end, int d) {
        int res = 0;
        for (int ss = start; ss < start + d; ++ss) {
            int se = (end - ss) / d * d + ss;

            int i = ss;
            int j = se;
            while (i < j) {
                if (s[i] != s[j]) {
                    ++res;
                }
                i += d;
                j -= d;
            }
        }
        return res;
    }

    private int solve(char[] s, int k, int i) {
        int n = s.length;
        if (k <= 0) {
            return MAX;
        }
        if (i >= n - 1) {
            return MAX;
        }
        if (k == 1) {
            return i == n ? MAX : v[i][n - 1];
        }
        if (dp[i][k] != null) {
            return dp[i][k];
        }
        int res = MAX;
        for (int j = i + 1; j < n; ++j) {
            int later = solve(s, k - 1, j + 1);
            int cost = v[i][j] + later;
            res = Math.min(res, cost);
        }
        dp[i][k] = res;
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new MinChangesToMakeKSemiPalindromes().minimumChanges("bbcaaaaacbb", 2));
    }
}
