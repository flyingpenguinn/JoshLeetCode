import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeMap;

public class CountSubarraysWithDistantSums {
    static class FenWick {
        private int[] bit;

        public FenWick(int n) {
            this.bit = new int[n];
        }

        private int q(int i) {
            int res = 0;
            while (i > 0) {
                res += bit[i];
                i -= i & (-i);
            }
            return res;
        }

        private void u(int i, int d) {

            while (i < bit.length) {
                bit[i] += d;
                i += i & (-i);
            }
        }
    }

    public long distantSubarrays(int[] a, int goal, int k) {
        int n = a.length;
        long[] psum = new long[n + 1];
        for (int i = 1; i <= n; ++i) {
            psum[i] = psum[i - 1] + a[i - 1];
        }
        TreeMap<Long, Integer> rm = new TreeMap<>();
        List<Long> list = new ArrayList<>();
        for (int i = 0; i <= n; ++i) {
            long cv = psum[i];
            list.add(cv);
        }
        Collections.sort(list);
        int rank = 0;
        for (int i = 0; i < list.size(); ++i) {
            long lv = list.get(i);
            if (i == 0 || list.get(i) > list.get(i - 1)) {
                ++rank;
                rm.put(lv, rank);
            }
        }
        FenWick bit = new FenWick(rank + 1);
        bit.u(rm.get(0L), 1);
        long res = 0;
        for (int i = 1; i <= n; ++i) {
            long v1 = psum[i] - goal - k;
            Long key1 = rm.floorKey(v1);
            long cnt1 = 0;
            if (key1 != null) {
                int rank1 = rm.get(key1);
                cnt1 = bit.q(rank1);
            }

            long v2 = psum[i] - goal + k - 1;
            Long key2 = rm.floorKey(v2);
            long cnt2 = 0;
            if (key2 != null) {
                int rank2 = rm.get(key2);
                cnt2 = bit.q(rank2);

            }
            long rem2 = i - cnt2;
            long cur = cnt1 + rem2;
            cur = Math.min(i, cur);
            res += cur;
            bit.u(rm.get(psum[i]), 1);
        }
        return res;
    }
}
