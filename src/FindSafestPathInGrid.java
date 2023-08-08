import java.util.*;

public class FindSafestPathInGrid {
    private static final int MAX = (int) (1e9 + 7);
    private int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public int maximumSafenessFactor(List<List<Integer>> a) {
        int m = a.size();
        int n = a.get(0).size();
        Deque<int[]> q = new ArrayDeque<>();

        int[][] td = new int[m][n];
        for (int i = 0; i < m; ++i) {
            Arrays.fill(td[i], MAX);
        }
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (a.get(i).get(j) == 1) {
                    q.offer(new int[]{i, j, 0});
                    td[i][j] = 0;
                }
            }
        }
        while (!q.isEmpty()) {
            int[] top = q.poll();
            int r = top[0];
            int c = top[1];
            int cd = top[2];
            int nd = cd + 1;
            for (int[] d : dirs) {
                int nr = r + d[0];
                int nc = c + d[1];
                if (nr >= 0 && nr < m && nc >= 0 && nc < n && td[nr][nc] > nd) {
                    td[nr][nc] = nd;
                    q.offer(new int[]{nr, nc, nd});
                }
            }
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>((x, y) -> Integer.compare(y[2], x[2]));
        pq.offer(new int[]{0, 0, td[0][0]});
        int[][] dist = new int[m][n];
        for (int i = 0; i < m; ++i) {
            Arrays.fill(dist[i], -1);
        }
        dist[0][0] = td[0][0];
        while (!pq.isEmpty()) {
            int[] top = pq.poll();
            int r = top[0];
            int c = top[1];
            int cd = top[2];
            if (r == m - 1 && c == n - 1) {
                return cd;
            }
            for (int[] d : dirs) {
                int nr = r + d[0];
                int nc = c + d[1];
                if (nr >= 0 && nr < m && nc >= 0 && nc < n) {
                    int nd = Math.min(cd, td[nr][nc]);
                    if (dist[nr][nc] < nd) {
                        pq.offer(new int[]{nr, nc, nd});
                        dist[nr][nc] = nd;
                    }
                }
            }
        }
        return -1;
    }
}
