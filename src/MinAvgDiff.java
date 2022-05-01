public class MinAvgDiff {
    public int minimumAverageDifference(int[] a) {
        int n = a.length;
        long[] right = new long[n + 1];
        right[n - 1] = a[n - 1];
        for (int i = n - 2; i >= 0; --i) {
            right[i] = right[i + 1] + a[i];
        }
        long left = 0;
        long res = (long) 1e12;
        int resi = -1;
        for (int i = 0; i < n; ++i) {
            left += a[i];
            long leftAvg = left / (i + 1);
            long cr = right[i + 1];
            long rightAvg = i == n - 1 ? 0 : cr / (n - i - 1);

            long cur = Math.abs(leftAvg - rightAvg);
            if (cur < res) {
                res = cur;
                resi = i;
            }
        }
        return resi;
    }
}
