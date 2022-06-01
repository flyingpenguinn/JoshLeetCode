import java.util.Arrays;
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
