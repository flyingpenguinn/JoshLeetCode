import java.util.Arrays;

public class TheKnightsTour {
    private int[][] dirs = {{2, 1}, {-2, 1}, {-2, -1}, {2, -1}, {1, 2}, {-1, 2}, {1, -2}, {-1, -2}};

    public int[][] tourOfKnight(int m, int n, int r, int c) {
        int[][] a = new int[m][n];
        for (int i = 0; i < m; ++i) {
            Arrays.fill(a[i], -1);
        }
        dfs(a, r, c, 0);
        return a;
    }

    private boolean dfs(int[][] a, int i, int j, int steps) {
        int m = a.length;
        int n = a[0].length;
        a[i][j] = steps;
        if (steps + 1 == m * n) {
            return true;
        }
        for (int[] d : dirs) {
            int ni = i + d[0];
            int nj = j + d[1];

            if (ni >= 0 && ni < m && nj >= 0 && nj < n) {
                if (a[ni][nj] < 0) {
                    boolean later = dfs(a, ni, nj, steps + 1);
                    if (later) {
                        return true;
                    }
                }
            }
        }
        a[i][j] = -1;
        return false;
    }
}
