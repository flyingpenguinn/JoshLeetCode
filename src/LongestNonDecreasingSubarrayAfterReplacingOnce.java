public class LongestNonDecreasingSubarrayAfterReplacingOnce {
    public int longestSubarray(int[] a) {
        int n = a.length;
        if (n == 1) {
            return 1;
        }
        int[] left = new int[n];
        int[] right = new int[n];
        int i = 0;
        while (i < n) {
            int j = i + 1;
            while (j < n && a[j] >= a[j - 1]) {
                ++j;
            }
            // i.. j-1
            int len = j - i;
            int li = 0;
            for (int k = i; k < j; ++k) {
                right[k] = len - li;
                ++li;
            }
            int ri = 0;
            for (int k = j - 1; k >= i; --k) {
                left[k] = len - ri;
                ++ri;
            }

            i = j;
        }
        int res = Math.max(left[n - 2], right[1]) + 1;
        for (i = 1; i + 1 < n; ++i) {
            int rv = right[i + 1];
            int lv = left[i - 1];
            if (a[i - 1] <= a[i + 1]) {
                res = Math.max(res, lv + rv + 1);
            } else {
                res = Math.max(res, lv + 1);
                res = Math.max(res, rv + 1);
            }
        }
        return res;
    }
}
