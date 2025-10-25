public class MinOperationsToTransformArray {
    public long minOperations(int[] a, int[] b) {
        int n = a.length;
        int bn = b.length;
        long last = b[bn - 1];
        long raw = 0;
        long res = Long.MAX_VALUE;
        for (int i = 0; i < n; ++i) {
            long av = a[i];
            long diff = Math.abs(av - b[i]);
            raw += diff;
        }
        for (int i = 0; i < n; ++i) {
            long av = a[i];
            if (av > last && av > b[i]) {
                long cur = raw - (av - b[i]) + av - Math.min(b[i], last) + 1;
                res = Math.min(res, cur);
            } else if (av < last && av < b[i]) {
                long cur = raw - (b[i] - av) + Math.max(b[i], last) - av + 1;
                res = Math.min(res, cur);
            } else {
                long cur = raw + Math.abs(av - last) + 1;
                res = Math.min(res, cur);
            }
        }
        return res;
    }
}
