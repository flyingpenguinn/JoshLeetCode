import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

public class MaximizeSumDeviceRatings {
    private void update(Map<Integer, Integer> m, int k, int d) {
        int nv = m.getOrDefault(k, 0) + d;
        if (nv <= 0) {
            m.remove(k);
        } else {
            m.put(k, nv);
        }
    }

    public long maxRatings(int[][] a) {
        int m = a.length;
        int n = a[0].length;
        long res = 0;
        for (int i = 0; i < m; ++i) {
            Arrays.sort(a[i]);
            res += a[i][0];
        }
        if (n == 1 || m == 1) {
            return res;
        }
        long sumdelta = 0;
        TreeMap<Integer, Integer> mins = new TreeMap<>();
        for (int i = 0; i < m; ++i) {
            int diff = a[i][1] - a[i][0];
            sumdelta += diff;
            update(mins, a[i][0], 1);
        }
        long maxres = 0;
        for (int i = 0; i < m; ++i) {
            update(mins, a[i][0], -1);
            int v1 = mins.firstKey();
            int v3 = a[i][0];
            int realmin = Math.min(v1, v3);
            long realloss = a[i][1] - realmin;

            long cdelta = sumdelta - realloss;
            long cres = res + cdelta;
            maxres = Math.max(maxres, cres);
            update(mins, a[i][0], 1);
        }
        return maxres;
    }
}
