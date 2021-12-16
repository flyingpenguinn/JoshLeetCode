import java.util.HashSet;
import java.util.Set;

public class DenoteMaxBombs {
    private Set<Integer>[] g;

    public int maximumDetonation(int[][] a) {
        int n = a.length;
        g = new HashSet[n];
        for (int i = 0; i < n; ++i) {
            g[i] = new HashSet<>();
        }
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (i == j) {
                    continue;
                }
                if (within(a[i], a[j])) {
                    g[i].add(j);
                }
            }
        }
        int res = 0;
        for (int i = 0; i < n; ++i) {
            boolean[] seen = new boolean[n];
            int nodes = dfs(i, seen);
            res = Math.max(res, nodes);
        }
        return res;
    }

    private boolean within(int[] v1, int[] v2) {
        long sqd = dist(v1[0], v1[1], v2[0], v2[1]);
        long sqr = 1L * v1[2] * v1[2];
        return sqd <= sqr;
    }

    private long dist(long a, long b, long c, long d) {
        long d1 = a - c;
        long d2 = b - d;
        return d1 * d1 + d2 * d2;
    }

    private int dfs(int i, boolean[] seen) {
        seen[i] = true;
        int res = 1;
        for (int ne : g[i]) {
            if (seen[ne]) {
                continue;
            }
            res += dfs(ne, seen);
        }
        return res;
    }
}
