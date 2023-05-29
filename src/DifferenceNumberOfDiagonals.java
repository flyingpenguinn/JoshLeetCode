import java.util.HashSet;
import java.util.Set;

public class DifferenceNumberOfDiagonals {
    public int[][] differenceOfDistinctValues(int[][] a) {
        int m = a.length;
        int n = a[0].length;
        int[][] res = new int[m][n];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                int k = i - 1;
                int p = j - 1;
                Set<Integer> set = new HashSet<>();
                while (k >= 0 && p >= 0) {
                    set.add(a[k][p]);
                    --k;
                    --p;
                }
                int tl = set.size();
                k = i + 1;
                p = j + 1;
                set.clear();
                while (k < m && p < n) {
                    set.add(a[k][p]);
                    ++k;
                    ++p;
                }
                int br = set.size();
                res[i][j] = Math.abs(tl - br);
            }
        }
        return res;
    }
}
