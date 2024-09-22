public class MinSecondsToMakeMountainHeightZero {
    public long minNumberOfSeconds(int h, int[] a) {
        int n = a.length;
        long l = 0;
        long u = (long) 2e18;
        while (l <= u) {
            long mid = l + (u - l) / 2;
            long reduced = 0;
            for (int i = 0; i < n; ++i) {
                long x = solve(a[i], mid);
                reduced += x;
            }
            if (reduced >= h) {
                u = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }

    private long solve(long x, long t) {
        double p = 2 * 1.0 * t / x;
        long v1 = (long) Math.sqrt(p);
        long v2 = v1 - 1;
        long v3 = v1 + 1;
        if (v3 * (v3 + 1) <= p) {
            return v3;
        } else if (v1 * (v1 + 1) <= p) {
            return v1;
        } else {
            return v2;
        }
    }
}
