import base.ArrayUtils;

import java.util.*;

public class CountNumberOfPossibleRootNodes {
    // rerootting.  get answer for 0 first then look for 1
    // if we move from root 0 to root 1, the only thing changed is 0>1 to 1->0
    // so we dfs again then we know the score when we traverse each node down to its child. the score at most moves by 1
    private Map<Integer, Set<Integer>> g = new HashMap<>();
    private Map<Integer, Set<Integer>> tree = new HashMap<>();
    private Map<Integer, Set<Integer>> gm = new HashMap<>();

    private int res = 0;

    public int rootCount(int[][] edges, int[][] guesses, int k) {
        int n = edges.length + 1;
        for (int[] e : edges) {
            int v1 = e[0];
            int v2 = e[1];
            g.computeIfAbsent(v1, p -> new HashSet<>()).add(v2);
            g.computeIfAbsent(v2, p -> new HashSet<>()).add(v1);
        }
        dfsbuildtree(0, -1);
        int score = 0;
        for (int[] g : guesses) {
            int p = g[0];
            int c = g[1];
            gm.computeIfAbsent(p, s -> new HashSet<>()).add(c);
            if (containsValue(tree, p, c)) {
                ++score;
            }
        }
        if (score >= k) {
            ++res;
        }
        for (int ne : tree.getOrDefault(0, new HashSet<>())) {
            dfsreroot(ne, 0, score, k);
        }
        return res;
    }

    private void dfsreroot(int i, int p, int curscore, int k) {
        if (containsValue(gm, p, i)) {
            --curscore;
        }
        if (containsValue(gm, i, p)) {
            ++curscore;
        }
        if (curscore >= k) {
            ++res;
        }
        for (int ne : g.getOrDefault(i, new HashSet<>())) {
            if (ne == p) {
                continue;
            }
            dfsreroot(ne, i, curscore, k);
        }
    }


    private boolean containsValue(Map<Integer, Set<Integer>> m, int k, int v) {
        return m.getOrDefault(k, new HashSet<>()).contains(v);
    }

    private void dfsbuildtree(int i, int p) {
        for (int ne : g.getOrDefault(i, new HashSet<>())) {
            if (ne == p) {
                continue;
            }
            tree.computeIfAbsent(i, k -> new HashSet<>()).add(ne);
            dfsbuildtree(ne, i);
        }
    }


    public static void main(String[] args) {
        System.out.println(new CountNumberOfPossibleRootNodes().rootCount(ArrayUtils.read("[[0,1],[1,2],[1,3],[4,2]]"), ArrayUtils.read("[[1,3],[0,1],[1,0],[2,4]]"), 3));
    }

}
