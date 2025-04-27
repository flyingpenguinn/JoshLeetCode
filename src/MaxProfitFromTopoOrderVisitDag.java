import java.util.*;

public class MaxProfitFromTopoOrderVisitDag {
    // DAG problems usually neeed bitmask dp. 22 indicated this!
    // Dont really need a pos, use st to indicate which pos we are in
    private Map<Integer, Set<Integer>> g = new HashMap<>();
    private Map<Integer, Set<Integer>> dep = new HashMap<>();
    private int Min = (int) -1e9;
    private int[] dp;
    private int[] premask;

    public int maxProfit(int n, int[][] edges, int[] score) {
        for (int[] e : edges) {
            int v1 = e[0];
            int v2 = e[1];
            g.computeIfAbsent(v2, k -> new HashSet<>()).add(v1);
        }
        boolean[] seen = new boolean[n];
        for (int i = 0; i < n; ++i) {
            if (!seen[i]) {
                dfs(i, seen);
            }
        }
        premask = new int[n];
        for (int i = 0; i < n; ++i) {
            Set<Integer> alldep = dep.getOrDefault(i, new HashSet<>());
            for (int cdep : alldep) {
                premask[i] |= (1 << cdep);
            }
        }
        dp = new int[(1 << n)];
        Arrays.fill(dp, 0);
        for (int st = (1 << n) - 1; st >= 0; --st) {
            int pos = Integer.bitCount(st);
            int res = 0;
            for (int j = 0; j < n; ++j) {
                if (((st >> j) & 1) == 1) {
                    continue;
                }
                int prej = premask[j];
                if ((prej & st) == prej) {
                    int nst = st | (1 << j);
                    int later = (pos + 1) * score[j] + dp[nst];
                    res = Math.max(res, later);
                }
            }
            dp[st] = res;
        }
        return dp[0];
    }

    private void dfs(int i, boolean[] seen) {
        seen[i] = true;
        Set<Integer> alli = dep.getOrDefault(i, new HashSet<>());
        for (int ne : g.getOrDefault(i, new HashSet<>())) {
            if (!seen[ne]) {
                dfs(ne, seen);
            }
            Set<Integer> allne = dep.getOrDefault(ne, new HashSet<>());
            alli.addAll(allne);
            alli.add(ne);
        }
        dep.put(i, alli);
    }
}
