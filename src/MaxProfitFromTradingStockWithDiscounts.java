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
    private int Min = (int) -1e7;
    private List<Integer>[] t;
    private int[][][][] dp;

    public int maxProfit(int n, int[] present, int[] future, int[][] hierarchy, int budget) {
        t = new ArrayList[n];
        for (int i = 0; i < n; ++i) {
            t[i] = new ArrayList<>();
        }
        for (int[] hi : hierarchy) {
            int u = hi[0] - 1;
            int v = hi[1] - 1;
            t[u].add(v);
        }

        dp = new int[n][budget + 1][2][2];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j <= budget; ++j) {
                for (int k = 0; k <= 1; ++k) {
                    Arrays.fill(dp[i][j][k], Min);
                }
            }
        }

        solve(0, present, future, budget);
        int res = 0;
        for (int i = 0; i <= budget; ++i) {
            res = Math.max(res, dp[0][i][0][0]);
            res = Math.max(res, dp[0][i][0][1]);
        }
        return res;
    }

    private void solve(int i, int[] present, int[] future, int budget) {
        dp[i][0][0][0] = 0;
        dp[i][0][1][0] = 0;
        if (present[i] <= budget) {
            dp[i][present[i]][0][1] = future[i] - present[i];
        }
        if (present[i] / 2 <= budget && i != 0) {
            dp[i][present[i] / 2][1][1] = future[i] - present[i] / 2;
        }

        for (int ne : t[i]) {
            solve(ne, present, future, budget);
            mergeconv(ne, i, present, budget);
        }
    }

    private void mergeconv(int ne, int i, int[] present, int budget) {
        int[][][] ndp = new int[budget + 1][2][2];
        for (int j = 0; j <= budget; ++j) {
            for (int k = 0; k <= 1; ++k) {
                Arrays.fill(ndp[j][k], Min);
            }
        }

        for (int cur = 0; cur <= budget; ++cur) {
            for (int sub = 0; sub + cur <= budget; ++sub) {
                for (int parentbuy = 0; parentbuy <= 1; ++parentbuy) {
                    for (int mebuy = 0; mebuy <= 1; ++mebuy) {

                        int newbudget = cur + sub;
                        int bestchild = Math.max(dp[ne][sub][mebuy][0], dp[ne][sub][mebuy][1]);
                        int bestcur = dp[i][cur][parentbuy][mebuy] + bestchild;
                        ndp[newbudget][parentbuy][mebuy] = Math.max(ndp[newbudget][parentbuy][mebuy], bestcur);

                    }
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
