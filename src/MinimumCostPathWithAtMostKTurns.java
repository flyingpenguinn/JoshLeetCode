import java.util.Arrays;
import java.util.PriorityQueue;

public class MinimumCostPathWithAtMostKTurns {
    private int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    private int Max = (int) 1e9;

    public int minCost(int[][] a, int k) {
        int m = a.length;
        int n = a[0].length;
        int[][][][] dist = new int[m][n][5][k + 1];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                for (int p = 0; p < 5; ++p) {
                    Arrays.fill(dist[i][j][p], Max);
                }
            }
        }
        dist[0][0][4][0] = a[0][0];
        PriorityQueue<int[]> pq = new PriorityQueue<>((x, y) -> Integer.compare(x[4], y[4]));
        pq.offer(new int[]{0, 0, 4, 0, a[0][0]});
        while (!pq.isEmpty()) {
            int[] top = pq.poll();
            int r = top[0];
            int c = top[1];
            int cdir = top[2];
            int cturn = top[3];
            int cd = top[4];
            if (r == m - 1 && c == n - 1) {
                return cd;
            }
            for (int di = 0; di < 4; ++di) {
                int[] d = dirs[di];
                int nr = r + d[0];
                int nc = c + d[1];
                if (nr >= 0 && nr < m && nc >= 0 && nc < n) {
                    int nturn = cturn;
                    if (cdir < 4 && di != cdir) {
                        nturn += 1;
                    }
                    if (nturn > k) {
                        continue;
                    }
                    int nd = cd + a[nr][nc];
                    if (dist[nr][nc][di][nturn] > nd) {
                        dist[nr][nc][di][nturn] = nd;
                        pq.offer(new int[]{nr, nc, di, nturn, nd});
                    }
                }
            }
        }
        return -1;
    }
}
