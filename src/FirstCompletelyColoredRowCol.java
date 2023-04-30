public class FirstCompletelyColoredRowCol {
    public int firstCompleteIndex(int[] a, int[][] g) {
        int m = g.length;
        int n = g[0].length;
        int[][] pos = new int[m * n + 1][2];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                pos[g[i][j]] = new int[]{i, j};
            }
        }
        int[] rows = new int[m];
        int[] cols = new int[n];
        for (int i = 0; i < a.length; ++i) {
            int v = a[i];
            int row = pos[v][0];
            int col = pos[v][1];
            ++rows[row];
            ++cols[col];
            if (rows[row] == n || cols[col] == m) {
                return i;
            }
        }
        return -1;
    }
}
