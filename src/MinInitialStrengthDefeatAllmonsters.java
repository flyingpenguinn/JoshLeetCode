public class MinInitialStrengthDefeatAllmonsters {
    private long Max = (long) 1e18;

    public long minInitialStrength(int[] a, int[][] bs) {
        int n = a.length;
        long[] run = new long[n + 1];
        for (int[] b : bs) {
            int l = b[0];
            int r = b[1];
            long v = b[2];
            run[l] += v;
            run[r + 1] -= v;
        }
        long l = 0;
        long u = Max;
        while (l <= u) {
            long mid = l + (u - l) / 2;
            if (doable(a, mid, run)) {
                u = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }

    private boolean doable(int[] a, long start, long[] run) {
        int n = a.length;
        long st = start;
        long bonus = 0L;
        for (int i = 0; i < n; ++i) {
            bonus += run[i];
            long cur = st + bonus;
            if (cur < a[i]) {
                return false;
            }
            st -= a[i];
            if (st < 0) {
                st = 0;
            }
        }
        return true;
    }
}
