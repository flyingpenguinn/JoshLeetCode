import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class EqualSumGridPartitionI {


    public boolean canPartitionGrid(int[][] ia) {
        int m = ia.length;
        int n = ia[0].length;
        if (m == 1 && n == 1) {
            return false;
        }
        long[][] a = new long[m][n];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                a[i][j] = ia[i][j];
            }
        }

        long[] cols = new long[n];
        long allsum = 0;

        for (int j = 0; j < n; ++j) {
            long csum = 0;
            for (int i = 0; i < m; ++i) {
                csum += a[i][j];
            }
            cols[j] = csum;
            allsum += csum;

        }
        long leftsum = 0;

        for (int j = 0; j < n; ++j) {
            long rightsum = allsum - leftsum;
            if (leftsum == rightsum) {
                return true;
            }
            leftsum += cols[j];
        }

        // rows
        long[] rows = new long[m];
        for (int i = 0; i < m; ++i) {
            long rsum = 0;
            for (int j = 0; j < n; ++j) {
                rsum += a[i][j];
            }
            rows[i] = rsum;
        }
        leftsum = 0;

        for (int i = 0; i < m; ++i) {
            long rightsum = allsum - leftsum;
            if (leftsum == rightsum) {
                return true;
            }

            leftsum += rows[i];
        }
        return false;
    }
}
