import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MaxProfitFromTradingStockWithDiscounts {
    // subtree convolution
    // dp return value is subtree spending array on c
    private List<Integer>[] t;
    private int[][][] dp;
    private int Min = (int) -1e9;

    public int maxProfit(int n, int[] present, int[] future, int[][] hierarchy, int budget) {
        t = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            t[i] = new ArrayList<>();
        }
        for (int[] h : hierarchy) {
            t[h[0] - 1].add(h[1] - 1);
        }
        dp = new int[n][2][budget + 1];
        for (int i = 0; i < n; ++i) {
            Arrays.fill(dp[i], null);
        }
        int[] res0 = dfs(0, 0, present, future, budget);
        int res = 0;
        for (int c = 0; c <= budget; c++) {
            res = Math.max(res, res0[c]);
        }
        return res;
    }

    private int[] dfs(int i, int pb, int[] p, int[] f, int budget) {
        if (dp[i][pb] != null) {
            return dp[i][pb];
        }
        int[] dp1 = new int[budget + 1];
        Arrays.fill(dp1, Min);
        dp1[0] = 0;
        for (int ne : t[i]) {
            int[] ndp = new int[budget + 1];
            Arrays.fill(ndp, Min);
            int[] later = dfs(ne, 0, p, f, budget);
            for (int c1 = 0; c1 <= budget; ++c1) {
                for (int c2 = 0; c1 + c2 <= budget; ++c2) {
                    int bestlater = later[c2] + dp1[c1];
                    int nc = c1 + c2;
                    ndp[nc] = Math.max(ndp[nc], bestlater);
                }
            }
            dp1 = ndp;
        }
        int purchase = p[i];
        int half = p[i] / 2;
        if (pb == 1 && half <= budget) {
            purchase = half;

        }
        if (budget >= purchase) {
            // have to separate dp1 and dp2 because they differ on dp[purchase] and dp[0]
            int[] dp2 = new int[budget + 1];
            Arrays.fill(dp2, Min);
            dp2[purchase] = f[i] - purchase;

            for (int ne : t[i]) {
                int[] ndp = new int[budget + 1];
                Arrays.fill(ndp, Min);
                int[] later = dfs(ne, 1, p, f, budget);
                for (int c1 = 0; c1 <= budget; ++c1) {
                    for (int c2 = 0; c1 + c2 <= budget; ++c2) {
                        int bestlater = later[c2] + dp2[c1];
                        int nc = c1 + c2;
                        ndp[nc] = Math.max(ndp[nc], bestlater);
                    }
                }
                dp2 = ndp;
            }
            int[] dpcombine = new int[budget + 1];
            for (int c = 0; c < dp1.length; ++c) {
                dpcombine[c] = Math.max(dp1[c], dp2[c]);
            }
            dp[i][pb] = dpcombine;
            return dpcombine;
        } else {
            dp[i][pb] = dp1;
            return dp1;
        }
    }
}
