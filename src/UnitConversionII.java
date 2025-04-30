import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class UnitConversionII {
    private Map<Integer, Long>[] g;

    private long Mod = (long) (1e9 + 7);
    private long[] conv;

    public int[] queryConversions(int[][] es, int[][] qs) {
        int n = es.length + 1;
        g = new HashMap[n];
        conv = new long[n];
        Arrays.fill(conv, -1);
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
        int qn = qs.length;
        int[] res = new int[qn];
        int ri = 0;
        for (int qi = 0; qi < qn; ++qi) {
            int v1 = qs[qi][0];
            int v2 = qs[qi][1];
            long cv1 = conv[v1];
            long cv2 = conv[v2];
            long cur = modinverse(cv1) * cv2;
            cur %= Mod;
            res[ri++] = (int) cur;
        }
        return res;
    }

    private void dfs(int i, long cd) {
        conv[i] = cd;
        for (int ne : g[i].keySet()) {
            if (conv[ne] != -1) {
                continue;
            }
            long cw = g[i].get(ne);
            long nd = cw * cd;
            nd %= Mod;
            dfs(ne, nd);
        }
    }

    private long modinverse(long a) {
        long m = Mod;
        long y = 0;
        long x = 1;
        while (a > 1) {
            long q = a / m;
            long t = m;
            m = a % m;
            a = t;
            t = y;
            y = x - q * y;
            x = t;
        }
        if (x < 0) {
            x += Mod;
        }
        return x;
    }
}
