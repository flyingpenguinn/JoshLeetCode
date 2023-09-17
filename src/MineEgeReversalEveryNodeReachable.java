import base.ArrayUtils;

import java.util.List;
import java.util.*;

public class MineEgeReversalEveryNodeReachable {


    int dfs(Map<Integer, Map<Integer, Integer>> g,
            int[][] dists, boolean visit[], int cur) {
        visit[cur] = true;
        int allReverseEdges = 0;
        final Map<Integer, Integer> nm = g.getOrDefault(cur, new HashMap<>());
        for (int v : nm.keySet()) {
            if (!visit[v]) {
                dists[v][0] = dists[cur][0] + 1;
                dists[v][1] = dists[cur][1];
                if (nm.get(v) == 1) {
                    dists[v][1] = dists[cur][1] + 1;
                    allReverseEdges++;
                }
                allReverseEdges += dfs(g, dists, visit, v);
            }
        }

        return allReverseEdges;
    }


    public int[] minEdgeReversals(int n, int[][] edges) {
        int e = edges.length;

        Map<Integer, Map<Integer, Integer>> g = new HashMap<>();
        // 0: dist
        // 1: backedges
        int[][] dists = new int[n][2];
        boolean visit[] = new boolean[n];
        for (int i = 0; i < e; i++) {
            int v1 = edges[i][0];
            int v2 = edges[i][1];
            g.computeIfAbsent(v1, k -> new HashMap<>()).put(v2, 0);
            g.computeIfAbsent(v2, k -> new HashMap<>()).put(v1, 1);
        }
        int allReverse = dfs(g, dists, visit, 0);

        int[] res = new int[n];

        for (int i = 0; i < n; i++) {
            // the first half is actually all backedges when we traverse from thsi node i from 0. this is the same as all reverse - reverse on this node
            // the second part covers path from 0 to i
            int cur = (allReverse - dists[i][1]) + (dists[i][0] - dists[i][1]);
            res[i] = cur;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new MineEgeReversalEveryNodeReachable().minEdgeReversals(4, ArrayUtils.read("[[2,0],[2,1],[1,3]]")));
    }

}
