import java.util.ArrayList;
import java.util.List;

public class MinimizeTotalPriceOfTrips {
    private List<Integer>[] g;
    private int[] meets;
    private List<Integer> goodresult = new ArrayList<>();
    private final int MAX = (int) 1e9;
    private int[] price;
    private Integer[][][] dp;

    public int minimumTotalPrice(int n, int[][] edges, int[] price, int[][] trips) {
        g = new ArrayList[n];
        meets = new int[n];

        dp = new Integer[n][n + 1][2];
        this.price = price;
        for (int i = 0; i < n; ++i) {
            g[i] = new ArrayList<>();
        }
        for (int[] e : edges) {
            int v1 = e[0];
            int v2 = e[1];
            g[v1].add(v2);
            g[v2].add(v1);
        }
        for (int[] t : trips) {
            List<Integer> nodes = new ArrayList<>();
            int s = t[0];
            int e = t[1];
            goodresult.clear();
            dfs(s, e, n, nodes);
            for (int ni : goodresult) {
                ++meets[ni];
            }
        }
        int res = (int) 1e9;
        for (int i = 0; i < n; ++i) {

            int cur = dfssolve(0, n, 1);
            res = Math.min(cur, res);
        }
        return res;
    }

    // j: whether we can half or not
    private int dfssolve(int i, int p, int j) {
        if (dp[i][p][j] != null) {
            return dp[i][p][j];
        }
        int curprice = price[i] * meets[i];
        int way1 = curprice; // do not half this
        int later = 0;
        for (int ne : g[i]) {
            if (ne == p) {
                continue;
            }
            later += dfssolve(ne, i, 1);
        }
        way1 += later;
        int way2 = MAX;
        if (j == 1) {
            way2 = curprice / 2;
            later = 0;
            for (int ne : g[i]) {
                if (ne == p) {
                    continue;
                }
                later += dfssolve(ne, i, 0);
            }
            way2 += later;
        }
        int res = Math.min(way1, way2);
        dp[i][p][j] = res;
        return res;
    }

    private void dfs(int cur, int e, int p, List<Integer> nodes) {
        nodes.add((cur));
        if (cur == e) {
            goodresult = new ArrayList<>(nodes);
            return;
        }
        for (int ne : g[cur]) {
            if (ne == p) {
                continue;
            }
            dfs(ne, e, cur, nodes);
        }
        nodes.remove(nodes.size() - 1);
    }
}
