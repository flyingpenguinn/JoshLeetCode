import base.ArrayUtils;

import java.util.Arrays;

public class MinCostCuttingStick {
    // use l and u to indicate the segment. setup the two ends as segment points too
    // note we know we cant cut on l and u themselves: we can't cut on 0 and n
    int[][] dp;

    public int minCost(int n, int[] cuts) {
        int[] ncuts = new int[cuts.length + 2];
        ncuts[0] = 0;
        ncuts[1] = n;
        for (int i = 2; i < ncuts.length; i++) {
            ncuts[i] = cuts[i - 2];
        }
        Arrays.sort(ncuts);
        dp = new int[ncuts.length][ncuts.length];
        for (int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], -1);
        }
        return dom(ncuts, 0, ncuts.length - 1);
    }

    private int dom(int[] cuts, int l, int u) {
        if (l == u - 1) {
            return 0;
        }
        if (dp[l][u] != -1) {
            return dp[l][u];
        }
        int min = Integer.MAX_VALUE;
        for (int i = l + 1; i < u; i++) {
            int lcost = dom(cuts, l, i);
            int rcost = dom(cuts, i, u);
            int cur = cuts[u] - cuts[l] + lcost + rcost;
            min = Math.min(min, cur);
        }
        dp[l][u] = min;
        return min;
    }


    public static void main(String[] args) {
        System.out.println(new MinCostCuttingStick().minCost(7, ArrayUtils.read1d("1,3,4,5")));
    }
}
