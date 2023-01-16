import java.util.*;

public class DiffBetweenMaxAndMinPriceSum {
    private Map<Integer, Set<Integer>> tree = new HashMap<>();
    private long[] dp;

    private long res = 0;

    public long maxOutput(int n, int[][] edges, int[] price) {
        dp = new long[n];
        for (int[] e : edges) {
            int v1 = e[0];
            int v2 = e[1];
            tree.computeIfAbsent(v1, k -> new HashSet<>()).add(v2);
            tree.computeIfAbsent(v2, k -> new HashSet<>()).add(v1);
        }
        dfs(0, -1, price);
        dfs2(0, -1, price, 0);
        return res;
    }

    // max path, including myself
    private long dfs(int i, int p, int[] price) {
        long cres = price[i];
        for (int ne : tree.getOrDefault(i, new HashSet<>())) {
            if (p == ne) {
                continue;
            }
            long cne = dfs(ne, i, price);
            cres = Math.max(cres, price[i] + cne);
        }
        dp[i] = cres;
        return cres;
    }

    private void dfs2(int i, int p, int[] price, long maxp) {
        long cur = Math.max(maxp, dp[i] - price[i]);
        res = Math.max(res, cur);
        TreeMap<Long, Long> tm = new TreeMap<>();
        for (int ne : tree.getOrDefault(i, new HashSet<>())) {
            if (p == ne) {
                continue;
            }
            long cne = dp[ne];
            update(tm, cne, 1);
        }
        for (int ne : tree.getOrDefault(i, new HashSet<>())) {
            if (p == ne) {
                continue;
            }
            long cne = dp[ne];
            update(tm, cne, -1);
            long cmax1 = (tm.isEmpty() ? 0 : tm.lastKey()) + price[i];
            long cmax2 = maxp + price[i];
            long cmaxp = Math.max(cmax1, cmax2);
            dfs2(ne, i, price, cmaxp);
            update(tm, cne, 1);
        }
    }

    private void update(TreeMap<Long, Long> tm, long k, long d) {
        long nv = tm.getOrDefault(k, 0L) + d;
        if (nv <= 0) {
            tm.remove(k);
        } else {
            tm.put(k, nv);
        }
    }
}
