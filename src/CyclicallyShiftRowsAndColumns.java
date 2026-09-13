public class CyclicallyShiftRowsAndColumns {
    public int[][] cyclicShift(int n, int[][] a, int[] rs, int[] cs) {
        for (int i = 0; i < n; ++i) {
            int shifts = rs[i];
            rowshift(a, i, shifts);
        }
        for(int j=0; j<n; ++j){
            int shifts = cs[j];
            colshift(a, j, shifts);
        }
        return a;
    }

    private void rowshift(int[][] a, int row, int k) {
        int n = a.length;
        int[] newrow = new int[n];
        for (int j = 0; j < n; ++j) {
            newrow[(j - k + n) % n] = a[row][j];
        }
        for (int j = 0; j < n; ++j) {
            a[row][j] = newrow[j];
        }
    }

    private void colshift(int[][] a, int col, int k) {
        int n = a.length;
        int[] newcol = new int[n];
        for (int i = 0; i < n; ++i) {
            newcol[(i - k + n) % n] = a[i][col];
        }
        for (int i = 0; i < n; ++i) {
            a[i][col] = newcol[i];
        }
    }
}
