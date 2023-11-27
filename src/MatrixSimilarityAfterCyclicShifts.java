import java.util.Arrays;

public class MatrixSimilarityAfterCyclicShifts {
    public boolean areSimilar(int[][] a, int x) {
        int m = a.length;
        int n = a[0].length;
        int[][] na = new int[m][n];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (i % 2 == 1) {
                    int nj = (j + x) % n;
                    na[i][nj] = a[i][j];
                } else {
                    int nj = (j - x) % n;
                    if (nj < 0) {
                        nj += n;
                    }
                    na[i][nj] = a[i][j];
                }

            }
        }
        return Arrays.deepEquals(na, a);
    }
}
