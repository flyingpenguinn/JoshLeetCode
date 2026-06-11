import java.util.*;

public class DiffBetweenMaxAndMinPriceSum {
    // reroot with max 1,2 / min 1,2 paths
    private List<Integer>[] t;
    private long[] min1;
    private long[] max1;
    private long[] min2;
    private long[] max2;
    private long[] p;
    private long res;
    private long Max = (int) 1e9;
    private long Min = (int) -1e9;

    public long maxOutput(int n, int[][] edges, int[] price) {
        t = new ArrayList[n];
        min1 = new long[n];
        Arrays.fill(min1, Max);
        max1 = new long[n];
        Arrays.fill(max1, Min);
        min2 = new long[n];
        Arrays.fill(min2, Max);
        max2 = new long[n];
        Arrays.fill(max2, Min);
        p = new long[n];
        for (int i = 0; i < n; ++i) {
            p[i] = price[i];
        }
        for (int i = 0; i < n; ++i) {
            t[i] = new ArrayList<>();
        }
        for (int[] e : edges) {
            int v1 = e[0];
            int v2 = e[1];
            t[v1].add(v2);
            t[v2].add(v1);
        }
        dfs1(0, -1);

        dfs2(0, -1, Min, Max);
        return res;
    }

    private void dfs2(int i, int pa, long uppermax, long uppermin) {
        long curmax = Math.max(max1[i], p[i] + uppermax);
        long curmin = Math.min(min1[i], p[i] + uppermin);
        long cres = curmax - curmin;
        res = Math.max(res, cres);
        for (int ne : t[i]) {
            if (ne == pa) {
                continue;
            }
            long othermax = max1[i] - p[i];
            long newuppermax = Math.max(uppermax + p[i], p[i]);
            if (max1[ne] == othermax) {
                newuppermax = Math.max(newuppermax, max2[i]);
            } else {
                newuppermax = Math.max(newuppermax, max1[i]);
            }

            long othermin = min1[i] - p[i];
            long newuppermin = Math.min(p[i], uppermin + p[i]);
            if (min1[ne] == othermin) {
                newuppermin = Math.min(newuppermin, min2[i]);
            } else {
                newuppermin = Math.min(newuppermin, min1[i]);
            }
            dfs2(ne, i, newuppermax, newuppermin);
        }
    }

    private void dfs1(int i, int pa) {
        max1[i] = p[i];
        min1[i] = p[i];
        for (int ne : t[i]) {
            if (ne == pa) {
                continue;
            }
            dfs1(ne, i);
            long nemax = max1[ne];
            long nemin = min1[ne];
            long cnemax = nemax + p[i];
            long cnemin = nemin + p[i];
            if (cnemax > max1[i]) {
                max2[i] = max1[i];
                max1[i] = cnemax;
            } else if (cnemax > max2[i]) {
                max2[i] = cnemax;
            }

            if (cnemin < min1[i]) {
                min2[i] = min1[i];
                min1[i] = cnemin;
            } else if (cnemin < min2[i]) {
                min2[i] = cnemin;
            }
        }
    }
}
