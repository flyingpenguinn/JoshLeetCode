import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MaxWeightKedgePath {
    // sparse map bssed dp!
    private Map<Integer, Integer>[] g;
    private Map<Integer, int[][]> dp = new HashMap<>();
    private int Min = (int) -1e9;


    public int maxWeight(int n, int[][] edges, int k, int t) {
        if (k == 0) {
            return 0;
        }
        g = new HashMap[n];
        for (int i = 0; i < n; ++i) {
            g[i] = new HashMap<>();
        }


        for (int[] e : edges) {
            int v1 = e[0];
            int v2 = e[1];
            int w = e[2];
            g[v1].put(v2, w);
        }
        int res = -1;
        for (int i = 0; i < n; ++i) {
            calc(i, k, t, 0, n, k);
            if (dp.containsKey(0)) {
                res = Math.max(res, dp.get(0)[i][k]);
            }
        }
        return res;
    }

    private int calc(int i, int k, int t, int csum, int n, int ok) {
        if (k == 0) {
            return 0;
        }
        if (dp.containsKey(csum)) {
            if (dp.get(csum)[i][k] != -2) {
                return dp.get(csum)[i][k];
            }
        }
        int res = Min;
        for (int ne : g[i].keySet()) {
            int cw = g[i].get(ne);
            if (csum + cw < t) {
                int later = cw + calc(ne, k - 1, t, csum + cw, n, ok);
                res = Math.max(res, later);
            }
        }
        if (!dp.containsKey(csum)) {
            int[][] dp2 = new int[n][ok + 1];
            for (int p = 0; p < n; ++p) {
                Arrays.fill(dp2[p], -2);
            }
            dp2[i][k] = res;
            dp.put(csum, dp2);
        } else {
            dp.get(csum)[i][k] = res;
        }
        return res;
    }
}
