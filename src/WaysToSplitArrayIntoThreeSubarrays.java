public class WaysToSplitArrayIntoThreeSubarrays {
        private static final int MOD = 1000000007; // 1e9 + 7

        public int waysToSplit(int[] a) {
            int n = a.length;
            int[] sum = new int[n];
            sum[0] = a[0];
            for (int i = 1; i < n; ++i) {
                sum[i] = sum[i - 1] + a[i];
            }
            long res = 0;
            for (int i = 0; i + 2 < n; ++i) {
                int t = sum[i];
                int rest = sum[n - 1] - t;
                if (rest < 2 * t) {
                    break;
                }
                int p1 = bs1(sum, i, i + 1, n - 1, t);
                int p2 = Math.min(n - 2, bs2(sum, i));
                res += p2 - p1 + 1;
                res %= MOD;
            }
            return (int) res;
        }

        private int bs1(int[] a, int pre, int l, int u, int t) {
            // First index where value >= t
            while (l <= u) {
                int mid = l + (u - l) / 2;
                int sum = a[mid] - a[pre];
                if (sum >= t) {
                    u = mid - 1;
                } else {
                    l = mid + 1;
                }
            }
            return l;
        }

        private int bs2(int[] a, int pre) {
            int n = a.length;
            // Find last index where sum(left) <= sum(right)
            int l = pre + 1;
            int u = n - 1;
            while (l <= u) {
                int mid = l + (u - l) / 2;
                int sum2 = a[n - 1] - a[mid];
                int sum1 = a[mid] - a[pre];
                if (sum1 <= sum2) {
                    l = mid + 1;
                } else {
                    u = mid - 1;
                }
            }
            return u;
    }
}
