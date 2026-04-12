public class FindDegreeOfEachVertex {
    public int[] findDegrees(int[][] a) {
        int m = a.length;
        int n = a[0].length;
        int[] deg = new int[m];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (a[i][j] == 1) {
                    ++deg[i];
                    ++deg[j];
                }
            }
        }
        for (int i = 0; i < m; ++i) {
            deg[i] /= 2;
        }
        return deg;
    }
}
