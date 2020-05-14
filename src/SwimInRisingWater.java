import base.ArrayUtils;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/*
LC#778
On an N x N grid, each square grid[i][j] represents the elevation at that point (i,j).

Now rain starts to fall. At time t, the depth of the water everywhere is t. You can swim from a square to another 4-directionally adjacent square if and only if the elevation of both squares individually are at most t. You can swim infinite distance in zero time. Of course, you must stay within the boundaries of the grid during your swim.

You start at the top left square (0, 0). What is the least time until you can reach the bottom right square (N-1, N-1)?

Example 1:

Input: [[0,2],[1,3]]
Output: 3
Explanation:
At time 0, you are in grid location (0, 0).
You cannot go anywhere else because 4-directionally adjacent neighbors have a higher elevation than t = 0.

You cannot reach point (1, 1) until time 3.
When the depth of water is 3, we can swim anywhere inside the grid.
Example 2:

Input: [[0,1,2,3,4],[24,23,22,21,5],[12,13,14,15,16],[11,17,18,19,20],[10,9,8,7,6]]
Output: 16
Explanation:
 0  1  2  3  4
24 23 22 21  5
12 13 14 15 16
11 17 18 19 20
10  9  8  7  6

The final route is marked in bold.
We need to wait until time 16 so that (0, 0) and (4, 4) are connected.
Note:

2 <= N <= 50.
grid[i][j] is a permutation of [0, ..., N*N - 1].
 */
public class SwimInRisingWater {
    // path with smallest max v...turn + in dijkastra to max
    int Max = 1000000;
    private int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    private boolean inRange(int[][] b, int ni, int nj) {
        return ni >= 0 && ni < b.length && nj >= 0 && nj < b[0].length;
    }


    public int swimInWater(int[][] g) {
        int m = g.length;
        int n = g[0].length;

        // min max path...
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o1[2], o2[2]);
            }
        });

        boolean[][] done = new boolean[m][n];
        pq.offer(new int[]{0, 0, g[0][0]});
        int[][] dist = new int[m][n];

        for (int i = 0; i < m; i++) {
            Arrays.fill(dist[i], Max);
        }
        dist[0][0] = g[0][0];
        while (!pq.isEmpty()) {
            int[] top = pq.poll();
            int i = top[0];
            int j = top[1];
            if (i == m - 1 && j == n - 1) {
                break;
            }
            if (done[i][j]) {
                continue;
            }
            done[i][j] = true;
            for (int[] d : directions) {
                int ni = top[0] + d[0];
                int nj = top[1] + d[1];
                if (inRange(g, ni, nj)) {
                    int t = Math.max(dist[i][j], g[ni][nj]);
                    if (dist[ni][nj] > t) {
                        dist[ni][nj] = t;
                        pq.offer(new int[]{ni, nj, t});
                    }
                }
            }
        }
        return dist[m-1][n-1];
    }
}

class SwimRisingWaterBinary {
    // can also binary search the result..

    private int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};


    private boolean inRange(int[][] board, int ni, int nj) {
        return ni >= 0 && ni < board.length && nj >= 0 && nj < board[0].length;
    }


    public int swimInWater(int[][] grid) {
        int l = 0;
        int u = grid.length * grid.length - 1;
        boolean[][] visited = new boolean[grid.length][grid[0].length];

        while (l <= u) {
            for (int i = 0; i < visited.length; i++) {
                Arrays.fill(visited[i], false);
            }
            int mid = l + (u - l) / 2;
            if (canSwim(grid, mid, 0, 0, visited)) {
                u = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }

    private boolean canSwim(int[][] grid, int mid, int i, int j, boolean[][] visited) {
        visited[i][j] = true;
        if (grid[i][j] > mid) {
            return false;
        }
        if (i == grid.length - 1 && j == grid[0].length - 1) {
            return true;
        }
        for (int[] d : directions) {
            int nr = i + d[0];
            int nc = j + d[1];
            if (inRange(grid, nr, nc) && !visited[nr][nc] && grid[nr][nc] <= mid) {

                boolean can = canSwim(grid, mid, nr, nc, visited);
                if (can) {
                    return true;
                }
            }
        }
        return false;
    }
}