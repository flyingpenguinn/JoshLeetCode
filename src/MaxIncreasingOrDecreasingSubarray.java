public class MaxIncreasingOrDecreasingSubarray {
    public int longestMonotonicSubarray(int[] a) {
        return Math.max(check(a, true), check(a, false));
    }

    private int check(int[] a, boolean inc) {
        int n = a.length;
        int i = 0;
        int res = 0;
        while (i < n) {
            int j = i + 1;
            while (j < n && (inc ? a[j] > a[j - 1] : a[j] < a[j - 1])) {
                ++j;
            }
            int cur = j - i;
            res = Math.max(cur, res);
            i = j;
        }

        return res;
    }
}
