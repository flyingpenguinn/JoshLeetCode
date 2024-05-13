public class CheckIfGridSatisfiesConditions {
    public boolean satisfiesConditions(int[][] a) {
        int m = a.length;
        int n = a[0].length;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (i + 1 < m && a[i][j] != a[i + 1][j]) {
                    return false;
                }
                if (j + 1 < n && a[i][j] == a[i][j + 1]) {
                    return false;
                }
            }
        }
        return true;
    }
}
