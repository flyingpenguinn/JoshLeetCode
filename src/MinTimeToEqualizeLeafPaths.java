import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MinTimeToEqualizeLeafPaths {
    // fix any middle node
    private List<Integer>[] t;
    private int res = 0;

    public int minIncrease(int n, int[][] edges, int[] cost) {
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
        dfs(0, -1, cost);
        return res;
    }

    private long dfs(int i, int p, int[] cost) {
        Map<Long, Integer> cm = new HashMap<>();
        long maxlater = 0;
        int ccount = 0;
        for (int ne : t[i]) {
            if (ne == p) {
                continue;
            }
            long later = dfs(ne, i, cost);
            cm.put(later, cm.getOrDefault(later, 0) + 1);
            maxlater = Math.max(maxlater, later);
            ++ccount;
        }
        int other = ccount - cm.getOrDefault(maxlater, 0);
        res += other;
        return maxlater + cost[i];
    }
}
