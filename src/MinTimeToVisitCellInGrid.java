import java.util.Arrays;
import java.util.PriorityQueue;

public class MinTimeToVisitCellInGrid {
    // simliar to swim in rising water. but here if we get a bigger cell, we have to ping pong with the surrounding cells
    private int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    private int Max = 100000000;

    public int minimumTime(int[][] g) {
        int m = g.length;
        int n = g[0].length;
        PriorityQueue<int[]> pq = new PriorityQueue<>((x, y) -> Integer.compare(x[2], y[2]));
        // row, col, max value from 0,0 to this node
        int[][] dists = new int[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(dists[i], Max);
        }
        boolean[][] done = new boolean[m][n];

        pq.offer(new int[]{0, 0, g[0][0]});
        dists[0][0] = g[0][0];
        while (!pq.isEmpty()) {
            int[] top = pq.poll();
            int i = top[0];
            int j = top[1];
            int dist = top[2];
            if (i == m - 1 && j == n - 1) {
                return dist;
            }
            if (done[i][j]) {
                continue;
            }
            done[i][j] = true;
            boolean hassmaller = false;
            int cnd = dist + 1;
            for (int[] d : dirs) {
                int ni = i + d[0];
                int nj = j + d[1];
                if (ni >= 0 && ni < m && nj >= 0 && nj < n) {
                    if (g[ni][nj] <= cnd) {
                        hassmaller = true;
                        break;
                    }
                }
            }
            for (int[] d : dirs) {
                int ni = i + d[0];
                int nj = j + d[1];
                if (ni >= 0 && ni < m && nj >= 0 && nj < n) {
                    int ndist = 0;
                    if (g[ni][nj] > cnd) {
                        if (!hassmaller) {
                            continue;
                        } else {
                            int delta = g[ni][nj] - cnd;
                            ndist = delta % 2 == 0 ? g[ni][nj] : g[ni][nj] + 1;
                        }
                    } else {
                        ndist = dist + 1;
                    }
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
