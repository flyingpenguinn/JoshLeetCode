public class MaximizeAscendingSubarraySum {
    // find ascending streak and start from end of it
    public int maxAscendingSum(int[] a) {
        int n = a.length;
        int res = 0;
        int i = 0;
        while (i < n) {
            int cur = a[i];
            int j = i + 1;
            while (j < n && a[j] > a[j - 1]) {
                cur += a[j];
                j++;
            }
            res = Math.max(res, cur);
            i = j;
        }
        return res;
    }
}
