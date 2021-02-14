public class MinDegreeOfConnectedTrioInGraph {
    // force the thrid in the trio to be > the other 2 to avoid duplicates
    public int minTrioDegree(int n, int[][] es) {
        boolean[][] g = new boolean[n + 1][n + 1];
        int[] deg = new int[n + 1];
        for (int[] e : es) {
            int s = e[0];
            int t = e[1];
            g[s][t] = true;
            g[t][s] = true;
            deg[s]++;
            deg[t]++;
        }
        int res = Integer.MAX_VALUE;
        for (int[] e : es) {
            int s = e[0];
            int t = e[1];
            // start from max(s,t) to avoid duplicated calc
            for (int i = Math.max(s, t); i <= n; i++) {
                if (g[s][i] && g[t][i]) {
                    // trio is s, e, i
                    int cur = deg[s] - 2 + deg[t] - 2 + deg[i] - 2;
                    res = Math.min(res, cur);
                }
            }
        }
        return res >= Integer.MAX_VALUE ? -1 : res;
    }
}
