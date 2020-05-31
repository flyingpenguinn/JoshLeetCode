import java.util.*;

/*
LC#1466
There are n cities numbered from 0 to n-1 and n-1 roads such that there is only one way to travel between two different cities (this network form a tree). Last year, The ministry of transport decided to orient the roads in one direction because they are too narrow.

Roads are represented by connections where connections[i] = [a, b] represents a road from city a to b.

This year, there will be a big event in the capital (city 0), and many people want to travel to this city.

Your task consists of reorienting some roads such that each city can visit the city 0. Return the minimum number of edges changed.

It's guaranteed that each city can reach the city 0 after reorder.



Example 1:



Input: n = 6, connections = [[0,1],[1,3],[2,3],[4,0],[4,5]]
Output: 3
Explanation: Change the direction of edges show in red such that each node can reach the node 0 (capital).
Example 2:



Input: n = 5, connections = [[1,0],[1,2],[3,2],[3,4]]
Output: 2
Explanation: Change the direction of edges show in red such that each node can reach the node 0 (capital).
Example 3:

Input: n = 3, connections = [[1,0],[2,0]]
Output: 0


Constraints:

2 <= n <= 5 * 10^4
connections.length == n-1
connections[i].length == 2
0 <= connections[i][0], connections[i][1] <= n-1
connections[i][0] != connections[i][1]
 */
public class ReorderRoutesToMakeAllPaths {
    // bfs and in the process, reverse the directions
    public int minReorder(int n, int[][] c) {
        List<Integer>[] g = new ArrayList[n];
        List<Integer>[] rg = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            g[i] = new ArrayList<>();
            rg[i] = new ArrayList<>();
        }
        for (int[] ci : c) {
            rg[ci[1]].add(ci[0]);
            g[ci[0]].add(ci[1]);
        }
        Deque<Integer> q = new ArrayDeque<>();
        q.offer(0);
        Set<Integer> seen = new HashSet<>();
        seen.add(0);
        int changed = 0;
        while (!q.isEmpty()) {
            int top = q.poll();
            for (int next : rg[top]) {
                if (!seen.contains(next)) {
                    seen.add(next);
                    q.offer(next);
                }
            }
            for (int next : g[top]) {
                if (!seen.contains(next)) {
                    seen.add(next);
                    changed++;
                    q.offer(next);
                }
            }
        }
        return changed;
    }
}
