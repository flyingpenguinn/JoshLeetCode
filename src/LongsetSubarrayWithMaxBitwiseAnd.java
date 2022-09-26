public class LongsetSubarrayWithMaxBitwiseAnd {
    // must be the biggest number consecutively...
    public int longestSubarray(int[] a) {
        int n = a.length;
        int max = -1;
        for (int i = 0; i < n; ++i) {
            max = Math.max(max, a[i]);
        }
        int i = 0;
        int res = 0;
        while (i < n) {
            if (a[i] != max) {
                ++i;
                continue;
            }
            int j = i;
            while (j < n && a[j] == a[i]) {
                ++j;
            }
            res = Math.max(res, j - i);
            i = j;
        }
        return res;
    }
}
