public class CountUnhappyFriends {
    public int unhappyFriends(int n, int[][] prefs, int[][] pairs) {
        int[][] ps = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - 1; j++) {
                int v = prefs[i][j];
                ps[i][v] = j;
            }
        }
        int[] pm = new int[n];
        for (int i = 0; i < pairs.length; i++) {
            int v1 = pairs[i][0];
            int v2 = pairs[i][1];
            pm[v1] = v2;
            pm[v2] = v1;
        }
        int res = 0;
        for (int i = 0; i < n; i++) {
            int x = i;
            int y = pm[i];
            for (int u = 0; u < n; u++) {
                int v = pm[u];
                if (u == x || u == y || v == x || v == y) {
                    continue;
                }
                if (ps[x][u] < ps[x][y] && ps[u][x] < ps[u][v]) {
                    res++;
                    break;
                }
            }
        }
        return res;
    }
}
