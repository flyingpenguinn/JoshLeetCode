public class MinValidPairSum {
    public int maxValidPairSum(int[] a, int k) {
        int n = a.length;
        int[] maxr = new int[n];
        maxr[n - 1] = a[n - 1];
        for (int i = n - 2; i >= 0; --i) {
            maxr[i] = Math.max(a[i], maxr[i + 1]);
        }
        int res = 0;
        for (int i = 0; i + k < n; ++i) {
            int j = i + k;
            int cur = a[i] + maxr[j];
            res = Math.max(res, cur);
        }
        return res;
    }
}
