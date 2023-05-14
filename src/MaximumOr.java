public class MaximumOr {
    public long maximumOr(int[] a, int k) {
        int n = a.length;
        long[] pre = new long[n];
        long[] suff = new long[n + 1];
        long pow = 1;

        for (int i = 0; i < k; i++) {
            pow *= 2;
        }

        pre[0] = a[0];
        for (int i = 1; i < n; i++) {
            pre[i] = pre[i - 1] | a[i];
        }
        suff[n] = 0;
        for (int i = n - 1; i >= 0; i--) {
            suff[i] = suff[i + 1] | a[i];
        }

        long res = 0;
        for (int i = 0; i < n; i++) {
            long cur = (i == 0 ? 0 : pre[i - 1]) | (a[i] * pow) | suff[i + 1];
            res = Math.max(res, cur);
        }
        return res;
    }
}
