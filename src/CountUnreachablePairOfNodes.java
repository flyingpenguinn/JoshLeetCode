import java.util.Arrays;

public class CountUnreachablePairOfNodes {
    private int[] pa;
    private long[] size;

    public long countPairs(int n, int[][] edges) {
        pa = new int[n];
        size = new long[n];
        for (int i = 0; i < n; ++i) {
            pa[i] = i;
        }
        Arrays.fill(size, 1L);
        for (int[] e : edges) {
            int v1 = e[0];
            int v2 = e[1];
            union(v1, v2);
        }
        long res = 0;
        for (int i = 0; i < n; ++i) {
            if (pa[i] == i) {
                long cur = n - size[i];
                long other = n - cur;
                res += cur * other;
            }
        }
        return res / 2;
    }

    private int find(int i) {
        if (pa[i] == i) {
            return i;
        }
        int rt = find(pa[i]);
        pa[i] = rt;
        return rt;
    }

    private void union(int i, int j) {
        int ai = find(i);
        int aj = find(j);
        if (ai == aj) {
            return;
        }
        if (size[ai] < size[aj]) {
            pa[ai] = aj;
            size[aj] += size[ai];
        } else {
            pa[aj] = ai;
            size[ai] += size[aj];
        }
    }
}
