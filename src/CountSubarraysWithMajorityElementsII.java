import base.ArrayUtils;

import java.util.*;

public class CountSubarraysWithMajorityElementsII {
    private int[] bit;

    private void u(int i) {
        while (i < bit.length) {
            ++bit[i];
            i += i & (-i);
        }
    }

    private int v(int i) {
        int res = 0;
        while (i > 0) {
            res += bit[i];
            i -= i & (-i);
        }
        return res;
    }

    public long countMajoritySubarrays(int[] a, int t) {
        int n = a.length;
        long[] cnt = new long[n];
        int ccnt = 0;
        TreeSet<Long> evs = new TreeSet<>();
        for (int i = 0; i < n; ++i) {
            if (a[i] == t) {
                ++ccnt;
            }
            cnt[i] = ccnt;
            long ev = 2 * cnt[i] - i;
            evs.add(ev);
        }
        int rank = 1;
        Map<Long, Integer> rm = new HashMap<>();
        for (long ek : evs) {
            rm.put(ek, rank++);
        }
        bit = new int[rank];
        long res = 0;
        for (int i = 0; i < n; ++i) {
            long cev = 2 * cnt[i] - i;
            int crank = rm.get(cev);
            long counter = v(crank - 1);
            res += counter;
            if (cnt[i] * 2 > (i + 1)) {
                ++res;
            }
            u(crank);
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new CountSubarraysWithMajorityElementsII().countMajoritySubarrays(ArrayUtils.read1d("[1,2,2,3]"), 2));
    }
}
