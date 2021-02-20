import java.util.*;

public class TreeOfCoPrimes {
    // number range is small. we go through all the ancestor numbers
    private static Set<Integer>[] cop;

    public int[] getCoprimes(int[] a, int[][] es) {
        int n = a.length;
        if (cop == null) {
            cop = new HashSet[51];
            for (int i = 1; i <= 50; i++) {
                cop[i] = new HashSet<>();
            }
            for (int i = 1; i <= 50; i++) {
                for (int j = 1; j <= 50; j++) {
                    if (gcd(i, j) == 1) {
                        cop[i].add(j);
                    }
                }
            }
        }
        Set<Integer>[] tree = new HashSet[n];
        for (int i = 0; i < n; i++) {
            tree[i] = new HashSet<>();
        }
        for (int[] e : es) {
            int v1 = e[0];
            int v2 = e[1];
            tree[v1].add(v2);
            tree[v2].add(v1);
        }
        int[] res = new int[n];
        Arrays.fill(res, -1);
        dfs(tree, 0, 0, new HashMap<>(), cop, res, a, -1);
        return res;
    }

    private int gcd(int i, int j) {
        if (i < j) {
            return gcd(j, i);
        }
        return j == 0 ? i : gcd(j, i % j);
    }

    private void dfs(Set<Integer>[] tree, int i, int depth, Map<Integer, int[]> anc, Set<Integer>[] cop, int[] res, int[] a, int pa) {
        int maxd = -1;
        int maxnum = -1;
        // among all the ancestor numbers, find the max depth and corresponding value
        for (int k : anc.keySet()) {
            if (cop[a[i]].contains(k)) {
                int cd = anc.get(k)[0];
                if (cd > maxd) {
                    maxd = cd;
                    maxnum = k;
                }
            }
        }
        if (maxnum != -1) {
            res[i] = anc.get(maxnum)[1];
        }
        int[] od = anc.get(a[i]);
        anc.put(a[i], new int[]{depth, i});
        for (int ne : tree[i]) {
            if (ne == pa) {
                continue;
            }
            dfs(tree, ne, depth + 1, anc, cop, res, a, i);
        }
        if (od == null) {
            anc.remove(a[i]);
        } else {
            anc.put(a[i], od);
        }
    }
}
