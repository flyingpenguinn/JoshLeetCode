import base.ArrayUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MaxProfitFromTradingStockWithDiscounts {

     /*
   subtree convolution template
    Tree Knapsack / Subtree Convolution:
    for each node there are resources B.
    Resource dimensions may be multidimensional; additional DP state dimensions are carried through the merge via for loops on them
ndp[b1 + b2] = Math.max(ndp[b1 + b2], cdp[b1] + child[b2]);
Time: O(B^2) per child, O(n * B^2) overall.

   private void dfs(int i) {
   // 1. Initialize local decisions
   int[] cdp = init(i);

   // 2. Merge children one by one
   for (int ne : t[i]) {
       dfs(ne);
       cdp = merge(cdp, dp[ne]);
   }

   // 3. assign back if all states are in cdp
   dp[i] = cdp
   }

   merge()
    ndp = new
    for(int b1=0; b1<=B; b1++)
      for(int b2=0; b1+b2<=B; b2++)
        skip invalid cdp and child
        ndp[b1+b2] = max(ndp[b1+b2], cdp[b1] + child[b2])
        // max/min/count depending on DP type
    return ndp so as to be the new cdp in next child

    */

    private List<Integer>[] t;
    private int[][][][] dp;
    private static final int NEG = -1000000000;

    public int maxProfit(int n, int[] present, int[] future, int[][] hierarchy, int budget) {
        t = new ArrayList[n];
        // n, p buy, cbuy, budget
        dp = new int[n][2][2][budget + 1];

        for (int i = 0; i < n; ++i) {
            t[i] = new ArrayList<>();
        }
        for (int[] h : hierarchy) {
            int v1 = h[0] - 1;
            int v2 = h[1] - 1;
            t[v1].add(v2);
        }

        solve(0, budget, present, future);
        int res = NEG;
        for (int i = 0; i <= budget; ++i) {
            int cres = Math.max(dp[0][0][0][i], dp[0][0][1][i]);
            res = Math.max(cres, res);
        }
        return res;
    }


    private void solve(int i, int bg, int[] present, int[] future) {
        int[][][] cur = new int[2][2][bg + 1];

        for (int pb = 0; pb <= 1; ++pb) {
            for (int buy = 0; buy <= 1; ++buy) {
                Arrays.fill(cur[pb][buy], NEG);
            }

            // i does not buy
            cur[pb][0][0] = 0;

            // i buys
            int cost = pb == 1 ? present[i] / 2 : present[i];
            if (cost <= bg) {
                cur[pb][1][cost] = future[i] - cost;
            }
        }

        for (int ne : t[i]) {
            solve(ne, bg, present, future);
            cur = mergesub(cur, ne, bg);
        }
        dp[i] = cur;
    }

    private int[][][] mergesub(int[][][] cur, int ne, int bg) {
        int[][][] ndp = new int[2][2][bg + 1];

        for (int pb = 0; pb <= 1; ++pb) {
            for (int buy = 0; buy <= 1; ++buy) {
                Arrays.fill(ndp[pb][buy], NEG);

                for (int current = 0; current <= bg; ++current) {
                    if (cur[pb][buy][current] == NEG) {
                        continue;
                    }

                    for (int bne = 0; current + bne <= bg; ++bne) {
                        int child = Math.max(dp[ne][buy][0][bne], dp[ne][buy][1][bne]);
                        if (child == NEG) {
                            continue;
                        }

                        ndp[pb][buy][current + bne] = Math.max(
                                ndp[pb][buy][current + bne],
                                cur[pb][buy][current] + child
                        );
                    }
                }
            }
        }
        return ndp;
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
