public class ModifyTheMatrix {
    public int[][] modifiedMatrix(int[][] a) {
        int m = a.length;
        int n = a[0].length;
        for (int j = 0; j < n; ++j) {
            int max = -2;
            for (int i = 0; i < m; ++i) {
                max = Math.max(max, a[i][j]);
            }
            for (int i = 0; i < m; ++i) {
                if (a[i][j] == -1) {
                    a[i][j] = max;
                }
            }
        }
        return a;
    }
}
