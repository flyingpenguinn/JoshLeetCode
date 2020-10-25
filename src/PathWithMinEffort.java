import java.util.Arrays;
import java.util.PriorityQueue;

public class PathWithMinEffort {
    // similar to swim in rising wather, binary search or dijkstra
    public int minimumEffortPath(int[][] a) {
        int min = Integer.MAX_VALUE;
        int max = -1;
        int m = a.length;
        int n = a[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                min = Math.min(min, a[i][j]);
                max = Math.max(max, a[i][j]);
            }
        }
        int l = 0;
        int u = max - min;
        while (l <= u) {
            int mid = l + (u - l) / 2;
            if (doable(a, mid)) {
                u = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }

    private boolean doable(int[][] a, int mid) {
        int m = a.length;
        int n = a[0].length;
        boolean[][] v = new boolean[m][n];
        return dfs(a, 0, 0, mid, v);
    }

    private int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    private boolean dfs(int[][] a, int i, int j, int lim, boolean[][] v) {
        int m = a.length;
        int n = a[0].length;
        v[i][j] = true;
        if (i == m - 1 && j == n - 1) {
            return true;
        }
        for (int[] d : dirs) {
            int ni = i + d[0];
            int nj = j + d[1];
            if (ni >= 0 && ni < m && nj >= 0 && nj < n && !v[ni][nj] && Math.abs(a[ni][nj] - a[i][j]) <= lim) {
                if (dfs(a, ni, nj, lim, v)) {
                    return true;
                }
            }
        }
        return false;
    }
}

class PathWithMinEffortDijkstra {
    private int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public int minimumEffortPath(int[][] a) {
        int m = a.length;
        int n = a[0].length;
        PriorityQueue<int[]> q = new PriorityQueue<>((x, y) -> Integer.compare(x[0], y[0]));// d, i, j
        q.offer(new int[]{0, 0, 0});
        int[][] dist = new int[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }
        dist[0][0] = 0;
        boolean[][] done = new boolean[m][n];
        while (!q.isEmpty()) {
            int[] top = q.poll();
            int i = top[1];
            int j = top[2];
            if (done[i][j]) {
                continue;
            }
            if (i == m - 1 && j == n - 1) {
                break;
            }
            done[i][j] = true;
            for (int[] d : dirs) {
                int ni = i + d[0];
                int nj = j + d[1];
                int ndist = 0;
                if (ni >= 0 && ni < m && nj >= 0 && nj < n && dist[ni][nj] > (ndist = Math.max(top[0], Math.abs(a[ni][nj] - a[i][j])))) {
                    dist[ni][nj] = ndist;
                    q.offer(new int[]{ndist, ni, nj});
                }
            }
        }
        return dist[m - 1][n - 1];
    }
}
