public class MinimizeTheMaxOfTwoArrays {
    // greedy trick...
    public int minimizeSet(int d1, int d2, int u1, int u2) {
        int l = 1;
        int u = (int) 2e9;
        long lcm = getlcm(d1, d2);
        //   System.out.println(lcm);
        while (l <= u) {
            int mid = l + (u - l) / 2;
            if (good(mid, d1, d2, u1, u2, lcm)) {
                u = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return (int) l;
    }

    private long getlcm(long v1, long v2) {
        return v1 * v2 / gcd(v1, v2);
    }

    private long gcd(long a, long b) {
        if (a < b) {
            return gcd(b, a);
        }
        return b == 0L ? a : gcd(b, a % b);
    }

    private boolean good(int t, int d1, int d2, int u1, int u2, long lcm) {
        boolean g1 = t - t / d1 >= u1;
        boolean g2 = t - t / d2 >= u2;
        boolean g3 = t - t / lcm >= (u1 + u2);
        return g1 && g2 && g3;
    }
}
