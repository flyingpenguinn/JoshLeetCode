public class FindPeakElement {
    public int findPeakElement(int[] a) {
        int n = a.length;
        int l = 0;
        int u = n - 1;
        long infmin = Integer.MIN_VALUE - 1L;
        while (l <= u) {
            int m = l + (u - l) / 2;
            long bf = m == 0 ? infmin : a[m - 1];
            long af = m == n - 1 ? infmin : a[m + 1];
            if (bf < a[m] && af < a[m]) {
                return m;
            } else if (bf < a[m]) {
                l = m + 1;
            } else {
                u = m - 1;
            }
        }
        return -1;
    }
}
