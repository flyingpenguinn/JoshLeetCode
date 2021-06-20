public class CountSubIslands {
    // DO NOT STOP when g1 != g2. we need to carry through for this "bad" g2 island
    private int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public int countSubIslands(int[][] g1, int[][] g2) {
        int m = g1.length;
        int n = g1[0].length;
        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (g2[i][j] == 1 && g1[i][j] == 1) {
                    if (dfs(g1, g2, i, j)) {
                        res++;
                    }
                }
            }
        }
        return res;
    }

    private boolean dfs(int[][] g1, int[][] g2, int i, int j) {
        g2[i][j] = 2;
        g1[i][j] = 2;
        int m = g1.length;
        int n = g1[0].length;
        boolean res = true;
        for (int[] d : dirs) {
            int ni = i + d[0];
            int nj = j + d[1];
            if (ni >= 0 && ni < m && nj >= 0 && nj < n) {
                if (g2[ni][nj] == 1 && g1[ni][nj] == 1) {
                    boolean later = dfs(g1, g2, ni, nj);
                    if (!later) {
                        res = false;
                    }
                } else if (g2[ni][nj] == 1) {
                    // bad g1
                    res = false;
                    dfs(g1, g2, ni, nj);
                }
            }
        }
        return res;
    }
}
