import java.util.Arrays;

public class NumberOfPossibleSetOfClosingBranches {
    // mask + floyd
    private int[][] g;
    private int Max = (int) 1e9;

    public int numberOfSets(int n, int maxDistance, int[][] roads) {
        g = new int[n][n];
        for (int i = 0; i < n; ++i) {
            Arrays.fill(g[i], Max);
            g[i][i] = 0;
        }
        for (int[] r : roads) {
            int u = r[0];
            int v = r[1];
            int w = r[2];
            int minw = Math.min(g[u][v], w);
            g[u][v] = minw;
            g[v][u] = minw;
        }
        int res = 0;
        for (int st = 0; st < (1 << n); ++st) {
            int[][] ng = new int[n][n];
            for (int i = 0; i < n; ++i) {
                ng[i] = Arrays.copyOf(g[i], n);
            }
            for (int i = 0; i < n; ++i) {
                if (contains(st, i)) {
                    for (int j = 0; j < n; ++j) {
                        ng[i][j] = Max;
                        ng[j][i] = Max;
                    }
                }
            }
            boolean good = floyd(ng, maxDistance, st);
            if (good) {
                ++res;
            }
        }
        return res;
    }

    private boolean floyd(int[][] g, int maxDist, int st) {
        int n = g.length;
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; ++i) {
            Arrays.fill(dp[i], Max);
            dp[i][i] = 0;
        }
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (i == j || g[i][j] >= Max) {
                    continue;
                }
                dp[i][j] = g[i][j];
            }
        }
        for (int k = 0; k < n; ++k) {
            for (int i = 0; i < n; ++i) {
                for (int j = 0; j < n; ++j) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k][j]);
                }
            }
        }
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (contains(st, i) || contains(st, j)) {
                    continue;
                }
                if (dp[i][j] > maxDist) {
                    return false;
                }
            }
        }
        return true;
    }


    private boolean contains(int st, int i) {
        return (((st >> i) & 1) == 1);
    }
}
