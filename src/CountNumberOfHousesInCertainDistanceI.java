import java.util.Arrays;

public class CountNumberOfHousesInCertainDistanceI {
    private int[][] g;
    private int[][] dist;
    private int MAX = (int) 1e9;

    public int[] countOfPairs(int n, int x, int y) {
        g = new int[n + 1][n + 1];
        for (int i = 1; i <= n; ++i) {
            Arrays.fill(g[i], MAX);
        }
        dist = new int[n + 1][n + 1];
        for (int i = 1; i + 1 <= n; ++i) {
            g[i][i + 1] = 1;
            g[i + 1][i] = 1;
        }
        g[x][y] = 1;
        g[y][x] = 1;
        floyd();
        int[] res = new int[n];
        for (int i = 1; i <= n; ++i) {
            for (int j = 1; j <= n; ++j) {
                if (i == j) {
                    continue;
                }
                int cur = dist[i][j];

                //  System.out.println(i+" "+j+" "+cur);
                ++res[cur - 1];
            }
        }
        return res;
    }

    private void floyd() {
        int n = g.length - 1;
        for (int i = 1; i <= n; ++i) {
            dist[i][i] = 0;
        }
        for (int i = 1; i <= n; ++i) {
            for (int j = 1; j <= n; ++j) {
                dist[i][j] = g[i][j];
            }
        }
        for (int k = 1; k <= n; ++k) {
            for (int i = 1; i <= n; ++i) {
                for (int j = 1; j <= n; ++j) {
                    if (dist[i][j] > dist[i][k] + dist[k][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }
    }
}
