public class MinNumberOfDaysToDisconnectIslands {
    // if not 0 then 1 or 2. 1 is easy to check. could have used union find
    private boolean single(int[][] a) {
        int comp = 0;
        int m = a.length;
        int n = a[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (a[i][j] == 1) {
                    if (comp == 1) {
                        return false;
                    }
                    dfs(a, i, j);
                    comp++;
                }
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (a[i][j] == 2) {
                    a[i][j] = 1;
                }
            }
        }
        return true;
    }

    public int minDays(int[][] a) {
        int m = a.length;
        int n = a[0].length;
        if (!single(a)) {
            return 0;
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (a[i][j] == 1) {
                    a[i][j] = 0;
                    if (!single(a)) {
                        return 1;
                    }
                    a[i][j] = 1;
                }
            }
        }
        return 2;
    }

    private int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    private void dfs(int[][] a, int i, int j) {
        a[i][j] = 2;
        for (int[] d : dirs) {
            int ni = i + d[0];
            int nj = j + d[1];
            if (ni >= 0 && ni < a.length && nj >= 0 && nj < a[0].length && a[ni][nj] == 1) {
                dfs(a, ni, nj);
            }
        }
    }
}
