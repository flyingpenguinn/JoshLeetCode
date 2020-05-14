import base.ArrayUtils;

import java.util.Arrays;
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
    // dijkastra with a twist of calculating travelled dist
    int Max = 1000000000;

    public int reachableNodes(int[][] edges, int m, int n) {
        int[][] g = new int[n][n];

        for (int i = 0; i < n; i++) {
            Arrays.fill(g[i], -1);
        }
        boolean[] done = new boolean[n];
        for (int[] e : edges) {
            g[e[0]][e[1]] = e[2];
            g[e[1]][e[0]] = e[2];
        }
        PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));
        int[] dist = new int[n];
        Arrays.fill(dist, Max);
        q.offer(new int[]{0, 0});
        dist[0] = 0;
        int r = 0;
        while (!q.isEmpty()) {
            int[] top = q.poll();
            int cur = top[0];
            if (done[cur]) {
                continue;
            }
            r++;
            done[cur] = true;
            for (int i = 0; i < n; i++) {
                if (g[cur][i] < 0) {
                    // <0 means not reachable. note dist can ==0
                    continue;
                }
                if (g[cur][i] + dist[cur] + 1 > m) {
                    r += m - dist[cur];
                    // so that we dont revisit these visited dots when we go i-> cur later
                    g[i][cur] -= m - dist[cur];
                } else {
                    r += g[cur][i];
                    // so that we dont revisit these visited dots when we go i-> cur later
                    g[i][cur] = -1;
                    int nd = dist[cur] + g[cur][i] + 1;
                    if (dist[i] > nd) {
                        dist[i] = nd;
                        q.offer(new int[]{i, nd});
                    }
                }
            }
        }
        return r;
    }


    public static void main(String[] args) {

        //   System.out.println(new ReachableNodesInSubdividedGraph().reachableNodes(ArrayUtils.read("[[0,1,10],[0,2,1],[1,2,2]]"), 6, 3));
        System.out.println(new ReachableNodesInSubdividedGraph().reachableNodes(ArrayUtils.read("[[0,1,4],[1,2,6],[0,2,8],[1,3,1]]"), 10, 4));

    }
}
