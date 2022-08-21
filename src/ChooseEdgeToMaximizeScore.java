import java.util.*;


public class ChooseEdgeToMaximizeScore {
    private Map<Integer, Map<Integer, Integer>> tree = new HashMap<>();
    private Long[][] dp;

    public long maxScore(int[][] edges) {
        int n = edges.length;
        dp = new Long[n][2];
        for (int i = 0; i < n; ++i) {
            int s = edges[i][0];
            if (s == -1) {
                continue;
            }
            int e = i;
            int w = edges[i][1];
            tree.computeIfAbsent(s, k -> new HashMap<>()).put(e, w);
        }
        return solve(0, 1);
    }

    private long solve(int i, int allow) {
        if (dp[i][allow] != null) {
            return dp[i][allow];
        }
        Set<Integer> nexts = tree.getOrDefault(i, new HashMap<>()).keySet();
        long allone = 0;
        for (int ne : nexts) {
            allone += solve(ne, 1);
        }
        long res = allone;
        if (allow == 1) {
            for (int ne : nexts) {
                long len = tree.get(i).get(ne);
                long cur = len + allone - solve(ne, 1) + solve(ne, 0);
                res = Math.max(res, cur);
            }
        }
        dp[i][allow] = res;
        return res;
    }
}
