import java.util.*;

public class RearrangingFruits {
    // we can always use minv as buffer for 2 swaps, or directly swap for 1 swap
    // for each neg value in map there is a pos value so we dont really care the swapping we just use the min numbers if possible
    private void update(Map<Integer, Integer> m, int k, int d) {
        int nv = m.getOrDefault(k, 0) + d;
        m.put(k, nv);
    }

    public long minCost(int[] a, int[] b) {
        int n = a.length;
        int minv = Integer.MAX_VALUE;
        TreeMap<Integer, Integer> cmap = new TreeMap<>();
        for (int i = 0; i < n; ++i) {
            minv = Math.min(minv, a[i]);
            update(cmap, a[i], 1);
        }
        for (int i = 0; i < n; ++i) {
            minv = Math.min(minv, b[i]);
            update(cmap, b[i], -1);
        }
        long res = 0;

        long rem = 0;
        List<Integer> list = new ArrayList<>();

        for (int ak : cmap.keySet()) {
            int cnt = cmap.get(ak);
            if (cnt % 2 == 1) {
                return -1;
            }
            if (cnt == 0) {
                continue;
            }
            list.add(ak);
            rem += Math.abs(cmap.get(ak) / 2L);
        }
        rem /= 2;
        for (int i = 0; i < list.size() && rem > 0; ++i) {
            int sk = list.get(i);
            long cc = Math.abs(cmap.get(sk) / 2L);
            long sw = Math.min(rem, cc);
            long cur1 = sk * sw;
            long cur2 = 2L * minv * sw;
            res += Math.min(cur1, cur2);
            rem -= sw;
        }
        return res;
    }
}
