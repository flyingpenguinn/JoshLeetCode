import base.ArrayUtils;

import java.util.ArrayList;
import java.util.List;

public class NumberOfWaysToAssignEdgeWeightsII {
    private List<Integer>[] t;

    public int maximumPoints(int[][] edges, int[] coins, int k) {
        int en = edges.length;
        int n = en + 1;
        t = new ArrayList[n];
        for (int i = 0; i < n; ++i) {
            t[i] = new ArrayList<>();
        }
        for (int[] e : edges) {
            int v1 = e[0];
            int v2 = e[1];
            t[v1].add(v2);
            t[v2].add(v1);
        }
        dp = new Integer[n][15];
        return dfs(0, -1, 0, coins, k);
    }

    private Integer[][] dp;
    private int BITS = 14;

    // used j times of way2
    private int dfs(int i, int parent, int j, int[] coins, int k) {
        if (dp[i][j] != null) {
            return dp[i][j];
        }
        int cv = coins[i] >> j;
        int way1 = cv - k;
        for (int ne : t[i]) {
            if (ne == parent) {
                continue;
            }
            int later = dfs(ne, i, j, coins, k);
            way1 += later;
        }
        int way2 = cv / 2;
        for (int ne : t[i]) {
            if (ne == parent) {
                continue;
            }
            int later = dfs(ne, i, Math.min(BITS, j + 1), coins, k);
            way2 += later;
        }
        int res = Math.max(way1, way2);
        dp[i][j] = res;
        return res;
    }

    static void main() {
        System.out.println(new NumberOfWaysToAssignEdgeWeightsII().maximumPoints(ArrayUtils.read("[[1,0],[2,1],[3,1]]"), ArrayUtils.read1d("[8,2,7,1]"), 2));
        System.out.println(new NumberOfWaysToAssignEdgeWeightsII().maximumPoints(ArrayUtils.read("[[0,1],[1,2],[2,3]]"), ArrayUtils.read1d("[10,10,3,3]"), 5));
    }
}
