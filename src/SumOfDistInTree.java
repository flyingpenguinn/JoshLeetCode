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
    // we try to calculate this node's value based on parent's
    Map<Integer, Set<Integer>> tree = new HashMap<>();
    // we wnat the subtree size, not the immediate children size, so can't use tree.get().size()
    Map<Integer, Integer> subsize = new HashMap<>();
    int[] r;

    public int[] sumOfDistancesInTree(int n, int[][] es) {
        for (int[] e : es) {
            tree.computeIfAbsent(e[0], k -> new HashSet<>()).add(e[1]);
            tree.computeIfAbsent(e[1], k -> new HashSet<>()).add(e[0]);
        }
        int[] dfsres = dfs(0, -1);
        r = new int[n];
        dfsdist(0, dfsres[0] + dfsres[1], dfsres[1], -1);
        return r;
    }

    // all dists, node counts
    // note when we traverse non cyclic undirected tree we should remember parent not to step on it again
    int[] dfs(int i, int p) {
        int[] res = new int[]{0, 0};
        for (int next : tree.getOrDefault(i, new HashSet<>())) {
            if (next == p) {
                continue;
            }
            int[] cur = dfs(next, i);
            res[0] += cur[1] + cur[0];
            res[1] += cur[1];
        }
        res[1] += 1;
        subsize.put(i, res[1]);
        return res;
    }

    void dfsdist(int i, int dist, int allnodes, int p) {
        int size = subsize.get(i);
        r[i] = dist + allnodes - 2 * size;
        for (int next : tree.getOrDefault(i, new HashSet<>())) {
            if (next == p) {
                continue;
            }
            dfsdist(next, r[i], allnodes, i);
        }
    }

    public static void main(String[] args) {
        //      int[][] edges = ArrayUtils.read("[[0,1],[0,2],[2,3],[2,4],[2,5],[3,6],[3,7],[4,8],[5,9]]");
        int[][] edges = ArrayUtils.read("[[0,1],[0,2],[2,3],[2,4],[2,5],[3,6],[3,7],[4,8],[5,9]]");
        System.out.println(Arrays.toString(new SumOfDistInTree().sumOfDistancesInTree(10, edges)));
    }
}
