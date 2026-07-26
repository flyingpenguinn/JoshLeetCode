import java.util.Arrays;
import java.util.PriorityQueue;

public class MinCostPathWithAlternatingDirsIII {
    private long Max = (long) 1e18;
    private long Min = -Max;
    private long Mod = (long) (1e9 + 7);
    private int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public long minCost(int m, int n, int[][] penalty) {
        PriorityQueue<long[]> pq = new PriorityQueue<>((x, y) -> Long.compare(x[3], y[3]));
        long[][][] dist = new long[m][n][2];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                Arrays.fill(dist[i][j], Max);
            }
        }
        dist[0][0][0] = 1;
        pq.offer(new long[]{0, 0, 0, 1});
        while (!pq.isEmpty()) {
            long[] top = pq.poll();
            long r = top[0];
            long c = top[1];
            int parity = (int) top[2];
            long cd = top[3];
            if (dist[(int) r][(int) c][parity] != cd) {
                continue;
            }
            if (r == m - 1 && c == n - 1) {
                return cd;
            }
            int np = parity ^ 1;
            for (int di = 0; di < 4; ++di) {
                int[] d = dirs[di];
                long nr = r + d[0];
                long nc = c + d[1];
                if (nr >= 0 && nr < m && nc >= 0 && nc < n) {
                    long nd = cd + (nr + 1) * (nc + 1);
                    if (di % 2 != np % 2) {
                        nd += penalty[(int) r][(int) c];
                    }
                    if (dist[(int) nr][(int) nc][np] > nd) {
                        dist[(int) nr][(int) nc][np] = nd;
                        pq.offer(new long[]{nr, nc, np, nd});
                    }

                }

            }
            long waitd = cd + penalty[(int) r][(int) c];
            if (dist[(int) r][(int) c][np] > waitd) {
                dist[(int) r][(int) c][np] = waitd;
                pq.offer(new long[]{r, c, np, waitd});
            }
        }

        return -1;
    }
}
