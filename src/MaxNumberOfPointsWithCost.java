import base.ArrayUtils;

import java.util.Map;
import java.util.TreeMap;

public class MaxNumberOfPointsWithCost {
    // on each row as we go to the right or go to the left, we "enable" more values that can compete the max value
    private int Max = 1000000000;
    private int Min = -Max;

    public long maxPoints(int[][] a) {
        int m = a.length;
        int n = a[0].length;
        long[][] dp = new long[m][n];
        for (int j = 0; j < n; j++) {
            dp[0][j] = a[0][j];
        }
        for (int i = 1; i < m; i++) {
            long maxleft = Min;
            // dp[i][p]-j+p if p<=j
            for (int j = 0; j < n; j++) {
                maxleft = Math.max(maxleft, dp[i - 1][j] + j);
                dp[i][j] = Math.max(dp[i][j], maxleft - j + a[i][j]);
            }
            long maxright = Min;
            for (int j = n - 1; j >= 0; j--) {
                maxright = Math.max(maxright, dp[i - 1][j] - j);
                dp[i][j] = Math.max(dp[i][j], maxright + j + a[i][j]);
            }
        }
        long rt = Min;
        for (int j = 0; j < n; j++) {
            rt = Math.max(rt, dp[m - 1][j]);
        }
        return rt;
    }

    public static void main(String[] args) {
        System.out.println(new MinNumberOfPointsWithCostTreeMap().maxPoints(ArrayUtils.read("[[1,2,3],[1,5,1],[3,1,1]]")));
    }
}


class MinNumberOfPointsWithCostTreeMap {
    // as we move to right we get the max out of remaining right side elements in the last row and max out of growing left side of the last row
    private int Max = 1000000000;
    private int Min = -Max;

    public long maxPoints(int[][] a) {
        int m = a.length;
        int n = a[0].length;
        long[][] dp = new long[m][n];
        TreeMap<Long, Integer> m2 = new TreeMap<>();
        for (int j = 0; j < n; j++) {
            dp[0][j] = a[0][j];
            update(m2, dp[0][j] - j, 1);
        }
        for (int i = 1; i < m; i++) {
            TreeMap<Long, Integer> m1 = new TreeMap<>();
            TreeMap<Long, Integer> nm2 = new TreeMap<>();
            // dp[i][p]-j+p if p<=j
            for (int j = 0; j < n; j++) {
                update(m2, dp[i - 1][j] - j, -1);
                update(m1, dp[i - 1][j] + j, 1);
                long cur = 0;
                if (!m1.isEmpty() && !m2.isEmpty()) {
                    long maxm2 = m2.lastKey();
                    long maxm1 = m1.lastKey();
                    cur = Math.max(maxm2 + j + a[i][j], maxm1 - j + a[i][j]);

                } else if (m1.isEmpty()) {
                    long maxm2 = m2.lastKey();
                    cur = maxm2 + j + a[i][j];
                } else {
                    long maxm1 = m1.lastKey();
                    cur = maxm1 - j + a[i][j];
                }
                dp[i][j] = cur;
                update(nm2, dp[i][j] - j, 1);
            }
            m2 = nm2;
        }
        long rt = Min;
        for (int j = 0; j < n; j++) {
            rt = Math.max(rt, dp[m - 1][j]);
        }
        return rt;
    }

    private void update(Map<Long, Integer> m, long k, int d) {
        int nv = m.getOrDefault(k, 0) + d;
        if (nv <= 0) {
            m.remove(k);
        } else {
            m.put(k, nv);
        }
    }

}