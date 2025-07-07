import java.util.*;

public class PowerGridMaintainence {
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
        if (ai < aj) {
            pa[aj] = ai;
        } else {
            pa[ai] = aj;
        }
    }

    public int[] processQueries(int n, int[][] connections, int[][] queries) {
        pa = new int[n + 1];
        Arrays.fill(pa, -1);
        for (int[] c : connections) {
            int x = c[0];
            int y = c[1];
            union(x, y);
        }
        Map<Integer, TreeSet<Integer>> tm = new HashMap<>();
        for (int i = 1; i <= n; ++i) {
            int ai = find(i);
            tm.computeIfAbsent(ai, p -> new TreeSet<>()).add(i);
        }
        Set<Integer> off = new HashSet<>();
        int qn = queries.length;
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < qn; ++i) {
            int type = queries[i][0];
            int q = queries[i][1];
            if (type == 1) {
                if (!off.contains(q)) {
                    res.add(q);
                } else {
                    int aq = find(q);
                    TreeSet<Integer> cset = tm.get(aq);
                    if (cset.isEmpty()) {
                        res.add(-1);
                    } else {
                        res.add(cset.first());
                    }
                }
            } else if (type == 2) {
                off.add(q);
                int aq = find(q);
                tm.computeIfAbsent(aq, p -> new TreeSet<>()).remove(q);
            }
        }
        int[] rres = new int[res.size()];
        for (int i = 0; i < res.size(); ++i) {
            rres[i] = res.get(i);
        }
        return rres;
    }
}
