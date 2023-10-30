import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MaxPointsAfterCollectingAllFromNodes {
    private Map<Integer, Set<Integer>> tree = new HashMap<>();
    private Integer[][] dp;

    public int maximumPoints(int[][] edges, int[] coins, int k) {
        for (int[] e : edges) {
            int v1 = e[0];
            int v2 = e[1];
            tree.computeIfAbsent(v1, p -> new HashSet<>()).add(v2);
            tree.computeIfAbsent(v2, p -> new HashSet<>()).add(v1);
        }
        dp = new Integer[edges.length + 1][16];
        return dfs(0, -1, 0, coins, k);
    }

    private int dfs(int i, int p, int j, int[] coins, int k) {
        if (dp[i][j] != null) {
            return dp[i][j];
        }
        int cur = coins[i];
        if (j < 14) {
            long div = (1 << j);
            cur /= div;
        } else {
            cur = 0;
        }
        int way1 = cur >= k ? cur - k : -Math.abs(cur - k);
        for (int ne : tree.getOrDefault(i, new HashSet<>())) {
            if (ne == p) {
                continue;
            }
            way1 += dfs(ne, i, j, coins, k);
        }
        int way2 = cur / 2;
        for (int ne : tree.getOrDefault(i, new HashSet<>())) {
            if (ne == p) {
                continue;
            }
            way2 += dfs(ne, i, Math.min(j + 1, 15), coins, k);
        }
        int res = Math.max(way1, way2);
        dp[i][j] = res;
        return res;
    }
}
