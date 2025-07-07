import java.util.Arrays;

public class MinTimeForKConnectedComponents {
    public int minTime(int n, int[][] edges, int k) {
        int l = 0;
        int u = (int) 2e9;
        while (l <= u) {
            int mid = l + (u - l) / 2;
            if (components(n, edges, mid) >= k) {
                u = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }

    private int[] pa;

    private int find(int i) {
        if (pa[i] == -1) {
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
        pa[ai] = aj;
    }

    private int components(int n, int[][] edges, int f) {
        pa = new int[n];
        Arrays.fill(pa, -1);
        for (int[] e : edges) {
            int u = e[0];
            int v = e[1];
            int w = e[2];
            if (w <= f) {
                continue;
            }
            union(u, v);
        }
        int comps = 0;
        for (int i = 0; i < n; ++i) {
            if (pa[i] == -1) {
                ++comps;
            }
        }
        return comps;
    }
}
