import base.ArrayUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MaxProfitFromTradingStockWithDiscounts {
    // subtree convolution template 2
    /*
    Counting DP:
  invalid/default = 0
  transition = += ways

Max DP:
  invalid/default = -INF
  transition = max(...)

Min DP:
  invalid/default = +INF
  transition = min(...)
     */
    private List<Integer>[] t;
    private int[][][][] dp;
    private int Min = -(int) 1e9;

    public int maxProfit(int n, int[] present, int[] future, int[][] hierarchy, int budget) {
        t = new ArrayList[n];
        for (int i = 0; i < n; ++i) {
            t[i] = new ArrayList<>();
        }
        for (int[] h : hierarchy) {
            int v1 = h[0] - 1;
            int v2 = h[1] - 1;
            t[v1].add(v2);
        }
        dp = new int[n][budget + 1][2][2];
        for (int i = 0; i < n; ++i) {
            init(dp[i]);
        }
        solve(0, budget, present, future);
        int res = 0;
        for (int j = 0; j <= budget; ++j) {
            res = Math.max(res, dp[0][j][0][0]);
            res = Math.max(res, dp[0][j][0][1]);
        }
        return res;
    }

    private void init(int[][][] dp) {

        for (int j = 0; j <= dp[0].length; ++j) {
            for (int k = 0; k < dp[j].length; ++k) {
                Arrays.fill(dp[j][k], Min);
            }
        }

    }


    private void solve(int i, int allb, int[] p, int[] f) {
        // at leaf level set what happens when we only have this node
        /*budget dimension means exact money already spent, a one-node subtree has only these possibilities:

        do not buy i → spend 0
        buy i without discount → spend exactly p[i]
        buy i with discount → spend exactly p[i] / 2
        */
        dp[i][0][0][0] = 0;
        dp[i][0][1][0] = 0;
        if (p[i] <= allb) {
            dp[i][p[i]][0][1] = f[i] - p[i];
        }
        if (p[i] / 2 <= allb) {
            dp[i][p[i] / 2][1][1] = f[i] - p[i] / 2;
        }
        for (int ne : t[i]) {
            solve(ne, allb, p, f);
            merge(ne, i, allb);
        }

    }

    private void merge(int ne, int i, int allb) {
        int[][][] ndp = new int[allb + 1][2][2];
        init(ndp);
        for (int pb = 0; pb <= allb; ++pb) {
            for (int cb = 0; cb + pb <= allb; ++cb) {
                int sumb = pb + cb;
                for (int pf = 0; pf <= 1; ++pf) {
                    int maxsub = Math.max(dp[ne][cb][pf][0], dp[ne][cb][pf][1]);
                    int profit0 = dp[i][pb][0][pf] + maxsub;
                    ndp[sumb][0][pf] = Math.max(ndp[sumb][0][pf], profit0);
                    int profit1 = dp[i][pb][1][pf] + maxsub;
                    ndp[sumb][1][pf] = Math.max(ndp[sumb][1][pf], profit1);
                }
            }
        }
        dp[i] = ndp;
    }

    static void main() {
        System.out.println(
                new MaxProfitFromTradingStockWithDiscounts().maxProfit(2, ArrayUtils.read1d("[1,2]"), ArrayUtils.read1d("[4,3]"), ArrayUtils.read("[[1,2]]"), 3));

        System.out.println(
                new MaxProfitFromTradingStockWithDiscounts().maxProfit(4, ArrayUtils.read1d("[2,41,34,44]"), ArrayUtils.read1d("[48,11,3,49]"), ArrayUtils.read("[[1,4],[4,3],[4,2]]"), 32));


        System.out.println(
                new MaxProfitFromTradingStockWithDiscounts().maxProfit(3, ArrayUtils.read1d("[49,46,48]"), ArrayUtils.read1d("[44,38,38]"), ArrayUtils.read("[[1,3],[3,2]]"), 75));

        System.out.println(
                new MaxProfitFromTradingStockWithDiscounts().maxProfit(3, ArrayUtils.read1d("[42,27,32]"), ArrayUtils.read1d("[46,8,17]"), ArrayUtils.read("[[1,2],[2,3]]"), 93));


        System.out.println(
                new MaxProfitFromTradingStockWithDiscounts().maxProfit(2, ArrayUtils.read1d("[21,44]"), ArrayUtils.read1d("[3,13]"), ArrayUtils.read("[[1,2]]"), 65));


        System.out.println(
                new MaxProfitFromTradingStockWithDiscounts().maxProfit(3, ArrayUtils.read1d("[5,2,3]"), ArrayUtils.read1d("[8,5,6]"), ArrayUtils.read("[[1,2],[2,3]]"), 7));

        System.out.println(
                new MaxProfitFromTradingStockWithDiscounts().maxProfit(3, ArrayUtils.read1d("[29,45,14]"), ArrayUtils.read1d("[1,11,9]"), ArrayUtils.read("[[1,2],[1,3]]"), 136));

        System.out.println(
                new MaxProfitFromTradingStockWithDiscounts().maxProfit(3, ArrayUtils.read1d("[4,6,8]"), ArrayUtils.read1d("[7,9,11]"), ArrayUtils.read("[[1,2],[1,3]]"), 10));


        System.out.println(
                new MaxProfitFromTradingStockWithDiscounts().maxProfit(2, ArrayUtils.read1d("[3,4]"), ArrayUtils.read1d("[5,8]"), ArrayUtils.read("[[1,2]]"), 4));


    }


}
