import java.util.HashMap;
import java.util.Map;

public class MinCostToSplitAnArray {
    private Integer[] dp;

    public int minCost(int[] a, int k) {
        dp = new Integer[a.length];
        return solve(a, 0, k);
    }

    private int solve(int[] a, int i, int k) {
        int n = a.length;
        if (i == n) {
            return 0;
        }
        if (dp[i] != null) {
            return dp[i];
        }
        Map<Integer, Integer> m = new HashMap<>();
        int tlen = 0;
        int res = Integer.MAX_VALUE;
        for (int j = i; j < n; ++j) {
            int oldv = m.getOrDefault(a[j], 0);
            int nv = oldv + 1;
            m.put(a[j], nv);
            if (oldv == 1) {
                tlen += 2;
            } else if (oldv > 1) {
                ++tlen;
            }

            int cur = k + tlen + solve(a, j + 1, k);
            res = Math.min(res, cur);
        }
        dp[i] = res;
        return res;
    }
}
