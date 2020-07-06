import java.util.ArrayDeque;
import java.util.Deque;

/*
LC#286
You are given a m x n 2D grid initialized with these three possible values.

-1 - A wall or an obstacle.
0 - A gate.
INF - Infinity means an empty room. We use the value 231 - 1 = 2147483647 to represent INF as you may assume that the distance to a gate is less than 2147483647.
Fill each empty room with the distance to its nearest gate. If it is impossible to reach a gate, it should be filled with INF.

Example:

Given the 2D grid:

INF  -1  0  INF
INF INF INF  -1
INF  -1 INF  -1
  0  -1 INF INF
After running your function, the 2D grid should be:

  3  -1   0   1
  2   2   1  -1
  1  -1   2  -1
  0  -1   3   4
 */
public class WallsAndGates {
    // spfa. think of all 0 connected with 0 dist
    private int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public void wallsAndGates(int[][] a) {
        if (a == null || a.length == 0 || a[0].length == 0) {
            return;
        }
        int m = a.length;
        int n = a[0].length;
        Deque<int[]> q = new ArrayDeque<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (a[i][j] == 0) {
                    q.offer(new int[]{i, j, 0});
                }
            }
        }
        while (!q.isEmpty()) {
            int[] top = q.poll();
            int r = top[0];
            int c = top[1];
            int dist = top[2];
            a[r][c] = dist;
            for (int[] d : dirs) {
                int nr = r + d[0];
                int nc = c + d[1];
                if (nr >= 0 && nr < m && nc >= 0 && nc < n && a[nr][nc] != -1 && a[nr][nc] > dist + 1) {
                    // if already visited, would be < dist+1
                    a[nr][nc] = dist + 1;
                    q.offer(new int[]{nr, nc, dist + 1});
                }
            }
        }
    }

    private boolean inRange(int nr, int nc, int r, int c) {
        return nr >= 0 && nr < r && nc >= 0 && nc < c;
    }
}
