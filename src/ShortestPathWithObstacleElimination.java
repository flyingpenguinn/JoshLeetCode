import java.util.ArrayDeque;
import java.util.Deque;

/*
LC#1293
Given a m * n grid, where each cell is either 0 (empty) or 1 (obstacle). In one step, you can move up, down, left or right from and to an empty cell.

Return the minimum number of steps to walk from the upper left corner (0, 0) to the lower right corner (m-1, n-1) given that you can eliminate at most k obstacles. If it is not possible to find such walk return -1.



Example 1:

Input:
grid =
[[0,0,0],
 [1,1,0],
 [0,0,0],
 [0,1,1],
 [0,0,0]],
k = 1
Output: 6
Explanation:
The shortest path without eliminating any obstacle is 10.
The shortest path with one obstacle elimination at position (3,2) is 6. Such path is (0,0) -> (0,1) -> (0,2) -> (1,2) -> (2,2) -> (3,2) -> (4,2).


Example 2:

Input:
grid =
[[0,1,1],
 [1,1,1],
 [1,0,0]],
k = 1
Output: -1
Explanation:
We need to eliminate at least two obstacles to find such a walk.


Constraints:

grid.length == m
grid[0].length == n
1 <= m, n <= 40
1 <= k <= m*n
grid[i][j] == 0 or 1
grid[0][0] == grid[m-1][n-1] == 0
 */
public class ShortestPathWithObstacleElimination {
    // for each ij, add another status to mark the obstacles eliminated
    // typical shortest path with extra status
    int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    int Max = 1000000007;

    public int shortestPath(int[][] g, int k) {
        int m = g.length;
        int n = g[0].length;
        Deque<int[]> q = new ArrayDeque<>();
        boolean[][][] seen = new boolean[m][n][k + 1];
        q.offer(new int[]{0, 0, 0, g[0][0]});

        int min = Max;
        while (!q.isEmpty()) {
            int[] top = q.poll();
            int i = top[0];
            int j = top[1];
            int dist = top[2];
            int ck = top[3];
            if (ck > k) {
                continue;
            }
            if (i == m - 1 && j == n - 1) {
                return dist;
            }
            for (int[] d : dirs) {
                int ni = i + d[0];
                int nj = j + d[1];
                if (ni >= 0 && ni < m && nj >= 0 && nj < n) {
                    int ak = g[ni][nj];
                    int nk = ck + ak;
                    if (nk <= k && !seen[ni][nj][nk]) {
                        seen[ni][nj][nk] = true;
                        q.offer(new int[]{ni, nj, dist + 1, nk});
                    }
                }
            }
        }
        return min >= Max ? -1 : min;
    }
}
