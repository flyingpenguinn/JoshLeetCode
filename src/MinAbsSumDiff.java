import java.util.TreeSet;

public class MinAbsSumDiff {
    private int mod = 1000000007;

    public int minAbsoluteSumDiff(int[] a, int[] b) {
        int n = a.length;
        long res = 0;
        TreeSet<Integer> ts = new TreeSet<>();
        for (int i = 0; i < n; i++) {
            res += Math.abs(a[i] - b[i]);
            ts.add(a[i]);
        }
        if (res == 0) {
            return 0;
        }
        long min = res;
        for (int i = 0; i < n; i++) {
            long diff = Math.abs(a[i] - b[i]);
            long close = find(ts, b[i]);
            long newdiff = Math.abs(close - b[i]);
            if (newdiff < diff) {
                long cur = res - diff + newdiff;
                min = Math.min(min, cur);
            }
        }
        return (int) (min % mod);
    }

    private long find(TreeSet<Integer> ts, int v) {
        Integer up = ts.floor(v);
        Integer low = ts.ceiling(v);
        if (up == null) {
            return low;
        } else if (low == null) {
            return up;
        } else {
            if (Math.abs(up - v) < Math.abs(low - v)) {
                return up;
            } else {
                return low;
            }
        }
    }
}
