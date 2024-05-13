import java.util.List;

public class MaxDiffScoreInGrid {
    // actually can be simplied to ck - c1
    public int maxScore(List<List<Integer>> g) {
        int m = g.size();
        int n = g.get(0).size();
        int[][] a = new int[m][n];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                a[i][j] = g.get(i).get(j);
            }
        }
        int[] maxr = new int[m];
        int[] maxc = new int[n];
        int[][] dp = new int[m][n];
        int[][] res = new int[m][n];
        for (int i = m - 1; i >= 0; --i) {
            for (int j = n - 1; j >= 0; --j) {
                if (i == m - 1 && j == n - 1) {
                    dp[i][j] = 0;
                    res[i][j] = (int) -1e9;
                } else if (i == m - 1) {
                    int way1 = maxr[i] - a[i][j];
                    dp[i][j] = Math.max(way1, 0);
                    res[i][j] = way1;
                } else if (j == n - 1) {
                    int way1 = maxc[j] - a[i][j];
                    dp[i][j] = Math.max(way1, 0);
                    res[i][j] = way1;
                } else {
                    int way1 = maxr[i] - a[i][j];
                    int way2 = maxc[j] - a[i][j];
                    int cur = Math.max(way1, way2);
                    dp[i][j] = Math.max(cur, 0);
                    res[i][j] = cur;
                }
                maxr[i] = Math.max(maxr[i], a[i][j] + dp[i][j]);
                maxc[j] = Math.max(maxc[j], a[i][j] + dp[i][j]);
            }
        }
        int rt = (int) -1e9;
        for (int i = m - 1; i >= 0; --i) {
            for (int j = n - 1; j >= 0; --j) {
                rt = Math.max(rt, res[i][j]);
            }
        }
        return rt;
    }
}
