public class MinSwapsToGroupAllOnesII {
    // just like I, mind all 0
    public int minSwaps(int[] a) {
        int n = a.length;
        int[] a2 = new int[2 * n];
        for (int i = 0; i < 2 * n; ++i) {
            a2[i] = a[i % n];
        }
        int len = 0;
        for (int i = 0; i < n; ++i) {
            len += a[i];
        }
        if (len == 0) {
            return 0;
        }
        int[] sum2 = new int[2 * n];
        sum2[0] = a2[0];
        for (int i = 1; i < 2 * n; ++i) {
            sum2[i] = sum2[i - 1] + a2[i];
        }
        int res = n + 1;
        for (int i = 0; i < n; ++i) {
            int j = i + len - 1;
            int ones = sum2[j] - (i == 0 ? 0 : sum2[i - 1]);
            int zeros = len - ones;
            res = Math.min(res, zeros);
        }
        return res;
    }
}
