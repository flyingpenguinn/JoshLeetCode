import java.util.ArrayList;
import java.util.List;

public class MinOperationsWriteYInGrid {
    public int minimumOperationsToWriteY(int[][] a) {
        int n = a.length;
        List<Integer> ys = new ArrayList<>();
        List<Integer> nys = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (i == j && i <= n / 2) {
                    ys.add(a[i][j]);
                } else if (i + j == n - 1 && i <= n / 2) {
                    ys.add(a[i][j]);
                } else if (j == n / 2 && i >= n / 2) {
                    ys.add(a[i][j]);
                } else {
                    nys.add(a[i][j]);
                }
            }
        }
        int res = (int) 1e9;
        for (int i = 0; i <= 2; ++i) {
            for (int j = 0; j <= 2; ++j) {
                if (i == j) {
                    continue;
                }
                int changed1 = find(ys, i);
                int changed2 = find(nys, j);
                int cur = changed1 + changed2;
                res = Math.min(res, cur);
            }
        }
        return res;
    }

    private int find(List<Integer> v, int t) {
        int res = 0;
        for (int vi : v) {
            if (vi != t) {
                ++res;
            }
        }
        return res;
    }

}
