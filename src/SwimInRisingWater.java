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
    // dijikastra so that we calc the min time needed to reach a cell from 0,0. this is like turning the + in dijkastra into max
    private int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    private int Max = 100000000;

    public int swimInWater(int[][] g) {
        int n = g.length;
        PriorityQueue<int[]> pq = new PriorityQueue<>((x, y) -> Integer.compare(x[2], y[2]));
        // row, col, max value from 0,0 to this node
        int[][] dists = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dists[i], Max);
        }
        boolean[][] done = new boolean[n][n];
        pq.offer(new int[]{0, 0, g[0][0]});
        dists[0][0] = g[0][0];
        while (!pq.isEmpty()) {
            int[] top = pq.poll();
            int i = top[0];
            int j = top[1];
            int dist = top[2];
            if (i == n - 1 && j == n - 1) {
                return dist;
            }
            if (done[i][j]) {
                continue;
            }
            done[i][j] = true;
            for (int[] d : dirs) {
                int ni = i + d[0];
                int nj = j + d[1];
                if (ni >= 0 && ni < n && nj >= 0 && nj < n) {
                    int ndist = Math.max(dist, g[ni][nj]);
                    if (dists[ni][nj] > ndist) {
                        dists[ni][nj] = ndist;
                        pq.offer(new int[]{ni, nj, ndist});
                    }
                }
            }
        }
        return -1;
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