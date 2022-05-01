import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class EcapeTheSpreadingFire {
    private int Max = (int) 1e9;
    private int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public int maximumMinutes(int[][] a) {
        int m = a.length;
        int n = a[0].length;
        int[][] dist = new int[m][n];
        Deque<int[]> q = new ArrayDeque<>();
        for (int i = 0; i < m; ++i) {
            Arrays.fill(dist[i], Max + 10);
        }
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (a[i][j] == 1) {
                    q.offer(new int[]{i, j, 0});
                    dist[i][j] = 0;
                }
            }
        }
        while (!q.isEmpty()) {
            int[] top = q.poll();
            int i = top[0];
            int j = top[1];
            int t = top[2];
            int nt = t + 1;
            for (int[] d : dirs) {
                int ni = i + d[0];
                int nj = j + d[1];
                if (ni >= 0 && ni < m && nj >= 0 && nj < n && a[ni][nj] != 2 && dist[ni][nj] > nt) {
                    dist[ni][nj] = nt;
                    q.offer(new int[]{ni, nj, nt});
                }
            }
        }
        int l = 0;
        int u = Max;
        while (l <= u) {
            int mid = l + (u - l) / 2;
            if (doable(a, mid, dist)) {
                l = mid + 1;
            } else {
                u = mid - 1;
            }
        }
        return u;
    }

    private boolean doable(int[][] a, int mid, int[][] dist) {
        Deque<int[]> q = new ArrayDeque<>();
        int m = a.length;
        int n = a[0].length;
        q.offer(new int[]{0, 0, mid});
        boolean[][] seen = new boolean[m][n];
        while (!q.isEmpty()) {
            int[] top = q.poll();
            int i = top[0];
            int j = top[1];
            if (i == m - 1 && j == n - 1) {
                return true;
            }
            int t = top[2];
            int nt = t + 1;
            for (int[] d : dirs) {
                int ni = i + d[0];
                int nj = j + d[1];
                if (ni == m - 1 && nj == n - 1 && dist[ni][nj] == nt) {
                    return true;
                }
                if (ni >= 0 && ni < m && nj >= 0 && nj < n && a[ni][nj] != 2 && !seen[ni][nj] && dist[ni][nj] > nt) {
                    seen[ni][nj] = true;
                    q.offer(new int[]{ni, nj, nt});
                }
            }
        }
        return false;
    }

}
