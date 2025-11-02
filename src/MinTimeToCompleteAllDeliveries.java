public class MinTimeToCompleteAllDeliveries {
    // set problem!
    public long minimumTime(int[] d, int[] r) {
        long l = 0;
        long h = 1;
        while (!ok(h, d[0], d[1], r[0], r[1])) {
            h = h * 2;
        }
        while (l < h) {
            long m = l + (h - l) / 2;
            if (ok(m, d[0], d[1], r[0], r[1])) {
                h = m;
            } else {
                l = m + 1;
            }
        }
        return l;
    }

    private boolean ok(long t, int d1, int d2, int r1, int r2) {
        long a1 = t - t / r1;
        long a2 = t - t / r2;
        long cap = t - t / lcm(r1, r2);
        if (a1 < d1) {
            return false;
        }
        if (a2 < d2) {
            return false;
        }
        return (long) d1 + (long) d2 <= cap;
    }

    private long lcm(int a, int b) {
        return (long) a / gcd(a, b) * (long) b;
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            int c = a % b;
            a = b;
            b = c;
        }
        return a;
    }
}
