/*
LC#1129
You are playing a simplified Pacman game. You start at the point (0, 0), and your destination is (target[0], target[1]). There are several ghosts on the map, the i-th ghost starts at (ghosts[i][0], ghosts[i][1]).

Each turn, you and all ghosts simultaneously *may* move in one of 4 cardinal directions: north, east, west, or south, going from the previous point to a new point 1 unit of distance away.

You escape if and only if you can reach the target before any ghost reaches you (for any given moves the ghosts may take.)  If you reach any square (including the target) at the same time as a ghost, it doesn't count as an escape.

Return True if and only if it is possible to escape.

Example 1:
Input:
ghosts = [[1, 0], [0, 3]]
target = [0, 1]
Output: true
Explanation:
You can directly reach the destination (0, 1) at time 1, while the ghosts located at (1, 0) or (0, 3) have no way to catch up with you.
Example 2:
Input:
ghosts = [[1, 0]]
target = [2, 0]
Output: false
Explanation:
You need to reach the destination (2, 0), but the ghost at (1, 0) lies between you and the destination.
Example 3:
Input:
ghosts = [[2, 0]]
target = [1, 0]
Output: false
Explanation:
The ghost can reach the target at the same time as you.
Note:

All points have coordinates with absolute value <= 10000.
The number of ghosts will not exceed 100.
 */

import java.util.*;

public class ShortestPathWithAlternatingColors {
    // one bfs on starting red and then another one on starting with blue
    Map<Integer, Set<Integer>>[] g = new HashMap[2];

    {
        g[0] = new HashMap<>();
        g[1] = new HashMap<>();
    }

    boolean[][] v;
    int[] r;
    int Max = 10000000;

    public int[] shortestAlternatingPaths(int n, int[][] re, int[][] be) {
        // r=0 b=1
        v = new boolean[n + 1][2];
        r = new int[n];
        Arrays.fill(r, Max);
        buildg(re, 0);
        buildg(be, 1);

        bfs(0);
        for (int i = 0; i < v.length; i++) {
            // need to clear up for next bfs
            Arrays.fill(v[i], false);
        }
        bfs(1);
        for (int i = 0; i < r.length; i++) {
            if (r[i] >= Max) {
                r[i] = -1;
            }
        }
        return r;
    }

    void buildg(int[][] es, int i) {
        for (int[] e : es) {
            Set<Integer> set = g[i].getOrDefault(e[0], new HashSet<>());
            set.add(e[1]);
            g[i].put(e[0], set);
        }
    }

    // start from 0 color c
    void bfs(int c) {
        // node, color to expand at node, dist from 0
        Deque<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{0, c, 0});
        v[0][c] = true;
        while (!q.isEmpty()) {
            int[] top = q.poll();
            System.out.println(Arrays.toString(top));
            int cur = top[0];
            int col = top[1];
            int ncol = col == 0 ? 1 : 0;
            int dist = top[2];
            if (r[cur] > dist) {
                r[cur] = dist;
            }
            Set<Integer> nexts = g[col].getOrDefault(cur, new HashSet<>());
            for (int next : nexts) {
                if (!v[next][ncol]) {
                    v[next][ncol] = true;
                    q.offer(new int[]{next, ncol, dist + 1});
                }
            }
        }
    }
}
