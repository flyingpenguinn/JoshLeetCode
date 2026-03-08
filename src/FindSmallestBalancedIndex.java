public class FindSmallestBalancedIndex {
    private long Max = (long) 1e16;

    public int smallestBalancedIndex(int[] a) {
        int n = a.length;
        long[] right = new long[n];
        right[n - 1] = 1;
        for (int i = n - 2; i >= 0; --i) {
            long lv = 0;
            if (a[i + 1] >= Max / right[i + 1]) {
                lv = Max;
            } else {
                lv = right[i + 1] * a[i + 1];
            }
            right[i] = lv;
        }
        long lsum = 0;
        for (int i = 0; i < n; ++i) {
            if (lsum == right[i]) {
                return i;
            }
            lsum += a[i];
        }
        return -1;
    }
}
