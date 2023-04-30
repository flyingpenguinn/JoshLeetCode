public class MaxNumFishInGrid {
    public int findMaxFish(int[][] g) {
        int m = g.length;
        int n = g[0].length;
        int res = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (g[i][j] > 0) {
                    int cur = dfs(g, i, j);
                    res = Math.max(res, cur);
                }
            }
        }
        return res;
    }

    private int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    private int dfs(int[][] g, int i, int j) {
        int m = g.length;
        int n = g[0].length;
        int cur = g[i][j];
        g[i][j] = -1;
        for (int[] d : dirs) {
            int ni = i + d[0];
            int nj = j + d[1];
            if (ni >= 0 && nj >= 0 && ni < m && nj < n && g[ni][nj] > 0) {
                int later = dfs(g, ni, nj);
                cur += later;
            }
        }
        return cur;
    }
}
