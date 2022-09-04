public class MaxRowsCoveredByColumns {
    public int maximumRows(int[][] a, int cols) {
        int m = a.length;
        int n = a[0].length;
        int res = 0;
        for (int st = 0; st < (1 << n); ++st) {
            int bits = Integer.bitCount(st);
            if (bits != cols) {
                continue;
            }
            int rows = 0;
            for (int i = 0; i < m; ++i) {
                boolean good = true;
                for (int j = 0; j < n; ++j) {
                    if (a[i][j] == 0) {
                        continue;
                    }
                    if (((st >> j) & 1) == 0) {
                        //  System.out.println(st+" not covering row "+i+" due to column "+j);
                        good = false;
                        break;
                    }
                }
                if (good) {
                    ++rows;
                }
            }
            res = Math.max(res, rows);
        }
        return res;
    }
}
