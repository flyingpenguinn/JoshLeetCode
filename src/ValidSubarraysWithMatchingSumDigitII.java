import base.ArrayUtils;

import java.util.*;

public class ValidSubarraysWithMatchingSumDigitII {
    public int countValidSubarrays(int[] a, int x) {
        int n = a.length;
        long psum = 0;
        List<Long> plist = new ArrayList<>();
        plist.add(0L);
        for (int i = 0; i < n; ++i) {
            psum += a[i];
            plist.add(psum);
        }
        Collections.sort(plist);
        Map<Long, Integer> rm = new HashMap<>();
        int rank = 1;
        int i = 0;
        int pn = plist.size();
        long[][] bit = new long[10][n + 10];
        while (i < pn) {
            int j = i;
            while (j < pn && plist.get(i).equals(plist.get(j))) {
                ++j;
            }
            rm.put(plist.get(i), rank);
            ++rank;
            i = j;
        }
        psum = 0;
        long res = 0;
        u(bit[0], rm.get(0L));
        for (i = 0; i < n; ++i) {
            psum += a[i];
            int ld = (int) (psum % 10);

            for (long p = 1; p <= 1e14; p *= 10) {
                long r1 = x * p;
                long r2 = (x + 1) * p - 1;
                long v1 = psum - r1;
                long v2 = psum - r2;
                if (v1 < 0 && v2 < 0) {
                    break;
                }
                int target = 0;
                if (ld >= x) {
                    target = ld - x;

                } else {
                    target = 10 + ld - x;
                }
                long cur = q(bit[target], getrank(plist, v1)) - q(bit[target], getrank(plist, v2 - 1));
                res += cur;

            }

            int crank = rm.get(psum);
            u(bit[ld], crank);
        }
        return (int) res;
    }

    private long q(long[] bit, int i) {
        long res = 0;
        while (i > 0) {
            res += bit[i];
            i -= i & (-i);
        }
        return res;
    }

    private void u(long[] bit, int i) {

        while (i < bit.length) {
            bit[i] += 1;
            i += i & (-i);
        }
    }

    private int getrank(List<Long> a, long t) {
        // last pos <=
        int l = 0;
        int u = a.size() - 1;
        while (l <= u) {
            int mid = l + (u - l) / 2;
            long mv = a.get(mid);
            if (mv <= t) {
                l = mid + 1;
            } else {
                u = mid - 1;
            }
        }
        return l;
    }


    static void main() {
        //   System.out.println(new ValidSubarraysWithMatchingSumDigitII().countValidSubarrays(ArrayUtils.read1d("[445101066,889962558]"), 6));
        System.out.println(new ValidSubarraysWithMatchingSumDigitII().countValidSubarrays(ArrayUtils.read1d("[1,100,1]"), 1));
        System.out.println(new ValidSubarraysWithMatchingSumDigitII().countValidSubarrays(ArrayUtils.read1d("[1]"), 2));
    }
}
