import java.util.ArrayDeque;
import java.util.Deque;

public class CountIslandsTotalValueDivisibleByK {
    private boolean[][] seen;

    public int countIslands(int[][] a, int k) {
        int m = a.length;
        int n = a[0].length;
        int res = 0;
        seen = new boolean[m][n];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (seen[i][j] || a[i][j] <= 0) {
                    continue;
                }
                long sum = bfs(a, i, j);
                if (sum % k == 0) {
                    ++res;
                }
            }
        }
        return res;
    }

    private int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    private long bfs(int[][] a, int i, int j) {
        int m = a.length;
        int n = a[0].length;

        Deque<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{i, j});
        seen[i][j] = true;
        long res = 0;
        while (!q.isEmpty()) {
            int[] top = q.poll();
            int r = top[0];
            int c = top[1];
            res += a[r][c];
            for (int[] d : dirs) {
                int nr = r + d[0];
                int nc = c + d[1];
                if (nr >= 0 && nr < m && nc >= 0 && nc < n && a[nr][nc] > 0 && !seen[nr][nc]) {
                    seen[nr][nc] = true;
                    q.offer(new int[]{nr, nc});
                }
            }
        }
        return res;
    }
}
