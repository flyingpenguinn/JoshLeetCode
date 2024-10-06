import java.util.*;

public class RemoveMethodsFromProject {
    private Map<Integer, Set<Integer>> g = new HashMap<>();
    private int[] st;
    private boolean changed = false;

    public List<Integer> remainingMethods(int n, int k, int[][] es) {
        st = new int[n];
        for (int[] e : es) {
            int v1 = e[0];
            int v2 = e[1];
            g.computeIfAbsent(v1, p -> new HashSet<>()).add(v2);
        }
        dfs1(k);
        for (int i = 0; i < n; ++i) {
            if (i == k || st[i] != 0) {
                continue;
            }
            dfs2(i);
        }
        List<Integer> res = new ArrayList<>();
        if (changed) {
            for (int i = 0; i < n; ++i) {
                res.add(i);
            }
        } else {
            for (int i = 0; i < n; ++i) {
                if (st[i] == 3 || st[i] == 4) {
                    res.add(i);
                }
            }
        }
        return res;

    }

    private void dfs1(int i) {
        // System.out.println("dfs1 "+i);
        st[i] = 1;
        for (int ne : g.getOrDefault(i, new HashSet<>())) {
            if (st[ne] == 1) {
                continue;
            }
            if (st[ne] == 2) {
                continue;
            }
            dfs1(ne);
        }
        st[i] = 2;
    }

    private void dfs2(int i) {
        //System.out.println("dfs2 "+i);
        if (st[i] == 1 || st[i] == 2) {
            changed = true;
        }
        st[i] = 3;
        for (int ne : g.getOrDefault(i, new HashSet<>())) {
            if (st[ne] == 3) {
                continue;
            }
            if (st[ne] == 4) {
                continue;
            }

            dfs2(ne);
        }
        st[i] = 4;
    }
}
