public class MinOperationsToMakeMountainArrayI {
    // LC 4118 (biweekly virtual, not solved in contest; written after review 2026-11-22).
    // Dp on value: dp1[i][v] = min cost to make a[0..i] strictly increasing positive with a[i] = v;
    // transition min over u < v is a running prefix min. dp2 is the same thing from the right (the decreasing side
    // read right-to-left is increasing, so it is scanned in the same value direction). Combine at the peak and
    // subtract |a[i] - v| once since both sides counted it. Values never need to exceed max(a) + n <= 2000.
    // Review bugs: dp2 first scanned values downward (u > v), dp2[i - 1] instead of dp2[i + 1], sentinel only on the last row.
    public int minOperations(int[] a) {
        int n = a.length;
        int limit = 2001;
        int[][] dp1 = new int[n][limit + 1];
        int[][] dp2 = new int[n][limit + 1];
        for (int j = 1; j < limit; ++j) {
            dp1[0][j] = Math.abs(a[0] - j);
            dp2[n - 1][j] = Math.abs(a[n - 1] - j);
        }
        int Max = (int) 1e9;
        for (int i = 0; i < n; ++i) {
            dp1[i][0] = Max;
            dp2[i][0] = Max;
        }

        for (int i = 1; i < n; ++i) {
            int best0 = dp1[i - 1][0];
            for (int j = 1; j < limit; ++j) {
                dp1[i][j] = best0 + Math.abs(a[i] - j);
                best0 = Math.min(best0, (dp1[i - 1][j]));
            }

        }
        for (int i = n - 2; i >= 1; --i) {
            int best1 = dp2[i + 1][0];
            for (int j = 1; j <limit; ++j) {
                dp2[i][j] = best1 + Math.abs(a[i] - j);
                best1 = Math.min(best1, (dp2[i + 1][j]));
            }
        }
        int res = Max;
        for (int i = 1; i < n - 1; ++i) {
            for (int j = 1; j < limit; ++j) {
                int v1 = dp1[i][j];
                int v2 = dp2[i][j];
                int cur = v1+v2 - Math.abs(a[i]-j);
                res = Math.min(res, cur);
            }
        }
        return res>=Max? -1: res;
    }
}
