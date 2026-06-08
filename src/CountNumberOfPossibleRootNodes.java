import base.ArrayUtils;

import java.util.*;

public class CountNumberOfPossibleRootNodes {
    // re-rooting / rerooting.  get answer for 0 first then look for 1
    // if we move from root 0 to root 1, the only thing changed is 0>1 to 1->0
    // so we dfs again then we know the score when we traverse each node down to its child. the score at most moves by 1
    private List<Integer>[] t;
    private Set<Integer>[] gm;
    private int[] raw;
    int res = 0;

    public int rootCount(int[][] edges, int[][] guesses, int k) {
        int en = edges.length;
        int n = en + 1;
        raw = new int[n];
        t = new ArrayList[n];
        gm = new HashSet[n];
        for (int i = 0; i < n; ++i) {
            t[i] = new ArrayList<>();
            gm[i] = new HashSet<>();
        }
        for (int[] e : edges) {
            int v1 = e[0];
            int v2 = e[1];
            t[v1].add(v2);
            t[v2].add(v1);
        }
        for (int[] e : guesses) {
            int v1 = e[0];
            int v2 = e[1];
            gm[v1].add(v2);

        }
        dfs1(0, -1);
        dfs2(0, -1, 0, k);
        return res;
    }

    private void dfs2(int i, int p, int upper, int k) {
        if (raw[i] + upper >= k) {
            ++res;
        }
        for (int ne : t[i]) {
            if (ne == p) {
                continue;
            }
            int other = raw[i] - raw[ne];
            if (gm[i].contains(ne)) {
                --other;
            }
            int newupper = upper + other;

            if (gm[ne].contains(i)) {
                newupper += 1;
            }
            dfs2(ne, i, newupper, k);
        }
    }

    private int dfs1(int i, int p) {
        int res = 0;
        for (int ne : t[i]) {
            if (ne == p) {
                continue;
            }
            res += dfs1(ne, i);
            if (gm[i].contains(ne)) {
                ++res;
            }
        }
        raw[i] = res;
        return res;
    }


    public static void main(String[] args) {
        System.out.println(new CountNumberOfPossibleRootNodes().rootCount(ArrayUtils.read("[[0,1],[1,2],[1,3],[4,2]]"), ArrayUtils.read("[[1,3],[0,1],[1,0],[2,4]]"), 3));
    }

}
