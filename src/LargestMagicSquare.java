import java.util.HashSet;
import java.util.Set;

public class LargestMagicSquare {
    private int[][] rows;
    private int[][] cols;

    public int largestMagicSquare(int[][] a) {
        int m = a.length;
        int n = a[0].length;
        rows = new int[m][n];
        cols = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                rows[i][j] = (i == 0 ? 0 : rows[i - 1][j]) + a[i][j];
                cols[i][j] = (j == 0 ? 0 : cols[i][j - 1]) + a[i][j];
            }
        }
        int res = 1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = Math.min(i + 1, j + 1); k >= 2; k--) {
                    if (isMagic(a, i, j, k)) {
                        res = Math.max(res, k);
                        break;
                    }
                }
            }
        }
        return res;
    }


    private boolean isMagic(int[][] a, int i, int j, int k) {
        int m = a.length;
        int n = a[0].length;
        int i0 = i - k + 1;
        int j0 = j - k + 1;
        Set<Integer> sums = new HashSet<>();
        for (int r = i0; r <= i; r++) {
            int rowsum = cols[r][j] - (j0 == 0 ? 0 : cols[r][j0 - 1]);
            sums.add(rowsum);
            if (sums.size() > 1) {
                return false;
            }
        }
        for (int c = j0; c <= j; c++) {
            int colsum = rows[i][c] - (i0 == 0 ? 0 : rows[i0 - 1][c]);
            sums.add(colsum);
            if (sums.size() > 1) {
                return false;
            }
        }
        int dsum = 0;
        for (int p = 0; p < k; p++) {
            dsum += a[i - p][j - p];
        }
        sums.add(dsum);
        if (sums.size() > 1) {
            return false;
        }
        int rdsum = 0;
        for (int p = 0; p < k; p++) {
            rdsum += a[i0 + p][j - p];
        }
        sums.add(rdsum);
        if (sums.size() > 1) {
            return false;
        }
        return true;
    }
}
