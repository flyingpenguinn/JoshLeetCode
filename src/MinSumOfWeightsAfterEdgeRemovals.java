import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MinSumOfWeightsAfterEdgeRemovals {
    private Map<Integer, Integer>[] t;

    public long maximizeSumOfWeights(int[][] edges, int k) {
        int en = edges.length;
        int n = en + 1;
        t = new HashMap[n];
        for (int i = 0; i < n; ++i) {
            t[i] = new HashMap<>();
        }
        for (int[] e : edges) {
            int v1 = e[0];
            int v2 = e[1];
            int v3 = e[2];
            t[v1].put(v2, v3);
            t[v2].put(v1, v3);
        }
        dp = new long[n][2];
        for (int i = 0; i < n; ++i) {
            Arrays.fill(dp[i], -1);
        }
        return dfs(t, 0, 1, -1, k);
    }

    private long[][] dp;

    private long dfs(Map<Integer, Integer>[] t, int i, int pecut, int pa, int k) {
        int n = t.length;
        if (dp[i][pecut] != -1) {
            return dp[i][pecut];
        }
        List<Long> npq = new ArrayList<>();
        long benefit = 0;
        long res = 0;
        int cut = t[i].size() - k;
        if (pecut == 1 && i != 0) {
            --cut;
        }
        cut = Math.max(0, cut);
        for (int ne : t[i].keySet()) {
            if (pa == ne) {
                continue;
            }
            int w = t[i].get(ne);
            long way1 = w + dfs(t, ne, 0, i, k);
            long way2 = dfs(t, ne, 1, i, k);
            long diff = way2 - way1;
            npq.add(diff);
            res += way1;
        }
        Collections.sort(npq, Collections.reverseOrder());
        for (int j = 0; j < cut; ++j) {
            res += npq.get(j);
        }
        for (int j = cut; j < npq.size() && npq.get(j) > 0; ++j) {
            res += npq.get(j);
        }
        dp[i][pecut] = res;
        return res;
    }
}
