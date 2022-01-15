import base.ArrayUtils;

public class StampingTheGrid {
    // we can stamp an area if it's all 0
    // how do we know whether we can stamp a point? there is a "stampable point in the range

    // we are really operating on i+1 and j+1 of the prefix sum
    private int[][] pref2d(int[][] v, int m, int n) {
        int[][] res = new int[m + 1][n + 1];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                res[i + 1][j + 1] = res[i + 1][j] + res[i][j + 1] - res[i][j] + v[i][j];
            }
        }
        return res;
    }

    private int sum2d(int[][] sum, int c1, int r1, int c2, int r2) {
        int cur = sum[c2 + 1][r2 + 1] + sum[c1][r1] - sum[c1][r2 + 1] - sum[c2 + 1][r1];
        return cur;
    }

    public boolean possibleToStamp(int[][] g, int h, int w) {
        int m = g.length;
        int n = g[0].length;
        int[][] stamp = new int[m][n];
        int[][] pref = pref2d(g, m, n);
        for (int i = h - 1; i < m; ++i) {
            for (int j = w - 1; j < n; ++j) {
                // right corner
                int subsum = sum2d(pref, i - h + 1, j - w + 1, i, j);
                int cur = (subsum == 0 ? 1 : 0);
                stamp[i][j] = cur;
            }
        }
        int[][] pref2 = pref2d(stamp, m, n);
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (g[i][j] == 0) {
                    int sumd = sum2d(pref2, i, j, Math.min(m - 1, i + h - 1), Math.min(n - 1, j + w - 1));
                    boolean doable = sumd != 0;
                    if (!doable) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        //  System.out.println(new StampingTheGrid().possibleToStamp(ArrayUtils.read("[[1,1,1],[1,1,1],[1,1,1]]"), 2, 1));
        System.out.println(new StampingTheGrid().possibleToStamp(ArrayUtils.read("\n" +
                "[[1,1,1,1,1,0],[1,0,0,1,0,0],[1,1,0,1,1,0],[1,0,0,1,0,0],[1,0,1,0,1,0],[1,1,1,0,1,1],[0,0,1,0,0,0],[0,0,1,1,0,0]]"), 5, 1));
    }
}
