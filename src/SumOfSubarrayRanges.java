public class SumOfSubarrayRanges {
    // TODO try the On method
    public long subArrayRanges(int[] a) {
        int n = a.length;
        long res = 0;
        for (int i = 0; i < n; ++i) {
            long minv = a[i];
            long maxv = a[i];
            for (int j = i + 1; j < n; ++j) {
                minv = Math.min(minv, 0L + a[j]);
                maxv = Math.max(maxv, 0L + a[j]);
                res += Math.abs(maxv - minv);
            }
        }
        return res;
    }
}
