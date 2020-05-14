import base.ArrayUtils;

import java.util.*;


/*
lc#1245
Given an undirected tree, return its diameter: the number of edges in a longest path in that tree.

The tree is given as an array of edges where edges[i] = [u, v] is a bidirectional edge between nodes u and v.  Each node has labels in the set {0, 1, ..., edges.length}.



Example 1:



Input: edges = [[0,1],[0,2]]
Output: 2
Explanation:
A longest path of the tree is the path 1 - 0 - 2.
Example 2:



Input: edges = [[0,1],[1,2],[2,3],[1,4],[4,5]]
Output: 4
Explanation:
A longest path of the tree is the path 3 - 2 - 1 - 4 - 5.


Constraints:

0 <= edges.length < 10^4
edges[i][0] != edges[i][1]
0 <= edges[i][j] <= edges.length
The given edges form an undirected tree.
 */
public class TreeDiameter {
    // almost the same as "min height tree"
    // pick a random node to do a bfs, to find one end
    // then do another bfs to find the farthest node from the node found above
    Map<Integer, Set<Integer>> graph = new HashMap<>();

    public int treeDiameter(int[][] edges) {
        buildGraph(edges);
        int[] farthest = bfs(graph.keySet().iterator().next());
        return bfs(farthest[0])[1];
    }

    private int[] bfs(int start) {
        Deque<int[]> q = new ArrayDeque<>();
        int[] st = {start, 0};
        Set<Integer> seen = new HashSet<>();
        q.offerFirst(st);
        seen.add(start);
        int[] farthest = st;
        while (!q.isEmpty()) {
            int[] top = q.poll();
            int node = top[0];
            int dist = top[1];
            if (dist > farthest[1]) {
                farthest = top;
            }
            for (Integer next : graph.getOrDefault(node, new HashSet<>())) {
                if (!seen.contains(next)) {
                    seen.add(next);
                    q.offerFirst(new int[]{next, dist + 1});
                }
            }
        }
        return farthest;
    }

    private void buildGraph(int[][] edges) {
        for (int[] e : edges) {
            graph.computeIfAbsent(e[0], k -> new HashSet<>()).add(e[1]);
            graph.computeIfAbsent(e[1], k -> new HashSet<>()).add(e[0]);
        }
    }

    public static void main(String[] args) {
        System.out.println(new TreeDiameter().treeDiameter(ArrayUtils.read("[[0,1],[0,2]]")));
        System.out.println(new TreeDiameter().treeDiameter(ArrayUtils.read("[[0,1],[1,2],[2,3],[1,4],[4,5]]")));
    }
}
