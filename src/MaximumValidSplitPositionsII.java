public class MaximumValidSplitPositionsII {
    // TODO sparse table + binary search
    private int n, lg;
    private int[][] st;
    private int[] log;

    public int maxValidSplits(int[] nums) {
        n = nums.length;

        log = new int[n + 1];
        for (int i = 2; i <= n; ++i) {
            log[i] = log[i >> 1] + 1;
        }

        lg = log[n] + 1;
        st = new int[lg][n];

        for (int i = 0; i < n; ++i) {
            st[0][i] = nums[i];
        }

        for (int j = 1; j < lg; ++j) {
            for (int i = 0; i + (1 << j) <= n; ++i) {
                st[j][i] = gcd(
                        st[j - 1][i],
                        st[j - 1][i + (1 << (j - 1))]
                );
            }
        }

        int res = score(-1);

        for (int k = 0; k < n; ++k) {
            res = Math.max(res, score(k));
        }

        return res;
    }

    private int score(int k) {
        int m = k == -1 ? n : n - 1;
        if (m <= 1) return 0;

        int g;
        if (k == -1) {
            g = range(0, n - 1);
        } else {
            g = gcd(range(0, k - 1), range(k + 1, n - 1));
        }

        int lo = 0, hi = m - 1;

        while (lo < hi) {
            int mid = (lo + hi) >> 1;
            if (prefix(k, mid) == g) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }

        int l = lo;

        lo = 0;
        hi = m - 1;

        while (lo < hi) {
            int mid = (lo + hi + 1) >> 1;
            if (suffix(k, mid) == g) {
                lo = mid;
            } else {
                hi = mid - 1;
            }
        }

        int r = lo;

        return Math.max(0, r - l);
    }

    private int prefix(int k, int i) {
        if (k == -1) {
            return range(0, i);
        }

        if (i < k) {
            return range(0, i);
        }

        return gcd(
                range(0, k - 1),
                range(k + 1, i + 1)
        );
    }

    private int suffix(int k, int i) {
        if (k == -1) {
            return range(i, n - 1);
        }

        if (i < k) {
            return gcd(
                    range(i, k - 1),
                    range(k + 1, n - 1)
            );
        }

        return range(i + 1, n - 1);
    }

    private int range(int l, int r) {
        if (l > r) return 0;

        int j = log[r - l + 1];

        return gcd(
                st[j][l],
                st[j][r - (1 << j) + 1]
        );
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            int t = a % b;
            a = b;
            b = t;
        }
        return a;
    }
}
