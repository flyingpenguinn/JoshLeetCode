import java.util.*;

public class MinMovesToCleanClassRoom {
    // only need deque for simple bfs
    // only need best for dominance case!
    private int Max = (int) 1e18;

    private int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public int minMoves(String[] a, int maxe) {
        int m = a.length;
        int n = a[0].length();

        int si = -1;
        int sj = -1;
        int[][] lmap = new int[m][n];
        int lindex = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (a[i].charAt(j) == 'S') {
                    si = i;
                    sj = j;
                } else if (a[i].charAt(j) == 'L') {
                    lmap[i][j] = lindex++;
                }
            }
        }
        int[][][] best = new int[m][n][(1 << (lindex))];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                Arrays.fill(best[i][j], -1);

            }
        }
        Deque<int[]> pq = new ArrayDeque<>();
        pq.offer(new int[]{si, sj, maxe, 0, 0});
        while (!pq.isEmpty()) {
            int[] top = pq.poll();
            int r = top[0];
            int c = top[1];
            int ce = top[2];
            int cl = top[3];
            int cd = top[4];
            if (cl + 1 == (1 << lindex)) {
                return cd;
            }
            if (ce == 0) {
                continue;
            }
            int nd = cd + 1;
            for (int[] d : dirs) {
                int nr = r + d[0];
                int nc = c + d[1];
                if (nr >= 0 && nr < m && nc >= 0 && nc < n && a[nr].charAt(nc) != 'X') {
                    int nce = ce - 1;

                    if (a[nr].charAt(nc) == 'R') {
                        nce = maxe;
                    } else if (nce < 0) {
                        continue;
                    }
                    int ncl = cl;
                    if (a[nr].charAt(nc) == 'L') {
                        int cindex = lmap[nr][nc];
                        ncl |= (1 << cindex);
                    }
                    if (nce <= best[nr][nc][ncl]) {
                        continue;
                    }
                    best[nr][nc][ncl] = nce;
                    pq.offer(new int[]{nr, nc, nce, ncl, nd});

                }
            }
        }
        return -1;
    }
}
