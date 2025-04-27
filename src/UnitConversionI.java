import java.util.HashMap;
import java.util.Map;

public class UnitConversionI {
    private Map<Integer, Long>[] g;
    private int[] res;
    private long Mod = (long) (1e9 + 7);

    public int[] baseUnitConversions(int[][] es) {
        int n = es.length + 1;
        res = new int[n];
        g = new HashMap[n];
        for (int i = 0; i < n; ++i) {
            g[i] = new HashMap<>();
        }
        for (int[] e : es) {
            int v1 = e[0];
            int v2 = e[1];
            long w = e[2];
            g[v1].put(v2, w);
        }
        dfs(0, 1);
        return res;
    }

    private void dfs(int i, long cd) {
        res[i] = (int) cd;
        for (int ne : g[i].keySet()) {
            long cw = g[i].get(ne);
            long nd = cw * cd;
            nd %= Mod;
            dfs(ne, nd);
        }
    }
}
