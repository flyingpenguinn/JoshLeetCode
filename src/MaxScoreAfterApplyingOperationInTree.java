import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MaxScoreAfterApplyingOperationInTree {
    private Map<Integer, Set<Integer>> t;
    private int[] vs;
    private Long[][] dp;

    public long maximumScoreAfterOperations(int[][] edges, int[] values) {
        int n = edges.length + 1;
        dp = new Long[n][2];
        t = new HashMap<>();
        vs = values;
        for (int[] e : edges) {
            int v1 = e[0];
            int v2 = e[1];
            t.computeIfAbsent(v1, k -> new HashSet<>()).add(v2);
            t.computeIfAbsent(v2, k -> new HashSet<>()).add(v1);
        }
        return solve(0, -1, 0);
    }

    private long solve(int i, int p, int j) {
        Set<Integer> nexts = t.getOrDefault(i, new HashSet<>());
        if (nexts.size() == 1 && nexts.iterator().next() == p) {
            if (j == 0) {
                return 0;
            } else {
                return vs[i];
            }
        }
        if (dp[i][j] != null) {
            return dp[i][j];
        }
        long way1 = vs[i];
        for (int ne : nexts) {
            if (ne == p) {
                continue;
            }
            way1 += solve(ne, i, j);
        }
        long way2 = 0;
        for (int ne : nexts) {
            if (ne == p) {
                continue;
            }
            way2 += solve(ne, i, 1);
        }
        long res = Math.max(way1, way2);
        dp[i][j] = res;
        return res;
    }
}
