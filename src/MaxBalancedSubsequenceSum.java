import java.util.Arrays;
import java.util.TreeMap;

public class MaxBalancedSubsequenceSum {
    // rank map and bit find max
    private long[] bit;
    private int maxrk = -1;
    private long Min = (long) (-1e16);

    public long maxBalancedSubsequenceSum(int[] ia) {
        int n = ia.length;
        long[] a = new long[n];
        for (int i = 0; i < n; ++i) {
            a[i] = ia[i];
        }
        TreeMap<Long, Integer> rank = new TreeMap<>();
        long[] da = new long[n];
        for (int i = 0; i < n; ++i) {
            da[i] = i - a[i];
        }
        Arrays.sort(da);
        int rk = 1;
        for (int i = 1; i <= n; i++) {
            if (i == n || da[i] != da[i - 1]) {
                rank.put(da[i - 1], rk++);
            }
        }
        maxrk = rk;
        bit = new long[maxrk];
        long res = Min;
        for (int i = n - 1; i >= 0; --i) {
            long ck = i - a[i];
            int cr = rank.get(ck);
            long maxv = sum(cr);
            long cur = Math.max(a[i], a[i] + maxv);
            res = Math.max(res, cur);
            add(cr, cur);
        }
        return res;
    }


    private int lowbit(int x) {
        return x & -x;
    }

    private void add(int x, long c) {
        for (int i = x; i < maxrk; i += lowbit(i)) {
            bit[i] = Math.max(bit[i], c);
        }
    }

    private long sum(int x) {
        long res = Min;
        for (int i = x; i > 0; i -= lowbit(i)) {
            res = Math.max(res, bit[i]);
        }
        return res;
    }
}
