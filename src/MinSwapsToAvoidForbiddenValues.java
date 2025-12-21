import java.util.HashMap;
import java.util.Map;

public class MinSwapsToAvoidForbiddenValues {
    public int minSwaps(int[] a, int[] f) {
        int n = a.length;
        Map<Integer, Integer> mf = new HashMap<>();
        for (int fi : f) {
            mf.put(fi, mf.getOrDefault(fi, 0) + 1);
        }
        Map<Integer, Integer> ma = new HashMap<>();
        for (int ai : a) {
            ma.put(ai, ma.getOrDefault(ai, 0) + 1);
        }
        for (int k : mf.keySet()) {
            int remf = n - mf.get(k);
            int rema = ma.getOrDefault(k, 0);

            if (rema > remf) {
                return -1;
            }
        }
        Map<Integer, Integer> bads = new HashMap<>();
        int bcount = 0;
        for (int i = 0; i < n; ++i) {
            if (a[i] == f[i]) {
                bads.put(a[i], bads.getOrDefault(a[i], 0) + 1);
                ++bcount;
            }
        }
        int maxbads = 0;
        for (int bk : bads.keySet()) {
            int count = bads.get(bk);
            maxbads = Math.max(maxbads, count);
        }
        final int half = (bcount + 1) / 2;
        if (maxbads <= half) {
            return half;
        } else {
            int diff = maxbads - half;
            return half + diff;
        }
    }
}
