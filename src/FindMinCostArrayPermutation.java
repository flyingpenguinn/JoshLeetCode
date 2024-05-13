import java.util.Arrays;

public class FindMinCostArrayPermutation {
    // dont need to memorize on i when we have st
    private int min = (int) 1e9;
    private int[] res;
    private int[][] next;
    private int[][] dp;

    public int[] findPermutation(int[] a) {
        int n = a.length;
        int chosen = 0;
        for (int i = 0; i < n; ++i) {
            next = new int[(1 << n)][n];
            dp = new int[(1 << n)][n];

            for (int k = 0; k < (1 << n); ++k) {
                Arrays.fill(dp[k], -1);
            }

            int cur = dfs(a, (1 << i), a[i], a[i]);
            if (cur > min) {
                continue;
            }
            int[] cres = new int[n];
            int j = 1;
            int st = (1 << i);
            int pre = a[i];
            cres[0] = a[i];
            while (j < n) {
                int nxt = next[st][pre];
                cres[j] = a[nxt];
                st = st | (1 << nxt);
                pre = a[nxt];
                ++j;
            }
            if (cur < min) {
                chosen = a[i];
                min = cur;
                res = cres;
            } else if (chosen > a[i]) {
                chosen = a[i];
                res = cres;
            }
        }
        return res;
    }

    private int dfs(int[] a, int st, int pre, int first) {
        int n = a.length;
        if (st + 1 == (1 << n)) {
            return Math.abs(pre - a[first]);
        }
        if (dp[st][pre] != -1) {
            return dp[st][pre];
        }
        int res = (int) 1e9;
        int chosen = 0;
        for (int j = 0; j < n; ++j) {
            if (((st >> j) & 1) == 0) {
                int cur = a[j];
                int cp = Math.abs(pre - a[cur]);
                int nst = (st | (1 << j));
                int later = cp + dfs(a, nst, cur, first);
                if (later < res) {
                    res = later;
                    next[st][pre] = j;
                    chosen = cur;
                } else if (later == res && chosen > cur) {
                    next[st][pre] = j;
                    chosen = cur;
                }
            }
        }
        dp[st][pre] = res;
        return res;
    }
}
