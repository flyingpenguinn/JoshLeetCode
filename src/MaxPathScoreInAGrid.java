import java.util.Arrays;

public class MaxPathScoreInAGrid {
    private int Min = (int) -2e9;

    public int maxPathScore(int[][] a, int cost) {
        int m = a.length;
        int n = a[0].length;
        cost = Math.min(cost, m + n);
        int[][] last = new int[n][cost + 1];
        init(m, n, last);

        for (int i = m - 1; i >= 0; --i) {
            int[][] cur = new int[n][cost + 1];
            init(m, n, cur);
            for (int j = n - 1; j >= 0; --j) {
                int v = a[i][j];
                int ccost = a[i][j] == 0 ? 0 : 1;
                for (int k = 0; k <= cost; ++k) {
                    int ck = k - ccost;
                    if (ck < 0) {
                        continue;
                    }
                    if (i == m - 1 && j == n - 1) {
                        cur[j][k] = v;
                    } else if (i == m - 1) {
                        cur[j][k] = v + cur[j + 1][ck];
                    } else if (j == n - 1) {
                        cur[j][k] = v + last[j][ck];
                    } else {
                        int way1 = cur[j + 1][ck];
                        int way2 = last[j][ck];
                        int cway = Math.max(way1, way2);
                        cur[j][k] = v + cway;
                    }
                }
            }
            last = cur;
        }
        int rt = Min;
        for (int j = 0; j <= cost; ++j) {
            rt = Math.max(rt, last[0][j]);
        }
        return rt < 0 ? -1 : rt;
    }

    protected void init(int m, int n, int[][] last) {
        for (int j = 0; j < n; ++j) {
            Arrays.fill(last[j], Min);
        }
    }
}
