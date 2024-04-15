import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KthSmallestWithSingleDenominationCombi {
    private Map<Integer, List<int[]>> subs = new HashMap<>();

    public long findKthSmallest(int[] a, int k) {
        int n = a.length;
        for (int st = 1; st < (1 << n); ++st) {
            int bits = Integer.bitCount(st);
            int[] indexes = new int[bits];
            int ii = 0;
            for (int j = 0; j < n; ++j) {
                if (((st >> j) & 1) == 1) {
                    indexes[ii++] = j;
                }
            }
            subs.computeIfAbsent(bits, p -> new ArrayList<>()).add(indexes);
        }
        long l = 0;
        long u = (long) 2e12;
        while (l <= u) {
            long mid = l + (u - l) / 2;
            if (count(a, mid) >= k) {
                u = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }

    private long count(int[] a, long t) {
        int n = a.length;
        long res = 0;
        for (int ksize : subs.keySet()) {
            int sign = ksize % 2 == 1 ? 1 : -1;
            for (int[] subset : subs.get(ksize)) {
                long lcm = calclcm(a, subset);
                long cur = t / lcm;
                res += cur * sign;
            }
        }
        return res;
    }

    private long calclcm(int[] a, int[] subset) {
        int sn = subset.length;
        long res = a[subset[0]];
        for (int i = 1; i < sn; ++i) {
            res = lcm(res, a[subset[i]]);
        }
        return res;
    }

    private long lcm(long a, long b) {
        return a * b / gcd(a, b);
    }

    private long gcd(long a, long b) {
        if (a < b) {
            return gcd(b, a);
        }
        return b == 0 ? a : gcd(b, a % b);
    }

}
