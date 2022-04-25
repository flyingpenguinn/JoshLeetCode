import base.ArrayUtils;

import java.util.HashMap;
import java.util.Map;

public class MaxCostOfTripWithKHighways {
    // k edgs => k+1 cities to visit!
    private Map<Integer, Map<Integer, Integer>> g = new HashMap<>();
    private Integer[][] dp;

    public int maximumCost(int n, int[][] highways, int k) {
        dp = new Integer[n][(1 << n)];
        for (int[] h : highways) {
            int start = h[0];
            int end = h[1];
            int len = h[2];
            g.computeIfAbsent(start, key -> new HashMap<>()).put(end, len);
            g.computeIfAbsent(end, key -> new HashMap<>()).put(start, len);
        }
        int res = Integer.MIN_VALUE;
        for (int i = 0; i < n; ++i) {
            int cur = solve(i, (1 << i), k);
            res = Math.max(res, cur);
        }
        return res < 0 ? -1 : res;
    }

    private int solve(int i, int st, int k) {
        if (Integer.bitCount(st) == k + 1) {
            return 0;
        }
        if (dp[i][st] != null) {
            return dp[i][st];
        }
        int res = Integer.MIN_VALUE;
        Map<Integer, Integer> tm = g.getOrDefault(i, new HashMap<>());
        for (int ne : tm.keySet()) {
            int toll = tm.get(ne);
            if (((st >> ne) & 1) == 0) {
                int cur = toll + solve(ne, (st | (1 << ne)), k);
                res = Math.max(res, cur);
            }
        }
        dp[i][st] = res;
        return res;
    }

    public static void main(String[] args) {

        System.out.println(new MaxCostOfTripWithKHighways().maximumCost(15, ArrayUtils.read("[[14,2,8],[7,9,47],[4,1,48],[0,8,86],[7,6,54],[13,14,13],[6,12,57],[5,8,98],[14,3,31],[4,6,93],[13,9,41],[8,3,26],[1,12,65],[3,0,80],[2,5,50],[0,12,3],[3,10,60],[4,8,34],[13,3,94],[0,5,83],[1,2,28],[5,1,60],[6,5,7],[3,12,85],[9,1,26],[2,7,69],[5,13,20],[11,5,84],[9,6,86],[9,4,94],[0,7,31],[4,11,45],[11,6,93],[2,0,88],[6,1,8],[3,9,51],[0,9,75],[4,14,76],[3,4,45],[13,11,26],[3,6,89],[4,5,36],[2,3,35],[2,10,53],[4,12,82],[12,5,42],[9,12,12],[14,0,48],[9,8,88],[11,1,70]]"), 50));
    }
}
