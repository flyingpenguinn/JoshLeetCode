public class MaximizePairStrengthUsingGcd {
    private long gcd(long a, long b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    public long maxPairStrength(int[] a) {
        int n = a.length;
        long res = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                long v1 = a[i];
                long v2 = a[j];
                long gv = gcd(v1, v2);
                long cur = v1 * v2 / (gv * gv);
                res = Math.max(res, cur);
            }
        }
        return res;
    }
}
