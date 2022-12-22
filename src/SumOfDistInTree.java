import base.ArrayUtils;

import java.util.*;

/*
LC#834
An undirected, connected tree with N nodes labelled 0...N-1 and N-1 edges are given.

The ith edge connects nodes edges[i][0] and edges[i][1] together.

Return a list ans, where ans[i] is the sum of the distances between node i and all other nodes.

Example 1:

Input: N = 6, edges = [[0,1],[0,2],[2,3],[2,4],[2,5]]
Output: [8,12,6,10,10,10]
Explanation:
Here is a diagram of the given tree:
  0
 / \
1   2
   /|\
  3 4 5
We can see that dist(0,1) + dist(0,2) + dist(0,3) + dist(0,4) + dist(0,5)
equals 1 + 1 + 2 + 2 + 2 = 8.  Hence, answer[0] = 8, and so on.
Note: 1 <= N <= 10000
 */
public class SumOfDistInTree {
    // count nodes based on results of the parent
    private Map<Integer, Set<Integer>> g = new HashMap<>();
    int[] counts;
    int[] dists;

    public int[] sumOfDistancesInTree(int n, int[][] edges) {
        counts = new int[n];
        dists = new int[n];
        for (int[] e : edges) {
            g.computeIfAbsent(e[0], k -> new HashSet<>()).add(e[1]);
            g.computeIfAbsent(e[1], k -> new HashSet<>()).add(e[0]);
        }
        dfs(0, -1);
        dfsres(0, -1);
        return dists;
    }

    void dfs(int i, int pa) {
        // get the raw dist purely based on subtree results
        int curdist = 0;
        int curcount = 1;
        for (int ne : g.getOrDefault(i, new HashSet<>())) {
            if (ne == pa) {
                continue;
            }
            dfs(ne, i);
            curdist += counts[ne] + dists[ne];
            curcount += counts[ne];
        }
        dists[i] = curdist;
        counts[i] = curcount;
    }

    void dfsres(int i, int pa) {
        int n = counts.length;
        for (int ne : g.getOrDefault(i, new HashSet<>())) {
            if (ne == pa) {
                continue;
            }
            // for each sub node ne, it's the parent dist
            // - nodes in its own subtree, as all of them gets -1
            // + all nodes above itself, because each of them gets +1
            dists[ne] = dists[i] - counts[ne] + (n - counts[ne]);
            dfsres(ne, i);
        }
    }
}
