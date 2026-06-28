public class MaxSubarraySumAfterMultiplier {
    public long maxSubarraySum(int[] a, int k) {
        int n = a.length;
        long[] left = new long[n];
        long[] right = new long[n];

        long sum = 0;

        for (int i = 0; i < n; ++i) {
            left[i] = sum;
            sum = Math.max(0, sum + a[i]);
        }

        sum = 0;

        for (int i = n - 1; i >= 0; --i) {
            right[i] = sum;
            sum = Math.max(0, sum + a[i]);
        }

        long mul = solve(a, k, left, right, true);
        long div = solve(a, k, left, right, false);

        return Math.max(mul, div);
    }

    private long solve(
            int[] a,
            int k,
            long[] left,
            long[] right,
            boolean multiply
    ) {
        long pref = 0;
        long best = Long.MIN_VALUE / 4;
        long res = Long.MIN_VALUE;

        for (int r = 0; r < a.length; ++r) {
            best = Math.max(best, left[r] - pref);

            long v;

            if (multiply) {
                v = (long) a[r] * k;
            } else {
                v = a[r] / k;
            }

            pref += v;
            res = Math.max(res, pref + best + right[r]);
        }

        return res;
    }
}
