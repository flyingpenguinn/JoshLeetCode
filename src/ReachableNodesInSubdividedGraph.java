import base.ArrayUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/*
LC#882
Starting with an undirected graph (the "original graph") with nodes from 0 to N-1, subdivisions are made to some of the edges.

The graph is given as follows: edges[k] is a list of integer pairs (i, j, n) such that (i, j) is an edge of the original graph,

and n is the total number of new nodes on that edge.

Then, the edge (i, j) is deleted from the original graph, n new nodes (x_1, x_2, ..., x_n) are added to the original graph,

and n+1 new edges (i, x_1), (x_1, x_2), (x_2, x_3), ..., (x_{n-1}, x_n), (x_n, j) are added to the original graph.

Now, you start at node 0 from the original graph, and in each move, you travel along one edge.

Return how many nodes you can reach in at most M moves.



Example 1:

Input: edges = [[0,1,10],[0,2,1],[1,2,2]], M = 6, N = 3
Output: 13
Explanation:
The nodes that are reachable in the final graph after M = 6 moves are indicated below.

Example 2:

Input: edges = [[0,1,4],[1,2,6],[0,2,8],[1,3,1]], M = 10, N = 4
Output: 23


Note:

0 <= edges.length <= 10000
0 <= edges[i][0] < edges[i][1] < N
There does not exist any i != j for which edges[i][0] == edges[j][0] and edges[i][1] == edges[j][1].
The original graph has no parallel edges.
0 <= edges[i][2] <= 10000
0 <= M <= 10^9
1 <= N <= 3000
A reachable node is a node that can be travelled to using at most M moves starting from node 0.
 */
public class ReachableNodesInSubdividedGraph {
    // dijkastra with a twist of calculating travelled dist: we will still need to visit nodes we touched before because we need to pick up
    // middle ones
    private int Max = 10000000;

    public int reachableNodes(int[][] es, int m, int n) {
        Map<Integer, Integer>[] g = new HashMap[n];
        for (int i = 0; i < n; i++) {
            g[i] = new HashMap<>();
        }
        for (int[] e : es) {
            int start = e[0];
            int end = e[1];
            int nodes = e[2];
            g[start].put(end, nodes);
            g[end].put(start, nodes);
        }
        int[] dist = new int[n];
        Arrays.fill(dist, Max);
        dist[0] = 0;
        boolean[] done = new boolean[n];
        PriorityQueue<int[]> pq = new PriorityQueue<>((x, y) -> Integer.compare(x[1], y[1]));
        // node id, dist, sort by dist
        pq.offer(new int[]{0, 0});
        int res = 0;
        while (!pq.isEmpty()) {
            int[] top = pq.poll();
            int i = top[0];
            int d = top[1];

            if (done[i]) {
                continue;
            }
            res++; // the node itself. note we must do after done to avoid double countinue
            done[i] = true;
            Map<Integer, Integer> nexts = g[i];
            for (int next : nexts.keySet()) {
                int edge = nexts.get(next);
                int nd = edge + d + 1;
                // dist to reach the next node
                if (nd <= m) {
                    if (dist[next] > nd) {
                        // we could have reached next earlier so checking here. we need to add the remnants on the edge regardelss of this check
                        dist[next] = nd;
                        pq.offer(new int[]{next, nd});
                    }
                    // consumed this edge
                    g[next].remove(i);
                    res += edge;
                } else {
                    int walked = m - d;
                    // note how many we can walk in reverse
                    g[next].put(i, edge - walked);
                    res += walked;
                }
            }
        }
        return res;
    }


    public static void main(String[] args) {

        //   System.out.println(new ReachableNodesInSubdividedGraph().reachableNodes(ArrayUtils.read("[[0,1,10],[0,2,1],[1,2,2]]"), 6, 3));
        System.out.println(new ReachableNodesInSubdividedGraph().reachableNodes(ArrayUtils.read("[[0,1,4],[1,2,6],[0,2,8],[1,3,1]]"), 10, 4));

    }
}
