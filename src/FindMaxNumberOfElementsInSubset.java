import java.util.*;

public class FindMaxNumberOfElementsInSubset {
    private void update(Map<Long, Integer> m, long k) {
        int nv = m.getOrDefault(k, 0) + 1;
        m.put(k, nv);
    }

    private Set<Long> seen = new HashSet<>();
    private Map<Long, Long> g = new HashMap<>();
    private Map<Long, Integer> m = new HashMap<>();

    public int maximumLength(int[] ia) {
        int n = ia.length;
        long[] a = new long[n];
        for (int i = 0; i < n; ++i) {
            a[i] = ia[i];
        }
        Arrays.sort(a);
        for (int i = 0; i < n; ++i) {
            update(m, a[i]);
        }
        // System.out.println(m);
        for (int i = 0; i < n; ++i) {
            long sq = 1L * a[i] * a[i];
            if (m.containsKey(sq)) {
                g.put(a[i], sq);
            }
        }
        int res = 1;
        for (long k : g.keySet()) {
            if (k == 1) {
                int cur = m.get(k);
                if (cur % 2 == 0) {
                    --cur;
                }
                res = Math.max(res, cur);
                continue;
            }
            if (!seen.contains(k)) {
                int cur = dfs(k);
                res = Math.max(res, cur);
            }
        }
        return res;
    }

    private int dfs(long k) {
        seen.add(k);
        if (m.get(k) == 1) {
            return 1;
        }
        if (!g.containsKey(k)) {
            return 1;
        }
        int later = dfs(g.get(k));
        return later + 2;
    }
}
