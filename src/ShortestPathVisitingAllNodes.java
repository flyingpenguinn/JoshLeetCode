import base.ArrayUtils;

import java.util.*;

/*
LC#847
An undirected, connected graph of N nodes (labeled 0, 1, 2, ..., N-1) is given as graph.

graph.length = N, and j != i is in the list graph[i] exactly once, if and only if nodes i and j are connected.

Return the length of the shortest path that visits every node. You may start and stop at any node, you may revisit nodes multiple times, and you may reuse edges.



Example 1:

Input: [[1,2,3],[0],[0],[0]]
Output: 4
Explanation: One possible path is [1,0,2,0,3]
Example 2:

Input: [[1],[0,2,4],[1,3,4],[2],[1,2]]
Output: 4
Explanation: One possible path is [0,1,4,2,3]


Note:

1 <= graph.length <= 12
0 <= graph[i].length < graph.length
 */
public class ShortestPathVisitingAllNodes {
    // typical bfs with state!
    // state is where we are + the visited state. if we come back to a node but we didnt visit any new node, we'd discard that thought
    // actually similar to LC#1293 shortest path with obstacle eliminated/ we add extra state to differentiate them: it's obstacles there and status here
    // note in #1293, in one path it won't have duplicated nodes. but in this problem duplications may occur. however it doesnt matter as we added state to rule out dupes
    public int shortestPathLength(int[][] a) {
        int n = a.length;
        // node, state, dist
        Deque<int[]> q = new ArrayDeque<>();
        boolean[][] seen = new boolean[n][1 << n];
        for (int i = 0; i < a.length; i++) {
            q.offer(new int[]{i, 1 << i, 0});
            seen[i][1 << i] = true;
        }
        while (!q.isEmpty()) {
            int[] top = q.poll();
            int i = top[0];
            int state = top[1];
            int dist = top[2];
            if (state + 1 == (1 << n)) {
                return dist;
            }
            for (int j : a[i]) {
                int nst = state | (1 << j);
                if (!seen[j][nst]) {
                    seen[j][nst] = true;
                    q.offer(new int[]{j, nst, dist + 1});
                }
            }
        }
        return -1;
    }
}
