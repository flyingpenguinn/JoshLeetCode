import java.util.ArrayDeque;
import java.util.Deque;

public class CountUnguardedCells {
    // each position is with a state. the status must be counted using the state too
    private int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public int countUnguarded(int m, int n, int[][] guards, int[][] walls) {
        Deque<int[]> q = new ArrayDeque<>();
        boolean[][][] seen = new boolean[m][n][4]; // seen must also include the direction!
        for (int[] g : guards) {
            for (int j = 0; j < 4; ++j) {
                q.offer(new int[]{g[0], g[1], j});
                seen[g[0]][g[1]][j] = true;
            }

        }
        boolean[][] iswall = new boolean[m][n];
        for (int[] w : walls) {
            iswall[w[0]][w[1]] = true;
        }
        while (!q.isEmpty()) {
            int[] top = q.poll();
            int i = top[0];
            int j = top[1];
            int d = top[2];
            int ni = i + dirs[d][0];
            int nj = j + dirs[d][1];
            if (ni >= 0 && ni < m && nj >= 0 && nj < n) {
                if (!iswall[ni][nj] && !seen[ni][nj][d]) {
                    seen[ni][nj][d] = true;
                    q.offer(new int[]{ni, nj, d});
                }
            }

        }
        int bad = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                for (int k = 0; k < 4; ++k) {
                    if (seen[i][j][k] || iswall[i][j]) {
                        ++bad;
                        break;
                    }
                }
            }
        }
        return m * n - bad;
    }
}
