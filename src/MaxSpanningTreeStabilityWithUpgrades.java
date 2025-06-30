import java.util.Arrays;

public class MaxSpanningTreeStabilityWithUpgrades {
    private int[] pa;

    public int maxStability(int n, int[][] edges, int k) {
        pa = new int[n];
        Arrays.fill(pa, -1);
        for (int[] e : edges) {
            int v1 = e[0];
            int v2 = e[1];
            int av1 = find(v1);
            int av2 = find(v2);
            int must = e[3];
            if (must == 0) {
                continue;
            }
            if (av1 == av2) {
                return -1;
            }
            union(v1, v2);
        }

        Arrays.sort(edges, (x, y) -> Integer.compare(y[2], x[2]));
        int u = edges[0][2] * 2;
        int l = 0;
        while (l <= u) {
            int mid = l + (u - l) / 2;
            if (possible(n, mid, edges, k)) {
                l = mid + 1;
            } else {
                u = mid - 1;
            }
        }
        return u;
    }

    private int find(int i) {
        if (pa[i] == -1) {
            return i;
        }
        int rt = find(pa[i]);
        pa[i] = rt;
        return rt;
    }

    private boolean union(int u, int v) {
        int au = find(u);
        int av = find(v);
        if (au == av) {
            return false;
        }
        pa[au] = av;
        return true;
    }

    // all edges must >=t
    private boolean possible(int n, int t, int[][] edges, int k) {
        pa = new int[n];
        Arrays.fill(pa, -1);
        for (int[] e : edges) {
            int v1 = e[0];
            int v2 = e[1];
            int s = e[2];
            int must = e[3];
            if (must == 1) {
                if (s < t) {
                    return false;
                } else {
                    union(v1, v2);
                }
            }
        }
        for (int[] e : edges) {
            int v1 = e[0];
            int v2 = e[1];
            int av1 = find(v1);
            int av2 = find(v2);
            int s = e[2];
            int must = e[3];
            if (must == 1) {
                continue;
            }
            if (av1 == av2) {
                continue;
            }
            if (s * 2 < t) {
                continue;
            }
            if (s < t) {
                if (k > 0) {
                    --k;
                } else {
                    continue;
                }
            }
            union(v1, v2);
        }


        int roots = 0;
        for (int i = 0; i < n; ++i) {
            if (pa[i] == -1) {
                ++roots;
                if (roots > 1) {
                    return false;
                }
            }
        }
        return true;
    }
}
