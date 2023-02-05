public class DisconnectMatrixByAtmostOneFlip {
    public boolean isPossibleToCutPath(int[][] a) {
        int m = a.length;
        int n = a[0].length;
        return !dfs(a, 0, 0) || !dfs(a, 0, 0);
    }

    private boolean dfs(int[][] a, int i, int j) {
        int m = a.length;
        int n = a[0].length;
        if (i >= m || j >= n || a[i][j] == 0) {
            return false;
        }
        if (i == m - 1 && j == n - 1) {
            return true;
        }
        if (!(i == 0 && j == 0)) {
            a[i][j] = 0;
        }
        return dfs(a, i + 1, j) || dfs(a, i, j + 1);
    }
}
