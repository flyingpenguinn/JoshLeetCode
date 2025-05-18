import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.PriorityQueue;

public class MinObstacleRemovalToReachCorner {
    private int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public int minimumObstacles(int[][] a) {
        int m = a.length;
        int n = a[0].length;
        int[][] dist = new int[m][n];
        for (int i = 0; i < m; ++i) {
            Arrays.fill(dist[i], (int) 2e9);
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>((x, y) -> Integer.compare(x[2], y[2]));
        pq.offer(new int[]{0, 0, 0});
        boolean[][] done = new boolean[m][n];
        while (!pq.isEmpty()) {
            int[] top = pq.poll();
            int i = top[0];
            int j = top[1];
            int cd = top[2];
            if (done[i][j]) {
                continue;
            }
            if (i == m - 1 && j == n - 1) {
                return cd;
            }
            done[i][j] = true;
            int nd = cd + a[i][j];
            for (int[] d : dirs) {
                int ni = i + d[0];
                int nj = j + d[1];
                if (ni >= 0 && ni < m && nj >= 0 && nj < n && dist[ni][nj] > nd) {
                    dist[ni][nj] = nd;
                    pq.offer(new int[]{ni, nj, nd});
                }
            }
        }
        return -1;
    }
}

class MinObstacleRemoval01BFS {
    // 01 BFS is best when you can "teleport"
    private int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public int minimumObstacles(int[][] g) {
        int m = g.length;
        int n = g[0].length;
        int[][] dist = new int[m][n];
        int Max = (int) 1e9;
        for (int i = 0; i < m; ++i) {
            Arrays.fill(dist[i], Max);
        }
        Deque<int[]> dq = new ArrayDeque<>();
        dist[0][0] = 0;
        dq.offerLast(new int[]{0, 0});
        while (!dq.isEmpty()) {
            int[] top = dq.pollFirst();
            int i = top[0];
            int j = top[1];
            int cd = dist[i][j];
            if (i == m - 1 && j == n - 1) {
                return cd;
            }
            for (int[] d : dirs) {
                int ni = i + d[0];
                int nj = j + d[1];
                if (ni >= 0 && ni < m && nj >= 0 && nj < n && g[ni][nj] == 0 && dist[ni][nj] > cd) {
                    dist[ni][nj] = cd;
                    dq.offerFirst(new int[]{ni, nj});
                }
            }
            for (int[] d : dirs) {
                int ni = i + d[0];
                int nj = j + d[1];
                int nd = cd + 1;
                if (ni >= 0 && ni < m && nj >= 0 && nj < n && g[ni][nj] == 1 && dist[ni][nj] > nd) {
                    dist[ni][nj] = nd;
                    dq.offerLast(new int[]{ni, nj});
                }
            }
        }
        return -1;
    }
}