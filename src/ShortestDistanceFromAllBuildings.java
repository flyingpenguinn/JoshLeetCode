import base.ArrayUtils;

import java.util.ArrayDeque;
import java.util.Deque;

/*
LC#317
You want to build a house on an empty land which reaches all buildings in the shortest amount of distance. You can only move up, down, left and right. You are given a 2D grid of values 0, 1 or 2, where:

Each 0 marks an empty land which you can pass by freely.
Each 1 marks a building which you cannot pass through.
Each 2 marks an obstacle which you cannot pass through.
Example:

Input: [[1,0,2,0,1],[0,0,0,0,0],[0,0,1,0,0]]

1 - 0 - 2 - 0 - 1
|   |   |   |   |
0 - 0 - 0 - 0 - 0
|   |   |   |   |
0 - 0 - 1 - 0 - 0

Output: 7

Explanation: Given three buildings at (0,0), (0,4), (2,2), and an obstacle at (0,2),
             the point (1,2) is an ideal empty land to build a house, as the total
             travel distance of 3+3+1=7 is minimal. So return 7.
Note:
There will be at least one building. If it is not possible to build such house according to the above rules, return -1.
 */
public class ShortestDistanceFromAllBuildings {
    /*

improvement on normal bfs!
Short version: BFS from every building, calculate the distances and find the minimum distance in the end.

Key optimization : we do not go into a land, if it is not accessible by at least one of previous buildings.
because otherwise expanding fromthat land is useless.

so if g[i][j] == 0 but it's not reachable from one of the buildings then no point of expanding this node further

we bfs from building because this way it can filter useless empty lands quickly
 */
    public int shortestDistance(int[][] g) {
        int m = g.length;
        int n = g[0].length;
        int[][] reach = new int[m][n];
        int[][] dist = new int[m][n];
        int buildings = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (g[i][j] == 1) {
                    bfs(g, i, j, reach, buildings, dist);
                    buildings++;
                }
            }
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (reach[i][j] == buildings) {
                    min = Math.min(min, dist[i][j]);
                }
            }
        }
        return min == Integer.MAX_VALUE ? -1 : min;
    }

    int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    void bfs(int[][] g, int si, int sj, int[][] reach, int buildings, int[][] dist) {
        int m = g.length;
        int n = g[0].length;
        Deque<int[]> q = new ArrayDeque<>();
        boolean[][] v = new boolean[m][n];
        q.offer(new int[]{si, sj, 0});
        v[si][sj] = true;
        while (!q.isEmpty()) {
            int[] top = q.poll();
            int i = top[0];
            int j = top[1];
            if (g[i][j] == 0) {
                reach[i][j]++;
                dist[i][j] += top[2];
            }
            for (int[] d : dirs) {
                int ni = i + d[0];
                int nj = j + d[1];
                if (ni >= 0 && ni < m && nj >= 0 && nj < n && !v[ni][nj]) {
                    v[ni][nj] = true;
                    if (g[ni][nj] == 2 || g[ni][nj] == 1) {
                        continue;
                    }
                    if (g[ni][nj] == 0 && reach[ni][nj] < buildings) {
                        continue;
                    }
                    q.offer(new int[]{ni, nj, top[2] + 1});
                }
            }
        }
    }

}
